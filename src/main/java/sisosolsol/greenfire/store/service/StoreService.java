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

    // 관리자 초록불 장소 상태에 따른 목록 페이징 조회 [신청 대기, 신청 승인]
    public Map<String, Object> getStoreListByStoreStatus(String storeStatus, int page, int limit) {
        int totalCount = storeMapper.countStoresByStoreStatus(storeStatus); // `WAITING`과 'APPROVE' 등 스토어 상태에 따른 총 개수를 조회

        int buttonAmount = 5; // 페이지 하단에 보일 버튼 수
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount, limit, buttonAmount);

        List<ApplyStoreListDTO> storeList = storeMapper.findStoreListByStoreStatus(selectCriteria, storeStatus);

        Map<String, Object> storeListResponse = new HashMap<>();
        storeListResponse.put("paging", selectCriteria);
        storeListResponse.put("storeList", storeList);

        return storeListResponse;
    }

}
