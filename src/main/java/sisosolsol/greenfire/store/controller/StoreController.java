package sisosolsol.greenfire.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;
import sisosolsol.greenfire.store.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

    private  final StoreService storeService;

    @GetMapping("/list")
    public ResponseEntity<List<StoreListDTO>> getStoreList() {
        List<StoreListDTO> storeList = storeService.getStoreList();
        return ResponseEntity.ok(storeList);

    }
}
