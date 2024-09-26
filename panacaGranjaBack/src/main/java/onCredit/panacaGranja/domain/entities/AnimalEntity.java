package onCredit.panacaGranja.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "animal")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int edad;
    private String nombre;
    private String especie;
    private boolean peligroso;

    @ManyToOne
    @JoinColumn(name = "corral_id")
    private CorralEntity corral;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<RestriccionEntity> restricciones;
    
}
