package exercicio05;

import java.util.Objects;

// igualdade pelo código
public class Disciplina {
    private String codigo;
    private String nome;
    private int semestreSugerido;

    public Disciplina(String codigo, String nome, int semestreSugerido) {
        this.codigo = codigo;
        this.nome = nome;
        this.semestreSugerido = semestreSugerido;
    }

    // item a
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getSemestreSugerido() {
        return semestreSugerido;
    }
}
