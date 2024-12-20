package edu.miu.cs544.moe.emr.domain.treatment;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class TreatmentSpecification {
    private TreatmentSpecification() {
    }

    public static Specification<Treatment> hasPatientName(String patientName) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("visit").get("patient").get("name")), "%" + patientName.toLowerCase() + "%");
    }

    public static Specification<Treatment> hasPatientUuid(String patientUuid) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("visit").get("patient").get("uuid")), "%" + patientUuid.toLowerCase() + "%");
    }

    public static Specification<Treatment> hasVisitUuid(String visitUuid) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("visit").get("uuid")), "%" + visitUuid.toLowerCase() + "%");
    }

    public static Specification<Treatment> hasVisitBeforeDate(LocalDate date) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("visit").get("visitDate"), date);
    }

    public static Specification<Treatment> hasVisitAfterDate(LocalDate date) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("visit").get("visitDate"), date);
    }

    public static Specification<Treatment> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Treatment> hasDescription(String description) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Treatment> hasUuid(String uuid) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("uuid")), "%" + uuid.toLowerCase() + "%");
    }
}
