package br.com.hcgv.screensound.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")

public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;

    public Artista(String nome, TipoArtista tipoArtista) {
        this.nome = nome;
        this.tipo = tipoArtista;
    }

    @Override
    public String toString() {
        return "\nArtista = " + nome +
                " - Tipo = " + tipo +
                "\nMúsicas = " + musicas;

    }

    public Artista() {
    }
    public TipoArtista getTipo() {
        return tipo;
    }
    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Musica> getMusicas() {
        return musicas;
    }
    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}
