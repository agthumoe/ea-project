package edu.miu.cs544.moe.emr.domain.doctor;

import edu.miu.cs544.moe.emr.shared.repository.CoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CoreRepository<Doctor> {
}
