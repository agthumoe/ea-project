package edu.miu.cs544.moe.emr.domain.vital;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.domain.visit.VisitRepository;
import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import edu.miu.cs544.moe.emr.domain.vital.dto.VitalRequest;
import edu.miu.cs544.moe.emr.domain.vital.dto.VitalResponse;
import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VitalServiceImpl implements VitalService {
    private final VitalRepository vitalRepository;
    private final VisitRepository visitRepository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageProvider;

    @Override
    public Page<VitalResponse> getAll(Pageable pageable) {
        return this.mapper.map(this.vitalRepository.findAll(pageable), VitalResponse.class);
    }

    @Override
    public Page<VitalResponse> getAllByPatientId(Long patientId, Pageable pageable) {
        return this.mapper.map(this.vitalRepository.findByVisitPatientId(patientId, pageable), VitalResponse.class);
    }

    @Override
    public VitalResponse getOne(Long id) {
        Vital vital = this.vitalRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("vital.exceptions.notFound", null)));
        return this.mapper.map(vital, VitalResponse.class);
    }

    @Override
    public VitalResponse getOneByVisitId(Long visitId) {
        Vital vital = this.vitalRepository.findByVisitId(visitId).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("vital.exceptions.notFound", null)));
        return this.mapper.map(vital, VitalResponse.class);
    }

    @Override
    @Transactional
    public VitalResponse create(Long visitId, VitalRequest request) {
        Vital vital = this.mapper.map(request, Vital.class);
        Visit visit = this.visitRepository.findById(visitId).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("visit.exceptions.notFound", null)));
        vital.setVisit(visit);
        return this.mapper.map(this.vitalRepository.save(vital), VitalResponse.class);
    }

    @Override
    @Transactional
    public VitalResponse update(Long id, VitalRequest request) {
        Vital vital = this.vitalRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("vital.exceptions.notFound", null)));
        this.mapper.map(request, vital);
        return this.mapper.map(this.vitalRepository.save(vital), VitalResponse.class);
    }

    @Override
    public void delete(Long id) {
        this.vitalRepository.deleteById(id);
    }
}
