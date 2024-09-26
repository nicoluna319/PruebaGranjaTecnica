package onCredit.panacaGranja.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import onCredit.panacaGranja.domain.entities.RestriccionEntity;

public interface RestriccionRepository extends JpaRepository<RestriccionEntity,Long> {

    List<RestriccionEntity> findByAnimalIdOrAnimalRestringidoId(Long animalId, Long animalRestringidoId);
    
    List<RestriccionEntity> findByAnimalId(Long animalId);

}
