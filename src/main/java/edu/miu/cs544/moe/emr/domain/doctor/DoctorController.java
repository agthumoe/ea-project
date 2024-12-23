package edu.miu.cs544.moe.emr.domain.doctor;

import edu.miu.cs544.moe.emr.domain.doctor.dto.DoctorRequest;
import edu.miu.cs544.moe.emr.domain.doctor.dto.DoctorResponse;
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
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@ApiResponse(content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})
public class DoctorController {
    private final DoctorService doctorService;

    @PreAuthorize("permitAll()")
    @GetMapping
    public Page<DoctorResponse> getAllDoctors(Pageable pageable) {
        return this.doctorService.getAll(pageable);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("{id}")
    public DoctorResponse getOne(@PathVariable Long id) {
        return this.doctorService.getOne(id);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public DoctorResponse create(@RequestBody @Validated DoctorRequest doctor) {
        return this.doctorService.create(doctor);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("{id}")
    public DoctorResponse update(@PathVariable Long id, @RequestBody @Validated DoctorRequest doctor) {
        return this.doctorService.update(id, doctor);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.doctorService.delete(id);
    }
}
