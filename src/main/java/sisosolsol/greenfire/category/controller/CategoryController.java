package sisosolsol.greenfire.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisosolsol.greenfire.category.model.dto.CategoryCreateDTO;
import sisosolsol.greenfire.category.model.dto.CategoryDTO;
import sisosolsol.greenfire.category.model.dto.CategoryUpdateDTO;
import sisosolsol.greenfire.common.enums.category.CategoryType;
import sisosolsol.greenfire.category.service.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategoryList(@RequestParam CategoryType categoryType) {
        List<CategoryDTO> categoryList = categoryService.getCategoryList(categoryType);
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody CategoryCreateDTO category) {
        int categoryCode = categoryService.registCategory(category);
        return ResponseEntity.created(URI.create("/category/" + categoryCode)).build();
    }

    @PutMapping("/{categoryCode}")
    public ResponseEntity<CategoryUpdateDTO> modifyCategory(@PathVariable Integer categoryCode,
                                               @RequestBody CategoryUpdateDTO category) {

        categoryService.updateCategory(categoryCode, category);
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{categoryCode}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryCode,
                                               @RequestBody CategoryType categoryType) {
        categoryService.deleteCategory(categoryCode, categoryType);
        return ResponseEntity.noContent().build();
    }
}
