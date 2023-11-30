package ac.daejeon.app.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AppVo {

    //이메일
    private String studentEmail;
    //학생번호
    private String studentId;

    //학생 번호
    private int studentIdx;

    //앱조인 인증번호
    private String authCode;

    //저장할 비밀번호
    private String password;

    //파이어베이스 accessToken
    private String accessToken;


    //파이어베이스 fcmToken
    private String fcmToken;

    //파이어베이스 fcmToken 테이블 idx
    private int fcmTokenIdx;

    //모바일 os type
    private String mobileOsType;

    //학생이름
    private String studentName;

    //주소
    private String addr;

    //연락처
    private String phone;

    //파일을 변경 했는지
    private boolean isChangeFile;

    //여권 사진 파일
    private MultipartFile passportFile;
    private int passportFileIdx;
    private String passportFileUuid;
    private String passportFileExt;
    private String passportFileOrigName;
    private int passportFileYear;
    private int passportFileMonth;
    private int passportFileDay;
    private String passportFileBinary;


    //개인정보 사진 파일
    private MultipartFile personalInfoFile;
    private int personalInfoFileIdx;
    private String personalInfoFileUuid;
    private String personalInfoFileExt;
    private String personalInfoFileOrigName;
    private int personalInfoFileYear;
    private int personalInfoFileMonth;
    private int personalInfoFileDay;
    private String personalInfoFileBinary;


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


    //신청 지원 테이블 인덱스
    private int applyApplicationIdx;

    // 영상 테이블 인덱스
    private int videoIdx;

    //영상 제목
    private String videoName;
    //영상링크
    private String videoLink;
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

    //시청 시간
    private int viewingTime;

    //마지막 시청 커서포인트
    private float lastSeconds;

    //마지막 시청 커서포인트
    private boolean isCheckAccessTokenForMobile = false;


    private int classSectionYear;
    private String classSectionCourse;
    private String classSectionClass;
    private int classSectionIdx;


    private int year;
    private int month;
    private int day;






}
