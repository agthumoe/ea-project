package edu.miu.cs544.moe.emr.domain.investigation;

import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationResponse;
import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationProjector;
import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationResponse;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InvestigationService {
    Page<InvestigationProjector> getAll(Pageable pageable);
    Page<InvestigationProjector> getByVisit(Long visitId, Pageable pageable);
    Page<InvestigationProjector> getByPatient(Long patientId, Pageable pageable);
    QuantitativeInvestigationResponse createQuantitativeInvestigation(Long visitId, QuantitativeInvestigationRequest request);
    DescriptiveInvestigationResponse createDescriptiveInvestigation(Long visitId, DescriptiveInvestigationRequest request);
    QuantitativeInvestigationResponse updateQuantitativeInvestigation(Long id, QuantitativeInvestigationRequest request);
    DescriptiveInvestigationResponse updateDescriptiveInvestigation(Long id, DescriptiveInvestigationRequest request);
    void delete(Long id);
    Optional<? extends InvestigationResponse> findById(Long id);
}
