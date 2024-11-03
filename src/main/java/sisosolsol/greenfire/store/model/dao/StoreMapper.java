package sisosolsol.greenfire.store.model.dao;

import org.apache.ibatis.annotations.Mapper;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;

import java.util.List;

@Mapper
public interface StoreMapper {
    // 초록불 장소 목록 조회 TODO: 현재 위치 정보를 기반으로 반경 지도 목록을 보여주는 것으로 수정 예정
    List<StoreListDTO> getStoreList();
}
