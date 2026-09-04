package br.com.alura.desafioPersistenciaDados;

import br.com.alura.desafioPersistenciaDados.principal.Menu;
import br.com.alura.desafioPersistenciaDados.repository.ArtistaRepository;
import br.com.alura.desafioPersistenciaDados.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired
	private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu(artistaRepository, musicaRepository);
		menu.exibirMenu();
	}
}
