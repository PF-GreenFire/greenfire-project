package sisosolsol.greenfire.category.model.dto;

import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.category.model.type.CategoryType;

@Getter
@ToString
public class CategoryCreateDTO {
    CategoryType categoryType;
    String categoryName;
}
