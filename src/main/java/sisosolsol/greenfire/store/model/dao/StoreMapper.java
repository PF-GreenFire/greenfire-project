package sisosolsol.greenfire.store.model.dao;

import org.apache.ibatis.annotations.Mapper;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<StoreListDTO> getStoreList();
}
