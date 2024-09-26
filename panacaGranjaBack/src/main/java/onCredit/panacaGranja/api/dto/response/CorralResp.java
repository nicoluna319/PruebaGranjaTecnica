package onCredit.panacaGranja.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorralResp {

    private Long id;
    private String nombre;
    private int capacidad;
}
