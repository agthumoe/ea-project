package edu.miu.cs544.moe.emr.domain.doctor;

import edu.miu.cs544.moe.emr.domain.doctor.dto.DoctorRequest;
import edu.miu.cs544.moe.emr.domain.doctor.dto.DoctorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {
    DoctorResponse create(DoctorRequest doctorRequest);
    Page<DoctorResponse> getAll(Pageable pageable);
    DoctorResponse getOne(Long id);
    DoctorResponse update(Long id, DoctorRequest doctorRequest);
    void delete(Long id);
}
