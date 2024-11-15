package sisosolsol.greenfire.store.model.dto;

import lombok.Getter;
import sisosolsol.greenfire.common.enums.store.StoreStatus;

import java.time.OffsetDateTime;

@Getter
public class StoreApplyListDTO {

    // 신청 장소 목록 조회용 DTO
    private Integer storeCode;
    private OffsetDateTime createdAt;
    private String storeName;
    private StoreStatus storeStatus;
    private String areaName;

}
