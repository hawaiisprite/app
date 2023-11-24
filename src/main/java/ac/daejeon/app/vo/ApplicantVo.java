package ac.daejeon.app.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ApplicantVo extends EntranceExamVo {

    //private int id;

    //private List<ApplicantVo> ApplicantVos;

    //applicant_information 테이블 idx
    private int applicantInformationIdx;

    //apply_member 테이블 idx
    private int applyMemberIdx;

    //apply_member 이메일
    private String applyMemberEmail;

    //apply_member 닉네임
    private String applyMemberNickname;

    //국적
    private String nationality;

    //여권번호
    private String passportNumber;

    // 여권발행일
    private String passportIssueDate;

    // 여권만료일
    private String passportExpirationDate;

    //지원자파일
    private MultipartFile applicantPhoto;
    private String applicantPhotoPath;
    private String applicantPhotoUuid;
    private String applicantPhotoExt;
    private String applicantPhotoOrigName;
    private String applicantPhotoYearMonthDay;
    private Boolean isChangeApplicantPhoto;


    // 지원자 사진의 경로나 데이터를 저장하는 필드
    //private String applicantPhotoPath;

    //한국어이름
    private String koreanName;

    //영어이름
    private String englishName;

    //생일
    private String birth;

    //성별
    private String gender;

    //외국인 등록번호
    private String foreignerRegNumber;

    //연락처
    private String phone;

    //보호자 연락처
    private String guardianPhone;

    //최종학력
    private String highLevelEdu;

    //전공
    private String major;

    //주소
    private String addr;

    //상세주소
    private String detailAddr;

    //시
    private String city;

    //군구
    private String state;

    //국가
    private String country;

    //우편번호
    private String zip;




    //TOPIK 성적 급(grade)
    private String koreanGrade;

    //TOPIK 성적 점(score)
    private String koreanScore;

    //기타언어
    private String otherLanguage;

    //한국어레벨 체크
    private int koreanLevelCheck;

    //지원동기
    private String motive;

    //가족 소개
    private String introduceFamily;

    //장래희망
    private String futurePlan;

    //특이사항
    private String etc;

    //픽업
    private int pickup;

    //기숙사
    private int dormitory;

    //생성인지 업데이트인지 확인 (create, modify)
    private String submitType;

    //여권(사본)
    private MultipartFile copyPassportPhoto;
    private String copyPassportPhotoPath;
    private String copyPassportPhotoUuid;
    private String copyPassportPhotoExt;
    private String copyPassportPhotoOrigName;
    private String copyPassportPhotoYearMonthDay;
    private Boolean isChangeCopyPassportPhoto;


    //여권사진
    private MultipartFile passportPhoto;
    private String passportPhotoPath;
    private String passportPhotoUuid;
    private String passportPhotoExt;
    private String passportPhotoOrigName;
    private String passportPhotoYearMonthDay;
    private Boolean isChangePassportPhoto;


    //호구부(중국인경우)
    private MultipartFile householdRegister;
    private String householdRegisterPath;
    private String householdRegisterUuid;
    private String householdRegisterExt;
    private String householdRegisterOrigName;
    private String householdRegisterYearMonthDay;
    private Boolean isChangeHouseholdRegister;

    //교육부 학력인증 (중국인경우)
    private MultipartFile higherEducationStudentInformation;
    private String higherEducationStudentInformationPath;
    private String higherEducationStudentInformationUuid;
    private String higherEducationStudentInformationExt;
    private String higherEducationStudentInformationOrigName;
    private String higherEducationStudentInformationYearMonthDay;
    private Boolean isChangeHigherEducationStudentInformation;

    //최종학력 졸업 증명서(고등학교 혹은 전문대학, 대학교 졸업증명)
    private MultipartFile finalGraduateCertificate;
    private String finalGraduateCertificatePath;
    private String finalGraduateCertificateUuid;
    private String finalGraduateCertificateExt;
    private String finalGraduateCertificateOrigName;
    private String finalGraduateCertificateYearMonthDay;
    private Boolean isChangeFinalGraduateCertificate;


    //잔고증명
    private MultipartFile balanceCertificate;
    private String balanceCertificatePath;
    private String balanceCertificateUuid;
    private String balanceCertificateExt;
    private String balanceCertificateOrigName;
    private String balanceCertificateYearMonthDay;
    private Boolean isChangeBalanceCertificate;


    //부모 재직증명 및 수입인증
    private MultipartFile parentsEmploymentAndIncomeCertificate;
    private String parentsEmploymentAndIncomeCertificatePath;
    private String parentsEmploymentAndIncomeCertificateUuid;
    private String parentsEmploymentAndIncomeCertificateExt;
    private String parentsEmploymentAndIncomeCertificateOrigName;
    private String parentsEmploymentAndIncomeCertificateYearMonthDay;
    private Boolean isChangeParentsEmploymentAndIncomeCertificate;

    //가족관계 증명서
    private MultipartFile familyRelationsCertificate;
    private String familyRelationsCertificatePath;
    private String familyRelationsCertificateUuid;
    private String familyRelationsCertificateExt;
    private String familyRelationsCertificateOrigName;
    private String familyRelationsCertificateYearMonthDay;
    private Boolean isChangeFamilyRelationsCertificate;

    //뒷 부분에 덜 작성 된 곳이 있는지 확인
    private Boolean isContinueNextLevel;

    //레벨 타입
    private String levelType;

    //심사 제출 상태
    private String applyingStatus;

    //심사 제출 거부 사유
    private String rejectMemo;

    //심사상태 idx
    private int applyingIdx;

    //apply_member 이메일 검색용
    private String searchEmail;

    //수정 타입
    private String applicantInfoChangeType;

    //장학금 코드
    private String scholarshipCode;
    //장학금명
    private String scholarshipName;
    //장학금 지급방식
    private String scholarshipPaymentMethod;
    //장학금 금액
    private String scholarshipAmount;
    //장학금 비고
    private String scholarshipEtc;







}
