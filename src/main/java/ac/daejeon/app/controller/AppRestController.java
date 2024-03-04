package ac.daejeon.app.controller;

import ac.daejeon.app.common.CommonCrypto;
import ac.daejeon.app.common.CommonVo;
import ac.daejeon.app.service.AppService;
import ac.daejeon.app.service.ConfigService;
import ac.daejeon.app.service.SupportProgramService;
import ac.daejeon.app.vo.AppVo;
import ac.daejeon.app.vo.ConfigVo;
import ac.daejeon.app.vo.LoginVo;
import ac.daejeon.app.vo.SupportProgramVo;
import com.google.firebase.auth.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/appRest/")
public class AppRestController {

    private final AppService appService;
    private final SupportProgramService supportProgramService;
    private final ConfigService configService;

    private final CommonCrypto commonCrypto;


    @RequestMapping(value = "doLogin", method = { RequestMethod.POST})
    public String doLogin(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo, LoginVo loginVo) {

        LoginVo loginInfo = appService.doLogin(loginVo);

        if(loginInfo != null) {

            /*int loginFailedCount = loginInfo.getLoginFailedCount();
            String lockDt = loginInfo.getLockDt();

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.minusHours(1).format(formatter);

            if(loginFailedCount >= 5) {

                int comparison = formattedDateTime.compareTo(lockDt);

                if(comparison < 0) {
                    jsonObj.addProperty("result", "overFailed");
                    return jsonObj.toString();
                } else {

                }
            } else {

            }*/

            if(loginInfo.getIsSamePassword()) {
                HttpSession session = httpServletRequest.getSession();

                session.setAttribute("STUDENT_ID", loginInfo.getStudentId());
                jsonObj.addProperty("result", "success");

                /*loginVo.setLoginStatus("success");
                loginService.saveLoginLog(loginVo, httpServletRequest);

                loginVo.setLoginFailedCount(0);
                loginService.setLoginFailedCount(loginVo);*/

            } else {
                /*loginVo.setLoginStatus("notAuthorized");
                loginService.saveLoginLog(loginVo, httpServletRequest);

                loginVo.setLoginFailedCount(loginFailedCount + 1);
                loginService.setLoginFailedCount(loginVo);*/

                jsonObj.addProperty("result", "notAuthorized");
            }
        } else {
            /*loginVo.setLoginStatus("notAuthorized");
            loginService.saveLoginLog(loginVo, httpServletRequest);*/
            jsonObj.addProperty("result", "notAuthorized");
        }

        return jsonObj.toString();
    }


    @RequestMapping(value = "checkEmailAndStudentId", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkEmailAndStudentId(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo) throws Exception {

        System.out.println("앱에서 통신되는지 확인 " + appVo);

        //appVo.getStudentEmail();
        //appService.checkAppJoinAuthCode(appVo);


        //String email = appVo.getStudentEmail();
        //String id = appVo.getStudentId();
        String resTxt = appService.checkEmailAndStudentId(appVo);


        //String emailEncode = CommonCrypto.aesCBCEncode("가나다라한글테스트");
        //String emailDecode = CommonCrypto.aesCBCDecode(emailEncode);



        /*if(res == 1) {



        }*/

        jsonObj.addProperty("result", resTxt);

        return jsonObj.toString();
    }


    //이메일 조인 인증
    @RequestMapping(value = "test2", method = {RequestMethod.GET, RequestMethod.POST})
    public String test2(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo) {


        System.out.println("테스트222");

        /*String resTxt = appService.checkAppJoinAuthCode(appVo);
        jsonObj.addProperty("result", resTxt);*/

        return jsonObj.toString();
    }


    //이메일 조인 인증
    @RequestMapping(value = "checkAuthCode", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkAuthCode(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo) {


        String resTxt = appService.checkAppJoinAuthCode(appVo);
        jsonObj.addProperty("result", resTxt);

        return jsonObj.toString();
    }


    //시청시간 보내기
    @RequestMapping(value = "sendViewingTime", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendViewingTime(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo) {


        System.out.println("뷰잉타임 " + appVo.getViewingTime());
        System.out.println("마지막 시청 포인트 " + appVo.getLastSeconds());


        String studentEmail = httpServletRequest.getSession().getAttribute("M_STUDENT_EMAIL").toString();
        appVo.setStudentEmail(studentEmail);

        appService.sendViewingTime(appVo);


        jsonObj.addProperty("result", "success");

        //String resTxt = appService.checkAppJoinAuthCode(appVo);
        //jsonObj.addProperty("result", resTxt);

        return jsonObj.toString();
    }


    //시청시간 보내기
    @RequestMapping(value = "getPlayerInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getPlayerInfo(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo) {

        String studentEmail = httpServletRequest.getSession().getAttribute("M_STUDENT_EMAIL").toString();
        appVo.setStudentEmail(studentEmail);

        AppVo playerInfo = appService.getVideoWatched(appVo);

        if(playerInfo != null) {
            jsonObj.addProperty("playerInfoJson", gson.toJson(playerInfo));
            jsonObj.addProperty("result", "success");
        } else {
            jsonObj.addProperty("result", "empty");
        }
        //System.out.println("플레이어 인포 " + playerInfo);

        return jsonObj.toString();
    }

    //fcm 토큰 받기
    @RequestMapping(value = "setFcmToken", method = {RequestMethod.GET, RequestMethod.POST})
    public String setFcmToken(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo) {

        String studentEmail = httpServletRequest.getSession().getAttribute("M_STUDENT_EMAIL").toString();
        appVo.setStudentEmail(studentEmail);

        appService.setFcmToken(appVo);

        jsonObj.addProperty("result", "성공?");

        return jsonObj.toString();
    }



    //파이어베이스
    @RequestMapping(value = "checkAccessToken", method = { RequestMethod.POST})
    public String checkAccessToken(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo) {

        String accessToken = appVo.getAccessToken();

        if (accessToken != null && !accessToken.equals("")) {

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseToken firebaseUser = null;
            try {
                firebaseUser = firebaseAuth.verifyIdToken(accessToken);
                if(firebaseUser == null) {
                    jsonObj.addProperty("result", "notAuthorized");
                } else {

                    //모바일 에서 체크할 경우 세션 하루 주기
                    if(appVo.isCheckAccessTokenForMobile()) {
                        HttpSession session = httpServletRequest.getSession();
                        session.setAttribute("M_STUDENT_EMAIL", firebaseUser.getEmail());
                        session.setMaxInactiveInterval(86400);
                    }

                    jsonObj.addProperty("result", "success");
                }
            } catch (FirebaseAuthException e) {
                //https://firebase.google.com/docs/reference/admin/error-handling error code list
                if(e.getAuthErrorCode() == AuthErrorCode.EXPIRED_ID_TOKEN) {
                    //responseGetWriter(response, "expired");
                    jsonObj.addProperty("result", "expired");
                } else {
                    jsonObj.addProperty("result", "notAuthorized");
                }
            }
        } else {
            jsonObj.addProperty("result", "notAuthorized");
        }

        return jsonObj.toString();
    }


    //파이어베이스 인증 테스트
    @RequestMapping(value = "test1", method = {RequestMethod.GET, RequestMethod.POST})
    public String test1(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo) throws FirebaseAuthException {


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        UserRecord userRecord = firebaseAuth.getUserByEmail("behoimi@naver.com");

        String email = "orbis77@naver.com";


        System.out.println("확인 " + userRecord.getUid());
        System.out.println("확인 " + userRecord.getEmail());

        ActionCodeSettings actionCodeSettings = ActionCodeSettings.builder()
                .setUrl("http://localhost:8080/checkout?cartId=1234")
                /*.setHandleCodeInApp(true)
                .setIosBundleId("com.example.ios")
                .setAndroidPackageName("com.example.android")
                .setAndroidInstallApp(true)
                .setAndroidMinimumVersion("12")
                .setDynamicLinkDomain("coolapp.page.link")*/
                .build();

        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(userRecord.getUid())
                .setEmail("orbis77@naver.com")
                //.setPhoneNumber("+11234567890")
                .setEmailVerified(true)
                .setPassword("zxczxc12");
                //.setDisplayName("Jane Doe")
                //.setPhotoUrl("http://www.example.com/12345678/photo.png")
                //.setDisabled(true);


        UserRecord userRecord2 = FirebaseAuth.getInstance().updateUser(request);


        /*try {
            String link = FirebaseAuth.getInstance().generatePasswordResetLink(
                    email, actionCodeSettings);
            // Construct email verification template, embed the link and send
            // using custom SMTP server.
            //sendCustomEmail(email, displayName, link);
            System.out.println("확인 " + link);
        } catch (FirebaseAuthException e) {
            System.out.println("Error generating email link: " + e.getMessage());
        }*/

        /*String email2 = "user@example.com";
        try {
            String link = FirebaseAuth.getInstance().generateEmailVerificationLink(
                    email2, actionCodeSettings);
            // Construct email verification template, embed the link and send
            // using custom SMTP server.
            //sendCustomEmail(email2, displayName, link);
        } catch (FirebaseAuthException e) {
            System.out.println("Error generating email link: " + e.getMessage());
        }*/






        //userRecord.


        /*String resTxt = appService.checkAppJoinAuthCode(appVo);
        jsonObj.addProperty("result", resTxt);*/

        return jsonObj.toString();
    }

    //여권사진 저장 혹은 수정
    @RequestMapping(value = "sendInfoPassport", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendInfoPassport(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);


        int res = appService.createPassportFile(appVo, commonVo);

        if(res == 1) {
            jsonObj.addProperty("result", "success");
        }

        return jsonObj.toString();
    }



    //여권사진 가져오기
    @RequestMapping(value = "getInfoPassport", method = {RequestMethod.GET, RequestMethod.POST})
    public String getInfoPassport(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);

        //appVo.setStudentEmail("orbis77@naver.com");

        AppVo passportInfo = appService.getInfoPassport(appVo, commonVo);



        if(passportInfo.getPassportFileUuid() == null) {
            jsonObj.addProperty("result", "empty");
        } else {
            jsonObj.addProperty("result", "success");
            jsonObj.addProperty("binary", passportInfo.getPassportFileBinary());
        }


        /*if(res == 1) {
            jsonObj.addProperty("result", "success");
        }*/

        return jsonObj.toString();
    }


    //개인정보 수정
    @RequestMapping(value = "modifyPersonalInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String modifyPersonalInfo(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        System.out.println("수정퍼스날인포");

        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);


        int res = appService.modifyPersonalInfo(appVo, commonVo);

        if(res == 1) {
            jsonObj.addProperty("result", "success");
        }

        return jsonObj.toString();
    }



    //개인정보 수정
    @RequestMapping(value = "getPersonalInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getPersonalInfo(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);


        AppVo personalInfo = appService.getPersonalInfo(appVo);

        jsonObj.addProperty("result", "success");
        jsonObj.addProperty("addr", personalInfo.getAddr());
        jsonObj.addProperty("jsonData", gson.toJson(personalInfo));
        jsonObj.addProperty("binary", personalInfo.getPersonalInfoFileBinary());

        //int res = appService.modifyPersonalInfo(appVo, commonVo);

        /*if(res == 1) {
            jsonObj.addProperty("result", "success");
        }*/

        return jsonObj.toString();
    }


    //지원프로그램 가져오기
    @RequestMapping(value = "getSupportProgram", method = {RequestMethod.GET, RequestMethod.POST})
    public String getSupportProgram(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        supportProgramVo.setApplicationType("counseling");
        List<SupportProgramVo> counselingList = supportProgramService.getApplicationList(supportProgramVo);



        jsonObj.addProperty("jsonArrData", gson.toJson(counselingList));
        jsonObj.addProperty("result", "success");

        //model.addAttribute("counselingListJson", gson.toJson(counselingList));

        return jsonObj.toString();
    }



    //지원프로그램 상세 가져오기
    @RequestMapping(value = "getSupportProgramDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public String getSupportProgramDetail(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);

        AppVo applicationInfo = appService.getApplicationDetail(appVo);

        int isAppliedSupportProgram = appService.isAppliedSupportProgram(appVo);

        jsonObj.addProperty("isAppliedSupportProgram", isAppliedSupportProgram);

        jsonObj.addProperty("jsonObjData", gson.toJson(applicationInfo));
        jsonObj.addProperty("result", "success");

        /*supportProgramVo.setApplicationType("counseling");
        List<SupportProgramVo> counselingList = supportProgramService.getApplicationList(supportProgramVo);
        jsonObj.addProperty("jsonArrData", gson.toJson(counselingList));
        jsonObj.addProperty("result", "success");*/
        //model.addAttribute("counselingListJson", gson.toJson(counselingList));

        return jsonObj.toString();
    }



    //지원프로그램 지원신청
    @RequestMapping(value = "applicationSupportProgram", method = {RequestMethod.GET, RequestMethod.POST})
    public String applicationSupportProgram(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo, CommonVo commonVo) throws FirebaseAuthException, IOException {


        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);


        int res = appService.applicationSupportProgram(appVo);

        System.out.println("반환값 " + res);

        if(res == 1) {
            jsonObj.addProperty("result", "success");
        } else if(res == 0) {
            jsonObj.addProperty("result", "exist");
        }

        //System.out.println("이메일은 " + firebaseUserEmail);

        /*SupportProgramVo applicationInfo = supportProgramService.getApplicationList(supportProgramVo).get(0);
        jsonObj.addProperty("jsonObjData", gson.toJson(applicationInfo));
        jsonObj.addProperty("result", "success");
*/
        /*supportProgramVo.setApplicationType("counseling");
        List<SupportProgramVo> counselingList = supportProgramService.getApplicationList(supportProgramVo);
        jsonObj.addProperty("jsonArrData", gson.toJson(counselingList));
        jsonObj.addProperty("result", "success");*/
        //model.addAttribute("counselingListJson", gson.toJson(counselingList));

        return jsonObj.toString();
    }



    //파이어베이스 인증 테스트
    @RequestMapping(value = "maptest", method = {RequestMethod.GET, RequestMethod.POST})
    public String maptest(HttpServletRequest httpServletRequest, JsonObject jsonObj, AppVo appVo) throws FirebaseAuthException {

        String clientId = "FJeKXqzKKG7HLw0qOG1A"; //애플리케이션 클라이언트 아이디
        String clientSecret = "HIrmb5TFDl"; //애플리케이션 클라이언트 시크릿


        String text = null;
        try {
            text = URLEncoder.encode("용운로 늘푸른약국", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text + "&display=1";    // JSON 결과



        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


        System.out.println(responseBody);

        jsonObj.addProperty("responseBody", responseBody);

        jsonObj.addProperty("result", "success");

        return jsonObj.toString();

    }




    //강의 가져오기
    @RequestMapping(value = "getClassSectionList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getClassSectionList(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);

        supportProgramVo.setApplicationType("counseling");
        List<AppVo> classSectionList = appService.getClassSectionList(appVo);


        //강사 로그인 인증 코드 가져오기
        /*public int getTodayAttendance(ProfessorVo professorVo) {
            return professorDao.getTodayAttendance(professorVo);
        }*/


        jsonObj.addProperty("jsonArrData", gson.toJson(classSectionList));
        jsonObj.addProperty("result", "success");

        //model.addAttribute("counselingListJson", gson.toJson(counselingList));

        return jsonObj.toString();
    }


    //출석여부 가져오기
    @RequestMapping(value = "getTodayAttendance", method = {RequestMethod.GET, RequestMethod.POST})
    public String getTodayAttendance(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo, CommonVo commonVo) throws FirebaseAuthException, IOException {

        String firebaseUserEmail = httpServletRequest.getAttribute("firebase_user_email").toString();
        appVo.setStudentEmail(firebaseUserEmail);

        supportProgramVo.setApplicationType("counseling");
        int attended = appService.getTodayAttendance(appVo);

        System.out.println("출석여부를 확인하겠습니다 " + attended);

        jsonObj.addProperty("attended", attended);
        jsonObj.addProperty("result", "success");

        LocalDateTime now = LocalDateTime.now();

        jsonObj.addProperty("year", now.getYear());
        jsonObj.addProperty("month", now.getMonthValue());
        jsonObj.addProperty("day", now.getDayOfMonth());

        return jsonObj.toString();
    }


    @RequestMapping(value = "getVideoList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getVideoList(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, SupportProgramVo supportProgramVo) throws FirebaseAuthException {

        List<SupportProgramVo> videoList = supportProgramService.getVideoList(supportProgramVo);

        jsonObj.addProperty("jsonArrData", gson.toJson(videoList));
        jsonObj.addProperty("result", "success");

        return jsonObj.toString();
    }



    @RequestMapping(value = "getConvenienceFacilityList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getConvenienceFacilityList(HttpServletRequest httpServletRequest, JsonObject jsonObj, Gson gson, AppVo appVo, ConfigVo configVo) {


        configVo.setWhereType(true);
        List<ConfigVo> convenienceFacilityList = configService.getConvenienceFacilityList(configVo);

        jsonObj.addProperty("jsonArrData", gson.toJson(convenienceFacilityList));
        jsonObj.addProperty("result", "success");

        return jsonObj.toString();
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }



}
