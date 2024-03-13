package ac.daejeon.cisims.common;

import ac.daejeon.app.common.CommonVo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value="/common/")
public class CommonController {

    @RequestMapping(value="/showPdf")
    public String showPdf(HttpServletResponse response) throws Exception {

        return "common/showPdf";
    }

    @RequestMapping(value="/alert")
    public String alert(HttpServletResponse response) throws Exception {

        return "common/alert";
    }


    @GetMapping("/downloadFile/{fileFromType}/{fileYear}/{fileMonth}/{fileDay}/{fileUuid}")
    public ResponseEntity<Resource> downloadFile(CommonVo commonVo) throws IOException {


        StringBuffer basicUrlBuffer = new StringBuffer();

        basicUrlBuffer.append("C:").append(File.separator).append("workspace").append(File.separator).append("image");

        // 외부 경로에 저장된 파일의 실제 경로를 지정합니다.
        String basic_url = basicUrlBuffer.toString();

        int fileYear = commonVo.getFileYear();
        int fileMonth = commonVo.getFileMonth();
        int fileDay = commonVo.getFileDay();

        String fileUuid = commonVo.getFileUuid();
        String fileFromType = commonVo.getFileFromType();

        if(fileFromType.equals("training")) {

            //상황에 따라 basic_url 변경하기
        } else {

        }

        StringBuffer str = new StringBuffer();
        str.append( basic_url).append( File.separator).append( fileYear).append(File.separator).append(fileMonth).append(File.separator).append(fileDay).append(File.separator).append(fileUuid).append(".zip");

        String externalFilePath = str.toString();
        Path filePath = Paths.get(externalFilePath);

        // 파일을 읽어와 Resource로 변환합니다.
        Resource resource = new UrlResource(filePath.toUri());

        // Content-Disposition 헤더를 설정하여 브라우저가 파일을 다운로드하도록 합니다.

        //String fileName3 = URLEncoder.encode("한글테스트123.zip", StandardCharsets.UTF_8.toString());

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=empty");

        // 파일 다운로드를 위한 ResponseEntity를 반환합니다.
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}