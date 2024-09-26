package onCredit.panacaGranja.api.dto.response;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CAnimalResumenResp {
    private Long corralId;
    private String nombreCorral;
    private List<AnimalResp> animales;
    private List<AnimalResp> animalesPeligrosos;
}