package escola.gerenciador;

import escola.cadastro.Avaliacao;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {

    List<Avaliacao> notas = new ArrayList<>();

    public void ordenar() {
        if (notas != null && !notas.isEmpty()) {
            quickSort(0, notas.size() - 1);
        }
    }

    public void quickSort(int baixo, int alto) {
        if (baixo < alto) {
            int aux = particionar(baixo, alto);

            quickSort(baixo, aux - 1);
            quickSort(aux + 1, alto);
        }
    }

    public int particionar(int baixo, int alto) {
        double aux = notas.get(alto).getNota();
        int i = (baixo - 1);

        for (int j = baixo; j < alto; j ++) {
            if (notas.get(j).getNota() <= aux) {
                i++;
                Avaliacao temp = notas.get(i);
                notas.set(i, notas.get(j));
                notas.set(j, temp);
            }
        }

        Avaliacao temp = notas.get(i + 1);
        notas.set(i + 1, notas.get(alto));
        notas.set(alto, temp);

        return i + 1;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        notas.add(avaliacao);
    }

    public void exibirNotas() {
        System.out.println();
        System.out.println("==== Notas Ordenadas ====");
        System.out.println("Notas: " + notas);
    }

    /*public void bubbleSort() {
        int n = notas.size();
        boolean troca;

        for (int i = 0; i < n - 1; i++) {
            troca = false;

            for (int j = 0; j < n - i - 1; j++) {
                //Recebe a nota atual
                double notaA = notas.get(j).getNota();
                //Recebe a proxima nota (atual + 1)
                double notaB = notas.get(j + 1).getNota();

                if (notaA > notaB) {
                    Avaliacao aux = notas.get(j);

                    //Altera o valor na posicao J para o valor de posicao J + 1
                    notas.set(j, notas.get(j + 1));
                    //Altera o valor na posicao J + 1 para o valor anterior J recebido da variavel auxiliar
                    notas.set(j + 1, aux);

                    troca = true;
                }
            }

            if (!troca) {
                break;
            }
        }
    }*/
}
