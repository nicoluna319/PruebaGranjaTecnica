package onCredit.panacaGranja.api.controllers;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import onCredit.panacaGranja.api.dto.request.AnimalReq;
import onCredit.panacaGranja.api.dto.response.AnimalResp;
import onCredit.panacaGranja.api.dto.response.CAnimalResumenResp;
import onCredit.panacaGranja.infrastructure.services.AnimalService;
import onCredit.panacaGranja.utils.enums.SortType;

@RestController
@RequestMapping("/animales")
@AllArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResp> create(@RequestBody AnimalReq request) {
        AnimalResp createdAnimal = animalService.create(request);
        return new ResponseEntity<>(createdAnimal, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResp> get(@PathVariable Long id) {
        AnimalResp animal = animalService.get(id);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalResp>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "NONE") SortType sortType) {

        Page<AnimalResp> animals = animalService.getAll(page, size, sortType);
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping("/resumen")
public ResponseEntity<List<CAnimalResumenResp>> getAnimalResumen() {
    List<CAnimalResumenResp> resumen = animalService.getAnimalsGroupedByCorral();
    return ResponseEntity.ok(resumen);
}


@GetMapping("/corral/{corralId}/promedio-edad")
public ResponseEntity<Double> getAverageAgeByCorral(@PathVariable Long corralId) {
    Double averageAge = animalService.getAverageAgeByCorralId(corralId);
    return ResponseEntity.ok(averageAge);
}


    @PutMapping("/{id}")
    public ResponseEntity<AnimalResp> update(
        @RequestBody AnimalReq request,
        @PathVariable Long id) {

        AnimalResp updatedAnimal = animalService.update(request, id);
        return new ResponseEntity<>(updatedAnimal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/corral/{corralId}/animales")
public ResponseEntity<Page<AnimalResp>> getAnimalsByCorral(
        @PathVariable Long corralId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") String sortType) {

    Page<AnimalResp> animals = animalService.getAnimalsByCorral(corralId, page, size, sortType);
    return ResponseEntity.ok(animals);
}

    
}
