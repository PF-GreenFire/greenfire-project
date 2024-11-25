package sisosolsol.greenfire.like.model.dto;

import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.like.LikeType;

import java.util.UUID;

@Getter
@ToString
public class LikeDTO {
    private Integer likeCode;
    private LikeType type;
    private Integer targetCode;
    private UUID userCode;
}
