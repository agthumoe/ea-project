package edu.miu.cs544.moe.emr.domain.visit;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitRequest;
import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@ApiResponse(content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml")})
public class VisitController {
    private final VisitService visitService;

    @GetMapping("/visits")
    public Page<VisitResponse> getAll(Pageable pageable) {
        return this.visitService.getAll(pageable);
    }

    @GetMapping("/visits/{id}")
    public VisitResponse getOne(@PathVariable Long id) {
        return this.visitService.getOne(id);
    }

    @GetMapping("/patient/{patientId}/visits")
    public Page<VisitResponse> getAllByPatientId(@PathVariable Long patientId, Pageable pageable) {
        return this.visitService.getAllByPatientId(patientId, pageable);
    }

    @PostMapping("/visits")
    public VisitResponse create(@RequestBody @Validated VisitRequest request) {
        return this.visitService.create(request);
    }

    @PutMapping("/visits/{id}")
    public VisitResponse update(@PathVariable Long id, @RequestBody @Validated VisitRequest request) {
        return this.visitService.update(id, request);
    }

    @DeleteMapping("/visits/{id}")
    public void delete(@PathVariable Long id) {
        this.visitService.delete(id);
    }
}
