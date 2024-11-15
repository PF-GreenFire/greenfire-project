package sisosolsol.greenfire.image.model.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImageUploadDTO {
    private Integer imageCode;
    private String path;
    private String originName;
    private String fileName;
}
