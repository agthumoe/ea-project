package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.shared.repository.CoreRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatientRepository extends CoreRepository<Patient>, JpaSpecificationExecutor<Patient> {
}
