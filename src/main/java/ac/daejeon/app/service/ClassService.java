package ac.daejeon.app.service;


import ac.daejeon.app.dao.ClassDao;
import ac.daejeon.app.vo.AppVo;
import ac.daejeon.app.vo.ClassVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassDao classDao;



    public List<ClassVo> getEvaluateClassList(ClassVo classVo) {

        return classDao.getEvaluateClassList(classVo);
    }
}
