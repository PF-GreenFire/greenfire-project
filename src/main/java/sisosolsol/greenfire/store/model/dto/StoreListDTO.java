package sisosolsol.greenfire.store.model.dto;

import lombok.Getter;
import sisosolsol.greenfire.common.enums.store.StoreFoodType;
import sisosolsol.greenfire.common.enums.store.StoreStatus;

import java.time.OffsetDateTime;

@Getter
public class StoreListDTO {

    // 초록불 메인 장소 목록 조회용 DTO
    private Integer storeCode;
    private OffsetDateTime createdAt;
    private String storeName;
    private StoreStatus storeStatus;
    private StoreFoodType storeFoodType;
    private String storeCategoryName;
    private String path;
    private String fileName;

}
