package br.com.alura.desafioPersistenciaDados.principal;

import br.com.alura.desafioPersistenciaDados.model.Artista;
import br.com.alura.desafioPersistenciaDados.model.EstiloMusical;
import br.com.alura.desafioPersistenciaDados.model.Musica;
import br.com.alura.desafioPersistenciaDados.repository.ArtistaRepository;
import br.com.alura.desafioPersistenciaDados.repository.MusicaRepository;

import java.util.*;

public class Menu {
    private final Scanner leitura = new Scanner(System.in);

    private ArtistaRepository artistaRepository;
    private MusicaRepository musicaRepository;

    public Menu(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    private List<Artista> artistas = new ArrayList<>();

    int option = -1;

    public void exibirMenu() {
        while (option != 0) {
            System.out.print("""
                    === Escolha uma opção abaixo ===
                    1 - Cadastrar artista
                    2 - Cadastar música
                    3 - Listar artistas cadastrados
                    4 - Listar músicas cadastradas
                    5 - Buscar música por nome
                    6 - Listar músicas de artista
                    
                    0 - Sair
                    \n""");
            option = leitura.nextInt();

            switch (option) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarArtistasCadastrados();
                    break;
                case 4:
                    listarMusicasCadastradas();
                    break;
                case 5:
                    buscarMusicaPorNome();
                    break;
                case 6:
                    listarMusicasDeArtista();
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtista() {
        leitura.nextLine();
        System.out.println("Informe o nome do artista:");
        String nomeArtista = leitura.nextLine();
        System.out.println("O artista é SOLO, DUPLA ou BANDA? ");
        String tipoArtista = leitura.nextLine();
        System.out.println("Informe o estilo musical: ");
        System.out.println(Arrays.stream(EstiloMusical.values()).toList());
        String estiloMusical = leitura.nextLine();
        Artista artista = new Artista(nomeArtista, tipoArtista, estiloMusical);
        try {
            artistaRepository.save(artista);
            System.out.println("Artista cadastrada com sucesso!");
        }catch (Exception e) {
            System.out.println("Erro ao cadastrar artista: " + e.getMessage());
        }
    }

    private void listarArtistasCadastrados() {
        artistas = artistaRepository.listarArtistasCadastrados();
        for (Artista artista : artistas) {
            System.out.println("Artista: " + artista.getNome() + " | Tipo: " + artista.getTipoArtista());
        }
    }

    private void cadastrarMusica() {
        leitura.nextLine();
        try {
            System.out.println("Seleceito a qual artista deseja cadastrar a música: ");
            listarArtistasCadastrados();
            String  nomeArtista = leitura.nextLine();
            Artista artistaSelecionado = artistas.stream()
                    .filter(artista -> artista.getNome().toLowerCase().contains(nomeArtista.toLowerCase()))
                    .findFirst()
                    .get();
            System.out.println("Informe a música que deseja cadastrar: ");
            String nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica, artistaSelecionado);
            musicaRepository.save(musica);
            System.out.println("Musica cadastrada com sucesso!");
        } catch (Exception e) {
            throw new NoSuchElementException("Artista não encontrado: " + e.getMessage());
        } catch (Throwable e) {
            throw new NullPointerException("Erro ao cadastrar música: " + e.getMessage());
        }
    }

    private void listarMusicasCadastradas() {
        List<Musica> musicas = musicaRepository.listarMusicasCadastradas();
        for (Musica musica : musicas) {
            System.out.println(musica.getNome());
        }
    }

    private void buscarMusicaPorNome() {
        leitura.nextLine();
        System.out.println("Informe o nome da música que deseja encontrar: ");
        String nomeMusica = leitura.nextLine();
        List<Musica> musicasEncontradas = musicaRepository.buscarMusicaPorNome(nomeMusica);
        for (Musica musica : musicasEncontradas) {
            System.out.println(musica);
        }
    }

    private void listarMusicasDeArtista() {
        leitura.nextLine();
        System.out.println("Informe o nome do artista: ");
        listarArtistasCadastrados();
        String nomeArtista = leitura.nextLine();
        List<Artista> artistaEncontrado = artistaRepository.buscarMusicasPorArtista(nomeArtista);
        artistaEncontrado.stream()
                .findFirst()
                .ifPresent(artista -> System.out.printf("""
                        === %s ===
                        %s
                        """, artista.getNome(), artista.getMusicas()));
    }


}
