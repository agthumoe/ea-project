package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<PatientDto> getAllPatients(String name, Gender gender, String phone, Integer ageFrom, Integer ageTo, String city, String state, String categoryName);
    Optional<Patient> getOnePatient(Long id);
    Patient create(Patient patient);
    Patient update(Long id, Patient patient);
    void deleteById(Long id);
    void delete(Patient patient);
}
