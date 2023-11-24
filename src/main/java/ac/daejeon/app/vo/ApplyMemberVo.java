package ac.daejeon.app.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ApplyMemberVo {

    //apply_member 테이블 idx
    private int applyMemberIdx;

    //apply_member 이메일
    private String applyMemberEmail;

    //이메일
    private String email;

    //닉네임
    private String nickname;

    //이메일 인증 코드
    private String authCode;

    //email_auth_code 테이블 idx
    private int emailAuthCodeIdx;

    //비밀번호
    private String password;

    //로그인시 패스워드 일치 확인되면 true
    private boolean samePassword;

    //여권번호
    private String passportNumber;

    //프로필 사진
    private MultipartFile applicantPhotoPath;




}
