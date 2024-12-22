package io.kan.opteammer.repositories;

import io.kan.opteammer.model.OperationReport;
import io.kan.opteammer.model.embeddedids.OperationReportId;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface OperationReportRepository extends CrudRepository<OperationReport, OperationReportId> {
    Optional<OperationReport> findById(OperationReportId operationReportId);
}