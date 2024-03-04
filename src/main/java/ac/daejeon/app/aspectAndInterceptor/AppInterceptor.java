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


        System.out.println("앱 인터셉터 " + requestURI);
        //관리자 부분
        String studentId = (String) session.getAttribute("STUDENT_ID");

        System.out.println("studentId" + studentId);

        //로그인 된 학생만
        CharSequence[] onlyLoginMemberAccessList =  {"/videoList", "/videoDetail", "/campusList", "/facilities", "/myData", "/supportProgramList", "/emergencyList", "/applicationList"};
        boolean isOnlyLoginMemberAccessList = StringUtils.containsAny(requestURI, onlyLoginMemberAccessList);

        if(isOnlyLoginMemberAccessList) {

            if(studentId == null) {
                response.sendRedirect("/app/main");
                return false;
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
