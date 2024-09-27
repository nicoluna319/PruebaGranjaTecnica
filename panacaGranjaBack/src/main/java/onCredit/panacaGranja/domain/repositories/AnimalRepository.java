package onCredit.panacaGranja.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onCredit.panacaGranja.domain.entities.AnimalEntity;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    List<AnimalEntity> findByCorralId(Long corralId);

    long countByCorralId(Long corralId);

    Page<AnimalEntity> findByCorralId(Long corralId, Pageable pageable);

    @Query("SELECT COUNT(a.id) > 0 FROM AnimalEntity a WHERE a.corral.id = :corralId")
    boolean existsByCorralId(Long corralId);

    @Query("SELECT a FROM AnimalEntity a WHERE a.corral.id = :corralId AND a.peligroso = true")
    List<AnimalEntity> findDangerousAnimalsByCorralId(@Param("corralId") Long corralId);
    
    
    boolean existsByCorralIdAndId(Long corralId, Long animalId);

    @Query("SELECT AVG(a.edad) FROM AnimalEntity a WHERE a.corral.id = :corralId")
    Double findAverageAgeByCorralId(@Param("corralId") Long corralId);

    
}
 