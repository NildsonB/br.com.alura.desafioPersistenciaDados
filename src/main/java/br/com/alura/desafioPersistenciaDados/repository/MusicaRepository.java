package br.com.alura.desafioPersistenciaDados.repository;

import br.com.alura.desafioPersistenciaDados.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    @Query("SELECT m FROM Musica m")
    List<Musica> listarMusicasCadastradas();

    @Query("SELECT m FROM Musica m WHERE m.nome ILIKE %:nomeMusica%")
    List<Musica> buscarMusicaPorNome(String nomeMusica);

}
