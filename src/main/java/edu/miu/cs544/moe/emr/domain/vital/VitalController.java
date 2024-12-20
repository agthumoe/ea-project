package edu.miu.cs544.moe.emr.domain.vital;

import edu.miu.cs544.moe.emr.domain.vital.dto.VitalRequest;
import edu.miu.cs544.moe.emr.domain.vital.dto.VitalResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@ApiResponse(content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})
public class VitalController {
    private final VitalService vitalService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vitals")
    public Page<VitalResponse> getAll(Pageable pageable) {
        return this.vitalService.getAll(pageable);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/patient/{patientId}/vitals")
    public Page<VitalResponse> getAllByPatientId(@PathVariable Long patientId, Pageable pageable) {
        return this.vitalService.getAllByPatientId(patientId, pageable);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vitals/{id}")
    public VitalResponse getOne(@PathVariable Long id) {
        return this.vitalService.getOne(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/visits/{visitId}/vitals")
    public VitalResponse getOneByVisitId(@PathVariable Long visitId) {
        return this.vitalService.getOneByVisitId(visitId);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @PostMapping("/visits/{visitId}/vitals")
    public VitalResponse create(@PathVariable Long visitId, VitalRequest request) {
        return this.vitalService.create(visitId, request);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @PutMapping("/vitals/{id}")
    public VitalResponse update(@PathVariable Long id, VitalRequest request) {
        return this.vitalService.update(id, request);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @DeleteMapping("/vitals/{id}")
    public void delete(@PathVariable Long id) {
        this.vitalService.delete(id);
    }
}
