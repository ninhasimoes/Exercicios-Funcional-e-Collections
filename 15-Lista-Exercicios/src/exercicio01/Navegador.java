package exercicio01;

import java.util.ArrayDeque;
import java.util.Deque;

public class Navegador {
    private String paginaAtual;

    // Deque declarado pela interface, implementado com ArrayDeque (nada de Stack!)
    // usando como PILHA: push coloca no topo, pop tira do topo, peek só olha o topo
    private Deque<String> historicoVoltar = new ArrayDeque<>();
    private Deque<String> historicoAvancar = new ArrayDeque<>();

    // item a
    public void visitar(String url) {
        // a página atual (se existir) vai pro topo do "voltar"
        if (paginaAtual != null) {
            historicoVoltar.push(paginaAtual);
        }
        paginaAtual = url;
        // visitou uma página nova: o "avançar" é descartado
        // (sem esse clear() o avancar() depois do visitar D devolveria true, que é o erro clássico)
        historicoAvancar.clear();
    }

    // item b
    public boolean voltar() {
        if (historicoVoltar.isEmpty()) {
            return false;
        }
        // a atual vai pro "avançar" e o topo do "voltar" vira a atual
        historicoAvancar.push(paginaAtual);
        paginaAtual = historicoVoltar.pop();
        return true;
    }

    // item c --> espelho do voltar()
    public boolean avancar() {
        if (historicoAvancar.isEmpty()) {
            return false;
        }
        historicoVoltar.push(paginaAtual);
        paginaAtual = historicoAvancar.pop();
        return true;
    }

    public String getPaginaAtual() {
        return paginaAtual;
    }

    public int qtdVoltar() {
        return historicoVoltar.size();
    }

    public int qtdAvancar() {
        return historicoAvancar.size();
    }

    public Deque<String> getHistoricoVoltar() {
        return historicoVoltar;
    }
}
