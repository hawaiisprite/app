package ac.daejeon.app.dao;

import ac.daejeon.app.vo.AppVo;
import ac.daejeon.app.vo.LoginVo;
import ac.daejeon.app.vo.SupportProgramVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppDao {

    private final SqlSession sqlSession;


    public int checkEmailAndStudentId(AppVo appVo) {
        //return 1;
        return sqlSession.selectOne("app.checkEmailAndStudentId", appVo);
    }


    public int createAppJoinAuthCode(AppVo appVo) {

        return sqlSession.insert("app.createAppJoinAuthCode", appVo);
    }

    public AppVo checkAppJoinAuthCode(AppVo appVo) {
        return sqlSession.selectOne("app.checkAppJoinAuthCode", appVo);
    }


    //패스워드 설정
    public int setStudentPassword(AppVo appVo) {

        return sqlSession.update("app.setStudentPassword", appVo);
    }

    //여권사진 저장
    public int createPassportFile(AppVo appVo) {

        return sqlSession.insert("app.createPassportFile", appVo);
    }


    //여권사진 가져오기
    public AppVo getInfoPassport(AppVo appVo) {

        return sqlSession.selectOne("app.getInfoPassport", appVo);
    }


    public int modifyPersonalInfo(AppVo appVo) {

        return sqlSession.update("app.modifyPersonalInfo", appVo);
    }


    //개인정보 가져오기
    public AppVo getPersonalInfo(AppVo appVo) {

        return sqlSession.selectOne("app.getPersonalInfo", appVo);
    }

    //지원프로그램 신청
    public int applicationSupportProgram(AppVo appVo) {

        return sqlSession.insert("app.applicationSupportProgram", appVo);
    }


    //지원프로그램 신청되어있는지 확인
    public int isAppliedSupportProgram(AppVo appVo) {

        return sqlSession.selectOne("app.isAppliedSupportProgram", appVo);
    }


    //지원 프로그램 가져오기
    public AppVo getApplicationDetail(AppVo appVo) {
        return sqlSession.selectOne("app.getApplicationDetail", appVo);
    }


    public AppVo getVideoDetail(AppVo appVo) {
        return sqlSession.selectOne("app.getVideoDetail", appVo);
    }


    public AppVo getVideoWatched(AppVo appVo) {
        return sqlSession.selectOne("app.getVideoWatched", appVo);
    }

    public int sendViewingTime(AppVo appVo) {

        int isExist = sqlSession.selectOne("app.isExistVideoWatched", appVo);

        System.out.println("확인11");

        if(isExist == 0) {
            System.out.println("확인22");
            sqlSession.insert("app.createVideoWatched", appVo);
        } else {

            AppVo videoWatchedInfo = sqlSession.selectOne("app.getVideoWatched", appVo);

            int watchedTime = videoWatchedInfo.getViewingTime();
            int viewingTime = appVo.getViewingTime();

            appVo.setViewingTime(watchedTime + viewingTime);
            sqlSession.update("app.modifyVideoWatched", appVo);

            System.out.println("확인33");

        }

        return 1;
    }

    //학생정보 가져오기
    public AppVo getStudentInfo(AppVo appVo) {
        return sqlSession.selectOne("app.getStudentInfo", appVo);
    }

    public List<AppVo> getClassSectionList(AppVo appVo) {

        //System.out.println("확인 " + appVo.getStudentEmail());

        return sqlSession.selectList("app.getClassSectionList", appVo);
    }


    public int getTodayAttendance(AppVo appVo) {
        return sqlSession.selectOne("app.getTodayAttendance", appVo);
    }

    //fcm 토큰 있는지 확인
    public AppVo isExistFcmToken(AppVo appVo) {
        return sqlSession.selectOne("app.isExistFcmToken", appVo);
    }


    //fcm 토큰 설정
    public int setFcmToken(AppVo appVo) {
        return sqlSession.insert("app.setFcmToken", appVo);
    }

    //fcm 토큰 수정
    public int modifyFcmToken(AppVo appVo) {
        return sqlSession.update("app.modifyFcmToken", appVo);
    }


    public List<AppVo> getVideoList(AppVo appVo) {

        return sqlSession.selectList("app.getVideoList", appVo);
    }

    public LoginVo doLogin(LoginVo loginVo) {

        return sqlSession.selectOne("login.getMemberInfo", loginVo);
    }

    public List<AppVo> getVideoPercentageList(AppVo appVo) {

        return sqlSession.selectList("app.getVideoPercentageList", appVo);
    }


    public List<AppVo> getNoticeList(AppVo appVo) {

        return sqlSession.selectList("app.getNoticeList", appVo);
    }

    public List<AppVo> getNoticeListForMain(AppVo appVo) {

        return sqlSession.selectList("app.getNoticeListForMain", appVo);
    }

    public List<SupportProgramVo> getCounselingList(SupportProgramVo supportProgramVo) {

        return sqlSession.selectList("app.getCounselingList", supportProgramVo);
    }

    public int cancelCounseling(SupportProgramVo supportProgramVo) {

        return sqlSession.update("app.cancelCounseling", supportProgramVo);
    }

    public int doCounseling(SupportProgramVo supportProgramVo) {


        return sqlSession.insert("app.applicantCounseling", supportProgramVo);
    }
}
