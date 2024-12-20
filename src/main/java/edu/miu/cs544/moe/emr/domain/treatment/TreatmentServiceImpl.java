package edu.miu.cs544.moe.emr.domain.treatment;

import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentRequest;
import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentResponse;
import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentWithVisitResponse;
import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.domain.visit.VisitRepository;
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
public class TreatmentServiceImpl implements TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final VisitRepository visitRepository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageProvider;
    private final JmsMessageSender messageSender;

    @Override
    @Transactional
    public TreatmentResponse create(Long visitId, TreatmentRequest request) {
        Treatment treatment = this.mapper.map(request, Treatment.class);
        Visit visit = this.visitRepository.findById(visitId).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("visit.exceptions.notFound", null)));
        treatment.setUuid(UUID.randomUUID().toString());
        treatment.setVisit(visit);
        treatment = this.treatmentRepository.save(treatment);
        this.messageSender.send(treatment, MessageType.CREATE);
        return this.mapper.map(treatment, TreatmentResponse.class);
    }

    @Override
    @Transactional
    public TreatmentResponse update(Long id, TreatmentRequest request) {
        Treatment treatment = this.treatmentRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("treatment.exceptions.notFound", null)));
        this.mapper.map(request, treatment);
        treatment = this.treatmentRepository.save(treatment);
        this.messageSender.send(treatment, MessageType.UPDATE);
        return this.mapper.map(treatment, TreatmentResponse.class);
    }

    @Override
    public void delete(Long id) {
        this.treatmentRepository.deleteById(id);
    }

    @Override
    public TreatmentWithVisitResponse getOne(Long id) {
        Treatment treatment = this.treatmentRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("treatment.exceptions.notFound", null)));
        return this.mapper.map(treatment, TreatmentWithVisitResponse.class);
    }

    @Override
    public Page<TreatmentResponse> getAll(Pageable pageable) {
        return this.mapper.map(this.treatmentRepository.findAll(pageable), TreatmentResponse.class);
    }

    @Override
    public Page<TreatmentResponse> getByVisit(Long visitId, Pageable pageable) {
        return this.mapper.map(this.treatmentRepository.findByVisitId(visitId, pageable), TreatmentResponse.class);
    }

    @Override
    public Page<TreatmentResponse> getByPatient(Long patientId, Pageable pageable) {
        return this.mapper.map(this.treatmentRepository.findByVisitPatientId(patientId, pageable), TreatmentResponse.class);
    }
}
