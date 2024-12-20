package edu.miu.cs544.moe.emr.domain.patient;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class PatientSpecification {
    private PatientSpecification() {}

    public static Specification<Patient> hasUuid(String uuid) {
        return (root, query, builder) -> builder.like(root.get("uuid"), "%" + uuid + "%");
    }

    public static Specification<Patient> hasName(String name) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Patient> isGender(Gender gender) {
        return (root, query, builder) -> builder.equal(root.get("gender"), gender);
    }

    public static Specification<Patient> hasPhone(String phone) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("phone")), "%" + phone + "%");
    }

    public static Specification<Patient> ageFrom(Integer age) {
        return (root, query, builder) -> {
            LocalDate today = LocalDate.now();
            LocalDate fromDate = today.minusYears(age);
            return builder.lessThanOrEqualTo(root.get("dateOfBirth"), fromDate);
        };
    }

    public static Specification<Patient> ageTo(Integer age) {
        return (root, query, builder) -> {
            LocalDate today = LocalDate.now();
            LocalDate toDate = today.minusYears(age);
            return builder.greaterThanOrEqualTo(root.get("dateOfBirth"), toDate);
        };
    }

    public static Specification<Patient> hasBloodGroup(BloodGroup bloodGroup) {
        return (root, query, builder) -> builder.equal(root.get("bloodGroup"), bloodGroup);
    }

    public static Specification<Patient> hasStreet(String street) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("address").get("street")), "%" + street.toLowerCase() + "%");
    }

    public static Specification<Patient> hasCity(String city) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("address").get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<Patient> hasState(String state) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("address").get("state")), "%" + state.toLowerCase() + "%");
    }

    public static Specification<Patient> hasZipCode(String zipCode) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("address").get("zipCode")), "%" + zipCode.toLowerCase() + "%");
    }
}
