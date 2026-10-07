package exercicio03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static void main() {
        // item b --> lista com duplicatas disfarçadas (maiúsculas e espaços extras)
        List<Convidado> inscricoes = new ArrayList<>();
        inscricoes.add(new Convidado("Ana", "Ana@Empresa.com", "Empresa"));
        inscricoes.add(new Convidado("Ana Souza", " ana@empresa.com ", "Empresa"));
        inscricoes.add(new Convidado("Bruno", "bruno@tech.com", "Tech"));
        inscricoes.add(new Convidado("Bruno Lima", "BRUNO@TECH.COM", "Tech"));
        inscricoes.add(new Convidado("Carla", "carla@dev.com", "Dev"));
        inscricoes.add(new Convidado("Carla M.", "  Carla@Dev.com", "Dev"));
        inscricoes.add(new Convidado("Diego", "diego@startup.com", "Startup"));
        inscricoes.add(new Convidado("Diego R.", "DIEGO@startup.com  ", "Startup"));
        inscricoes.add(new Convidado("Elisa", "elisa@fiap.com.br", "FIAP"));

        // teste do contrato equals/hashCode: mesmo e-mail com grafias diferentes
        Convidado c1 = new Convidado("Ana", "Ana@Empresa.com", "Empresa");
        Convidado c2 = new Convidado("Ana Souza", " ana@empresa.com ", "Empresa");
        System.out.println("equals: " + c1.equals(c2));
        System.out.println("mesmo hashCode: " + (c1.hashCode() == c2.hashCode()));

        // item c --> a duplicata some por causa do contrato do Set, a lista original continua intacta
        Set<Convidado> unicos = new HashSet<>(inscricoes);
        System.out.println("\nRegistros antes: " + inscricoes.size());
        System.out.println("Registros depois: " + unicos.size());

        // item d --> segundo conjunto com quem confirmou presença
        Set<Convidado> confirmados = new HashSet<>();
        confirmados.add(new Convidado("Ana", "ANA@EMPRESA.COM", "Empresa"));
        confirmados.add(new Convidado("Carla", "carla@dev.com", "Dev"));
        confirmados.add(new Convidado("Elisa", " elisa@fiap.com.br", "FIAP"));
        confirmados.add(new Convidado("Fabio", "fabio@externo.com", "Externo")); // não estava inscrito

        // retainAll --> fica só a INTERSEÇÃO (inscritos que confirmaram)
        // trabalhamos numa cópia, porque retainAll/removeAll alteram o conjunto em que são chamados
        Set<Convidado> inscritosQueConfirmaram = new HashSet<>(unicos);
        inscritosQueConfirmaram.retainAll(confirmados);
        System.out.println("\nInscritos que confirmaram presença:");
        inscritosQueConfirmaram.stream()
                .sorted(Comparator.comparing(Convidado::getNome))
                .forEach(System.out::println);

        // removeAll --> tira de "unicos" quem confirmou (DIFERENÇA): sobra quem ainda não confirmou
        Set<Convidado> naoConfirmaram = new HashSet<>(unicos);
        naoConfirmaram.removeAll(confirmados);
        System.out.println("\nInscritos que ainda não confirmaram:");
        naoConfirmaram.stream()
                .sorted(Comparator.comparing(Convidado::getNome))
                .forEach(System.out::println);
    }
}
