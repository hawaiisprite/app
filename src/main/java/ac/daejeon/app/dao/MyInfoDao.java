package ac.daejeon.app.dao;


import ac.daejeon.app.vo.MyInfoVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyInfoDao {

    private final SqlSession sqlSession;

    public int submitPassportFile(MyInfoVo myInfoVo) {

        return sqlSession.insert("myInfo.submitPassportFile", myInfoVo);
    }

    public int submitProfilePhotoFile(MyInfoVo myInfoVo) {

        return sqlSession.insert("myInfo.submitProfilePhotoFile", myInfoVo);
    }
}
