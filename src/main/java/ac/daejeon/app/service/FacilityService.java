package ac.daejeon.app.service;


import ac.daejeon.app.dao.FacilityDao;
import ac.daejeon.app.vo.FacilityVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityDao facilityDao;

    public List<FacilityVo> getConvenienceFacilityList(FacilityVo facilityVo) {

        return facilityDao.getConvenienceFacilityList(facilityVo);
    }
}
