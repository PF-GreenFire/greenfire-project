package sisosolsol.greenfire.category.model.dto;

import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.category.CategoryType;

@Getter
@ToString
public class CategoryCreateDTO {
    CategoryType categoryType;
    Integer categoryCode;
    String categoryName;
}
