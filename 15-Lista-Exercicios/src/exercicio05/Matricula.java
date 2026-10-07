package exercicio05;

public class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;
    private double nota;

    public Matricula(Aluno aluno, Disciplina disciplina, double nota) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
    }

    // aprovado quando nota >= 6,0
    public boolean isAprovado() {
        return nota >= 6.0;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getNota() {
        return nota;
    }
}
