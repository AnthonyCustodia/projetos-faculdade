/**
 * AULA 03 - Composição: um componente dentro do outro
 * ----------------------------------------------------
 * Não existe herança em React. A forma de reaproveitar marcação é
 * **composição**: colocar um componente dentro do outro na tag.
 *
 * Aqui o `Pai` simplesmente renderiza o `Filho` junto com o próprio
 * texto. Isso cria uma árvore de componentes (a "component tree").
 *
 * Duas formas de composing:
 *
 * 1. Como este `Pai` faz — por nome de tag:
 *        <Filho />
 *
 * 2. Pela prop especial `children`. Serve quando você quer que o PAI
 *    controle o que fica dentro do FILHO:
 *        <Cartao>
 *          <p>conteúdo que o pai montou</p>
 *        </Cartao>
 *
 * Escolha a forma 2 quando o componente for um "invólucro"
 * (card, modal, layout) que não deve se importar com o conteúdo.
 */
import Filho from './Filho'

const Pai = () => {
  return (
    <div>
      <p>Componente Pai</p>
      {/* Um componente pode ser usado como outro "elemento" do JSX. */}
      <Filho />
    </div>
  )
}

export default Pai
