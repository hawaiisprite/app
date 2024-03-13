package ac.daejeon.app.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MyInfoVo {

    //학생정보 인덱스
    private int studentIdx;

    //학생 학번
    private String studentId;

    //학생 비밀번호
    private String StudentPassword;

    //학생 이메일
    private String studentEmail;

    //학생 학번
    private String studentStatus;

    //학생 한글이름
    private String studentKoName;

    //학생 영어이름
    private String studentEnName;

    //학생 성별
    private String studentGender;

    //학생 학과
    private String studentDepartment;

    //학생 학년
    private int studentGradeLevel;

    //학생 학기
    private int studentSemester;

    //학생 국적
    private String studentNationality;

    //학생 재학상태
    private String studentEnrollmentStatus;

    //학생 입학 타입
    private String admissionType;

    //학생 입학일
    private String studentAdmissionDate;

    //학생 졸업일
    private String studentGraduationDate;

    //학생 재학변동일
    private String studentEnrollmentChangeDate;

    //학생 보험만료일자
    private String studentInsuranceExpirationDate;

    //학생 여권번호
    private String studentPassportNumber;

    //학생 여권만료일자
    private String studentPassportExpirationDate;

    //학생 외국인등록번호
    private String studentForeignerRegistrationNo;

    //학생 비자 종류
    private String studentVisaType;

    //학생 체류만료일
    private String studentResidenceExpirationDate;

    //학생 연락처
    private String studentPhone;

    //학생 주소
    private String studentAddr;

    //학생 출신고교
    private String studentHighSchoolOfOrigin;

    //학생 고교졸업일
    private String studentHighSchoolGraduationDate;

    //학생 전적대학
    private String studentPreviousUniversity;

    //학생 최종학력졸업일
    private String studentFinalEducationGraduationDate;

    //학생 보호자
    private String studentGuardian;

    //학생 보호자 주소
    private String studentGuardianAddr;

    //학생 보호자 연락처
    private String studentGuardianPhone;

    //학생 비고
    private String studentEtc;

    //학생 민족
    private String studentEthnicGroup;

    //학생 나이
    private int studentAge;

    //학생 토픽 급수
    private String studentTopikGrade;

    //학생 토픽 만료일자
    private String studentTopikExpirationDate;

    //학생 테이블 생성일자
    private String studentRegDate;

    //학생 테이블 수정일자
    private String studentModDate;

    private String studentName;
    private String studentEngName;
    private boolean isOrderByDescLimit1;

    //학생 수정한 아이디
    private String studentModifiedId;

    //학생 수정한 과거 idx
    private int studentHistoryIdx;

    //학생 수정이력 타입
    private String studentHistoryType;

    //학생 입학구분
    private String studentAdmissionType;


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
    private Boolean isChangePersonalInfoFile;


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
    private Boolean isChangePassportFile;


    //외국인 등록증 파일
    private MultipartFile foreignerRegistrationFile;
    private int foreignerRegistrationFileIdx;
    private String foreignerRegistrationFileUuid;
    private String foreignerRegistrationFileExt;
    private String foreignerRegistrationFileOrigName;
    private int foreignerRegistrationFileYear;
    private int foreignerRegistrationFileMonth;
    private int foreignerRegistrationFileDay;
    private String foreignerRegistrationFileBinary;
    private Boolean isChangeForeignerRegistrationFile;


    //학급 idx
    private int classIdx;

    //학급 제목
    private String classTitle;

    //학급 연도
    private int classYear;

    //학급 학기
    private int classSemester;

    //학급 학생 정원수
    private int classCapacity;

    //학급 비고
    private String classEtc;

    //학급 학생 배정된인원수
    private int studentAssignClassCount;

    //출석 기준 날짜
    private String attendanceDate;

    //출석수
    private int attendanceCount;

    //미출석수
    private int absentCount;

    //출석 연도
    private int attendanceYear;

    //출석 월
    private int attendanceMonth;

    //출석 일
    private int attendanceDay;

    //출석 일
    private double attendancePercentage;

    //출석 등록일
    private String attendanceRegDate;

    //지각 카운트
    private int tardinessCount;

    //조퇴 카운트
    private int earlyDismissalCount;

    //출결현황 선택된 학급 idx 들
    private int[] selectedClassIdxIntList;

    //출석순 혹은 미출석순으로 정렬
    private String orderByAttendanceType;


    //검색어 텍스트
    private String searchText;

    //검색 조건
    private boolean searchTypeKoName = false;
    private boolean searchTypeEnName = false;
    private boolean searchTypeGender = false;
    private boolean searchTypeDepartment = false;
    private boolean searchTypeStudentId = false;
    private boolean searchTypeSemester = false;

    private boolean searchTypePassportNumber = false;
    private boolean searchTypeForeignerRegistrationNo = false;
    private boolean searchTypePhone = false;
    private boolean searchTypeEmail = false;

    //통계 카운트
    private int studentLanguageCenterCount;
    private int studentExchangeCount;
    private int studentGraduateCount;
    private int studentGksCount;
    private int studentUndergraduateCount;
    private int studentPhdCount;
    private int studentEtcCount;
    private int studentUnspecifiedCount;
    private int studentAdmissionEtcCount;
    private int studentAdmissionUnspecifiedCount;

    private int studentEnrolledCount;
    private int studentLeaveOfAbsenceCount;
    private int studentEnrolledEtcCount;
    private int studentEnrolledUnspecifiedCount;

    private String visaExpirationDate;


    private String visaExpirationDate7;
    private String visaExpirationDate15;
    private String visaExpirationDate30;
    private String visaExpirationDate90;
    private String visaExpirationDateMore90;
    private String visaExpirationYesterday;
    private String visaExpirationDateEmpty;


    private int visaExpirationCount7;
    private int visaExpirationCount15;
    private int visaExpirationCount30;
    private int visaExpirationCount90;
    private int visaExpirationCountMore90;
    private int visaExpirationCountYesterday;
    private int visaExpirationCountEmpty;

    //비자 만료 검색 할지 여부
    private boolean isSearchExpirationVisa = false;

    //검색할 비자 타입 7,15,30,90,90이상,미지정
    private String visaExpirationSearchType;

    //미지정 수
    private int studentEmptyCount;

    private String dbEncDecKey = "djac_c_isims";

}
