import { useState } from 'react'

/**
 * AULA 06 - Formulários (versão 2: exibição após o submit)
 * =========================================================
 * Leia o README desta pasta antes do código: ele compara esta
 * versão com a `v1-exibicao-imediata`.
 *
 * ------------------------------------------------------------------
 * A DIFERENÇA PARA A VERSÃO 1
 *
 * A versão 1 mostra os dados enquanto o usuário digita. Aqui eles só
 * aparecem DEPOIS que o formulário é enviado.
 *
 * Isso é feito com UM SEGUNDO estado, `dadosEnviados`, que só recebe
 * um valor no momento do submit:
 *
 *   const [dadosEnviados, setDadosEnviados] = useState(null)
 *
 *   -> null  : ainda não foi enviado, nada aparece
 *   -> {...} : foi enviado, guardamos uma CÓPIA dos dados
 *
 * Guardar um objeto (e não só os valores soltos) nos dá uma fonte
 * única da verdade: os `dadosEnviados` são um "retrato" do momento
 * do envio. Se o usuário voltar a digitar, a tela de confirmação
 * continua mostrando o que foi enviado de verdade, e não o que está
 * no campo agora. Em uma tela de cadastro, por exemplo, é exatamente
 * o comportamento desejado.
 *
 * ------------------------------------------------------------------
 * POR QUE OS CAMPOS NÃO USAM `name` PARA SER ENVIADOS
 *
 * Aqui a "ação" do formulário é o próprio `handleSubmit`. O
 * `event.preventDefault()` impede o envio nativo, e nós montamos o
 * objeto à mão. Em um projeto real, esse objeto quase sempre vira
 * uma chamada de rede:
 *
 *   fetch('/api/usuarios', {
 *     method: 'POST',
 *     headers: { 'Content-Type': 'application/json' },
 *     body: JSON.stringify(dadosEnviados),
 *   })
 *
 * Só então `setDadosEnviados(...)` confirmaria na tela o que o
 * servidor aceitou.
 *
 * ------------------------------------------------------------------
 * RENDERIZAÇÃO CONDICIONAL DE VOLTA
 *
 * O `{dadosEnviados && (...)}` abaixo é o mesmo `&&` da Aula 05.
 * Funciona bem porque `dadosEnviados` começa como `null`, que é
 * "falsy" — sem o risco do `0` daquela aula.
 */
const UserInfoForm2 = () => {
  // 1) Estado dos campos: controlado, atualiza a cada tecla.
  const [nome, setNome] = useState('')
  const [email, setEmail] = useState('')
  const [telefone, setTelefone] = useState('')
  const [cep, setCep] = useState('')

  // 2) Estado da confirmação: só muda no submit.
  const [dadosEnviados, setDadosEnviados] = useState(null)

  const handleSubmit = (event) => {
    event.preventDefault()

    setDadosEnviados({ nome, email, telefone, cep })
  }

  return (
    <div>
      <h3>UserInfoForm2 — exibição após o envio</h3>

      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="v2-nome">Nome</label>
          <input
            id="v2-nome"
            name="nome"
            type="text"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
          />
        </div>

        <div>
          <label htmlFor="v2-email">E-mail</label>
          <input
            id="v2-email"
            name="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div>
          <label htmlFor="v2-telefone">Telefone</label>
          <input
            id="v2-telefone"
            name="telefone"
            type="tel"
            value={telefone}
            onChange={(e) => setTelefone(e.target.value)}
          />
        </div>

        <div>
          <label htmlFor="v2-cep">CEP</label>
          <input
            id="v2-cep"
            name="cep"
            type="text"
            value={cep}
            onChange={(e) => setCep(e.target.value)}
          />
        </div>

        <button type="submit">Enviar</button>
      </form>

      {/* Enquanto `dadosEnviados` for null, o `&&` devolve null e
          nada é desenhado. Depois do submit, o objeto aparece. */}
      {dadosEnviados && (
        <div>
          <p>Meu nome é: {dadosEnviados.nome}</p>
          <p>Meu e-mail é: {dadosEnviados.email}</p>
          <p>Meu telefone é: {dadosEnviados.telefone}</p>
          <p>Meu CEP é: {dadosEnviados.cep}</p>
        </div>
      )}
    </div>
  )
}

export default UserInfoForm2
