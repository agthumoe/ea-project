package edu.miu.cs544.moe.emr.domain.investigation;

import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationProjector;
import edu.miu.cs544.moe.emr.shared.repository.CoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestigationRepository extends CoreRepository<Investigation> {
    @Query("select i from Investigation i")
    Page<Investigation> getAll(Pageable pageable);

    @Query("select i from Investigation i where i.visit.id = :visitId")
    Page<Investigation> getByVisit(Long visitId, Pageable pageable);

    @Query("select i from Investigation i where i.visit.patient.id = :patientId")
    Page<Investigation> getByPatient(Long patientId, Pageable pageable);

    @EntityGraph(attributePaths = {"visit"})
    Optional<Investigation> findById(Long id);
}
