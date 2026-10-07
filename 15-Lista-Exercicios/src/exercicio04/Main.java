package exercicio04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// Neste exercício NÃO se usa Stream e NÃO tem if dentro das regras:
// as condições ficam dentro das lambdas (operador ternário no caso da Regra)
public class Main {
    // lista de regras como campo da classe, pra o método erros(Cadastro c) enxergar
    static List<Regra> regras = new ArrayList<>();

    static void main() {
        // item a --> uma regra = um Predicate<Cadastro>
        Predicate<Cadastro> nomeValido = c -> c.getNome().length() >= 3;
        Predicate<Cadastro> emailValido = c -> c.getEmail().contains("@") && c.getEmail().contains(".");
        Predicate<Cadastro> senhaTamanhoOk = c -> c.getSenha().length() >= 8;
        // matches com regex no lugar de chars().anyMatch(...) pra não usar Stream
        Predicate<Cadastro> senhaComDigito = c -> c.getSenha().matches(".*\\d.*");
        Predicate<Cadastro> idadeValida = c -> c.getIdade() >= 18 && c.getIdade() <= 120;

        // item b --> composição com and(), or() e negate()
        Predicate<Cadastro> cadastroValido = nomeValido
                .and(emailValido)
                .and(senhaTamanhoOk)
                .and(senhaComDigito)
                .and(idadeValida);

        // senha fraca = curta OU sem dígito
        Predicate<Cadastro> senhaFraca = senhaTamanhoOk.negate().or(senhaComDigito.negate());

        List<Cadastro> testes = Arrays.asList(
                new Cadastro("Ana Luiza", "ana@fiap.com.br", "senha1234", 19),     // tudo ok
                new Cadastro("Jo", "jo@mail.com", "abc12345", 25),                 // nome curto
                new Cadastro("Carlos", "carlos.mail.com", "carlos2024", 30),       // email sem @
                new Cadastro("Diego", "diego@mail.com", "123", 40),                // senha curta
                new Cadastro("Elisa", "elisa@mail.com", "senhaforte", 22),         // senha sem dígito
                new Cadastro("Fabio", "fabio@mail.com", "fabio12345", 17)          // menor de idade
        );

        System.out.println("Resultado dos predicados:");
        testes.forEach(c -> {
            System.out.println(c);
            System.out.println("   cadastroValido: " + cadastroValido.test(c)
                    + "  |  senhaFraca: " + senhaFraca.test(c));
        });

        // item c --> Regra implementada com lambdas: null = ok, senão a mensagem
        regras.add(c -> c.getNome().length() >= 3 ? null : "Nome deve ter ao menos 3 caracteres");
        regras.add(c -> c.getEmail().contains("@") && c.getEmail().contains(".") ? null : "E-mail deve conter @ e .");
        regras.add(c -> c.getSenha().length() >= 8 ? null : "Senha deve ter ao menos 8 caracteres");
        regras.add(c -> c.getSenha().matches(".*\\d.*") ? null : "Senha deve conter ao menos um dígito");
        regras.add(c -> c.getIdade() >= 18 && c.getIdade() <= 120 ? null : "Idade deve estar entre 18 e 120");

        System.out.println("\nErros por cadastro:");
        testes.forEach(c -> {
            System.out.println(c);
            List<String> erros = erros(c);
            if (erros.isEmpty()) {
                System.out.println("   sem erros");
            } else {
                erros.forEach(e -> System.out.println("   - " + e));
            }
        });
    }

    // percorre TODAS as regras e junta todas as mensagens (não para na primeira)
    // o único if está aqui no método (e não dentro das regras de validação)
    static List<String> erros(Cadastro c) {
        List<String> mensagens = new ArrayList<>();
        for (Regra regra : regras) {
            String mensagem = regra.validar(c);
            if (mensagem != null) {
                mensagens.add(mensagem);
            }
        }
        return mensagens;
    }
}
