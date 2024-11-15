package sisosolsol.greenfire.category.model.dto;

import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.category.CategoryType;

@Getter
@ToString
public class CategoryUpdateDTO {
    private String categoryName;
    private CategoryType categoryType;
}
