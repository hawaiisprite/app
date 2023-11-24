package ac.daejeon.app.common;

import org.springframework.web.multipart.MultipartFile;

public class Methods {


    //html 제거
    public String stripHtml() {
        return "";
    }

    //스크립트 제거
    public String stripScript() {
        return "";
    }


    //문자열중 숫자만 해당하는지 확인
    public Boolean isNumber(String str) {
        return false;
    }

    //이메일에 해당하는지 확인
    public Boolean isEmail(String str) {
        return false;
    }

    //유효한 패스워드 인지 확인
    public Boolean isValidPassword(String str) {
        return false;
    }

    //유효한 아이디 인지 확인
    public Boolean isValidId(String str) {
        return false;
    }

    //파일 사이즈가 넘었는지 확인
    public Boolean isMaximumFileSize(MultipartFile file) {
        return false;
    }

    //파일 종류가 사진인지 확인
    public Boolean isImg(String file) {
        return false;
    }


}
