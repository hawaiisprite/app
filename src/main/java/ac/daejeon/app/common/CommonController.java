package ac.daejeon.app.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;

import static ac.daejeon.app.common.Statics.base64Enc;


@Controller
public class CommonController {

    @RequestMapping(value="/common/downloadFile")
    public void downloadFile(HttpServletResponse response) throws Exception {

        String filePath = "C:\\workspace\\image\\test\\6b44834042d349798bb23fcf2505f77c.jpg";


        FileInputStream fis = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        File file = new File(filePath);

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
        }

        int len = 0;
        byte[] buf = new byte[1024];
        try {
            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }

            byte[] fileArray = baos.toByteArray();
            String outBinary = new String(base64Enc(fileArray));

            //outBinary = "data:image/jpeg;base64," + outBinary;

            //System.out.println("아웃 바이너리 " + outBinary);

            fis.close();
            baos.close();

            byte fileByte[] = Base64.getDecoder().decode(outBinary);

            response.setContentType("application/octet-stream");

            //response.setContentLength(fileByte.length);

            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("filetest.jpg", "UTF-8") + "\";");

            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(fileByte);


            response.getOutputStream().flush();

            response.getOutputStream().close();


        } catch (IOException e) {
            //System.out.println("Exception position : FileUtil - fileToString(File file)");

        }




    }

}
