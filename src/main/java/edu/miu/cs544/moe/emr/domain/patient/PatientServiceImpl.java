package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.patient.dto.PatientRequest;
import edu.miu.cs544.moe.emr.domain.patient.dto.PatientResponse;
import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageSource;

    @Override
    public Page<PatientResponse> getAll(String uuid, String name, Gender gender, String phone, Integer ageFrom, Integer ageTo, BloodGroup bloodGroup, String street, String city, String state, String zipCode, Pageable pageable) {
        Specification<Patient> spec = Specification.where(null);
        if (uuid != null) {
            spec = spec.and(PatientSpecification.hasUuid(uuid));
        }
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
            spec = spec.and(PatientSpecification.ageFrom(ageFrom));
        }
        if (ageTo != null) {
            spec = spec.and(PatientSpecification.ageTo(ageTo));
        }
        if (bloodGroup != null) {
            spec = spec.and(PatientSpecification.hasBloodGroup(bloodGroup));
        }
        if (street != null) {
            spec = spec.and(PatientSpecification.hasStreet(street));
        }
        if (city != null) {
            spec = spec.and(PatientSpecification.hasCity(city));
        }
        if (state != null) {
            spec = spec.and(PatientSpecification.hasState(state));
        }
        if (zipCode != null) {
            spec = spec.and(PatientSpecification.hasZipCode(zipCode));
        }
        return this.mapper.map(this.repository.findAll(spec, pageable), PatientResponse.class);
    }

    @Override
    public PatientResponse getOne(Long id) {
        Patient patient = this.repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("patient.exceptions.notFound", null)));
        return this.mapper.map(patient, PatientResponse.class);
    }

    @Override
    public PatientResponse create(PatientRequest patient) {
        Patient entity = this.mapper.map(patient, Patient.class);
        entity.setUuid(UUID.randomUUID().toString());
        this.repository.save(entity);
        return this.mapper.map(entity, PatientResponse.class);
    }

    @Override
    @Transactional
    public PatientResponse update(Long id, PatientRequest patient) {
        Patient entity = this.repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("patient.exceptions.notFound", null)));
        this.mapper.map(patient, entity);
        this.repository.save(entity);
        return this.mapper.map(entity, PatientResponse.class);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
