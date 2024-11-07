package sisosolsol.greenfire.post.model.dto;

import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.post.model.type.PostType;

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
}
