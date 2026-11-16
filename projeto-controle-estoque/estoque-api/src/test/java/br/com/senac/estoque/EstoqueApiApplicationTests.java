package br.com.senac.estoque;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Testa a API de ponta a ponta (login, produtos, movimentacoes) usando banco H2 em memoria.
// Rodar com: mvnw test
@SpringBootTest
@AutoConfigureMockMvc
class EstoqueApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @BeforeEach
    void cadastrarELogar() throws Exception {
        String email = "operador-" + UUID.randomUUID() + "@senac.br";

        mockMvc.perform(post("/usuarios/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Operador\",\"email\":\"" + email + "\",\"senha\":\"123456\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.senha").doesNotExist());

        String resposta = mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"" + email + "\",\"senha\":\"123456\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        token = "Bearer " + JsonPath.read(resposta, "$.token");
    }

    @Test
    void semTokenRetorna401() throws Exception {
        mockMvc.perform(get("/produtos")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/produtos").header("Authorization", "Bearer token-falso"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void corsLiberaOFrontendReact() throws Exception {
        mockMvc.perform(options("/produtos")
                        .header("Origin", "http://localhost:5173")
                        .header("Access-Control-Request-Method", "GET")
                        .header("Access-Control-Request-Headers", "Authorization"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:5173"));

        // Outra origem qualquer e bloqueada
        mockMvc.perform(options("/produtos")
                        .header("Origin", "http://site-estranho.com")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isForbidden());
    }

    @Test
    void loginComSenhaErradaRetorna400() throws Exception {
        mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"ninguem@senac.br\",\"senha\":\"errada\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("E-mail ou senha inválidos!"));
    }

    @Test
    void cadastroIgnoraEstoqueEEdicaoNaoAlteraEstoque() throws Exception {
        String codigo = UUID.randomUUID().toString();

        // Mesmo mandando "estoque": 999, o produto nasce com estoque 0
        String resposta = mockMvc.perform(post("/produtos").header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoBarras\":\"" + codigo + "\",\"nome\":\"Caneta\",\"descricao\":\"Azul\",\"estoque\":999}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estoque").value(0))
                .andExpect(jsonPath("$.ativo").value(true))
                .andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.read(resposta, "$.id");

        mockMvc.perform(put("/produtos/" + id).header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoBarras\":\"" + codigo + "\",\"nome\":\"Caneta Bic\",\"descricao\":\"Azul\",\"estoque\":500}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Caneta Bic"))
                .andExpect(jsonPath("$.estoque").value(0));

        // Codigo de barras repetido
        mockMvc.perform(post("/produtos").header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoBarras\":\"" + codigo + "\",\"nome\":\"Outra\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void produtoNaoPodeSerDeletadoSoDesativado() throws Exception {
        Integer id = criarProduto();

        mockMvc.perform(delete("/produtos/" + id).header("Authorization", token))
                .andExpect(status().isMethodNotAllowed());

        mockMvc.perform(patch("/produtos/" + id + "/desativar").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ativo").value(false));

        // Continua na base e aparece na listagem
        mockMvc.perform(get("/produtos/" + id).header("Authorization", token))
                .andExpect(status().isOk());

        // Produto desativado nao pode ser movimentado
        movimentar(id, "ENTRADA", 5).andExpect(status().isBadRequest());

        mockMvc.perform(patch("/produtos/" + id + "/ativar").header("Authorization", token))
                .andExpect(jsonPath("$.ativo").value(true));
    }

    @Test
    void entradaSomaESaidaSubtraiDoEstoque() throws Exception {
        Integer id = criarProduto();

        movimentar(id, "ENTRADA", 10)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipo").value("ENTRADA"))
                .andExpect(jsonPath("$.quantidade").value(10))
                .andExpect(jsonPath("$.usuario").value("Operador"));
        estoqueDeveSer(id, 10);

        movimentar(id, "entrada", 5).andExpect(status().isCreated());
        estoqueDeveSer(id, 15);

        movimentar(id, "SAIDA", 4).andExpect(status().isCreated());
        estoqueDeveSer(id, 11);

        // Saida maior que o estoque e recusada e nao altera nada
        movimentar(id, "SAIDA", 50)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("Estoque insuficiente! Disponível: 11"));
        estoqueDeveSer(id, 11);

        // Quantidade e tipo invalidos
        movimentar(id, "SAIDA", 0).andExpect(status().isBadRequest());
        movimentar(id, "TRANSFERENCIA", 1).andExpect(status().isBadRequest());

        // Lista so as movimentacoes deste produto (3 validas, mais recente primeiro)
        mockMvc.perform(get("/produtos/" + id + "/movimentacoes").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].tipo").value("SAIDA"))
                .andExpect(jsonPath("$[0].produtoId").value(id));
    }

    @Test
    void movimentacaoNaoPodeSerAlteradaNemExcluidaNemListadaTodas() throws Exception {
        Integer id = criarProduto();
        String resposta = movimentar(id, "ENTRADA", 3).andReturn().getResponse().getContentAsString();
        Integer movimentacaoId = JsonPath.read(resposta, "$.id");

        String urlMovimentacao = "/produtos/" + id + "/movimentacoes/" + movimentacaoId;
        mockMvc.perform(put(urlMovimentacao).header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"tipo\":\"SAIDA\",\"quantidade\":1}"))
                .andExpect(status().is4xxClientError());
        mockMvc.perform(delete(urlMovimentacao).header("Authorization", token))
                .andExpect(status().is4xxClientError());

        // Nao existe endpoint que lista as movimentacoes de todos os produtos
        mockMvc.perform(get("/movimentacoes").header("Authorization", token))
                .andExpect(status().isNotFound());

        estoqueDeveSer(id, 3);
    }

    @Test
    void produtoInexistenteRetorna404() throws Exception {
        mockMvc.perform(get("/produtos/999999").header("Authorization", token))
                .andExpect(status().isNotFound());
        movimentar(999999, "ENTRADA", 1).andExpect(status().isNotFound());
    }

    private Integer criarProduto() throws Exception {
        String resposta = mockMvc.perform(post("/produtos").header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoBarras\":\"" + UUID.randomUUID() + "\",\"nome\":\"Produto teste\"}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return JsonPath.read(resposta, "$.id");
    }

    private org.springframework.test.web.servlet.ResultActions movimentar(Integer produtoId, String tipo, int quantidade)
            throws Exception {
        return mockMvc.perform(post("/produtos/" + produtoId + "/movimentacoes").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tipo\":\"" + tipo + "\",\"quantidade\":" + quantidade + "}"));
    }

    private void estoqueDeveSer(Integer produtoId, int esperado) throws Exception {
        mockMvc.perform(get("/produtos/" + produtoId).header("Authorization", token))
                .andExpect(jsonPath("$.estoque").value(esperado));
    }
}
