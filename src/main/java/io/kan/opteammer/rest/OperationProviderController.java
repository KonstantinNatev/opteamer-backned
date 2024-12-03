package io.kan.opteammer.rest;

import io.kan.opteammer.dto.OperationProviderDTO;
import io.kan.opteammer.services.OperationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operation-provider")
public class OperationProviderController {

    OperationProviderService operationProviderService;

    @Autowired
    public OperationProviderController(OperationProviderService operationProviderService) {
        this.operationProviderService = operationProviderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationProviderDTO> getOperationProviderById(@PathVariable( name = "id") String id) {
        Optional<OperationProviderDTO> operationProviderDTOOptional = operationProviderService.getOperationProviderById(id);
        return operationProviderDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<OperationProviderDTO>> getAllOperationProviders() {
        List<OperationProviderDTO> operationProviderList = operationProviderService.getAllOperationProvider();
        return ResponseEntity.status(HttpStatus.OK).body(operationProviderList);
    }

    @PostMapping
    public ResponseEntity<OperationProviderDTO> createOperationProvider(@RequestBody OperationProviderDTO operationProviderDTO) {
        OperationProviderDTO createPatientDTO = operationProviderService.createOperationProvider(operationProviderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPatientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable( name = "id") String id) {
        boolean deleted = operationProviderService.deleteOperationProvider(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationProviderDTO> updateAsset(@PathVariable( name = "id") String id, @RequestBody OperationProviderDTO operationProviderDTO) {
        Optional<OperationProviderDTO> operationProviderDTOOptional = operationProviderService.updateOperationProvider(id,operationProviderDTO);
        return operationProviderDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
