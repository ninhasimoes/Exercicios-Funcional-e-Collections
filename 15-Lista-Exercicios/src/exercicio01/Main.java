package exercicio01;

public class Main {
    static void main() {
        Navegador nav = new Navegador();

        // item d --> simulação, exibindo a situação depois de cada operação
        nav.visitar("A");
        exibir("visitar A", nav, null);

        nav.visitar("B");
        exibir("visitar B", nav, null);

        nav.visitar("C");
        exibir("visitar C", nav, null);

        exibir("voltar", nav, nav.voltar());
        exibir("voltar", nav, nav.voltar());
        exibir("avancar", nav, nav.avancar());

        nav.visitar("D");
        exibir("visitar D", nav, null);

        exibir("voltar", nav, nav.voltar());
        exibir("avancar", nav, nav.avancar());
        exibir("avancar", nav, nav.avancar());   // tem que ser false: o visitar D esvaziou o avançar

        // item e --> conteúdo do histórico "voltar" SEM remover (forEach não consome o Deque)
        System.out.println("\nHistórico voltar (topo -> base):");
        nav.getHistoricoVoltar().forEach(System.out::println);
    }

    // resultado é Boolean (e não boolean) só pra aceitar null nas operações que não devolvem nada
    static void exibir(String operacao, Navegador nav, Boolean resultado) {
        String retorno = resultado == null ? "" : " -> " + resultado;
        System.out.println(operacao + retorno
                + "  |  atual: " + nav.getPaginaAtual()
                + "  |  voltar: " + nav.qtdVoltar()
                + "  |  avançar: " + nav.qtdAvancar());
    }
}
