package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.patient.dto.PatientRequest;
import edu.miu.cs544.moe.emr.domain.patient.dto.PatientResponse;
import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageSource;

    @Override
    public Page<PatientResponse> getAll(Pageable pageable) {
        return this.mapper.map(this.repository.findAll(pageable), PatientResponse.class);
    }

    @Override
    public PatientResponse getOne(Long id) {
        Patient patient = this.repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("patient.exceptions.notFound", null)));
        return this.mapper.map(patient, PatientResponse.class);
    }

    @Override
    public PatientResponse create(PatientRequest patient) {
        Patient entity = this.mapper.map(patient, Patient.class);
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
