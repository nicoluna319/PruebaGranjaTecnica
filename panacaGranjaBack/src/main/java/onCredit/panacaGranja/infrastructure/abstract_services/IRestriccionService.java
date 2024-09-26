package onCredit.panacaGranja.infrastructure.abstract_services;

import java.util.List;

import onCredit.panacaGranja.api.dto.request.RestriccionReq;
import onCredit.panacaGranja.api.dto.response.RestriccionResp;

public interface IRestriccionService extends CrudService<RestriccionReq,RestriccionResp,Long> {
    
    public String FIELD_BY_SORT = "id";
    // Método para obtener todas las restricciones para un animal específico
    List<RestriccionResp> getRestrictionsByAnimalId(Long animalId);
    List<RestriccionResp> findByAnimalIdOrAnimalRestringidoId(Long animalId, Long animalRestringidoId);

}
