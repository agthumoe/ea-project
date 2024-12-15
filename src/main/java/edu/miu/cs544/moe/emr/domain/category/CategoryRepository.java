package edu.miu.cs544.moe.emr.domain.category;

import edu.miu.cs544.moe.emr.domain.shared.CoreRepository;

import java.util.List;

public interface CategoryRepository extends CoreRepository<Category> {
    List<Category> findDistinctByPatients_NameContainingIgnoreCase(String name);
}
