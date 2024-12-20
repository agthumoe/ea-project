package edu.miu.cs544.moe.emr.domain.visit;

import edu.miu.cs544.moe.emr.shared.repository.CoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CoreRepository<Visit> {
    Page<Visit> findAllByPatientId(Long patientId, Pageable pageable);
}
