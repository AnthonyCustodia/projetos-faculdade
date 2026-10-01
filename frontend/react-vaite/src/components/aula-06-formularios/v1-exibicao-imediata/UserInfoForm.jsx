import { useState } from 'react'

/**
 * AULA 06 - Formulários (versão 1: exibição imediata)
 * ====================================================
 * Leia o README desta pasta antes do código: ele compara esta
 * versão com a `v2-exibicao-apos-submit`.
 *
 * ------------------------------------------------------------------
 * INPUT CONTROLADO
 *
 * Cada campo tem TRÊS peças que precisam andar juntas:
 *
 *   value={nome}                        -> o que aparece no campo
 *   onChange={(e) => setNome(e.target.value)}  -> como o valor entra no estado
 *   const [nome, setNome] = useState('') -> onde o valor fica guardado
 *
 * O que acontece a cada tecla:
 *   1. o usuário digita "P";
 *   2. dispara o `onChange` com o evento;
 *   3. `e.target` é o próprio <input>, e `e.target.value` é "P";
 *   4. `setNome("P")` guarda o valor e o React redesenha;
 *   5. `value={nome}` recebe o "P" de volta, e o campo mostra "P".
 *
 * Chamamos isso de "input controlado": o estado do React manda no
 * campo, e não o contrário.
 *
 * ------------------------------------------------------------------
 * VALOR INICIAL: '' e NÃO null
 *
 * Este `useState('')`, e não `useState(null)`. Um input controlado
 * com valor `null` faz o React avisar no console:
 *
 *   Warning: A component is changing an uncontrolled input to be controlled
 *
 * O motivo é que `null` significa "sem valor definido" para o React,
 * e o campo passa a se comportar como "não controlado". String vazia
 * `''` é um valor válido e mantém o campo controlado do início ao fim.
 *
 * ------------------------------------------------------------------
 * event.preventDefault()
 *
 * Um <form> sem esse comando faz o navegador recarregar a página ao
 * enviar. É o comportamento HTML padrão. Em React nós queremos
 * tratar o envio no JavaScript, então cancelamos esse comportamento.
 *
 * ------------------------------------------------------------------
 * ACESSIBILIDADE
 *
 * Cada <input> tem um <label htmlFor> com o mesmo `id`. Isso não é
 * enfeite: clicar no texto do rótulo foca o campo, e leitores de
 * tela anunciam qual é a informação pedida. O `name` também importa
 * porque é o que identifica o campo quando o formulário é enviado
 * de verdade (veja a versão 2).
 */
const UserInfoForm = () => {
  // Estados dos campos. Todos começam como string vazia.
  const [nome, setNome] = useState('')
  const [email, setEmail] = useState('')
  const [telefone, setTelefone] = useState('')
  const [cep, setCep] = useState('')

  const handleSubmit = (event) => {
    // Impede o recarregamento da página. Veio do event listener do
    // React, por isso `event`, e não o `e` do onChange.
    event.preventDefault()

    // Nesta versão o objetivo é apenas observar: o formulário
    // Controlled exibe os dados JÁ, atualizando a cada tecla, então
    // o console.log serve só para mostrar o que o React tem guardado.
    console.log({ nome, email, telefone, cep })
  }

  return (
    <div>
      <h3>UserInfoForm — exibição imediata</h3>

      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="v1-nome">Nome</label>
          <input
            id="v1-nome"
            name="nome"
            type="text"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
          />
        </div>

        <div>
          <label htmlFor="v1-email">E-mail</label>
          <input
            id="v1-email"
            name="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div>
          <label htmlFor="v1-telefone">Telefone</label>
          <input
            id="v1-telefone"
            name="telefone"
            type="tel"
            value={telefone}
            onChange={(e) => setTelefone(e.target.value)}
          />
        </div>

        <div>
          <label htmlFor="v1-cep">CEP</label>
          <input
            id="v1-cep"
            name="cep"
            type="text"
            value={cep}
            onChange={(e) => setCep(e.target.value)}
          />
        </div>

        <button type="submit">Enviar</button>
      </form>

      {/* Aqui o texto muda a CADA TECLA, porque está lendo o estado
          diretamente. Compare com a versão 2. */}
      <div>
        <p>Meu nome é: {nome}</p>
        <p>Meu e-mail é: {email}</p>
        <p>Meu telefone é: {telefone}</p>
        <p>Meu CEP é: {cep}</p>
      </div>
    </div>
  )
}

export default UserInfoForm
