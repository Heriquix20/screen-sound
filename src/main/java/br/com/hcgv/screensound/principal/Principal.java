package br.com.hcgv.screensound.principal;

import br.com.hcgv.screensound.model.Artista;
import br.com.hcgv.screensound.model.Musica;
import br.com.hcgv.screensound.model.TipoArtista;
import br.com.hcgv.screensound.repository.Repositoy;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private Repositoy repositorio;
    private Optional<Artista> artistaBusca;

    public Principal(Repositoy repositorio) {
        this.repositorio = repositorio;
    }

    public void aplication() {
        var opcao = -1;
        String menu = """
                \n1- Cadastrar artistas
                                
                2- Cadastrar músicas
                                
                3- Listar músicas
                                
                4- Buscar músicas por artistas
                                
                5- Listar todos os artistas e suas músicas
                                
                0- Sair
                
                 Escolha uma opção:""";

        while(opcao != 0) {
            System.out.println("\n\n************ SCREEN SOUND MUSICS ************");
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    musicasPorArtistas();
                    break;
                case 5:
                    listarArtistasEMusicas();
                    break;
                case 0:
                    System.out.println("\nScreen Sound turn off...\nScreenSound offline.");
                    break;
                default:
                    System.out.println("\nNúmero digitado inválido!!");
            }
        }
    }

    private void listarArtistasEMusicas() {
        List<Artista> listaGeral = repositorio.findAll();
        listaGeral.stream()
                .forEach(System.out::println);
    }


    private void listarArtistas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> System.out.println("\nArtista: " + a.getNome() + "Tipo: " + a.getTipo()));
    }


    private void musicasPorArtistas() {
        System.out.println("\nArtistas cadastrados:");
        listarArtistas();
        System.out.println("\nQual destes artistas listados você deseja ver as músicas?");
        var nomeArtista = scanner.nextLine();
        artistaBusca = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        if (artistaBusca.isPresent()) {
            var artistaEncontrado = artistaBusca.get();
            artistaEncontrado.getMusicas().stream()
                    .forEach(System.out::println);
        } else {
            System.out.println("\nArtista informado não cadastrado!!");
        }

    }

    private void listarMusicas() {
        System.out.println("\nMúsicas cadastradas:");
        List<Artista> artistas = repositorio.findAll();
        List<Musica> musicas = artistas.stream()
                .flatMap(a -> a.getMusicas().stream()
                        .map(m -> new Musica(m.getTitulo(), m.getArtista())))
                .collect(Collectors.toList());

                musicas.stream()
                        .forEach(System.out::println);
    }

    private void cadastrarMusicas() {
        System.out.println("\nArtista cadastrados:");
        listarArtistas();
        System.out.println("\nDe qual destes artistas é a música?");
        var nomeArtista = scanner.nextLine();
        artistaBusca = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        if (artistaBusca.isPresent()) {
            var artistaEncontrado = artistaBusca.get();
            System.out.println("\nQual o título da música?");
            var tituloMusica = scanner.nextLine();
            Musica musica = new Musica(tituloMusica, artistaEncontrado);
            List<Musica> musicas = new ArrayList<>();
            musicas.add(musica);
            System.out.println(musica);
            artistaEncontrado.setMusicas(musicas);
            repositorio.save(artistaEncontrado);
        } else {
            System.out.println("\nArtista não encontrado");
        }
    }

    private void cadastrarArtistas() {
        scanner.nextLine();
        System.out.println("\nDigite o nome do artista: ");
        var nome = scanner.nextLine();
        System.out.println("\nQual o tipo desse artista(solo, dupla, banda):");
        var tipo = scanner.nextLine();
        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
        Artista artista = new Artista(nome, tipoArtista);
        repositorio.save(artista);
        System.out.println("\n" + artista);
    }


}
