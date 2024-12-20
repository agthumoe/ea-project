package edu.miu.cs544.moe.emr.domain.treatment;

import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentRequest;
import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentResponse;
import edu.miu.cs544.moe.emr.domain.treatment.dto.TreatmentWithVisitResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@ApiResponse(content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})
public class TreatmentController {
    private final TreatmentService treatmentService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/treatments")
    public Page<TreatmentResponse> getAll(Pageable pageable) {
        return this.treatmentService.getAll(pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @GetMapping("/visits/{visitId}/treatments")
    public Page<TreatmentResponse> getByVisit(@PathVariable Long visitId, Pageable pageable) {
        return this.treatmentService.getByVisit(visitId, pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @GetMapping("/patients/{patientId}/treatments")
    public Page<TreatmentResponse> getByPatient(@PathVariable Long patientId, Pageable pageable) {
        return this.treatmentService.getByPatient(patientId, pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @GetMapping("/treatments/{id}")
    public TreatmentWithVisitResponse getOne(@PathVariable Long id) {
        return this.treatmentService.getOne(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
    @PostMapping("/visits/{visitId}/treatments")
    public TreatmentResponse create(@PathVariable Long visitId, @RequestBody @Validated TreatmentRequest request) {
        return this.treatmentService.create(visitId, request);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @PutMapping("/treatments/{id}")
    public TreatmentResponse update(@PathVariable Long id, @RequestBody @Validated TreatmentRequest request) {
        return this.treatmentService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
    @DeleteMapping("/treatments/{id}")
    public void delete(@PathVariable Long id) {
        this.treatmentService.delete(id);
    }
}
