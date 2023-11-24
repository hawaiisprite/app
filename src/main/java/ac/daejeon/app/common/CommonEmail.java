package ac.daejeon.app.common;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CommonEmail {

    private final JavaMailSender javaMailSender;

    //공통 이메일 코드 보내기
    public void sendAuthCodeEmail(String authCode, String email) {

        String content = "제목<br>";
        content += "인증코드입니다<br><br>";
        content += "<h3>" + authCode + "</h3>";

        MimeMessage message  = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null; // 2번째 인자는 Multipart여부 결정
        try {
            messageHelper = new MimeMessageHelper(message, false, "UTF-8");
            messageHelper.setFrom("hawaiisprite@gmail.com");
            messageHelper.setTo(email);

            messageHelper.setSubject("입학지원 회원 가입 인증 코드 입니다");
            messageHelper.setText(content, true); // 2번째 인자는 HTML여부 결정

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("오류 이유?? " + e);
        }

    }


    //아이디와 이메일 인증후
    public void sendJoinAppAuthCodeEmail(String authCode, String email) {

        String content = "제목<br>";
        content += "인증코드입니다<br><br>";
        content += "<h3>" + authCode + "</h3>";

        MimeMessage message  = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null; // 2번째 인자는 Multipart여부 결정
        try {
            messageHelper = new MimeMessageHelper(message, false, "UTF-8");
            messageHelper.setFrom("hawaiisprite@gmail.com");
            messageHelper.setTo(email);

            messageHelper.setSubject("앱에 학사 정보를 연동할 수 있는 인증 코드 입니다.");
            messageHelper.setText(content, true); // 2번째 인자는 HTML여부 결정

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("오류 이유?? " + e);
        }

    }


    public String generateRandomAuthCode() {

        Random rnd =new Random();

        StringBuffer buf =new StringBuffer();

        for(int i=0;i<10;i++){
            if(rnd.nextBoolean()){
                buf.append((char)((int)(rnd.nextInt(26))+97));
            }else{
                buf.append((rnd.nextInt(10)));
            }
        }

        return buf.toString();

    }


}
