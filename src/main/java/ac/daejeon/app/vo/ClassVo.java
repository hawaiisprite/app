package ac.daejeon.app.vo;


import lombok.Data;

@Data
public class ClassVo {

    //학급 타이틀
    private int classIdx;

    //학생 idx
    private int studentIdx;

    //학급 타이틀
    private String classTitle;

    //학급 평가 타이틀
    private String evaluateClassTitle;

    private int evaluateClassYear;

    private int evaluateClassSemester;

    private int evaluateClassCapacity;

    private String evaluateContent;

    private int standardYear;

    private int standardSemester;

    private int isEvaluatedClass;

}


