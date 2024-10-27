package sisosolsol.greenfire.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisosolsol.greenfire.category.model.dto.CategoryCreateDTO;
import sisosolsol.greenfire.category.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody CategoryCreateDTO category) {
        categoryService.registCategory(category);
        return ResponseEntity.ok().build();
    }
}
