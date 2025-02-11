package io.kan.opteammer.model;

import io.kan.opteammer.model.embeddedids.OperationReportId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author degijanos
 * @version 1.0
 * @since 2024. 06. 13.
 */

@Entity
@Table(name = "OPERATION_REPORTS")
@NoArgsConstructor
@Getter
@Setter
public class OperationReport {

    @EmbeddedId
    private OperationReportId operationReportId;

    @ManyToOne
    @MapsId("teamMemberId")
    @JoinColumn(name = "team_member_id", columnDefinition = "BIGINT")
    private TeamMember teamMember;

    @ManyToOne
    @MapsId("operationId")
    @JoinColumn(name = "operation_id", columnDefinition = "BIGINT")
    private Operation operation;

    @Column(name = "report")
    private String report;
}
