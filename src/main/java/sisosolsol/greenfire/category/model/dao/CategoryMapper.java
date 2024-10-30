package sisosolsol.greenfire.category.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sisosolsol.greenfire.category.model.dto.CategoryCreateDTO;
import sisosolsol.greenfire.category.model.dto.CategoryUpdateDTO;

@Mapper
public interface CategoryMapper {
    void registChallengeCategory(CategoryCreateDTO category);

    void registStoreCategory(CategoryCreateDTO category);

    int updateChallengeCategory(@Param("categoryCode") Integer categoryCode, @Param("category") CategoryUpdateDTO category);

    int updateStoreCategory(@Param("categoryCode") Integer categoryCode, @Param("category") CategoryUpdateDTO category);
}
