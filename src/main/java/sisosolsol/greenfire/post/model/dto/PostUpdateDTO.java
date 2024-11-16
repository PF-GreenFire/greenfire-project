package sisosolsol.greenfire.post.model.dto;

import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.post.PostType;
import sisosolsol.greenfire.image.model.dto.ImageUploadDTO;

import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class PostUpdateDTO {
    private Integer postCode;
    private String postContent;
    private PostType postType;
    private Integer storeCode;
    private Integer challengeCode;
    private String thumbnail;
    private UUID userCode;
    private List<ImageUploadDTO> images;
}
