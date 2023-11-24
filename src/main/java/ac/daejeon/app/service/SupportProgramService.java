package ac.daejeon.app.service;

import ac.daejeon.app.dao.SupportProgramDao;
import ac.daejeon.app.vo.SupportProgramVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SupportProgramService {

    private final SupportProgramDao supportProgramDao;
    private final PasswordEncoder passwordEncoder;



    //동영상 리스트
    public List<SupportProgramVo> getVideoList(SupportProgramVo supportProgramVo) {
        return supportProgramDao.getVideoList(supportProgramVo);
    }


    //동영상 생성
    public int createVideo(SupportProgramVo supportProgramVo) {
        return supportProgramDao.createVideo(supportProgramVo);
    }

    //동영상 수정
    public int modifyVideo(SupportProgramVo supportProgramVo) {
        return supportProgramDao.modifyVideo(supportProgramVo);
    }


    //각종 지원 프로그램 리스트
    public List<SupportProgramVo> getApplicationList(SupportProgramVo supportProgramVo) {
        return supportProgramDao.getApplicationList(supportProgramVo);
    }

    //각종 지원 프로그램 생성
    public int createApplication(SupportProgramVo supportProgramVo) {
        return supportProgramDao.createApplication(supportProgramVo);
    }

    //각종 지원 프로그램 수정
    public int modifyApplication(SupportProgramVo supportProgramVo) {
        return supportProgramDao.modifyApplication(supportProgramVo);
    }






}
