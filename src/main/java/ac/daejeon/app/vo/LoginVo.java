package ac.daejeon.app.vo;


import lombok.Data;

@Data
public class LoginVo {

    //멤버 idx
    private int memberIdx;

    //아이디
    private String studentId;

    //이메일
    private String studentEmail;

    //학생 idx
    private int StudentIdx;

    //비밀번호
    private String studentPassword;

    private Boolean isSamePassword = false;


}
