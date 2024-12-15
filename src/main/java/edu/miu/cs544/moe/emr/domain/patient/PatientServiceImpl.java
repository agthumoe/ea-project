package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.application.Mapper;
import edu.miu.cs544.moe.emr.domain.category.Category;
import edu.miu.cs544.moe.emr.domain.category.CategorySpecification;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository repository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<PatientDto> getAllPatients(String name, Gender gender, String phone, Integer ageFrom, Integer ageTo, String city, String state, String categoryName) {
        Specification<Patient> spec = (root, query, cb) -> cb.conjunction();
        if (name != null) {
            spec = spec.and(PatientSpecification.hasName(name));
        }
        if (gender != null) {
            spec = spec.and(PatientSpecification.isGender(gender));
        }
        if (phone != null) {
            spec = spec.and(PatientSpecification.hasPhone(phone));
        }
        if (ageFrom != null) {
            spec = spec.and(PatientSpecification.isOlderThan(ageFrom));
        }
        if (ageTo != null) {
            spec = spec.and(PatientSpecification.isYoungerThan(ageTo));
        }
        if (city != null) {
            spec = spec.and(PatientSpecification.liveInCity(city));
        }
        if (state != null) {
            spec = spec.and(PatientSpecification.liveInState(state));
        }
        if (categoryName != null) {
            Specification<Category> categorySpec = CategorySpecification.hasName(categoryName);
            spec = CategorySpecification.filterByPatientAndCategory(spec, categorySpec);
        }
        return this.mapper.map(this.repository.findAll(spec), PatientDto.class);
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
