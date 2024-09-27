package onCredit.panacaGranja.infrastructure.services;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import onCredit.panacaGranja.api.dto.request.AnimalReq;
import onCredit.panacaGranja.api.dto.response.AnimalResp;
import onCredit.panacaGranja.api.dto.response.CAnimalResumenResp;
import onCredit.panacaGranja.api.dto.response.RestriccionResp;
import onCredit.panacaGranja.domain.entities.AnimalEntity;
import onCredit.panacaGranja.domain.entities.CorralEntity;
import onCredit.panacaGranja.domain.repositories.AnimalRepository;
import onCredit.panacaGranja.domain.repositories.CorralRepository;
import onCredit.panacaGranja.infrastructure.abstract_services.IAnimalService;
import onCredit.panacaGranja.utils.enums.SortType;
import onCredit.panacaGranja.utils.exception.BadRequestException;

@Service
@AllArgsConstructor
public class AnimalService implements IAnimalService {
    @Autowired
    private final AnimalRepository animalRepository;
    private final CorralRepository corralRepository;
    private final RestriccionService restriccionService;

    @Override
    public AnimalResp create(AnimalReq request) {
        validateAnimalRestrictions(request.getCorralId(), request.getId());
        validateCorralCapacity(request.getCorralId());

        AnimalEntity animalEntity = requestToEntity(request);
        AnimalEntity savedAnimal = animalRepository.save(animalEntity);
        return entityToResponse(savedAnimal);
    }

    @Override
    public AnimalResp get(Long id) {
        return entityToResponse(find(id));
    }

    @Override
    public Page<AnimalResp> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = switch (sortType) {
            case NONE -> PageRequest.of(page, size);
            case ASC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        };

        return animalRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    public Page<AnimalResp> getAnimalsByCorral(Long corralId, int page, int size, String sortType) {
        PageRequest pageRequest = PageRequest.of(page, size, 
            sortType.equalsIgnoreCase("ASC") ? Sort.by("id").ascending() : Sort.by("id").descending());
    
        return animalRepository.findByCorralId(corralId, pageRequest)
                .map(this::entityToResponse);
    }
    
    

    @Override
    public AnimalResp update(AnimalReq request, Long id) {
        AnimalEntity existingAnimal = find(id);

        // Validar restricciones si el corral cambia
        if (!existingAnimal.getCorral().getId().equals(request.getCorralId())) {
            validateAnimalRestrictions(request.getCorralId(), id);
            validateCorralCapacity(request.getCorralId());
        }

        existingAnimal = requestToEntity(request);
        existingAnimal.setId(id);

        AnimalEntity updatedAnimal = animalRepository.save(existingAnimal);
        return entityToResponse(updatedAnimal);
    }

    @Override
    public void delete(Long id) {
        animalRepository.delete(find(id));
    }

    private AnimalEntity find(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Animal no encontrado"));
    }

    private void validateAnimalRestrictions(Long corralId, Long animalId) {
        List<RestriccionResp> restricciones = restriccionService.findByAnimalIdOrAnimalRestringidoId(animalId, animalId);

        if (restricciones == null) {
            restricciones = Collections.emptyList(); 
        }

        for (RestriccionResp restriccion : restricciones) {
            Long animalRestringidoId = restriccion.getAnimalRestringidoId();
            if (animalRestringidoId != null && animalRepository.existsByCorralIdAndId(corralId, animalRestringidoId)) {
                throw new BadRequestException("No se puede agregar este animal debido a restricciones de convivencia.");
            }
        }
    }

    private void validateCorralCapacity(Long corralId) {
        long count = animalRepository.countByCorralId(corralId);
        CorralEntity corralEntity = corralRepository.findById(corralId)
                .orElseThrow(() -> new BadRequestException("Corral no encontrado"));

        if (count >= corralEntity.getCapacidad()) {
            throw new BadRequestException("El corral ha alcanzado su capacidad máxima.");
        }
    }

    private AnimalResp entityToResponse(AnimalEntity animalEntity) {
        return AnimalResp.builder()
                .id(animalEntity.getId())
                .nombre(animalEntity.getNombre())
                .especie(animalEntity.getEspecie())
                .edad(animalEntity.getEdad())
                .peligroso(animalEntity.isPeligroso())
                .corralId(animalEntity.getCorral().getId())
                .build();
    }

    private AnimalEntity requestToEntity(AnimalReq request) {
        return AnimalEntity.builder()
                .nombre(request.getNombre())
                .especie(request.getEspecie())
                .edad(request.getEdad())
                .peligroso(request.isPeligroso())
                .corral(corralRepository.findById(request.getCorralId())
                        .orElseThrow(() -> new BadRequestException("Corral no encontrado")))
                .build();
    }


    public List<CAnimalResumenResp> getAnimalsGroupedByCorral() {
        List<CorralEntity> corrales = corralRepository.findAll();
        List<CAnimalResumenResp> resumen = new ArrayList<>();

        for (CorralEntity corral : corrales) {
            List<AnimalEntity> animales = animalRepository.findByCorralId(corral.getId());

            List<AnimalResp> animalesPeligrosos = animales.stream()
                    .filter(AnimalEntity::isPeligroso)
                    .map(this::entityToResponse)
                    .collect(Collectors.toList());

            resumen.add(CAnimalResumenResp.builder()
                    .corralId(corral.getId())
                    .nombreCorral(corral.getNombre())
                    .animales(animales.stream().map(this::entityToResponse).collect(Collectors.toList()))
                    .animalesPeligrosos(animalesPeligrosos)
                    .build());
        }

        return resumen;
    }

    @Override
    public Double getAverageAgeByCorralId(Long corralId) {
        Double averageAge = animalRepository.findAverageAgeByCorralId(corralId);
        if (averageAge == null) {
            throw new BadRequestException("No se pudo calcular el promedio de edad. Puede que no haya animales en el corral.");
        }
        return averageAge;
    }

}

