package onCredit.panacaGranja.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import onCredit.panacaGranja.api.dto.request.CorralReq;
import onCredit.panacaGranja.api.dto.response.CorralResp;
import onCredit.panacaGranja.infrastructure.abstract_services.ICorralService;
import onCredit.panacaGranja.utils.enums.SortType;

@RestController
@RequestMapping("/api/corrales")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CorralController {

    private final ICorralService corralService;

    @GetMapping
    public ResponseEntity<Page<CorralResp>> getAllCorrales(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "NONE") SortType sortType) {
        Page<CorralResp> corrales = corralService.getAll(page, size, sortType);
        return ResponseEntity.ok(corrales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorralResp> getCorralById(@PathVariable Long id) {
        CorralResp corral = corralService.get(id);
        return ResponseEntity.ok(corral);
    }

    @PostMapping
    public ResponseEntity<CorralResp> createCorral(@RequestBody CorralReq corralReq) {
        CorralResp newCorral = corralService.create(corralReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCorral);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorralResp> updateCorral(
            @PathVariable Long id, @RequestBody CorralReq corralReq) {
        CorralResp updatedCorral = corralService.update(corralReq, id);
        return ResponseEntity.ok(updatedCorral);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCorral(@PathVariable Long id) {
        corralService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
