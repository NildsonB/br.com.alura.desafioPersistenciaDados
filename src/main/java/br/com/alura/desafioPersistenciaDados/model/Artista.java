package br.com.alura.desafioPersistenciaDados.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    Enum<EstiloMusical> estiloMusical;
    Enum<TipoArtista> tipoArtista;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Musica> musicas;

    public Artista() {
    }

    public Artista(String nome,String tipoArtista, String estiloMusical) {
        this.nome = nome;
        this.tipoArtista = TipoArtista.selecaoTipoArtista(tipoArtista);
        this.estiloMusical = EstiloMusical.selecaoEstiloMusical(estiloMusical);
    }


    public String getNome() {
        return nome;
    }

    public Enum getEstiloMusical() {
        return estiloMusical;
    }

    public Enum getTipoArtista() {
        return tipoArtista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public String toString() {
        return "Nome= " + nome +
                "| Estilo Musical= " + estiloMusical +
                "| Tipo Artista= " + tipoArtista +
                "| Músicas= " + musicas;
    }
}
