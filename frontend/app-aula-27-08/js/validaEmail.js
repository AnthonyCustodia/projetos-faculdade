function validarEmail(email) {
    const regex = /\S+@\S+\.\S+/;
    if (!regex.test(email)) {
        console.error('Email invalido');
        return false;
    }
    return true;
};

const emailValido = 'joaosilva@email.com';
const emailInvalido = 'emailsemformato';

validarEmail(emailValido)
validarEmail(emailInvalido);