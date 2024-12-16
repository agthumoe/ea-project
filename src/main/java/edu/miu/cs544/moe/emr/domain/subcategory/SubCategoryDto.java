package edu.miu.cs544.moe.emr.domain.subcategory;

import edu.miu.cs544.moe.emr.domain.category.CategoryDto;
import lombok.Data;

@Data
public class SubCategoryDto {
    private Long id;
    private String name;
    private String description;
    private CategoryDto category;
}
