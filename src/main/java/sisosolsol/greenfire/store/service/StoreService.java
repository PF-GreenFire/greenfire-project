package sisosolsol.greenfire.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisosolsol.greenfire.store.model.dao.StoreMapper;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    public List<StoreListDTO> getStoreList() {
        return storeMapper.getStoreList();
    }
}
