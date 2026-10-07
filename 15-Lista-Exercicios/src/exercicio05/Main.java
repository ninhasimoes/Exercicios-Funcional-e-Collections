package exercicio05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    static final Locale BR = Locale.of("pt", "BR");   // pra exibir 7,15 (vírgula) em vez de 7.15

    static void main() {
        // item b --> alunos de dois cursos
        Aluno ana = new Aluno("1001", "Ana Lima", "SI");
        Aluno bruno = new Aluno("1002", "Bruno Costa", "SI");
        Aluno carla = new Aluno("1003", "Carla Souza", "SI");
        Aluno diego = new Aluno("1004", "Diego Ramos", "SI");
        Aluno elisa = new Aluno("2001", "Elisa Prado", "ADS");
        Aluno fabio = new Aluno("2002", "Fabio Nunes", "ADS");
        Aluno gabi = new Aluno("2003", "Gabi Matos", "ADS");
        Aluno hugo = new Aluno("2004", "Hugo Tavares", "ADS");

        Disciplina poo2 = new Disciplina("POO2", "POO2", 3);
        Disciplina edd = new Disciplina("EDD", "Estruturas de Dados", 2);
        Disciplina bd = new Disciplina("BD", "Banco de Dados", 2);
        Disciplina redes = new Disciplina("RED", "Redes", 4);
        Disciplina es = new Disciplina("ES", "Engenharia de Software", 5);   // só 2 matriculados: NÃO abre

        List<Matricula> matriculas = new ArrayList<>();
        // POO2 (4 alunos)
        matriculas.add(new Matricula(ana, poo2, 9.5));
        matriculas.add(new Matricula(bruno, poo2, 7.5));
        matriculas.add(new Matricula(carla, poo2, 7.8));
        matriculas.add(new Matricula(diego, poo2, 6.5));
        // Estruturas de Dados (5 alunos)
        matriculas.add(new Matricula(ana, edd, 9.0));
        matriculas.add(new Matricula(diego, edd, 4.8));    // Diego reprovado
        matriculas.add(new Matricula(elisa, edd, 7.0));
        matriculas.add(new Matricula(fabio, edd, 5.5));    // reprovado
        matriculas.add(new Matricula(gabi, edd, 5.0));     // reprovada
        // Banco de Dados (5 alunos)
        matriculas.add(new Matricula(carla, bd, 6.2));
        matriculas.add(new Matricula(diego, bd, 5.2));     // Diego reprovado de novo (2 disciplinas)
        matriculas.add(new Matricula(elisa, bd, 8.5));
        matriculas.add(new Matricula(fabio, bd, 6.0));
        matriculas.add(new Matricula(hugo, bd, 5.5));      // reprovado
        // Redes (4 alunos)
        matriculas.add(new Matricula(ana, redes, 8.8));
        matriculas.add(new Matricula(bruno, redes, 6.2));
        matriculas.add(new Matricula(gabi, redes, 8.0));
        matriculas.add(new Matricula(hugo, redes, 8.5));
        // Engenharia de Software (2 alunos) --> disciplina fechada
        matriculas.add(new Matricula(elisa, es, 6.5));
        matriculas.add(new Matricula(fabio, es, 7.5));

        // item c --> Map<Disciplina, Set<Aluno>>
        // TreeSet = Set que mantém a ordem natural (compareTo do Aluno); HashSet não garante ordem
        Map<Disciplina, Set<Aluno>> matriculadosPorDisciplina = matriculas.stream()
                .collect(Collectors.groupingBy(
                        Matricula::getDisciplina,
                        Collectors.mapping(Matricula::getAluno, Collectors.toCollection(TreeSet::new))));

        System.out.println("Disciplinas abertas (3 ou mais matriculados):");
        matriculadosPorDisciplina.entrySet().stream()
                .filter(e -> e.getValue().size() >= 3)
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Disciplina::getNome)))
                .forEach(e -> System.out.println("Disciplina " + e.getKey() + " — "
                        + e.getValue().size() + " matriculados: "
                        + e.getValue().stream().map(Aluno::getNome).collect(Collectors.joining(", "))));

        // item d --> média das notas por curso
        // o groupingBy só cria grupo pra curso que TEM matrícula, então curso sem matrícula nunca aparece
        // (nunca vai ter o averagingDouble "enganoso" de 0.0 de grupo vazio)
        Map<String, Double> mediaPorCurso = matriculas.stream()
                .collect(Collectors.groupingBy(
                        m -> m.getAluno().getCurso(),
                        Collectors.averagingDouble(Matricula::getNota)));

        System.out.println();
        System.out.println("Média por curso: " + mediaPorCurso.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(e -> e.getKey() + " = " + String.format(BR, "%.2f", e.getValue()))
                .collect(Collectors.joining(" | ")));

        // aprovados x reprovados (partitioningBy divide em true/false)
        Map<Boolean, Long> aprovacao = matriculas.stream()
                .collect(Collectors.partitioningBy(Matricula::isAprovado, Collectors.counting()));
        double total = matriculas.size();
        System.out.println(String.format(BR, "Aprovados: %d (%.1f%%) | Reprovados: %d (%.1f%%)",
                aprovacao.get(true), aprovacao.get(true) / total * 100,
                aprovacao.get(false), aprovacao.get(false) / total * 100));

        // item e --> Map<Aluno, List<String>> com as disciplinas em que cada aluno foi REPROVADO
        // filtra as reprovações ANTES de agrupar; mapping transforma a Matricula no nome da disciplina
        // OBS: Aluno é chave do Map só funciona porque equals/hashCode foram sobrescritos (pelo RM).
        // Se removermos os dois, o HashMap usa a identidade do objeto (endereço de memória): o mesmo
        // aluno vindo de outro "new Aluno(...)" viraria outra chave, e o agrupamento ficaria errado/duplicado.
        Map<Aluno, List<String>> reprovacoes = matriculas.stream()
                .filter(m -> !m.isAprovado())
                .collect(Collectors.groupingBy(
                        Matricula::getAluno,
                        Collectors.mapping(m -> m.getDisciplina().getNome(), Collectors.toList())));

        System.out.println("\nAlunos com 2+ reprovações:");
        reprovacoes.entrySet().stream()
                .filter(e -> e.getValue().size() >= 2)
                .forEach(e -> System.out.println(e.getKey() + " → " + e.getValue()));

        // melhor aluno (média das notas de cada aluno)
        Map<Aluno, Double> mediaPorAluno = matriculas.stream()
                .collect(Collectors.groupingBy(
                        Matricula::getAluno,
                        Collectors.averagingDouble(Matricula::getNota)));

        mediaPorAluno.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println(String.format(BR, "\nMelhor aluno: %s (%s) — média %.2f",
                        e.getKey(), e.getKey().getCurso(), e.getValue())));
    }
}
