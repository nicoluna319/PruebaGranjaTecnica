package onCredit.panacaGranja.infrastructure.abstract_services;

import onCredit.panacaGranja.api.dto.request.AnimalReq;
import onCredit.panacaGranja.api.dto.response.AnimalResp;

public interface IAnimalService extends CrudService<AnimalReq,AnimalResp, Long> {

    public final String FIELD_BY_SORT = "especie";
    Double getAverageAgeByCorralId(Long corralId);
    
}
