package br.com.hcgv.screensound;

import br.com.hcgv.screensound.principal.Principal;
import br.com.hcgv.screensound.repository.Repositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {

	@Autowired
	private Repositoy repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ScreensoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.aplication();
	}
}
