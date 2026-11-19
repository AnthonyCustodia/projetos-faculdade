package br.com.senac.api.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Utilitário para padronizar as respostas de erro dos controllers.
 * parserMensagem("Pedido não encontrado!") vira o JSON: { "mensagem": "Pedido não encontrado!" }
 */
public class ResponseUtil {
    public static Map<String, String>
                parserMensagem(String mensagem) {
        Map<String, String> saida = new HashMap<>();
        saida.put("mensagem", mensagem);

        return saida;
    }
}
