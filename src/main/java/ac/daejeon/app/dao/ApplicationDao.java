package ac.daejeon.app.dao;


import ac.daejeon.app.vo.ApplicationVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationDao {

    private final SqlSession sqlSession;

    //신청서 상세리스트
    public List<ApplicationVo> getApplicationDetailList(ApplicationVo applicationVo) {

        return sqlSession.selectOne("application.getApplicationDetailList", applicationVo);
    }
}
