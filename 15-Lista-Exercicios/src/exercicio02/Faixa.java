package exercicio02;

public class Faixa {
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private int ano;
    private int reproducoes;

    public Faixa(String titulo, String artista, int duracaoSegundos, int ano, int reproducoes) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.ano = ano;
        this.reproducoes = reproducoes;
    }

    @Override
    public String toString() {
        return artista + "  |  " + titulo + "  |  " + ano + "  |  " + duracaoSegundos + "s  |  " + reproducoes + " plays";
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public int getAno() {
        return ano;
    }

    public int getReproducoes() {
        return reproducoes;
    }
}
