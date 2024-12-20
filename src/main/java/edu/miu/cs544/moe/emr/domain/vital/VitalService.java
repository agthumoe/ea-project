package edu.miu.cs544.moe.emr.domain.vital;

import edu.miu.cs544.moe.emr.domain.vital.dto.VitalRequest;
import edu.miu.cs544.moe.emr.domain.vital.dto.VitalResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VitalService {
    Page<VitalResponse> getAll(Pageable pageable);
    Page<VitalResponse> getAllByPatientId(Long patientId, Pageable pageable);
    VitalResponse getOne(Long id);
    VitalResponse getOneByVisitId(Long visitId);
    VitalResponse create(Long visitId, VitalRequest request);
    VitalResponse update(Long id, VitalRequest request);
    void delete(Long id);
}
