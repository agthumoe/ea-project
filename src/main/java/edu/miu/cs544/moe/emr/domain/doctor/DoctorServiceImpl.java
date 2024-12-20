package edu.miu.cs544.moe.emr.domain.doctor;

import edu.miu.cs544.moe.emr.domain.doctor.dto.DoctorRequest;
import edu.miu.cs544.moe.emr.domain.doctor.dto.DoctorResponse;
import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageProvider;

    @Override
    public DoctorResponse create(DoctorRequest doctorRequest) {
        Doctor doctor = mapper.map(doctorRequest, Doctor.class);
        doctor.setUuid(UUID.randomUUID().toString());
        doctorRepository.save(doctor);
        return mapper.map(doctor, DoctorResponse.class);
    }

    @Override
    public Page<DoctorResponse> getAll(Pageable pageable) {
        return this.mapper.map(doctorRepository.findAll(pageable), DoctorResponse.class);
    }

    @Override
    public DoctorResponse getOne(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(messageProvider.getMessage("doctor.exceptions.notFound", null)));
        return mapper.map(doctor, DoctorResponse.class);
    }

    @Override
    @Transactional
    public DoctorResponse update(Long id, DoctorRequest doctorRequest) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(messageProvider.getMessage("doctor.exceptions.notFound", null)));
        mapper.map(doctorRequest, doctor);
        doctorRepository.save(doctor);
        return mapper.map(doctor, DoctorResponse.class);
    }

    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
}
