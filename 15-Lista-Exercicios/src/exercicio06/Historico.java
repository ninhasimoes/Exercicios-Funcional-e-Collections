package exercicio06;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Historico {
    private static final int MAX_RECENTES = 5;

    private List<Visualizacao> visualizacoes = new ArrayList<>();
    private Deque<Video> recentes = new ArrayDeque<>();   // início do Deque = mais recente

    // item a
    public void registrar(Video v, int percentual) {
        visualizacoes.add(new Visualizacao(v, percentual));

        // remove(v) usa o equals (título + canal): se o vídeo já estava lá, sai da posição antiga
        recentes.remove(v);
        // e entra no início (mais recente primeiro)
        recentes.addFirst(v);

        // passou de 5? descarta o mais antigo, que fica no fim
        if (recentes.size() > MAX_RECENTES) {
            recentes.removeLast();
        }
    }

    public List<Visualizacao> getVisualizacoes() {
        return visualizacoes;
    }

    public Deque<Video> getRecentes() {
        return recentes;
    }
}
