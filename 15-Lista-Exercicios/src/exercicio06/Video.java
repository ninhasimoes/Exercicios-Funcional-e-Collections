package exercicio06;

import java.util.Objects;
import java.util.Set;

// igualdade pelo título + canal
public class Video {
    private String titulo;
    private String canal;
    private int duracaoMinutos;
    private Set<String> tags;

    public Video(String titulo, String canal, int duracaoMinutos, Set<String> tags) {
        this.titulo = titulo;
        this.canal = canal;
        this.duracaoMinutos = duracaoMinutos;
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(titulo, video.titulo) && Objects.equals(canal, video.canal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, canal);
    }

    @Override
    public String toString() {
        return titulo + "  |  " + canal + "  |  " + duracaoMinutos + " min  |  " + tags;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCanal() {
        return canal;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public Set<String> getTags() {
        return tags;
    }
}
