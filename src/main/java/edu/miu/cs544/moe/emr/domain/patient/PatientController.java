package edu.miu.cs544.moe.emr.domain.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return this.patientService.getAllPatients();
    }

    @GetMapping("{id}")
    public Patient getOne(@PathVariable Long id) {
        return this.patientService.getOnePatient(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return this.patientService.create(patient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.patientService.deleteById(id);
    }
}
