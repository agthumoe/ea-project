package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.patient.dto.PatientRequest;
import edu.miu.cs544.moe.emr.domain.patient.dto.PatientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    Page<PatientResponse> getAll(Pageable pageable);
    PatientResponse getOne(Long id);
    PatientResponse create(PatientRequest patient);
    PatientResponse update(Long id, PatientRequest patient);
    void deleteById(Long id);
}
