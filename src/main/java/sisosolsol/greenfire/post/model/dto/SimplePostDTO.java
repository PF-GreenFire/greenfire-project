package sisosolsol.greenfire.post.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.post.model.type.PostType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class SimplePostDTO {
    private Integer postCode;
    private UUID userCode;
    private String writer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private String thumbnail;
    private PostType postType;
}
