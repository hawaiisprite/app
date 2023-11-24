package ac.daejeon.app.vo;

import lombok.Data;

import java.util.List;

@Data
public class EntranceExamVo {

    private int entranceYear;
    private String recruitmentCategory;
    private String recruitment;
    private int yearCategoryIdx;
    private int recruitmentCategoryIdx;

    private String interviewPlaceName;
    private String interviewPlaceEtc;
    private int interviewPlaceIdx;

    private String interviewerName;
    private String interviewerPhone;
    private String interviewerDepartment;
    private String interviewerPosition;
    private String interviewerEtc;
    private int interviewerIdx;


    private String helperName;
    private String helperPhone;
    private String helperDepartment;
    private String helperPosition;
    private String helperEtc;
    private int helperIdx;

    //셀렉트 마지막 부분 들고 오기
    private boolean isOrderByDescLimit1 = false;
    //마이바티스 where in for 문 실행
    private boolean isWhereIn = false;


    private String interviewerListIdxStr;


    private String helperListIdxStr;

    private String interviewTime;

    //면접고사장 비고
    private String interviewGroupEtc;

    private String[] interviewerListIdxStrSplit;
    private String[] helperListIdxStrSplit;

    //각 면접고사조 마다 면접관 리스트 가져오기
    private List<ApplicantVo> interviewerList;
    //각 면접고사조 마다 진행자 리스트 가져오기
    private List<ApplicantVo> helperList;

    private int interviewGroupIdx;

    private Boolean isSearchInterviewee = false;
    private String intervieweeListIdxStr;
    private String[] intervieweeListIdxStrSplit;

    private String score;
    private String bonusPoints;

    //합격자 오류 생겼는지 확인
    private String acceptedError;

    //장학금 코드 인덱스
    private int scholarshipCodeIdx;


    //장학금 코드 인덱스 리스트
    private List<Integer> scholarshipIdxList;

    //책정입학금
    private int admissionFee;
    //책정수업료
    private int tuitionFee;
    //책정등록금합계
    private int totalTuitionFees;

    //책정학생회비
    private int studentUnionFee;
    //책정학생회비 합계
    private int totalStudentUnionFees;

    //감면입학금
    private int discountedAdmissionFee;
    //감면수업료
    private int discountedTuitionFee;
    //감면금액합계
    private int totalDiscountAmount;

    //납부대상입학금
    private int admissionToBePaid;
    //납부대상수업료
    private int tuitionToBePaid;
    //납부대상등록금액합계
    private int totalTuitionToBePaid;

    //납부 입학금
    private int paidAdmissionFee;
    //납부수업료
    private int paidTuitionFee;
    //기납입예치금
    private int prepaidDeposit;
    //납부등록금액합계
    private int totalPaidTuitionFees;
    //납부학생회비
    private int paidStudentUnionFee;
    //납부학생경비합계
    private int totalPaidStudentExpenses;
    //납부총금액
    private int totalPaymentAmount;
    //등록 포기 상태
    private String registrationWithdrawn;

    //등록 포기 상태
    private Boolean isSearchRegistrationWithdrawn = false;


    //환불정보
    private String refund;


}
