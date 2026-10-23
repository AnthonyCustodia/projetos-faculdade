import React from 'react'

const Message = (props) => {
  return (
    <div>
      Ola, meu nome é {props.nome} e tenho {props.idade} anos.
    </div>
  )
}

export default Message
