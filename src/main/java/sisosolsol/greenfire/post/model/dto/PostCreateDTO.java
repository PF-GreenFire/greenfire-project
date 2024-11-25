package sisosolsol.greenfire.post.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sisosolsol.greenfire.image.model.dto.ImageUploadDTO;
import sisosolsol.greenfire.common.enums.post.PostType;

import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class PostCreateDTO {
    private Integer postCode;
    private String postContent;
    private PostType postType;
    private Integer storeCode;
    private Integer challengeCode;
    private String thumbnail;
    private UUID userCode;
    private List<ImageUploadDTO> images;
}
