package singleton;

public class Config {

    final String usuario = "ADMIN";
    final String senha = "123";

        private Config() {

    }

    private static Config instancia;

    public static Config getInstance() {
        if (instancia == null) {
            System.out.println("Criando instancia");
            instancia = new Config();
        }

        return instancia;
    }
}
