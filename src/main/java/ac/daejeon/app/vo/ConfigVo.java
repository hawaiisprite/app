package ac.daejeon.app.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ConfigVo {

    //관리자 테이블 인덱스
    private int adminIdx;

    //관리자 id
    private String adminId;

    //관리자 부서
    private String adminDepartment;

    //관리자 이름
    private String adminName;

    //관리자 이메일
    private String adminEmail;

    //관리자 연락처
    private String adminPhone;

    //관리자 비밀번호
    private String adminPassword;

    //마지막 하나만 들고오기
    boolean isOrderByDescLimit1 = false;

    //관리자 허용 메뉴 리스트 문자열 , 로 구분
    private String adminPermissionListStr;

    //관리자 허용 메뉴 리스트 문자열 , 로 구분
    private String[] adminPermissionListStrArr;

    //비밀번호 확인용
    private boolean isSamePassword = false;


    //강사 아이디
    private String professorId;

    //강사 넘버(아이디)
    private String professorNumber;

    //강사 비밀번호
    private String professorPassword;

    //강사 학생수
    private int professorStudentCount;

    //강사 테이블 idx
    private int professorIdx;

    //강사 idx 리스트
    private List<Integer> professorIdxList;

    //강사 프로필 사진
    private MultipartFile professorPhotoFile;

    //강사 프로필 사진 idx
    private int professorPhotoFileIdx;

    //대외문서 파일 uuid
    private String professorPhotoFileUuid;

    //대외문서 파일 ext
    private String professorPhotoFileExt;

    //대외문서 파일 원래 이픔
    private String professorPhotoFileOrigName;

    //대외문서 파일 저장 연도
    private int professorPhotoFileYear;

    //대외문서 파일 저장 월
    private int professorPhotoFileMonth;

    //대외문서 파일 저장 일
    private int professorPhotoFileDay;

    //대외문서 파일 바이너리
    private String professorPhotoFileBinary;

    //대외문서 파일 변경
    private boolean isProfessorPhotoChangeFile;

    //편의 시설 idx
    private int convenienceFacilityIdx;
    //편의 시설 타입
    private String convenienceFacilityType;
    //편의 시설 명
    private String convenienceFacilityTitle;
    //편의시설 플래그
    private String convenienceFacilityFlag;
    //편의시설 위도
    private Double convenienceFacilityLatitude;
    //편의시설 경도
    private Double convenienceFacilityLongitude;

    //편의시설 위도 경도 알아내기 위한 검색어
    private String convenienceFacilitySearchText;

    //그룹으로 묶을지
    private boolean isGroupBy = false;

    //where 절 특정
    private boolean isWhereType = false;

    //where 절 특정
    private boolean isWhereIdx = false;

}
