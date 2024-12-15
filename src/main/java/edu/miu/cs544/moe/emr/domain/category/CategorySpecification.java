package edu.miu.cs544.moe.emr.domain.category;

import edu.miu.cs544.moe.emr.domain.patient.Patient;
import edu.miu.cs544.moe.emr.domain.patient.PatientSpecification;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {
    public static Specification<Category> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Category> hasDescription(String description) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Patient> filterByPatientAndCategory(Specification<Patient> patientSpec, Specification<Category> categorySpec) {
        return (root, query, cb) -> {
            // Ensure distinct results to avoid duplicates due to joins or subqueries
            query.distinct(true);

            // Apply Patient Specifications
            Predicate patientPredicate = patientSpec.toPredicate(root, query, cb);

            // Create Subquery for Categories
            Subquery<Long> categorySubquery = query.subquery(Long.class);
            Root<Category> categoryRoot = categorySubquery.from(Category.class);
            Join<Category, Patient> categoryPatientJoin = categoryRoot.join("patients", JoinType.INNER);

            // Apply Category Specifications
            Predicate categoryPredicate = categorySpec.toPredicate(categoryRoot, query, cb);

            // Select Patient IDs that match the Category criteria
            categorySubquery.select(categoryPatientJoin.get("id")).where(cb.and(categoryPredicate));

            // Ensure the Patient is associated with one of the Categories in the Subquery
            Predicate associationPredicate = cb.in(root.get("id")).value(categorySubquery);

            return cb.and(patientPredicate, associationPredicate);
        };
    }
}
