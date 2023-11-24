package ac.daejeon.app.dao;


import ac.daejeon.app.vo.SupportProgramVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SupportProgramDao {

    private final SqlSession sqlSession;


    //동영상 리스트
    public List<SupportProgramVo> getVideoList(SupportProgramVo supportProgramVo) {

        return sqlSession.selectList("supportProgram.getVideoList", supportProgramVo);
    }

    //동영상 생성
    public int createVideo(SupportProgramVo supportProgramVo) {

        return sqlSession.insert("supportProgram.createVideo", supportProgramVo);
    }


    //동영상 수정
    public int modifyVideo(SupportProgramVo supportProgramVo) {

        return sqlSession.update("supportProgram.modifyVideo", supportProgramVo);
    }


    //각종 지원 프로그램 리스트
    public List<SupportProgramVo> getApplicationList(SupportProgramVo supportProgramVo) {

        return sqlSession.selectList("supportProgram.getApplicationList", supportProgramVo);
    }

    //각종 지원 프로그램 생성
    public int createApplication(SupportProgramVo supportProgramVo) {

        return sqlSession.insert("supportProgram.createApplication", supportProgramVo);
    }

    //각종 지원 프로그램 수정
    public int modifyApplication(SupportProgramVo supportProgramVo) {

        return sqlSession.update("supportProgram.modifyApplication", supportProgramVo);
    }

}
