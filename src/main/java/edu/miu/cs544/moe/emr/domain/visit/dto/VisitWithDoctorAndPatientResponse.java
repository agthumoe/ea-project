package edu.miu.cs544.moe.emr.domain.visit.dto;

import edu.miu.cs544.moe.emr.domain.person.dto.PersonResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitWithDoctorAndPatientResponse extends VisitResponse {
    private PersonResponse doctor;
    private PersonResponse patient;
}
