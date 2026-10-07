package exercicio06;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static final Locale BR = Locale.of("pt", "BR");

    static void main() {
        // item b --> vídeos de 4 canais, com tags que se repetem
        Video v1 = new Video("Java do zero", "CodaDev", 60, Set.of("java", "backend", "iniciante"));
        Video v2 = new Video("Streams na prática", "CodaDev", 40, Set.of("java", "streams"));
        Video v3 = new Video("Carreira em TI", "TechCarreira", 30, Set.of("carreira", "ti"));
        Video v4 = new Video("Spring Boot", "BackendBR", 90, Set.of("java", "backend", "spring"));
        Video v5 = new Video("SQL essencial", "DadosHoje", 50, Set.of("sql", "backend"));
        Video v6 = new Video("Entrevista técnica", "TechCarreira", 25, Set.of("carreira", "entrevista"));
        Video v7 = new Video("Docker em 20 min", "BackendBR", 20, Set.of("devops", "backend"));

        Historico historico = new Historico();
        historico.registrar(v1, 100);
        historico.registrar(v2, 30);
        historico.registrar(v3, 95);
        historico.registrar(v4, 50);
        historico.registrar(v1, 90);    // mesmo vídeo de novo: sobe pro início, sem repetir
        historico.registrar(v5, 20);
        historico.registrar(v6, 100);   // aqui entra o 6º vídeo distinto: o mais antigo sai
        historico.registrar(v7, 10);
        historico.registrar(v2, 60);    // v2 já tinha sido assistido

        // item c --> mais recente para o mais antigo
        System.out.println("Assistidos recentemente:");
        historico.getRecentes().forEach(System.out::println);

        // item d --> minutos efetivamente assistidos por canal = duração * percentual / 100
        // (100.0 pra a divisão não ser inteira e perder as casas decimais)
        Map<String, Double> minutosPorCanal = historico.getVisualizacoes().stream()
                .collect(Collectors.groupingBy(
                        vis -> vis.getVideo().getCanal(),
                        Collectors.summingDouble(vis -> vis.getVideo().getDuracaoMinutos()
                                * vis.getPercentualAssistido() / 100.0)));

        System.out.println("\nMinutos assistidos por canal (decrescente):");
        minutosPorCanal.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + " - " + String.format(BR, "%.1f", e.getValue()) + " min"));

        // item e --> concluído = alguma visualização com pelo menos 90%
        Set<Video> concluidos = historico.getVisualizacoes().stream()
                .filter(vis -> vis.getPercentualAssistido() >= 90)
                .map(Visualizacao::getVideo)
                .collect(Collectors.toSet());

        // iniciados = todos que foram assistidos; tirando os concluídos, sobram os "iniciados e não concluídos"
        Set<Video> iniciadosNaoConcluidos = historico.getVisualizacoes().stream()
                .map(Visualizacao::getVideo)
                .collect(Collectors.toCollection(HashSet::new));
        iniciadosNaoConcluidos.removeAll(concluidos);

        System.out.println("\nVídeos concluídos:");
        concluidos.stream().map(Video::getTitulo).sorted().forEach(System.out::println);
        System.out.println("\nVídeos iniciados e não concluídos:");
        iniciadosNaoConcluidos.stream().map(Video::getTitulo).sorted().forEach(System.out::println);

        // item f --> o método recebe um Stream; cada Stream só pode ser consumido UMA vez,
        // por isso criamos um novo com .stream() a cada chamada
        System.out.println(String.format(BR, "\nMédia de percentual assistido: %.2f%%",
                mediaPercentual(historico.getVisualizacoes().stream())));
    }

    // item f
    static double mediaPercentual(Stream<Visualizacao> visualizacoes) {
        return visualizacoes
                .mapToInt(Visualizacao::getPercentualAssistido)
                .average()
                .orElse(0.0);   // average() devolve OptionalDouble (vazio se não houver elementos)
    }
}
