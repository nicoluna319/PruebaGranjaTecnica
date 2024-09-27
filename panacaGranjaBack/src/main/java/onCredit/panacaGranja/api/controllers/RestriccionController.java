package onCredit.panacaGranja.api.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import onCredit.panacaGranja.api.dto.request.RestriccionReq;
import onCredit.panacaGranja.api.dto.response.RestriccionResp;
import onCredit.panacaGranja.infrastructure.abstract_services.IRestriccionService;
import onCredit.panacaGranja.utils.enums.SortType;

@RestController
@RequestMapping("/restricciones")
@AllArgsConstructor
public class RestriccionController {

    private final IRestriccionService restriccionService;

    private static final String FIELD_BY_SORT = "id"; 

    @PostMapping
    public ResponseEntity<RestriccionResp> create(@RequestBody RestriccionReq request) {
        RestriccionResp restriccionResp = restriccionService.create(request);
        return new ResponseEntity<>(restriccionResp, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestriccionResp> get(@PathVariable Long id) {
        RestriccionResp restriccionResp = restriccionService.get(id);
        return ResponseEntity.ok(restriccionResp);
    }

    @GetMapping
    public ResponseEntity<Page<RestriccionResp>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "NONE") SortType sortType) {

        PageRequest pagination = switch (sortType) {
            case NONE -> PageRequest.of(page, size);
            case ASC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        };

        int pageNumber = pagination.getPageNumber();
        int pageSize = pagination.getPageSize();
        SortType extractedSortType = sortType;

        Page<RestriccionResp> restriccionPage = restriccionService.getAll(pageNumber, pageSize, extractedSortType);
        return ResponseEntity.ok(restriccionPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestriccionResp> update(
            @PathVariable Long id,
            @RequestBody RestriccionReq request) {
        RestriccionResp restriccionResp = restriccionService.update(request, id);
        return ResponseEntity.ok(restriccionResp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restriccionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<RestriccionResp>> getRestrictionsByAnimalId(
            @PathVariable Long animalId) {
        List<RestriccionResp> restrictions = restriccionService.getRestrictionsByAnimalId(animalId);
        return ResponseEntity.ok(restrictions);
    }

    @GetMapping("/animal-restricciones")
    public ResponseEntity<List<RestriccionResp>> getAnimalRestrictions(
            @RequestParam Long animalId) {
        List<RestriccionResp> restrictions = restriccionService.findByAnimalIdOrAnimalRestringidoId(animalId, animalId);
        return ResponseEntity.ok(restrictions);
    }
}
