package ac.daejeon.app.dao;


import ac.daejeon.app.vo.AppVo;
import ac.daejeon.app.vo.ClassVo;
import ac.daejeon.app.vo.FacilityVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassDao {

    private final SqlSession sqlSession;


    //평가 학급 리스트
    public List<ClassVo> getEvaluateClassList(ClassVo classVo) {

        return sqlSession.selectList("class.getEvaluateClassList", classVo);
    }
}
