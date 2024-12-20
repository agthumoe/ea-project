package edu.miu.cs544.moe.emr.domain.treatment;

import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentRequest;
import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentResponse;
import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentWithVisitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TreatmentService {
    TreatmentResponse create(Long visitId, TreatmentRequest request);
    TreatmentResponse update(Long id, TreatmentRequest request);
    void delete(Long id);
    TreatmentWithVisitResponse getOne(Long id);
    Page<TreatmentResponse> getAll(String patientName, String patientUuid, String visitUuid, LocalDate visitBefore, LocalDate visitAfter, String uuid, String name, String description, Pageable pageable);
    Page<TreatmentResponse> getByVisit(Long visitId, Pageable pageable);
    Page<TreatmentResponse> getByPatient(Long patientId, Pageable pageable);
}
