package edu.miu.cs544.moe.emr.domain.subcategory;

import edu.miu.cs544.moe.emr.domain.shared.CoreRepository;

import java.util.List;

public interface SubCategoryRepository extends CoreRepository<SubCategory> {
    List<SubCategory> findByCategoryNameContainingIgnoreCase(String name);
}
