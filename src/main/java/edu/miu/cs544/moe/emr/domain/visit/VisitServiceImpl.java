package edu.miu.cs544.moe.emr.domain.visit;

import edu.miu.cs544.moe.emr.domain.doctor.Doctor;
import edu.miu.cs544.moe.emr.domain.doctor.DoctorRepository;
import edu.miu.cs544.moe.emr.domain.patient.Patient;
import edu.miu.cs544.moe.emr.domain.patient.PatientRepository;
import edu.miu.cs544.moe.emr.domain.visit.dto.VisitRequest;
import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import edu.miu.cs544.moe.emr.messaging.JmsMessageSender;
import edu.miu.cs544.moe.emr.messaging.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageProvider;
    private final JmsMessageSender messageSender;

    @Override
    public Page<VisitResponse> getAll(Pageable pageable) {
        return this.mapper.map(this.visitRepository.findAll(pageable), VisitResponse.class);
    }

    @Override
    public Page<VisitResponse> getAllByPatientId(Long patientId, Pageable pageable) {
        return this.mapper.map(this.visitRepository.findAllByPatientId(patientId, pageable), VisitResponse.class);
    }

    @Override
    public VisitResponse getOne(Long id) {
        Visit visit = this.visitRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("visit.exceptions.notFound", null)));
        return this.mapper.map(visit, VisitResponse.class);
    }

    @Override
    @Transactional
    public VisitResponse create(VisitRequest request) {
        Visit visit = this.mapper.map(request, Visit.class);
        Patient patient = this.patientRepository.findById(request.getPatientId()).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("patient.exceptions.notFound", null)));
        Doctor doctor = this.doctorRepository.findById(request.getDoctorId()).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("doctor.exceptions.notFound", null)));
        visit.setUuid(UUID.randomUUID().toString());
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit = this.visitRepository.save(visit);
        this.messageSender.send(visit, MessageType.CREATE);
        return this.mapper.map(visit, VisitResponse.class);
    }

    @Transactional
    @Override
    public VisitResponse update(Long id, VisitRequest request) {
        Visit visit = this.visitRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("visit.exceptions.notFound", null)));
        Patient patient = this.patientRepository.findById(request.getPatientId()).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("patient.exceptions.notFound", null)));
        Doctor doctor = this.doctorRepository.findById(request.getDoctorId()).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("doctor.exceptions.notFound", null)));
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        this.mapper.map(request, visit);
        visit = this.visitRepository.save(visit);
        this.messageSender.send(visit, MessageType.UPDATE);
        return this.mapper.map(visit, VisitResponse.class);
    }

    @Override
    public void delete(Long id) {
        this.visitRepository.deleteById(id);
    }
}
