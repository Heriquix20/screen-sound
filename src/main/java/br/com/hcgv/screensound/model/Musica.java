package br.com.hcgv.screensound.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")

public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @ManyToOne
    private Artista artista;

    public Musica(String titulo, Artista artista) {
        this.artista = artista;
        this.titulo = titulo;
    }

    public Musica() {
    }

    @Override
    public String toString() {
        return "\nTítulo = " + titulo +
                " - Artista = " + artista.getNome();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
