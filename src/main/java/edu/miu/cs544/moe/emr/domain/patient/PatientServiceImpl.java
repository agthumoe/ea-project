package edu.miu.cs544.moe.emr.domain.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository repository;
    @Override
    public List<Patient> getAllPatients() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Patient> getOnePatient(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Patient create(Patient patient) {
        return this.repository.save(patient);
    }

    @Override
    public Patient update(Long id, Patient patient) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void delete(Patient patient) {
        this.repository.delete(patient);
    }
}
