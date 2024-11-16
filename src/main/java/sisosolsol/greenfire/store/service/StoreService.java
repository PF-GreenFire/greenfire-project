package sisosolsol.greenfire.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.common.enums.image.ImageType;
import sisosolsol.greenfire.common.page.Pagination;
import sisosolsol.greenfire.common.page.SelectCriteria;
import sisosolsol.greenfire.image.model.dto.ImageUploadDTO;
import sisosolsol.greenfire.image.service.ImageService;
import sisosolsol.greenfire.location.model.dao.LocationMapper;
import sisosolsol.greenfire.location.model.dto.LocationDTO;
import sisosolsol.greenfire.location.service.LocationService;
import sisosolsol.greenfire.store.model.dao.StoreMapper;
import sisosolsol.greenfire.store.model.dto.StoreCreateDTO;
import sisosolsol.greenfire.store.model.dto.StoreListByStoreStatusDTO;
import sisosolsol.greenfire.store.model.dto.StoreListDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;
    private final LocationMapper locationMapper;
    private final LocationService locationService;
    private final ImageService imageService;

    // 초록불 메인 장소 목록 조회 TODO: 현재 위치 정보를 기반으로 반경 지도 목록을 보여주는 것으로 수정 예정
    public List<StoreListDTO> getStoreList() {
        return storeMapper.findStoreList();
    }

    // 관리자 초록불 장소 상태에 따른 목록 페이징 조회 [신청 대기, 신청 승인]
    public Map<String, Object> getStoreListByStoreStatus(String storeStatus, int page, int limit) {
        int totalCount = storeMapper.countStoresByStoreStatus(storeStatus); // `WAITING`과 'APPROVE' 등 스토어 상태에 따른 총 개수를 조회

        int buttonAmount = 5; // 페이지 하단에 보일 버튼 수
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount, limit, buttonAmount);

        List<StoreListByStoreStatusDTO> storeList = storeMapper.findStoreListByStoreStatus(selectCriteria, storeStatus);

        Map<String, Object> storeListResponse = new HashMap<>();
        storeListResponse.put("paging", selectCriteria);
        storeListResponse.put("storeList", storeList);

        return storeListResponse;
    }

    // 초록불 장소 신청 등록
    @Transactional
    public int registApplyStore(StoreCreateDTO storeCreateDTO) {
        LocationDTO location = storeCreateDTO.getLocation();
        int locationCode = locationMapper.findLocationByCoordinates(location.getLatitude(), location.getLongitude()); // 장소 신청 등록시 지역 정보 중복 방지용 조회
        System.out.println("찾은 locationCode: " + locationCode);


        if(locationCode == 0) {
            locationService.registLocation(location); // 중복 방지용 조회 값이 없을때 지역 정보 등록
            locationCode = locationMapper.findLocationByCoordinates(location.getLatitude(), location.getLongitude()); //등록된 최신 지역 코드 조회
            System.out.println("새로 생성된 locationCode: " + locationCode);
        }

        System.out.println("스토어 등록 전 전달할 locationCode: " + locationCode);
        storeMapper.registApplyStore(storeCreateDTO, locationCode); // 장소 신청 등록
        System.out.println("스토어 등록시 전달된 locationCode: " + locationCode);


        if (storeCreateDTO.getImages() != null) { // 이미지 파일 있을때 만 이미지 등록 TODO: fileName incoding 적용 예정 [현재 fileName 제외 등록]
            for (ImageUploadDTO image : storeCreateDTO.getImages()) {
                System.out.println("등록된 storeCode : " + storeCreateDTO.getStoreCode());
                imageService.saveImage(ImageType.STORE, storeCreateDTO.getStoreCode(), image);
            }
        }

        return storeCreateDTO.getStoreCode();
    }

}
