package ac.daejeon.app.controller;

import ac.daejeon.app.service.AppService;
import ac.daejeon.app.service.FCMNotificationService;
import ac.daejeon.app.service.SupportProgramService;
import ac.daejeon.app.vo.AppVo;
import ac.daejeon.app.vo.SupportProgramVo;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping(path = "/app")
@Controller
public class AppController {

    private final AppService appService;
    private final SupportProgramService supportProgramService;



    private final FCMNotificationService fcmNotificationService;

    //동영상 테스트
    @RequestMapping(method = {RequestMethod.GET}, path = "/videoTest")
    public String videoTest(Model model) {


        return "app/videoTest";
    }


    //동영상 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "/videoDetail/{videoIdx}")
    public String videoDetail(HttpServletRequest httpServletRequest, Model model, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo) {

        //String accessToken = httpServletRequest.getHeader("accessToken");
        //System.out.println("억세스 토큰입니당~" + appVo.getAccessToken());
        /*String res = Statics.checkFirebaseToken(appVo.getAccessToken());

        if(!res.equals("")) {
            httpServletRequest.getSession().setAttribute("M_STUDENT_EMAIL", res);
        }*/

        //System.out.println("억세스 토큰 " + res);

        AppVo videoDetail = appService.getVideoDetail(appVo);

        model.addAttribute("videoDetailJson", gson.toJson(videoDetail));
        //SupportProgramVo videoInfo = supportProgramService.getVideoList(supportProgramVo).get(0);

        return "app/videoDetail";
    }






}
