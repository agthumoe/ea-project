package edu.miu.cs544.moe.emr.service;

import edu.miu.cs544.moe.emr.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllPatients();
    Optional<Patient> getOnePatient(Long id);
    Patient create(Patient patient);
    Patient update(Long id, Patient patient);
    void deleteById(Long id);
    void delete(Patient patient);
}
