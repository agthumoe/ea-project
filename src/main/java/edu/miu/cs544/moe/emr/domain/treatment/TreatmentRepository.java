package edu.miu.cs544.moe.emr.domain.treatment;

import edu.miu.cs544.moe.emr.shared.repository.CoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreatmentRepository extends CoreRepository<Treatment>, JpaSpecificationExecutor<Treatment> {
    Page<Treatment> findByVisitId(Long visitId, Pageable pageable);
    Page<Treatment> findByVisitPatientId(Long patientId, Pageable pageable);
    @EntityGraph(attributePaths = {"visit"})
    @Query(name = "Treatment.findById")
    Optional<Treatment> findById(Long id);
}
