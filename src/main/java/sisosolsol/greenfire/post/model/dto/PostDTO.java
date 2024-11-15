package sisosolsol.greenfire.post.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.post.PostType;
import sisosolsol.greenfire.image.model.dto.ImageDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class PostDTO {
    private Integer postCode;
    private UUID userCode;
    private String writer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private String postContent;
    private PostType postType;
    private Integer storeCode;
    private String storeName;
    private Integer challengeCode;
    private String challengeTitle;
    private Integer like;
    private List<ImageDTO> images;
}
