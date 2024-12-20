package edu.miu.cs544.moe.emr.domain.investigation;

import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationWithVisitResponse;
import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationWithVisitResponse;
import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationResponse;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationWithVisitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InvestigationService {
    Page<InvestigationResponse> getAll(Pageable pageable);
    Page<InvestigationResponse> getByVisit(Long visitId, Pageable pageable);
    Page<InvestigationResponse> getByPatient(Long patientId, Pageable pageable);
    QuantitativeInvestigationWithVisitResponse createQuantitativeInvestigation(Long visitId, QuantitativeInvestigationRequest request);
    DescriptiveInvestigationWithVisitResponse createDescriptiveInvestigation(Long visitId, DescriptiveInvestigationRequest request);
    QuantitativeInvestigationWithVisitResponse updateQuantitativeInvestigation(Long id, QuantitativeInvestigationRequest request);
    DescriptiveInvestigationWithVisitResponse updateDescriptiveInvestigation(Long id, DescriptiveInvestigationRequest request);
    void delete(Long id);
    Optional<? extends InvestigationWithVisitResponse> findById(Long id);
}
