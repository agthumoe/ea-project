package edu.miu.cs544.moe.emr.domain.subcategory;

import edu.miu.cs544.moe.emr.domain.shared.CoreRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SubCategoryRepository extends CoreRepository<SubCategory>, JpaSpecificationExecutor<SubCategory> {
    List<SubCategory> findByCategoryNameContainingIgnoreCase(String name);
}
