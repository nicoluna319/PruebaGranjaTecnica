package onCredit.panacaGranja.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestriccionReq {
    
    private Long animalId;
    private Long animalRestringidoId;
}
