package onCredit.panacaGranja.infrastructure.abstract_services;

import onCredit.panacaGranja.api.dto.request.CorralReq;
import onCredit.panacaGranja.api.dto.response.CorralResp;

public interface ICorralService extends CrudService<CorralReq,CorralResp,Long> {
    public String FIELD_BY_SORT = "capacidad";
}
