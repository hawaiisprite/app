package ac.daejeon.app.aspectAndInterceptor;

import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.JsonObject;
import groovy.util.logging.Slf4j;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class AppInterceptor implements HandlerInterceptor {

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {

        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        CharSequence[] onlyLoginMemberAccessList =  {"sendInfoPassport", "getInfoPassport", "modifyPersonalInfo", "getPersonalInfo", "applicationSupportProgram",
                                                        "getSupportProgramDetail", "getVideoList", "getClassSectionList", "getTodayAttendance", "setFcmToken"};
        boolean isOnlyLoginMemberAccessList = StringUtils.containsAny(requestURI, onlyLoginMemberAccessList);

        if(isOnlyLoginMemberAccessList) {

            String accessToken = request.getHeader("accessToken");
            System.out.println("엑세스 토큰 " + accessToken);

            if (accessToken != null && !accessToken.equals("")) {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseToken firebaseUser = null;
                try {
                    firebaseUser = firebaseAuth.verifyIdToken(accessToken);
                    if(firebaseUser == null) {
                        responseGetWriter(response, "notAuthorized");
                        return false;
                    }

                    session.setAttribute("M_STUDENT_EMAIL", firebaseUser.getEmail());

                    request.setAttribute("firebase_user_email", firebaseUser.getEmail());
                } catch (FirebaseAuthException e) {

                    //https://firebase.google.com/docs/reference/admin/error-handling error code list
                    if(e.getAuthErrorCode() == AuthErrorCode.EXPIRED_ID_TOKEN) {
                        responseGetWriter(response, "expired");
                    } else {
                        responseGetWriter(response, "notAuthorized");
                    }
                    return false;
                }

            } else {
                responseGetWriter(response, "notAuthorized");
                return false;
                //responseGetWriter(response);
            }

        }

        return true;
    }

    private void responseGetWriter(HttpServletResponse response, String res) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("result", res);

        response.getWriter().write(jsonObj.toString());
    }


}
