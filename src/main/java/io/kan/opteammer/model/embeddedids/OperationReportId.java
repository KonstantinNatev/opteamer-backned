package io.kan.opteammer.model.embeddedids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author degijanos
 * @version 1.0
 * @since 2024. 06. 12.
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OperationReportId implements Serializable {

    @Column(name = "team_member_id")
    private Long teamMemberId;

    @Column(name = "operation_id")
    private Long operationId;
}
