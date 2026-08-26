package escola;

import escola.cadastro.Avaliacao;
import escola.gerenciador.Gerenciador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Gerenciador gerenciador = new Gerenciador();

        interacao(gerenciador);

        gerenciador.ordenar();
        gerenciador.exibirNotas();
    }

    public static void interacao(Gerenciador gerenciador) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema de Organização de Notas ===");
        System.out.print("Quantas notas você deseja inserir: ");
        int qtdNotas = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < qtdNotas; i++) {
            System.out.println("\n--- Dados do " + (i + 1) + "º Aluno ---");

            System.out.print("Digite a " + (i + 1) + "ª nota: ");
            double nota = scanner.nextDouble();

            Avaliacao avaliacao = new Avaliacao(nota);
            gerenciador.adicionarAvaliacao(avaliacao);
        }

        scanner.close();
    }

}
