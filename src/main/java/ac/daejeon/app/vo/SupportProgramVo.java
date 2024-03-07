package ac.daejeon.app.vo;

import lombok.Data;

@Data
public class SupportProgramVo {

    //범용 등록일
    private String reg_dt;
    //범용 수정일
    private String mod_dt;

    //영상 제목
    private String videoName;
    //영상링크
    private String videoLink;

    //영상정보
    private String videoInfo;
    //영상 분
    private int runningMinute;
    //영상 나머지 초 0~ 59까지
    private int runningSecond;

    //영상 모든초
    private int allSecond;

    //시청 시작일
    private String videoStartDay;
    //시청 종료일
    private String videoEndDay;

    //동영상 테이블 인덱스
    private int videoIdx;

    //마지막 하나만 들고오기
    boolean isOrderByDescLimit1 = false;

    //신청 테이블 인덱스
    private int applicationIdx;

    //신청 타입
    private String applicationType;

    //신청 프로그램명
    private String applicationName;

    //신청 인원
    private int applicationCount;

    //신청 시작일
    private String applicationStartDay;

    //신청 종료일
    private String applicationEndDay;


    private int standardYear;

    private int standardSemester;



    //상담 idx
    private int counselingIdx;

    //상담 신청했는지 확인
    private int isApplicantCounseling;

    //상담 제목
    private String counselingTitle;

    //상담사 이름
    private String counselingManager;

    //상담 인원
    private int counselingCapacity;

    //상담 시작일
    private String counselingStrDay;

    //상담 종료일
    private String counselingEndDay;

    //상담 내용
    private String counselingContent;

    //학생 인덱스
    private int studentIdx;

    //상담 내용
    private boolean isOverCounseling;


}
