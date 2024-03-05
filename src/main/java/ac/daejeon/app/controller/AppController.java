package ac.daejeon.app.controller;

import ac.daejeon.app.service.AppService;
import ac.daejeon.app.service.FCMNotificationService;
import ac.daejeon.app.service.FacilityService;
import ac.daejeon.app.service.SupportProgramService;
import ac.daejeon.app.vo.AppVo;
import ac.daejeon.app.vo.FacilityVo;
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
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/app/")
@Controller
public class AppController {

    private final AppService appService;
    private final SupportProgramService supportProgramService;

    private final FacilityService facilityService;



    private final FCMNotificationService fcmNotificationService;

    //동영상 테스트
    @RequestMapping(method = {RequestMethod.GET}, path = "videoTest")
    public String videoTest(HttpServletRequest httpServletRequest, Model model) {


        return "app/videoTest";
    }


    //동영상 리스트
    @RequestMapping(method = {RequestMethod.GET}, path = "videoList")
    public String videoList(HttpServletRequest httpServletRequest, Model model, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo) {

        LocalDate today = LocalDate.now();
        int nowYear = today.getYear();

        appVo.setStandardYear(nowYear);

        List<AppVo> videoListData = appService.getVideoList(appVo);

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);

        //System.out.println("비디오 리스트 " + videoListData);



        model.addAttribute("nowYear", nowYear);
        model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/videoList";
    }


    //동영상 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "videoDetail/{videoIdx}")
    public String videoDetail(HttpServletRequest httpServletRequest, Model model, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo) {

        //String accessToken = httpServletRequest.getHeader("accessToken");
        //System.out.println("억세스 토큰입니당~" + appVo.getAccessToken());
        /*String res = Statics.checkFirebaseToken(appVo.getAccessToken());

        if(!res.equals("")) {
            httpServletRequest.getSession().setAttribute("M_STUDENT_EMAIL", res);
        }*/

        //System.out.println("억세스 토큰 " + res);

        AppVo videoInfo = appService.getVideoDetail(appVo);

        model.addAttribute("videoInfo", videoInfo);
        //SupportProgramVo videoInfo = supportProgramService.getVideoList(supportProgramVo).get(0);

        return "app/videoDetail";
    }


    @RequestMapping(method = {RequestMethod.GET}, path = "videoDetail")
    public String videoDetail2(HttpServletRequest httpServletRequest, Model model, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo) {

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


    //동영상 리스트
    @RequestMapping(method = {RequestMethod.GET}, path = "campusList")
    public String campusList(HttpServletRequest httpServletRequest, Model model, Gson gson, SupportProgramVo supportProgramVo) {

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);
        //System.out.println("비디오 리스트 " + videoListData);
        //model.addAttribute("abc", "abcd다");\
        //model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/campusList";
    }


    //편의시설 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "facilities")
    public String facilities(HttpServletRequest httpServletRequest, Model model, Gson gson, FacilityVo facilityVo) {


        List<FacilityVo> convenienceFacilityList = facilityService.getConvenienceFacilityList(facilityVo);
        model.addAttribute("convenienceFacilityListJson", gson.toJson(convenienceFacilityList));

        //jsonObj.addProperty("data", gson.toJson(convenienceFacilityList));
        //jsonObj.addProperty("result", "success");

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);
        //System.out.println("비디오 리스트 " + videoListData);
        //model.addAttribute("abc", "abcd다");\
        //model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/facilities";
    }


    //편의시설 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "applicationList")
    public String applicationList(HttpServletRequest httpServletRequest, Model model, Gson gson, SupportProgramVo supportProgramVo) {

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);
        //System.out.println("비디오 리스트 " + videoListData);
        //model.addAttribute("abc", "abcd다");\
        //model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/applicationList";
    }


    //편의시설 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "myData")
    public String myData(HttpServletRequest httpServletRequest, Model model, Gson gson, SupportProgramVo supportProgramVo) {

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);
        //System.out.println("비디오 리스트 " + videoListData);
        //model.addAttribute("abc", "abcd다");\
        //model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/myData";
    }


    //편의시설 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "supportProgramList")
    public String supportProgramList(HttpServletRequest httpServletRequest, Model model, Gson gson, SupportProgramVo supportProgramVo) {

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);
        //System.out.println("비디오 리스트 " + videoListData);
        //model.addAttribute("abc", "abcd다");\
        //model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/supportProgramList";
    }


    //편의시설 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "login")
    public String login(HttpServletRequest httpServletRequest, Model model, Gson gson, SupportProgramVo supportProgramVo) {

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);
        //System.out.println("비디오 리스트 " + videoListData);
        //model.addAttribute("abc", "abcd다");\
        //model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/login";
    }


    //편의시설 상세
    @RequestMapping(method = {RequestMethod.GET}, path = "campusDetail1")
    public String campusDetail1(HttpServletRequest httpServletRequest, Model model, Gson gson, SupportProgramVo supportProgramVo) {

        //List<SupportProgramVo> videoListData = supportProgramService.getVideoList(supportProgramVo);
        //System.out.println("비디오 리스트 " + videoListData);
        //model.addAttribute("abc", "abcd다");\
        //model.addAttribute("videoListJson", gson.toJson(videoListData));

        return "app/campusDetail1";
    }



    //디자인 :: 긴급상황지원
    @RequestMapping(method = {RequestMethod.GET}, path = "designEmergencySupport")
    public String designEmergencySupport(HttpServletRequest httpServletRequest, Model model) {


        return "design/emergency_support_list";
    }



    //메인
    @RequestMapping(method = {RequestMethod.GET}, path = "main")
    public String main(HttpServletRequest httpServletRequest, Model model) {


        return "app/main";
    }



    //디자인 :: 메인화면
    @RequestMapping(method = {RequestMethod.GET}, path = "home")
    public String home(HttpServletRequest httpServletRequest, Model model) {


        return "design/main";
    }

    //디자인 :: 캠퍼스 안내
    @RequestMapping(method = {RequestMethod.GET}, path = "designCampus")
    public String designCampus(HttpServletRequest httpServletRequest, Model model) {


        return "design/camp_info_list";
    }

    //디자인 :: 캠퍼스 안내->상세
    @RequestMapping(method = {RequestMethod.GET}, path = "designCampusDetail")
    public String designCampusDetail(HttpServletRequest httpServletRequest, Model model) {


        return "design/camp_info_detail";
    }

    //디자인 :: 긴급상황지원
    @RequestMapping(method = {RequestMethod.GET}, path = "emergencyList")
    public String emergencyList(HttpServletRequest httpServletRequest, Model model) {


        return "app/emergencyList";
    }

    //디자인 :: 편의시설
    @RequestMapping(method = {RequestMethod.GET}, path = "designFacilities")
    public String designFacilities(Model model) {


        return "design/facilities";
    }

    //디자인 :: 내 자료실
    @RequestMapping(method = {RequestMethod.GET}, path = "designMyData")
    public String designMyData(Model model) {


        return "design/mydata_list";
    }

    //디자인 :: 내 자료실->상세
    @RequestMapping(method = {RequestMethod.GET}, path = "designMyDataDetail")
    public String designMyDataDetail(Model model) {


        return "design/mydata_detail";
    }

    //디자인 :: 내정보(수정)
    @RequestMapping(method = {RequestMethod.GET}, path = "designPersonalInfo")
    public String designPersonalInfo(Model model) {


        return "design/personalInfo_edit";
    }

    //디자인 :: 지원프로그램
    @RequestMapping(method = {RequestMethod.GET}, path = "designSupplyProgram")
    public String designSupplyProgram(Model model) {


        return "design/supply_list";
    }

    //디자인 :: 지원프로그램->상세
    @RequestMapping(method = {RequestMethod.GET}, path = "designSupplyProgramDetail")
    public String designSupplyProgramDetail(Model model) {


        return "design/supply_detail";
    }

    //디자인 :: 동영상보기
    @RequestMapping(method = {RequestMethod.GET}, path = "designVideo")
    public String designVideo(Model model) {


        return "design/video_list";
    }

    //디자인 :: 동영상보기->상세
    @RequestMapping(method = {RequestMethod.GET}, path = "designVideoDetail")
    public String designVideoDetail(Model model) {


        return "design/video_detail";
    }


    //디자인 :: 신청서 관리
    @RequestMapping(method = {RequestMethod.GET}, path = "designApplicationList")
    public String designApplicationList(Model model) {


        return "design/application_list";
    }

    //디자인 :: 신청서 관리->상세보기(비자연장신청)
    @RequestMapping(method = {RequestMethod.GET}, path = "designApplicationDetail")
    public String designApplicationDetail(Model model) {


        return "design/application_detail_visa";
    }

    //디자인 :: 신청서 관리->상세보기(비자연장신청)->step1
    @RequestMapping(method = {RequestMethod.GET}, path = "designApplicationDetailStep1")
    public String designApplicationDetailStep1(Model model) {


        return "design/application_detail_visa_step_1";
    }

    //디자인 :: 신청서 관리->상세보기(비자연장신청)->step2
    @RequestMapping(method = {RequestMethod.GET}, path = "designApplicationDetailStep2")
    public String designApplicationDetailStep2(Model model) {


        return "design/application_detail_visa_step_2";
    }

    //디자인 :: 신청서 관리->상세보기(비자연장신청)->step3
    @RequestMapping(method = {RequestMethod.GET}, path = "designApplicationDetailStep3")
    public String designApplicationDetailStep3(Model model) {


        return "design/application_detail_visa_step_3";
    }




}
