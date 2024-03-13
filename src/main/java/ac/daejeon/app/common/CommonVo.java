package ac.daejeon.app.common;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
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

    //어디에서 온 파일인지 확인
    private String fileFromType;

    //대외문서 idx
    private int filesIdx;

    //어드민 아이디
    private String adminId;

    //관리자에 적용된 메뉴 문자열 리스트 , 로 구분
    private String adminMenuListStr;

    //관리자 타입
    private String adminType;

}
