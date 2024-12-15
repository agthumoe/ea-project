package edu.miu.cs544.moe.emr.domain.vitals;

import edu.miu.cs544.moe.emr.domain.shared.CoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalRepository extends JpaRepository<Vital, Long> {
}
