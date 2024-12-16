package edu.miu.cs544.moe.emr.domain.subcategory;

import edu.miu.cs544.moe.emr.domain.category.Category;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SubCategorySpecification {
    public static Specification<SubCategory> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<SubCategory> hasDescription(String description) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<SubCategory> categoryName(String name) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<SubCategory, Category> categoryJoin = root.join("category", JoinType.INNER);
            root.fetch("category");
            return cb.like(cb.lower(categoryJoin.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
}
