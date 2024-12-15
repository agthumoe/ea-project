package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.application.Mapper;
import edu.miu.cs544.moe.emr.application.exception.NotFoundException;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<PatientDto> getAllPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer ageFrom,
            @RequestParam(required = false) Integer ageTo,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String categoryName
            ) {
        return this.patientService.getAllPatients(name, gender, phone, ageFrom, ageTo, city, state, categoryName);
    }

    @GetMapping("{id}")
    public Patient getOne(@PathVariable Long id) {
        return this.patientService.getOnePatient(id).orElseThrow(() -> new NotFoundException("Not found"));
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
