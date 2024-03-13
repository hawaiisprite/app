package ac.daejeon.app.vo;

import lombok.Data;

@Data
public class ApplicationVo {

    //학생 idx
    private int studentIdx;

    private String applicationType;

    private String applicationTitle;

    private String applicationContent;

    private String applicationRegDt;

    private String applicationModDt;

}