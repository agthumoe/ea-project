package edu.miu.cs544.moe.emr.domain.investigation;

import edu.miu.cs544.moe.emr.domain.investigation.descriptive.DescriptiveInvestigation;
import edu.miu.cs544.moe.emr.domain.investigation.descriptive.DescriptiveInvestigationRepository;
import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationWithVisitResponse;
import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationWithVisitResponse;
import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationResponse;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.QuantitativeInvestigation;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.QuantitativeInvestigationRepository;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationWithVisitResponse;
import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.domain.visit.VisitRepository;
import edu.miu.cs544.moe.emr.exception.BadRequestException;
import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvestigationServiceImpl implements InvestigationService{
    private final InvestigationRepository investigationRepository;
    private final QuantitativeInvestigationRepository quantitativeInvestigationRepository;
    private final DescriptiveInvestigationRepository descriptiveInvestigationRepository;
    private final VisitRepository visitRepository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageProvider;

    @Override
    public Page<InvestigationResponse> getAll(Pageable pageable) {
        return this.mapper.map(this.investigationRepository.getAll(pageable), InvestigationResponse.class);
    }

    @Override
    public Page<InvestigationResponse> getByVisit(Long visitId, Pageable pageable) {
        return this.mapper.map(this.investigationRepository.getByVisit(visitId, pageable), InvestigationResponse.class);
    }

    @Override
    public Page<InvestigationResponse> getByPatient(Long patientId, Pageable pageable) {
        return this.mapper.map(this.investigationRepository.getByPatient(patientId, pageable), InvestigationResponse.class);
    }

    @Override
    @Transactional
    public QuantitativeInvestigationWithVisitResponse createQuantitativeInvestigation(Long visitId, QuantitativeInvestigationRequest request) {
        QuantitativeInvestigation investigation = this.mapper.map(request, QuantitativeInvestigation.class);
        Visit visit = this.visitRepository.findById(visitId).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("visit.exceptions.notFound", null)));
        investigation.setUuid(UUID.randomUUID().toString());
        investigation.setVisit(visit);
        return this.mapper.map(this.quantitativeInvestigationRepository.save(investigation), QuantitativeInvestigationWithVisitResponse.class);
    }

    @Override
    public DescriptiveInvestigationWithVisitResponse createDescriptiveInvestigation(Long visitId, DescriptiveInvestigationRequest request) {
        DescriptiveInvestigation investigation = this.mapper.map(request, DescriptiveInvestigation.class);
        Visit visit = this.visitRepository.findById(visitId).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("visit.exceptions.notFound", null)));
        investigation.setUuid(UUID.randomUUID().toString());
        investigation.setVisit(visit);
        return this.mapper.map(this.descriptiveInvestigationRepository.save(investigation), DescriptiveInvestigationWithVisitResponse.class);
    }

    @Override
    @Transactional
    public QuantitativeInvestigationWithVisitResponse updateQuantitativeInvestigation(Long id, QuantitativeInvestigationRequest request) {
        QuantitativeInvestigation investigation = this.quantitativeInvestigationRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("investigation.exceptions.notFound", null)));
        this.mapper.map(request, investigation);
        return this.mapper.map(this.quantitativeInvestigationRepository.save(investigation), QuantitativeInvestigationWithVisitResponse.class);
    }

    @Override
    @Transactional
    public DescriptiveInvestigationWithVisitResponse updateDescriptiveInvestigation(Long id, DescriptiveInvestigationRequest request) {
        DescriptiveInvestigation investigation = this.descriptiveInvestigationRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("investigation.exceptions.notFound", null)));
        this.mapper.map(request, investigation);
        return this.mapper.map(this.descriptiveInvestigationRepository.save(investigation), DescriptiveInvestigationWithVisitResponse.class);
    }

    @Override
    public void delete(Long id) {
        this.investigationRepository.deleteById(id);
    }

    @Override
    public Optional<? extends InvestigationWithVisitResponse> findById(Long id) {
        Investigation investigation = this.investigationRepository.findById(id).orElseThrow(() -> new NotFoundException(this.messageProvider.getMessage("investigation.exceptions.notFound", null)));
        if(investigation instanceof QuantitativeInvestigation){
            return Optional.of(this.mapper.map(investigation, QuantitativeInvestigationWithVisitResponse.class));
        } else if(investigation instanceof DescriptiveInvestigation){
            return Optional.of(this.mapper.map(investigation, DescriptiveInvestigationWithVisitResponse.class));
        }
        throw new BadRequestException(this.messageProvider.getMessage("investigation.exceptions.invalidType", null));
    }

}
