package exercicio05;

import java.util.Objects;

// igualdade pelo RM
// Comparable pra o TreeSet conseguir ordenar (ordem natural = por nome, e o RM desempata)
public class Aluno implements Comparable<Aluno> {
    private String rm;
    private String nome;
    private String curso;

    public Aluno(String rm, String nome, String curso) {
        this.rm = rm;
        this.nome = nome;
        this.curso = curso;
    }

    // item a
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(rm, aluno.rm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rm);
    }

    @Override
    public int compareTo(Aluno outro) {
        int resultado = nome.compareTo(outro.nome);
        return resultado != 0 ? resultado : rm.compareTo(outro.rm);
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getRm() {
        return rm;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }
}
