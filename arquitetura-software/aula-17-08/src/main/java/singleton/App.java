package singleton;

public class App {

    public static void main(String[] args) {

        Config config1 = Config.getInstance();

        System.out.println("Usuario: " + config1.usuario);

        Config config2 = Config.getInstance();

        System.out.println("Usuario: " + config2.usuario);

    }

}
