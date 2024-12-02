package io.kan.opteammer.rest;

import io.kan.opteammer.dto.PatientDTO;
import io.kan.opteammer.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getAssetById(@PathVariable( name = "id") Long id) {
        Optional<PatientDTO> patientDTOOptional = patientService.getPatientById(id);
        return patientDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> assetList = patientService.getAllPatients();
        return ResponseEntity.status(HttpStatus.OK).body(assetList);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createAsset(@RequestBody PatientDTO patientDTO) {
        PatientDTO createPatientDTO = patientService.createPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPatientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable( name = "id") Long id) {
        boolean deleted = patientService.deletePatient(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updateAsset(@PathVariable( name = "id") Long id, @RequestBody PatientDTO patientDTO) {
        Optional<PatientDTO> patientDTOOptional = patientService.updatePatient(id,patientDTO);
        return patientDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
