package io.kan.opteammer.rest;

import io.kan.opteammer.dto.PreOperativeAssessmentDTO;
import io.kan.opteammer.services.PreOperativeAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preoperative-assessment")
public class PreOperativeAssessmentController {

    PreOperativeAssessmentService preOperativeAssessmentService;

    @Autowired
    public PreOperativeAssessmentController(PreOperativeAssessmentService preOperativeAssessmentService) {
        this.preOperativeAssessmentService = preOperativeAssessmentService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<PreOperativeAssessmentDTO> getPreOperativeAssessmentById(@PathVariable( name = "name") String name) {
        Optional<PreOperativeAssessmentDTO> preOperativeAssessmentDTOOptional = preOperativeAssessmentService.getPreOperativeAssessmentByName(name);
        return preOperativeAssessmentDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<PreOperativeAssessmentDTO>> getAllInventories() {
        List<PreOperativeAssessmentDTO> preOperativeAssessmentList = preOperativeAssessmentService.getAllPreOperativeAssessment();
        return ResponseEntity.status(HttpStatus.OK).body(preOperativeAssessmentList);
    }

    @PostMapping
    public ResponseEntity<PreOperativeAssessmentDTO> createPreOperativeAssessment(@RequestBody PreOperativeAssessmentDTO PreOperativeAssessmentDTO) {
            PreOperativeAssessmentDTO createPreOperativeAssessmentDTO = preOperativeAssessmentService.cratePreOperativeAssessment(PreOperativeAssessmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPreOperativeAssessmentDTO);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteInventory(@PathVariable( name = "name") String name) {
        boolean deleted = preOperativeAssessmentService.deletePreOperativeAssessment(name);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<PreOperativeAssessmentDTO> updatePreOperativeAssessment(@PathVariable(name = "name") String name, @RequestBody PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        Optional<PreOperativeAssessmentDTO> preOperativeAssessmentDTOOptional = preOperativeAssessmentService.updatePreOperativeAssessment(name, preOperativeAssessmentDTO);
        return preOperativeAssessmentDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
