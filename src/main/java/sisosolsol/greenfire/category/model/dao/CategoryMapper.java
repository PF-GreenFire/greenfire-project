package sisosolsol.greenfire.category.model.dao;

import org.apache.ibatis.annotations.Mapper;
import sisosolsol.greenfire.category.model.dto.CategoryCreateDTO;

@Mapper
public interface CategoryMapper {
    void registChallengeCategory(CategoryCreateDTO category);

    void registStoreCategory(CategoryCreateDTO category);
}
