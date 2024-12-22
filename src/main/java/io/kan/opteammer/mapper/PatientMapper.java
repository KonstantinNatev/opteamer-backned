package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.PatientDTO;
import io.kan.opteammer.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO toPatientDTO(Patient patient);

    Patient toPatient(PatientDTO patientDTO);

}

