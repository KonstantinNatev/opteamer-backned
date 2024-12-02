package io.kan.opteammer.services;

import io.kan.opteammer.dto.PatientDTO;
import io.kan.opteammer.model.Patient;
import io.kan.opteammer.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {
    PatientRepository patientRepository;

    private Patient mapDTOToEntity(PatientDTO patientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(patientDTO, Patient.class);
    }

    private PatientDTO mapEntityToDTO(Patient patient) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(patient, PatientDTO.class);
    }

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> getAllPatients() {
        List<PatientDTO> list = new ArrayList<>();

        Iterable<Patient> patients = patientRepository.findAll();
        patients.forEach(patient -> list.add(mapEntityToDTO(patient)));

        return list;
    }

    public Optional<PatientDTO> getPatientById(Long id) {
        try {
            Patient patientById = patientRepository.findById(id).orElseThrow();
            return Optional.of(mapEntityToDTO(patientById));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = mapDTOToEntity(patientDTO);
        patientRepository.save(patient);

        return mapEntityToDTO(patient);
    }

    public boolean deletePatient(Long id) {
        return patientRepository.findById(id).map(patient -> {
            patientRepository.delete(patient);
            return true;
        }).orElse(false);
    }

    public Optional<PatientDTO> updatePatient(Long id, PatientDTO patientDTO) {
        return patientRepository.findById(id).map(patient -> {
            patient.setName(patientDTO.getName());
            patient.setNin(patientDTO.getNin());
            patientRepository.save(patient);

            return mapEntityToDTO(patient);
        });
    }
}
