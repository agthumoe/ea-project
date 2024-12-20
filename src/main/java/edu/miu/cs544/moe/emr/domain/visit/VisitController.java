package edu.miu.cs544.moe.emr.domain.visit;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitRequest;
import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
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
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@ApiResponse(content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})
public class VisitController {
    private final VisitService visitService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/visits")
    public Page<VisitResponse> getAll(Pageable pageable) {
        return this.visitService.getAll(pageable);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/visits/{id}")
    public VisitResponse getOne(@PathVariable Long id) {
        return this.visitService.getOne(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/patient/{patientId}/visits")
    public Page<VisitResponse> getAllByPatientId(@PathVariable Long patientId, Pageable pageable) {
        return this.visitService.getAllByPatientId(patientId, pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @PostMapping("/visits")
    public VisitResponse create(@RequestBody @Validated VisitRequest request) {
        return this.visitService.create(request);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_NURSE')")
    @PutMapping("/visits/{id}")
    public VisitResponse update(@PathVariable Long id, @RequestBody @Validated VisitRequest request) {
        return this.visitService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
    @DeleteMapping("/visits/{id}")
    public void delete(@PathVariable Long id) {
        this.visitService.delete(id);
    }
}
