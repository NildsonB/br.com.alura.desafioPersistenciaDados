package br.com.alura.desafioPersistenciaDados.repository;

import br.com.alura.desafioPersistenciaDados.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT a FROM Artista a")
    List<Artista> listarArtistasCadastrados();

    @Query("SELECT a FROM Artista a WHERE a.nome ILIKE %:nomeArtista%")
    List<Artista> buscarMusicasPorArtista(String nomeArtista);
}
