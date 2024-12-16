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
        Specification<SubCategory> spec = Specification.where(null);
        spec = spec.and(SubCategorySpecification.hasName("Therapy"));
//        spec = spec.and(SubCategorySpecification.hasDescription("physical"));
        List<SubCategory> subCategories = subCategoryRepository.findAll(spec);
        return mapper.map(subCategories, SubCategoryDto.class);
    }
}
