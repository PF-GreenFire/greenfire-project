package sisosolsol.greenfire.location.model.dao;

import org.apache.ibatis.annotations.Mapper;
import sisosolsol.greenfire.location.model.dto.LocationDTO;

@Mapper
public interface LocationMapper {

    // 초록불 장소 신청 등록시 지역 정보 등록 전 데이터 조회 [중복 방지]
    int findLocationByCoordinates(Double latitude, Double longitude);

    //초록불 장소 신청 등록시 지역 정보 등록
    int registLocation(LocationDTO location);
}
