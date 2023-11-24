package ac.daejeon.app.common;

import lombok.Data;

@Data
public class CommonVo {

    //대외문서 파일 uuid
    private String fileUuid;

    //대외문서 파일 ext
    private String fileExt;

    //대외문서 파일 원래 이픔
    private String fileOrigName;

    //대외문서 파일 저장 연도
    private int fileYear;

    //대외문서 파일 저장 월
    private int fileMonth;

    //대외문서 파일 저장 일
    private int fileDay;

    //대외문서 idx
    private int filesIdx;

}
