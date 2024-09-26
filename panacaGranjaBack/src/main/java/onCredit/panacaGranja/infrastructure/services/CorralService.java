package onCredit.panacaGranja.infrastructure.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onCredit.panacaGranja.api.dto.request.CorralReq;
import onCredit.panacaGranja.api.dto.response.CorralResp;
import onCredit.panacaGranja.domain.entities.CorralEntity;
import onCredit.panacaGranja.domain.repositories.CorralRepository;
import onCredit.panacaGranja.infrastructure.abstract_services.ICorralService;
import onCredit.panacaGranja.utils.enums.SortType;
import onCredit.panacaGranja.utils.exception.BadRequestException;

@Service
@AllArgsConstructor
public class CorralService implements ICorralService {
    
    @Autowired
    private final CorralRepository corralRepository;
    
    @Override
    public CorralResp create(CorralReq request) {

        CorralEntity entity = this.requestToEntity(request);
        return this.entityToResponse(this.corralRepository.save(entity));
    }

    @Override
    public CorralResp get(Long id) {

        return this.entityToResponse(this.find(id));
    }

    @Override
    public CorralResp update(CorralReq request, Long id) {

        CorralEntity corral = this.find(id);
        corral = this.requestToEntity(request);
        corral.setId(id);

        return this.entityToResponse(this.corralRepository.save(corral));
    }

    @Override
    public void delete(Long id) {

        this.corralRepository.delete(this.find(id));
    }

    @Override
    public Page<CorralResp> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = switch (sortType) {
            case NONE -> PageRequest.of(page, size);
            case ASC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        };

        return this.corralRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    private CorralEntity find(Long id) {
        return this.corralRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Corral no encontrado"));
    }


    private CorralResp entityToResponse(CorralEntity entity) {
        return CorralResp.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .capacidad(entity.getCapacidad())
                .build();
    }

    private CorralEntity requestToEntity(CorralReq request) {
        return CorralEntity.builder()
                .nombre(request.getNombre())
                .capacidad(request.getCapacidad())
                .build();
    }

    
}
