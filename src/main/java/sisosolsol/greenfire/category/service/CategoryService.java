package sisosolsol.greenfire.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.category.model.dao.CategoryMapper;
import sisosolsol.greenfire.category.model.dto.CategoryCreateDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public void registCategory(CategoryCreateDTO category) {
        switch (category.getCategoryType()) {
            case CHALLENGE :
                categoryMapper.registChallengeCategory(category); break;
            case STORE:
                categoryMapper.registStoreCategory(category); break;
        }
    }
}
