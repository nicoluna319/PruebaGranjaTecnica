package onCredit.panacaGranja.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResp {
    
    private Long id;
    private String nombre;
    private String especie;
    private int edad;
    private boolean peligroso;
    private Long corralId;
    
}
