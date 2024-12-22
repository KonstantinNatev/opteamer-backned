package io.kan.opteammer.dto;

import io.kan.opteammer.model.OperationRoom;
import io.kan.opteammer.model.OperationType;
import io.kan.opteammer.model.enums.OperationState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private Long id;
    private OperationType operationType;
    private OperationRoom operationRoom;
    private PatientDTO patient;
    private OperationState state;
    private LocalDateTime startDate;
    private Set<TeamMemberDTO> teamMembers;
}
