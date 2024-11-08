package sisosolsol.greenfire.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sisosolsol.greenfire.store.model.dto.ApplyStoreListDTO;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;
import sisosolsol.greenfire.store.service.StoreService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

    private  final StoreService storeService;

    // 초록불 메인 장소 목록 조회 TODO: 현재 위치 정보를 기반으로 반경 지도 목록을 보여주는 것으로 수정 예정
    @GetMapping("/list")
    public ResponseEntity<List<StoreListDTO>> getStoreList() {
        List<StoreListDTO> storeList = storeService.getStoreList();
        return ResponseEntity.ok(storeList);
    }

    // 초록불 장소 신청 목록 조회 TODO: 추후 관리자만 목록 조회 할수 있겠금 권한 체크 예정 / 현재 데이터 3개 조회 되므로 limit 2로 임시 지정 -> 추후 수정 예정
    @GetMapping("/apply/list")
    public ResponseEntity<Map<String, Object>> getApplyStoreList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int limit
    ) {
        Map<String, Object> applyStoreList = storeService.getApplyStoreList(page, limit);
        return ResponseEntity.ok(applyStoreList);
    }

}
