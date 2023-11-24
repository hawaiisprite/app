package ac.daejeon.app.common;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommonDao {

    private final SqlSession sqlSession;

    //파일등록
    public int saveFileInfo(CommonVo commonVo) {
        return sqlSession.insert("common.saveFileInfo", commonVo);
    }

}
