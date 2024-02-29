package ac.daejeon.app.dao;


import ac.daejeon.app.vo.ConfigVo;
import ac.daejeon.app.vo.FacilityVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacilityDao {

    private final SqlSession sqlSession;


    //편의시설 가져오기
    public List<FacilityVo> getConvenienceFacilityList(FacilityVo facilityVo) {

        return sqlSession.selectList("facility.getConvenienceFacilityList", facilityVo);
    }

}
