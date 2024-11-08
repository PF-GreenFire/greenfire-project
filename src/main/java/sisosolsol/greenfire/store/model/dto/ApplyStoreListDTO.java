package sisosolsol.greenfire.store.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sisosolsol.greenfire.common.enums.StoreStatus;

import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
public class ApplyStoreListDTO {

    //신청 장소 목록 조회용 DTO
    private Integer storeCode;
    private OffsetDateTime createdAt;
    private String storeName;
    private StoreStatus storeStatus;
    private String areaName;

}
