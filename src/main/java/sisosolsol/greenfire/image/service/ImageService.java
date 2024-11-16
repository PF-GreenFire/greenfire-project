package sisosolsol.greenfire.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.image.model.dao.ImageMapper;
import sisosolsol.greenfire.image.model.dto.ImageUploadDTO;
import sisosolsol.greenfire.common.enums.image.ImageType;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;

    public void saveImage(ImageType imageType, Integer code, ImageUploadDTO image) {
        switch(imageType) {
            case STORE: imageMapper.saveStoreImage(code, image);
                break;
            case POST: imageMapper.savePostImage(code, image); break;
        }
    }

    public void deleteAllInPost(Integer postCode) {
        imageMapper.deleteAllInPost(postCode);
    }
}
