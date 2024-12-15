package edu.miu.cs544.moe.emr.domain.subcategory;

import edu.miu.cs544.moe.emr.domain.category.Category;
import edu.miu.cs544.moe.emr.domain.shared.model.MutableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SubCategory extends MutableModel {
    private String name;
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Category category;

    public SubCategory() {
    }

    public SubCategory(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }
}
