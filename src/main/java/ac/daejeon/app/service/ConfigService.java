package ac.daejeon.app.service;

import ac.daejeon.app.dao.ConfigDao;
import ac.daejeon.app.vo.ConfigVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigDao configDao;
    private final PasswordEncoder passwordEncoder;

    //관리자 등록
    public int createAdmin(ConfigVo configVo) {

        String encodePassword = passwordEncoder.encode(configVo.getAdminPassword());
        configVo.setAdminPassword(encodePassword);

        return configDao.createAdmin(configVo);
    }

    //관리자 리스트
    public List<ConfigVo> getAdminList(ConfigVo configVo) {

        return configDao.getAdminList(configVo);
    }

    //권한부여 등록
    public int createPermission(ConfigVo configVo) {
        return configDao.createPermission(configVo);
    }


    //편의시설 리스트
    public List<ConfigVo> getConvenienceFacilityList(ConfigVo configVo) {

        return configDao.getConvenienceFacilityList(configVo);
    }

    //편의시설 작성
    public int createConvenienceFacility(ConfigVo configVo) {

        return configDao.createConvenienceFacility(configVo);
    }

    //편의시설 수정
    public int modifyConvenienceFacility(ConfigVo configVo) {
        return configDao.modifyConvenienceFacility(configVo);
    }



}
