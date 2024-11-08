package sisosolsol.greenfire.store.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sisosolsol.greenfire.store.model.type.StoreFoodType;
import sisosolsol.greenfire.store.model.type.StoreStatus;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
public class StoreListDTO {

    private Integer storeCode;
    private OffsetDateTime createdAt;
    private String storeName;
    private StoreStatus storeStatus;
    private StoreFoodType storeFoodType;
    private String storeCategoryName;
    private String path;
    private String fileName;

}
