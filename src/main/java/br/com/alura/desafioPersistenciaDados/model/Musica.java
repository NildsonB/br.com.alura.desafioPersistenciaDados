package br.com.alura.desafioPersistenciaDados.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    @ManyToOne
    Artista artista;

    public Musica() {
    }

    public Musica(String nome, Artista artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public Artista getArtista() {
        return artista;
    }

    @Override
    public String toString() {
        return "Musica: " + nome;
    }
}
