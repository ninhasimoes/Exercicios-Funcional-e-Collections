package exercicio03;

import java.util.Objects;

public class Convidado {
    private String nome;
    private String email;
    private String empresa;

    public Convidado(String nome, String email, String empresa) {
        this.nome = nome;
        this.email = email;
        this.empresa = empresa;
    }

    // e-mail "normalizado": sem espaços nas pontas e tudo minúsculo
    // equals e hashCode TÊM que usar a mesma regra, senão o HashSet se perde
    private String emailNormalizado() {
        return email.trim().toLowerCase();
    }

    // item a
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Convidado convidado = (Convidado) o;
        return Objects.equals(emailNormalizado(), convidado.emailNormalizado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailNormalizado());
    }

    @Override
    public String toString() {
        return nome + "  |  [" + email + "]  |  " + empresa;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEmpresa() {
        return empresa;
    }
}
