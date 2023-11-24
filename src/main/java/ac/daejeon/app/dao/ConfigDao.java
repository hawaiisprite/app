package ac.daejeon.app.dao;


import ac.daejeon.app.vo.ConfigVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConfigDao {

    private final SqlSession sqlSession;

    //관리자 등록
    public int createAdmin(ConfigVo configVo) {

        return sqlSession.insert("config.createAdmin", configVo);
    }

    //관리자 가져오기
    public List<ConfigVo> getAdminList(ConfigVo configVo) {

        return sqlSession.selectList("config.getAdminList", configVo);
    }

    //권한부여 등록
    public int createPermission(ConfigVo configVo) {

        return sqlSession.update("config.modifyCreatePermission", configVo);
    }


    //편의시설 리스트
    public List<ConfigVo> getConvenienceFacilityList(ConfigVo configVo) {

        return sqlSession.selectList("config.getConvenienceFacilityList", configVo);
    }

    //편의시설 작성
    public int createConvenienceFacility(ConfigVo configVo) {
        return sqlSession.insert("config.createConvenienceFacility", configVo);
    }

    //편의시설 수정
    public int modifyConvenienceFacility(ConfigVo configVo) {
        return sqlSession.insert("config.modifyConvenienceFacility", configVo);
    }






}
