package sisosolsol.greenfire.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.category.model.dao.CategoryMapper;
import sisosolsol.greenfire.category.model.dto.CategoryCreateDTO;
import sisosolsol.greenfire.category.model.dto.CategoryUpdateDTO;
import sisosolsol.greenfire.category.model.type.CategoryType;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

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
}
