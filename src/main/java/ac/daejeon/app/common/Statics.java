package ac.daejeon.app.common;

import ac.daejeon.app.common.CommonDao;
import ac.daejeon.app.common.CommonVo;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

@Component
public class Statics {

    @Autowired
    CommonDao commonDao;

    public static String FILE_UPLOAD_PATH;

    public static String FILE_UPLOAD_TEMP_PATH;

    public static String FILE_UPLOAD_PASSWORD;

    public static String UNZIP_FILE_UPLOAD_PATH;


    @Value("${custom.path.upload-file}")
    public void setFileUploadPath(String fileUploadPath) {
        FILE_UPLOAD_PATH = fileUploadPath;
    }

    @Value("${custom.path.temp-upload-file}")
    public void setFileUploadTempPath(String fileUploadTempPath) {
        FILE_UPLOAD_TEMP_PATH = fileUploadTempPath;
    }

    @Value("${custom.path.upload-file-password}")
    public void setFileUploadPassword(String fileUploadPathPassword) {
        FILE_UPLOAD_PASSWORD = fileUploadPathPassword;
    }

    @Value("${custom.path.upload-file-unzip}")
    public void setUnzipFileUpload(String unzipFileUpload) {
        UNZIP_FILE_UPLOAD_PATH = unzipFileUpload;
    }

    //public static final String FILE_UPLOAD_PATH = BASE_URL;

    //public static final String FILE_UPLOAD_PATH  = "C:\\workspace\\image\\";

    public static final String STUDENT_FILE_UPLOAD_PATH  = "C:\\workspace\\image\\";



    public static final String DOWNLOAD_FILE_PATH  = "/download/";

    public static String converterAdd0(String val) {

        if(val.length() == 1) {
            val = "0" + val;
        }

        return val;
    }

    public static String converterRemove0(String val) {

        return val.replaceAll("^0+", "");
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
        }  else {

        }

        //년도 폴더에 월 폴더 생성
        File monthUploadFilePath = new File(yearUploadFilePath, month);
        if(!monthUploadFilePath.exists()){
            monthUploadFilePath.mkdir();
        } else {

        }

        //년도 폴더의 월 폴더에 일 폴더 생성
        File dayUploadFilePath = new File(monthUploadFilePath, day);
        if(!dayUploadFilePath.exists()){
            dayUploadFilePath.mkdir();
        } else {

        }

        //File.separator 윈도우면 \ 패스경로로 변환, 리눅스면 / 패스경로 자동 변환

        String uploadPath = dayUploadFilePath.getPath() + File.separator;

        //System.out.println("최종 업로드 패스 확인 " + dayUploadFilePath);

        return uploadPath;

    }


    private static String STATIC_PORT;

    @Value("${server.port}")
    public void setMyProperty(String port) {
        Statics.STATIC_PORT = port;
    }

    public static String port() {
        return STATIC_PORT;
    }

    public static Boolean zipWithPassword(String fileUuid, String ext, MultipartFile multipartFile) throws IOException {


        ServerSocket serverSocket;

        int port = Integer.parseInt(STATIC_PORT);
        // if wrong service value is used, crash with
        // port number
        if(port != 8245) {
            serverSocket = new ServerSocket(8245);
        }

        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String year = Integer.toString(today.getYear());
        String month = Integer.toString(today.getMonth().getValue());
        String day = Integer.toString(today.getDayOfMonth());


        //File oldfile = new File(multipartFile.getOriginalFilename());

        String tempFIleUuid = UUID.randomUUID().toString().replace("-", "");

        Path path = Paths.get(FILE_UPLOAD_TEMP_PATH + tempFIleUuid);
        multipartFile.transferTo(path);

        File convFile = new File(FILE_UPLOAD_TEMP_PATH + tempFIleUuid);

        //String savePath = collaborationUploadPath + collaborationFileName;

        /*
        convFile.renameTo(newFile);
        */

        //convFile.createNewFile();

        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());

            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String uploadPath = makeUploadFolder(year, month, day);

        String savePath = uploadPath + fileUuid + ".zip";



        ZipFile zipFile = new ZipFile(savePath, FILE_UPLOAD_PASSWORD.toCharArray());

        ZipParameters zipParameters = new ZipParameters();

        zipParameters.setEncryptFiles(true);

        zipParameters.setEncryptionMethod(EncryptionMethod.AES);

        zipParameters.setFileNameInZip(fileUuid + "." + ext);

        zipFile.addFile(convFile, zipParameters);

        boolean isSaved = false;

        try {
            multipartFile.transferTo(convFile);

            convFile.delete();

            isSaved = true;

        } catch (IOException e) {
            convFile.delete();
            //e.printStackTrace();
        }

        return isSaved;
    }



    public static String unzipWithPassword(String year, String month, String day, String uuid, String ext) throws IOException {

        year = replaceWrongPath(year);
        month = replaceWrongPath(month);
        day = replaceWrongPath(day);
        uuid = replaceWrongPath(uuid);
        ext = replaceWrongPath(ext);

        String zipFilePath = Statics.FILE_UPLOAD_PATH + year + File.separator + month + File.separator + day + File.separator + uuid + ".zip";

        String unzipFilePath = Statics.UNZIP_FILE_UPLOAD_PATH + File.separator + year + month + day + uuid + ext;

        String unzipAllFilePath = Statics.UNZIP_FILE_UPLOAD_PATH + File.separator + year + month + day + uuid + ext + File.separator + uuid + "." + ext;


        char[] charPassword = FILE_UPLOAD_PASSWORD.toCharArray();


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



                ServerSocket serverSocket;
                Properties props = new Properties();
                String filename = "file_list";
                fis = new FileInputStream(unzipFile);
                props.load(fis);
                String service = props.getProperty("Service No");
                int port = Integer.parseInt(service);
                // if wrong service value is used, crash with
                // port number
                if(port != 80)
                    serverSocket = new ServerSocket(port);
                else
                    serverSocket = new ServerSocket(4000);



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
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        // Handle IOException while closing FileInputStream
                    }
                }
                try {
                    baos.close();
                } catch (IOException e) {
                    // Handle IOException while closing ByteArrayOutputStream
                }

                // Delete the temporary unzipFileFolder
                try {
                    FileUtils.forceDelete(unzipFileFolder);
                } catch (IOException e) {
                    // Handle IOException while deleting unzipFileFolder
                }
            }

        } else {

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


    public static byte[] base64Enc(byte[] buffer) {
        return Base64.encodeBase64(buffer);
    }

    public static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("GET");

            for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            InputStream inputStream = (responseCode == HttpURLConnection.HTTP_OK) ? con.getInputStream() : con.getErrorStream();

            BufferedReader reader = null;

            try  {


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                reader = new BufferedReader(inputStreamReader);

                if (inputStream != null) {
                    inputStream.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                //return readBody(reader);
                StringBuilder responseBody = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                reader.close();

                return responseBody.toString();



            } catch (IOException e) {
                throw new RuntimeException("BufferedReader 실패", e);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if(reader != null) {
                    reader.close();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    public static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(BufferedReader reader) throws IOException {
        StringBuilder responseBody = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }

        reader.close();

        return responseBody.toString();
    }


    public static String replaceWrongPath(String name){
        if(name != null) {
            name = name.replaceAll("/","");
            name = name.replaceAll("\\\\","");
            name = name.replaceAll(".","");
            name = name.replaceAll("&","");
            name = name + "-report";
        }
        return name;
    }


    /*
        public static String readBody(InputStream body){
            try (InputStreamReader streamReader = new InputStreamReader(body);
                    BufferedReader lineReader = new BufferedReader(streamReader)) {

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
     */
}
