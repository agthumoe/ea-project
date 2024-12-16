package edu.miu.cs544.moe.emr.domain.subcategory;

import edu.miu.cs544.moe.emr.application.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubCategoryController {
    @Autowired
    private Mapper mapper;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @GetMapping("/subcategories")
    public List<SubCategoryDto> getSubCategories() {
        Specification<SubCategory> spec = (root, query, cb) -> {
            query.distinct(true);
            root.fetch("category");

            return cb.conjunction();
        };
//        spec = spec.and(SubCategorySpecification.hasName("Physical"));
//        spec = spec.and(SubCategorySpecification.hasDescription("physical"));
//        spec = spec.and(SubCategorySpecification.categoryName("Rehabilitation"));
        List<SubCategory> subCategories = subCategoryRepository.findAll(spec);
        return mapper.map(subCategories, SubCategoryDto.class);
    }
}
