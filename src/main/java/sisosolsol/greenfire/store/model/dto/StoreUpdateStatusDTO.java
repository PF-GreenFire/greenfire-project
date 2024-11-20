package sisosolsol.greenfire.store.model.dto;

import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.store.StoreStatus;

@Getter
@ToString
public class StoreUpdateStatusDTO {
    private StoreStatus status;
}
