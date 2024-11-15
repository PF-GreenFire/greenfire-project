package sisosolsol.greenfire.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.category.model.dao.CategoryMapper;
import sisosolsol.greenfire.category.model.dto.CategoryCreateDTO;
import sisosolsol.greenfire.category.model.dto.CategoryDTO;
import sisosolsol.greenfire.category.model.dto.CategoryUpdateDTO;
import sisosolsol.greenfire.category.model.type.CategoryType;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategoryList(CategoryType categoryType) {
        List<CategoryDTO> categoryList = null;
        switch (categoryType) {
            case CHALLENGE :
                categoryList = categoryMapper.getChallengeCategoryList(); break;
            case STORE:
                categoryList = categoryMapper.getStoreCategoryList(); break;
        }
        return categoryList;
    }

    public int registCategory(CategoryCreateDTO category) {
        switch (category.getCategoryType()) {
            case CHALLENGE :
                categoryMapper.registChallengeCategory(category);break;
            case STORE:
                categoryMapper.registStoreCategory(category); break;
        }
        return category.getCategoryCode();
    }


    public void updateCategory(Integer categoryCode, CategoryUpdateDTO category) {
        int result = 0;
        switch (category.getCategoryType()) {
            case CHALLENGE:
                result = categoryMapper.updateChallengeCategory(categoryCode, category);
                break;
            case STORE:
                result = categoryMapper.updateStoreCategory(categoryCode, category);
                break;
        }
    }

    public void deleteCategory(Integer categoryCode, CategoryType categoryType) {
        // TODO: 각 카테고리에 등록된 챌린지/스토어가 없을 때에만 삭제 허용
        int result = 0;
        switch (categoryType) {
            case CHALLENGE:
                result = categoryMapper.deleteChallengeCategory(categoryCode);
                break;
            case STORE:
                result = categoryMapper.deleteStoreCategory(categoryCode);
                break;
        }
    }

}
