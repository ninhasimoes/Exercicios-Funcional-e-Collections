package exercicio04;

public class Cadastro {
    private String nome;
    private String email;
    private String senha;
    private int idade;

    public Cadastro(String nome, String email, String senha, int idade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome + "  |  " + email + "  |  " + senha + "  |  " + idade;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdade() {
        return idade;
    }
}
