package ac.daejeon.app.common;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Statics {


    @Autowired
    CommonDao commonDao;

    public static final String FILE_UPLOAD_PATH  = "C:\\workspace\\image\\";


    public static final String UNZIP_FILE_UPLOAD_PATH  = "C:\\workspace\\unzip\\";


    public static final String DOWNLOAD_FILE_PATH  = "/download/";





    public static String converterAdd0(String val) {

        if(val.length() == 1) {
            val = "0" + val;
        }



        return val;
    }

    public static String randomId() {


        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }




    //파일 삭제 전용
    // 날짜별 경로 필드 split 후 os에 맞춰서 패스 재설정 그리고 uuid와 확장자 조합
    public static Boolean deleteImg(String yearMonthDay, String uuid, String ext) {

        String[] yearMonthDaySplit = yearMonthDay.split("/");
        String year = yearMonthDaySplit[0];
        String month = yearMonthDaySplit[1];
        String day = yearMonthDaySplit[2];

        String deleteFilePath = Statics.FILE_UPLOAD_PATH + year + File.separator + month + File.separator + day + File.separator + uuid + "." + ext;

        File file = new File(deleteFilePath);

        return file.delete();
    }

    // 날짜별 경로 필드 split 후 os에 맞춰서 패스 재설정 그리고 uuid와 확장자 조합
    public static String replaceToImgPath(String yearMonthDay, String uuid, String ext) {

        String[] yearMonthDaySplit = yearMonthDay.split("/");
        String year = yearMonthDaySplit[0];
        String month = yearMonthDaySplit[1];
        String day = yearMonthDaySplit[2];

        String filePath = Statics.DOWNLOAD_FILE_PATH + year + File.separator + month + File.separator + day + File.separator + uuid + "." + ext;
        return filePath;
    }

    //날짜별 업로드 폴더 생성 함수
    public static String makeUploadFolder(String year, String month, String day) {

        //년도폴더 부터 생성
        File yearUploadFilePath = new File(Statics.FILE_UPLOAD_PATH, year);
        if(!yearUploadFilePath.exists()){
            yearUploadFilePath.mkdir();
        }

        //년도 폴더에 월 폴더 생성
        File monthUploadFilePath = new File(yearUploadFilePath, month);
        if(!monthUploadFilePath.exists()){
            monthUploadFilePath.mkdir();
        }

        //년도 폴더의 월 폴더에 일 폴더 생성
        File dayUploadFilePath = new File(monthUploadFilePath, day);
        if(!dayUploadFilePath.exists()){
            dayUploadFilePath.mkdir();
        }


        //File.separator 윈도우면 \ 패스경로로 변환, 리눅스면 / 패스경로 자동 변환
        String uploadPath = dayUploadFilePath.getPath() + File.separator;
        //System.out.println("최종 업로드 패스 확인 " + dayUploadFilePath);

        return uploadPath;

    }

    public static Boolean zipWithPassword(String fileUuid, String ext, MultipartFile multipartFile) throws IOException {


        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String year = Integer.toString(today.getYear());
        String month = Integer.toString(today.getMonth().getValue());
        String day = Integer.toString(today.getDayOfMonth());

        File convFile = new File(multipartFile.getOriginalFilename());

        //String savePath = collaborationUploadPath + collaborationFileName;

        /*convFile.renameTo(newFile);*/

        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();

        String uploadPath = makeUploadFolder(year, month, day);

        String savePath = uploadPath + fileUuid + ".zip";

        String password = "qweasd12";

        ZipFile zipFile = new ZipFile(savePath, password.toCharArray());

        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        zipParameters.setFileNameInZip(fileUuid + "." + ext);

        zipFile.addFile(convFile, zipParameters);



        boolean isSaved = false;

        try {
            multipartFile.transferTo(convFile);
            isSaved = true;
        } catch (IOException e) {
            //e.printStackTrace();
        }


        return isSaved;
        /*ZipParameters param = new ZipParameters();
        param.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        param.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

        if (password != null) {
            param.setEncryptFiles(true);
            param.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            param.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            param.setPassword(password);
        }

        ZipFile zipFile = new ZipFile(to);

        if (files.size() == 0) {
            throw new RuntimeException("압축대상 파일이 없습니다.");
        }

        files.forEach(file -> {
            try {
                if (file.isFile()) {
                    zipFile.addFile(file, param);
                } else {
                    zipFile.addFolder(file, param);
                }
            } catch (ZipException e) {
                e.printStackTrace();
            }
        });*/
    }



    public static String unzipWithPassword(String year, String month, String day, String uuid, String ext) throws IOException {

        String zipFilePath = Statics.FILE_UPLOAD_PATH + year + File.separator + month + File.separator + day + File.separator + uuid + ".zip";
        String unzipFilePath = Statics.UNZIP_FILE_UPLOAD_PATH + File.separator + year + month + day + uuid + ext;

        String unzipAllFilePath = Statics.UNZIP_FILE_UPLOAD_PATH + File.separator + year + month + day + uuid + ext + File.separator + uuid + "." + ext;

        //String path = "C:\\workspace\\image\\2023\\10\\11\\6b44834042d349798bb23fcf2505f77c.jpg";
        //String path = "C:\\workspace\\image\\2023\\10\\11\\daejeon.pptx";

        char[] charPassword = "qweasd12".toCharArray();

        File file = new File(zipFilePath);
        ZipFile zipFile = new ZipFile(file);

        if (zipFile.isEncrypted()) {

            zipFile.setPassword(charPassword);
            zipFile.extractAll(unzipFilePath);

            FileInputStream fis = null;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            File unzipFile = new File(unzipAllFilePath);
            File unzipFileFolder = new File(unzipFilePath);

            try {
                fis = new FileInputStream(unzipFile);
            } catch (FileNotFoundException e) {
                return "";
            }

            int len = 0;
            byte[] buf = new byte[1024];
            try {
                while ((len = fis.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }

                byte[] fileArray = baos.toByteArray();
                String outBinary = new String(base64Enc(fileArray));

                fis.close();
                baos.close();

                FileUtils.forceDelete(unzipFileFolder);

                return outBinary;

            } catch (IOException e) {
                //System.out.println("Exception position : FileUtil - fileToString(File file)");
                return "";
            }

        }

        return "";

    }


    public static int saveFile(CommonVo commonVo, CommonDao commonDao, MultipartFile multipartFile) throws IOException {

        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String year = Integer.toString(today.getYear());
        String month = Integer.toString(today.getMonth().getValue());
        String day = Integer.toString(today.getDayOfMonth());

        String fileOrigName = multipartFile.getOriginalFilename();
        String fileExt = FilenameUtils.getExtension(fileOrigName);
        String fileUuid = UUID.randomUUID().toString().replace("-", "");

        boolean isSaved = zipWithPassword(fileUuid, fileExt, multipartFile);

        if(isSaved) {

            commonVo.setFileUuid(fileUuid);
            commonVo.setFileExt(fileExt);
            commonVo.setFileOrigName(fileOrigName);
            commonVo.setFileYear(Integer.parseInt(year));
            commonVo.setFileMonth(Integer.parseInt(month));
            commonVo.setFileDay(Integer.parseInt(day));

            commonDao.saveFileInfo(commonVo);

            int insertedFilesIdx = commonVo.getFilesIdx();

            return insertedFilesIdx;

        } else {
            return 0;
        }

    }


    static byte[] base64Enc(byte[] buffer) {
        return Base64.encodeBase64(buffer);
    }



    public static String checkFirebaseToken(String accessToken) {

        if (accessToken != null && !accessToken.equals("")) {

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseToken firebaseUser = null;
            try {
                firebaseUser = firebaseAuth.verifyIdToken(accessToken);
                if(firebaseUser == null) {


                    return "확인111";
                }


                return firebaseUser.getEmail();

                //request.setAttribute("firebase_user_email", firebaseUser.getEmail());


            } catch (FirebaseAuthException e) {

                return "확인222" + e;
            }

        } else {

            return "확인333";
            //responseGetWriter(response);
        }

    }


    public static String get(String apiUrl, Map<String, String> requestHeaders){
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
