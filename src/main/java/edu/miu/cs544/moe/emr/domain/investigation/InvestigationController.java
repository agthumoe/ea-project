package edu.miu.cs544.moe.emr.domain.investigation;

import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto.DescriptiveInvestigationWithVisitResponse;
import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationResponse;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationRequest;
import edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto.QuantitativeInvestigationWithVisitResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@ApiResponse(content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})
public class InvestigationController {
    private final InvestigationService investigationService;

    @GetMapping("/investigations")
    public Page<InvestigationResponse> getAll(Pageable pageable) {
        return this.investigationService.getAll(pageable);
    }

    @GetMapping("/visit/{visitId}/investigations")
    public Page<InvestigationResponse> getByVisit(@PathVariable Long visitId, Pageable pageable) {
        return this.investigationService.getByVisit(visitId, pageable);
    }

    @GetMapping("/patient/{patientId}/investigations")
    public Page<InvestigationResponse> getByPatient(@PathVariable Long patientId, Pageable pageable) {
        return this.investigationService.getByPatient(patientId, pageable);
    }

    @PostMapping("/visits/{visitId}/investigations/quantitative")
    public QuantitativeInvestigationWithVisitResponse createQuantitativeInvestigation(@PathVariable Long visitId, @RequestBody @Validated QuantitativeInvestigationRequest request) {
        return this.investigationService.createQuantitativeInvestigation(visitId, request);
    }

    @PostMapping("/visits/{visitId}/investigations/descriptive")
    public DescriptiveInvestigationWithVisitResponse createDescriptiveInvestigation(@PathVariable Long visitId, @RequestBody @Validated DescriptiveInvestigationRequest request) {
        return this.investigationService.createDescriptiveInvestigation(visitId, request);
    }

    @GetMapping("/investigations/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(this.investigationService.findById(id).orElse(null));
    }

    @PutMapping("/investigations/{id}/quantitative")
    public QuantitativeInvestigationWithVisitResponse updateQuantitativeInvestigation(@PathVariable Long id, @RequestBody @Validated QuantitativeInvestigationRequest request) {
        return this.investigationService.updateQuantitativeInvestigation(id, request);
    }

    @PutMapping("/investigations/{id}/descriptive")
    public DescriptiveInvestigationWithVisitResponse updateDescriptiveInvestigation(@PathVariable Long id, @RequestBody @Validated DescriptiveInvestigationRequest request) {
        return this.investigationService.updateDescriptiveInvestigation(id, request);
    }

    @DeleteMapping("/investigations/{id}")
    public void delete(@PathVariable Long id) {
        this.investigationService.delete(id);
    }
}
