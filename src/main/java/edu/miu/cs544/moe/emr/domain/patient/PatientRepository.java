package edu.miu.cs544.moe.emr.domain.patient;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    List<Patient> findByAddressCityContainingIgnoreCase(String addressCity);
    List<Patient> findByVitalTemperatureBetween(double min, double max);

    @Override
    @Cacheable("findPatientById")
    Optional<Patient> findById(Long id);
}
