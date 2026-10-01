import Welcome from './components/aula-01-componentes/Welcome'
import BomDia from './components/aula-01-componentes/BomDia'
import Descricao from './components/aula-02-props/Descricao'
import Cachorro from './components/aula-02-props/Cachorro'
import Pai from './components/aula-03-composicao/Pai'
import Contador from './components/aula-04-estado/Contador'
import RenderConditional from './components/aula-05-render-condicional/RenderConditional'
import VerificarIdade from './components/aula-05-render-condicional/VerificarIdade'
import UserInfoForm from './components/aula-06-formularios/v1-exibicao-imediata/UserInfoForm'
import UserInfoForm2 from './components/aula-06-formularios/v2-exibicao-apos-submit/UserInfoForm2'
import './App.css'

/**
 * O `App` é o componente raiz. Ele é o único lugar onde todos os
 * componentes da aula são "ligados" ao resto da aplicação.
 *
 * ------------------------------------------------------------------
 * DICAS DE ORGANIZAÇÃO
 *
 * 1. Nomes de arquivo que NÃO batem com a tag quebram o projeto.
 *    O import abaixo é `./components/aula-04-estado/Contador`, e o
 *    arquivo precisa se chamar exatamente `Contador.jsx`. No Windows
 *    isso passa despercebido porque o sistema de arquivos ignora
 *    maiúsculas e minúsculas, mas ao rodar o build em Linux ou no
 *    deploy o import falha. Mantenha as maiúsculas iguais.
 *
 * 2. Cada componente é importado do SEU diretório. A pasta diz a
 *    ordem das aulas, então ler os imports já conta a história:
 *    componente simples -> props -> composição -> estado ->
 *    condicional -> formulários.
 *
 * 3. Este arquivo é o "índice" das aulas. Cada `<section>` agrupa
 *    uma aula e mostra o arquivo de origem no cabeçalho, para
 *    você saber o que abrir no editor.
 *
 * ------------------------------------------------------------------
 * CORREÇÃO FEITA NESTA AULA
 *
 * A prop se chama `user` no componente, mas estava sendo passada
 * como `nome="Kaique"` aqui. O React não encontrou nada chamado
 * `user`, recebeu `undefined`, e o `&&` não renderizou nada.
 * Props são associadas pelo NOME EXATO, nunca por posição.
 */
const App = () => {
  return (
    <main>
      <h1>Aulas de React</h1>
      <p className="subtitulo">
        Cada bloco abaixo é um componente independente. Edite o arquivo indicado
        e veja o resultado na tela.
      </p>

      <section>
        <header>
          <h2>Aula 01 — Componentes básicos</h2>
          <code>aula-01-componentes/</code>
        </header>
        <Welcome />
        <BomDia />
      </section>

      <section>
        <header>
          <h2>Aula 02 — Props</h2>
          <code>aula-02-props/</code>
        </header>
        <Descricao nome="Anthony" idade={26} />
        {/* Troque os valores aqui e veja o componente mudar. */}
        <Cachorro nome="Duque" raca="SRD" />
      </section>

      <section>
        <header>
          <h2>Aula 03 — Composição</h2>
          <code>aula-03-composicao/</code>
        </header>
        <Pai />
      </section>

      <section>
        <header>
          <h2>Aula 04 — Estado (useState)</h2>
          <code>aula-04-estado/</code>
        </header>
        <Contador />
      </section>

      <section>
        <header>
          <h2>Aula 05 — Renderização condicional</h2>
          <code>aula-05-render-condicional/</code>
        </header>
        {/* NOME CORRETO agora: o componente espera `user`. */}
        <RenderConditional user="Kaique" />
        {/* Tente trocar por user={0} para ver o problema do `&&` com zero. */}
        <VerificarIdade idade={10} />
      </section>

      <section>
        <header>
          <h2>Aula 06 — Formulários</h2>
          <code>aula-06-formularios/</code>
        </header>
        <UserInfoForm />
        <hr />
        <UserInfoForm2 />
      </section>
    </main>
  )
}

export default App
