package onCredit.panacaGranja.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onCredit.panacaGranja.domain.entities.CorralEntity;

@Repository
public interface CorralRepository extends JpaRepository<CorralEntity, Long> {
    
}
