package escola.cadastro;

public class Avaliacao {

    private double nota;

    public Avaliacao(double nota) {
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return String.valueOf(nota);
    }
}
