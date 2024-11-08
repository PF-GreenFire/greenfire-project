package sisosolsol.greenfire.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisosolsol.greenfire.store.model.dao.StoreMapper;
import sisosolsol.greenfire.store.model.dto.ApplyStoreListDTO;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    // 초록불 메인 장소 목록 조회 TODO: 현재 위치 정보를 기반으로 반경 지도 목록을 보여주는 것으로 수정 예정
    public List<StoreListDTO> getStoreList() {
        return storeMapper.findStoreList();
    }

    // 초록불 장소 신청 목록 조회
    public List<ApplyStoreListDTO> getApplyStoreList() {
        return  storeMapper.findApplyStoreList();
    }
}
