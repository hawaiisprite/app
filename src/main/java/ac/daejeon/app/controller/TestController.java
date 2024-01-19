package ac.daejeon.app.controller;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.SecretKey;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@Controller
public class TestController {

    //문자열 리턴
    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, path = "/test1")
    public String test1(Model model) throws InterruptedException {


        String accessSecretKey = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";

        String accessSecretKey2 = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566E";

        String accessToken = Jwts.builder()
                .claim("userName", "ddd")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1))
                .signWith(SignatureAlgorithm.HS256, accessSecretKey)
                .compact();

        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecretKey));


        Thread.sleep(2000);


        try {
            Jwt<?, ?> decodeJwt = Jwts.parser()
                    //.requireIssuer("https://issuer.example.com")
                    .verifyWith(secret).build().parse(accessToken);



            System.out.println("이게 뭐죠 " + decodeJwt);
        } /*catch (Exception err) {

            System.out.println("에러임 " + err);
            System.out.println("에러임 " + err.getLocalizedMessage());
            System.out.println("에러임 " + err.getMessage());
            System.out.println("에러임 " + err.getCause());
            //System.out.println("에러임 " + Arrays.toString(err.getStackTrace()));


        } */catch (SignatureException | MalformedJwtException e) { //서명 오류 or JWT 구조 문제
           // ((HttpServletResponse) response).sendError(401, "SignatureException error");


            System.out.println("변조됨 " + e);

            //System.out.println("변조됨 " + e.);

        } catch (ExpiredJwtException e) {//유효 기간이 지난 JWT를 수신한 경우


            Claims a = e.getClaims();

            String userName = (String) a.get("userNamee");

            System.out.println("유저네임 가져옴?? " + userName);

            System.out.println("이부분 확인 " + e.getLocalizedMessage());
            System.out.println("이부분 확인 " + e.getHeader());
            System.out.println("이부분 확인 " + e.getMessage());
            System.out.println("이부분 확인 " + e.getClaims());
            System.out.println("이부분 확인 " + e.getStackTrace());

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();

            //System.out.println("이부분 확인 " + exceptionAsString);

            //((HttpServletResponse) response).sendError(401, "ExpiredJwtException error");
        } /*catch(Exception e) {
            SecurityContextHolder.clearContext();
        }
*/







        //fcmNotificationService.sendNotificationByToken();

        return "테스트중 " + accessToken;
    }

}
