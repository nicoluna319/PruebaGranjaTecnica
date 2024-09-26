package onCredit.panacaGranja.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import onCredit.panacaGranja.utils.enums.SortType;

public interface CrudService<RQ,RS,ID> {
    
    public RS create(RQ request);

    public RS get(ID id);

    public RS update(RQ request, ID id);

    public void delete(ID id);

    public Page<RS> getAll(int page, int size, SortType sortType);
}