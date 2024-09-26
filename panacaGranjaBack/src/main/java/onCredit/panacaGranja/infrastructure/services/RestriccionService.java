
package onCredit.panacaGranja.infrastructure.services;
import lombok.AllArgsConstructor;
import onCredit.panacaGranja.infrastructure.abstract_services.IRestriccionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import onCredit.panacaGranja.api.dto.request.RestriccionReq;
import onCredit.panacaGranja.api.dto.response.RestriccionResp;
import onCredit.panacaGranja.domain.entities.AnimalEntity;
import onCredit.panacaGranja.domain.entities.RestriccionEntity;
import onCredit.panacaGranja.domain.repositories.AnimalRepository;
import onCredit.panacaGranja.domain.repositories.RestriccionRepository;

import onCredit.panacaGranja.utils.enums.SortType;
import onCredit.panacaGranja.utils.exception.BadRequestException;

@Service
@AllArgsConstructor
public class RestriccionService implements IRestriccionService {

    private final RestriccionRepository restriccionRepository;
    private final AnimalRepository animalRepository;


    @Override
    public RestriccionResp create(RestriccionReq request) {
        validateAnimalsExist(request.getAnimalId(), request.getAnimalRestringidoId());

        RestriccionEntity restriccionEntity = requestToEntity(request);
        RestriccionEntity savedRestriccion = restriccionRepository.save(restriccionEntity);
        return entityToResponse(savedRestriccion);
    }

    @Override
    public RestriccionResp get(Long id) {
        return entityToResponse(find(id));
    }

    @Override 
    public Page<RestriccionResp> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = switch (sortType) {
            case NONE -> PageRequest.of(page, size);
            case ASC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        };

        return restriccionRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public RestriccionResp update(RestriccionReq request, Long id) {
        RestriccionEntity restriccionEntity = find(id);
        restriccionEntity = requestToEntity(request);
        restriccionEntity.setId(id);
        RestriccionEntity updatedRestriccion = restriccionRepository.save(restriccionEntity);
        return entityToResponse(updatedRestriccion);
    }

    @Override
    public void delete(Long id) {
        restriccionRepository.delete(find(id));
    }

    private RestriccionEntity find(Long id) {
        return restriccionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Restricción no encontrada"));
    }

    private void validateAnimalsExist(Long animalId, Long animalRestringidoId) {
        if (!animalRepository.existsById(animalId)) {
            throw new BadRequestException("Animal no encontrado");
        }
        if (!animalRepository.existsById(animalRestringidoId)) {
            throw new BadRequestException("Animal restringido no encontrado");
        }
    }

    @Override
    public List<RestriccionResp> getRestrictionsByAnimalId(Long animalId) {
        List<RestriccionEntity> restricciones = restriccionRepository.findByAnimalId(animalId);
        

        return restricciones.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestriccionResp> findByAnimalIdOrAnimalRestringidoId(Long animalId, Long animalRestringidoId) {
        List<RestriccionEntity> restricciones = restriccionRepository.findByAnimalIdOrAnimalRestringidoId(animalId, animalRestringidoId);


        return restricciones.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    private RestriccionResp entityToResponse(RestriccionEntity restriccionEntity) {
        return RestriccionResp.builder()
                .id(restriccionEntity.getId())
                .animalId(restriccionEntity.getAnimal().getId())
                .animalRestringidoId(restriccionEntity.getAnimalRestringido().getId())
                .build();
    }
    
    private RestriccionEntity requestToEntity(RestriccionReq request) {
        AnimalEntity animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new BadRequestException("Animal no encontrado"));

        AnimalEntity animalRestringido = animalRepository.findById(request.getAnimalRestringidoId())
                .orElseThrow(() -> new BadRequestException("Animal restringido no encontrado"));

        return RestriccionEntity.builder()
                .animal(animal)
                .animalRestringido(animalRestringido)
                .build();
    }
}
