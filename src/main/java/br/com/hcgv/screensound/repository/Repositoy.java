package br.com.hcgv.screensound.repository;

import br.com.hcgv.screensound.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repositoy extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);
}
