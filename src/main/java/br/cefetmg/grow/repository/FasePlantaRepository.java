package br.cefetmg.grow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.cefetmg.grow.model.EspeciePlanta;
import br.cefetmg.grow.model.FasePlanta;

public interface FasePlantaRepository extends JpaRepository<FasePlanta, Long> {

    // Usado no RegistroService para encontrar a próxima fase
    Optional<FasePlanta> findByEspeciePlantaAndOrdem(EspeciePlanta especiePlanta, Integer ordem);

    // Usados no FasePlantaService
    List<FasePlanta> findByEspeciePlantaId(Long especieId);

    boolean existsByNomeAndEspeciePlantaId(String nome, Long especieId);

    boolean existsByNomeAndEspeciePlantaIdAndIdNot(String nome, Long especieId, Long id);
}