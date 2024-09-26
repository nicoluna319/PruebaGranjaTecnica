package onCredit.panacaGranja.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestriccionResp {
    
    private Long id;
    private Long animalId;
    private Long animalRestringidoId;
}
