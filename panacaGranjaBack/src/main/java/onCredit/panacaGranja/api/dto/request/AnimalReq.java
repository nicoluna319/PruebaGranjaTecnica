package onCredit.panacaGranja.api.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalReq {
    
    private Long id; 
    
//Aqui irian @NotNull pero tengo errores de que esta desactualizado "Deprecated"
    private String nombre;
    private String especie;
    private int edad;
    private boolean peligroso;
    private Long corralId;

    public Long getId() {
        return id;
    }
}
