package sisosolsol.greenfire.image.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sisosolsol.greenfire.image.model.dto.ImageUploadDTO;

@Mapper
public interface ImageMapper {
    void savePostImage(@Param("postCode") Integer code, @Param("image") ImageUploadDTO image);

    void saveStoreImage(@Param("storeCode") Integer code, @Param("image") ImageUploadDTO image);

    void deleteAllInPost(Integer postCode);
}
