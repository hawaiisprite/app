package ac.daejeon.app.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {

        // 커스텀 에러 페이지의 경로를 반환
        return "common/error";
    }

    /*
        @Override
        public String getErrorPath() {
            return PATH;
        }
    */
}