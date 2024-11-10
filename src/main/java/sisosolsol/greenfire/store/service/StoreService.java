package sisosolsol.greenfire.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisosolsol.greenfire.common.page.Pagination;
import sisosolsol.greenfire.common.page.SelectCriteria;
import sisosolsol.greenfire.store.model.dao.StoreMapper;
import sisosolsol.greenfire.store.model.dto.ApplyStoreListDTO;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    // 초록불 메인 장소 목록 조회 TODO: 현재 위치 정보를 기반으로 반경 지도 목록을 보여주는 것으로 수정 예정
    public List<StoreListDTO> getStoreList() {
        return storeMapper.findStoreList();
    }

    // 초록불 장소 신청 목록 조회
    public Map<String, Object> getApplyStoreList(int page, int limit) {
        int totalCount = storeMapper.countWaitingStores(); // `WAITING` 상태 총 개수를 조회하는 메서드

        int buttonAmount = 5; // 페이지 하단에 보일 버튼 수
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount, limit, buttonAmount);

        List<ApplyStoreListDTO> applyStoreList = storeMapper.findApplyStoreList(selectCriteria);

        Map<String, Object> applyStoreListResponse = new HashMap<>();
        applyStoreListResponse.put("paging", selectCriteria);
        applyStoreListResponse.put("applyStoreList", applyStoreList);

        return applyStoreListResponse;
    }

}
