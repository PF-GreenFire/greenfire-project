package sisosolsol.greenfire.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisosolsol.greenfire.store.model.dto.StoreCreateDTO;
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

    // 관리자 초록불 장소 상태에 따른 목록 페이징 조회 [신청 대기, 신청 승인]
    // TODO: 추후 관리자만 목록 조회 할수 있겠금 권한 체크 예정 / 현재 데이터 3개 조회 되므로 limit 2로 임시 지정 -> 추후 수정 예정/ 추후 신청 거절건에 관해 조회 할 수도 있어서 결정 되면, WAITING, APPROVE 외의 값에 대한 예외 처리 예정
    @GetMapping("/{storeStatus}/list")
    public ResponseEntity<Map<String, Object>> getStoreListByStoreStatus(
            @PathVariable String storeStatus,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int limit
    ) {
        storeStatus = storeStatus.toUpperCase(); // 소문자로 들어왔을 경우, 대문자 변환
        Map<String, Object> storeList = storeService.getStoreListByStoreStatus(storeStatus, page, limit);
        return ResponseEntity.ok(storeList);
    }

    // 초록불 장소 신청 등록 TODO: service 단 예외 처리 , 예워니 handler 설정 적용 or enum 타입 관리용 유효성 검사 적용, 아.. 썸네일... 필요할 듯...ㅠㅠ 힝, 이미지 등록 적용 완료 했으나 인코딩 적용 예정
    @PostMapping("/apply")
    public ResponseEntity<String> createApplyStore(@RequestBody StoreCreateDTO storeCreateDTO){
        int storeCode = storeService.registApplyStore(storeCreateDTO);
        return ResponseEntity.created(null).body("success");
    }

}
