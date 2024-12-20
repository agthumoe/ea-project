package edu.miu.cs544.moe.emr.domain.visit;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitRequest;
import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VisitService {
    Page<VisitResponse> getAll(Pageable pageable);
    Page<VisitResponse> getAllByPatientId(Long patientId, Pageable pageable);
    VisitResponse getOne(Long id);
    VisitResponse create(VisitRequest request);
    VisitResponse update(Long id, VisitRequest request);
    void delete(Long id);
}
