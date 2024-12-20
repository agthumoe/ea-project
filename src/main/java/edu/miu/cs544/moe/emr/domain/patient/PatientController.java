package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.patient.dto.PatientRequest;
import edu.miu.cs544.moe.emr.domain.patient.dto.PatientResponse;
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
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@ApiResponse(content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Page<PatientResponse> getAllPatients(Pageable pageable) {
        return this.patientService.getAll(pageable);
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public PatientResponse getOne(@PathVariable Long id) {
        return this.patientService.getOne(id);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public PatientResponse create(@RequestBody @Validated PatientRequest patient) {
        return this.patientService.create(patient);
    }

    @PutMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public PatientResponse update(@PathVariable Long id, @RequestBody @Validated PatientRequest patient) {
        return this.patientService.update(id, patient);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        this.patientService.deleteById(id);
    }
}
