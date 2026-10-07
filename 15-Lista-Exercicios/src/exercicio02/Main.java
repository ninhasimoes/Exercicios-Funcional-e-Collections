package exercicio02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static void main() {
        // item a --> 5 artistas, anos repetidos (2019, 2021) e algumas faixas com poucas reproduções
        List<Faixa> playlist = new ArrayList<>();
        playlist.add(new Faixa("Sol", "Beatriz", 215, 2021, 120));
        playlist.add(new Faixa("Lua", "Beatriz", 198, 2019, 45));
        playlist.add(new Faixa("Mar", "Beatriz", 240, 2021, 2));
        playlist.add(new Faixa("Vento", "Carlos", 180, 2019, 300));
        playlist.add(new Faixa("Chuva", "Carlos", 205, 2023, 87));
        playlist.add(new Faixa("Fogo", "Ana", 260, 2020, 15));
        playlist.add(new Faixa("Terra", "Ana", 190, 2019, 1));
        playlist.add(new Faixa("Neve", "Diego", 175, 2021, 60));
        playlist.add(new Faixa("Areia", "Elisa", 230, 2022, 210));
        playlist.add(new Faixa("Pedra", "Elisa", 200, 2022, 0));

        // item b --> artista crescente e, dentro do mesmo artista, ano DECRESCENTE
        // o reversed() só no segundo critério (via Comparator.reverseOrder()), senão inverteria os artistas também
        System.out.println("Playlist ordenada por artista e ano decrescente:");
        playlist.stream()
                .sorted(Comparator.comparing(Faixa::getArtista)
                        .thenComparing(Faixa::getAno, Comparator.reverseOrder()))
                .forEach(System.out::println);

        // item c --> top 5 mais reproduzidas (sorted do Stream não mexe na lista original)
        System.out.println("\nTop 5 mais reproduzidas:");
        playlist.stream()
                .sorted(Comparator.comparingInt(Faixa::getReproducoes).reversed())
                .limit(5)
                .forEach(f -> System.out.println(f.getTitulo() + " - " + f.getReproducoes()));

        // item d --> removeIf devolve true se removeu algo, então contamos pelo tamanho antes e depois
        int antes = playlist.size();
        playlist.removeIf(f -> f.getReproducoes() < 3);
        int removidas = antes - playlist.size();
        System.out.println("\nFaixas removidas (menos de 3 reproduções): " + removidas);

        // item e --> soma com mapToInt + sum; a formatação hh:mm:ss fica aqui na Main
        int totalSegundos = playlist.stream()
                .mapToInt(Faixa::getDuracaoSegundos)
                .sum();

        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;
        System.out.println("Duração total da playlist: "
                + String.format("%02d:%02d:%02d", horas, minutos, segundos));
    }
}
