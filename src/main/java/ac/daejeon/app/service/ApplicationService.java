package ac.daejeon.app.service;


import ac.daejeon.app.dao.ApplicationDao;
import ac.daejeon.app.vo.ApplicationVo;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationDao applicationDao;

    //신청서 상세 리스트
    public List<ApplicationVo> getApplicationDetailList(ApplicationVo applicationVo) {

        return applicationDao.getApplicationDetailList(applicationVo);
    }
}
