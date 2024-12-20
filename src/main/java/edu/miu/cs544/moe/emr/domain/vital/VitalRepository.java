package edu.miu.cs544.moe.emr.domain.vital;

import edu.miu.cs544.moe.emr.shared.repository.CoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VitalRepository extends CoreRepository<Vital> {
    Optional<Vital> findByVisitId(Long visitId);
    Page<Vital> findByVisitPatientId(Long patientId, Pageable pageable);
}
