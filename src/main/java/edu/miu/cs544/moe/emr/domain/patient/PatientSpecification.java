package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.shared.enums.BloodGroup;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PatientSpecification {
    public static Specification<Patient> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Patient> isGender(Gender gender) {
        return (root, query, cb) -> cb.equal(root.get("gender"), gender);
    }

    public static Specification<Patient> hasPhone(String phone) {
        return (root, query, cb) -> cb.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Patient> hasEmail(BloodGroup bloodGroup) {
        return (root, query, cb) -> cb.equal(root.get("bloodGroup"), bloodGroup);
    }

    public static Specification<Patient> dateOfBirthBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> cb.between(root.get("dateOfBirth"), from, to);
    }

    public static Specification<Patient> ageBetween(int from, int to) {
        return (root, query, cb) -> cb.between(cb.function("DATEDIFF", Integer.class, cb.currentDate(), root.get("dateOfBirth")), from, to);
    }

    public static Specification<Patient> isOlderThan(int age) {
        return (root, query, cb) -> cb.lessThan(cb.function("DATEDIFF", Integer.class, cb.currentDate(), root.get("dateOfBirth")), age);
    }

    public static Specification<Patient> isYoungerThan(int age) {
        return (root, query, cb) -> cb.greaterThan(cb.function("DATEDIFF", Integer.class, cb.currentDate(), root.get("dateOfBirth")), age);
    }

    public static Specification<Patient> liveInCity(String city) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("address").get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<Patient> liveInState(String state) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("address").get("state")), "%" + state.toLowerCase() + "%");
    }
}
