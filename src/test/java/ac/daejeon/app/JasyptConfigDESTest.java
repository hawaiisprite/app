package ac.daejeon.app;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

class JasyptConfigDESTest {

    @Test
    void stringEncryptor() {
        //String url = "jdbc:log4jdbc:mariadb://221.157.125.227:3306/dju?characterEncoding=UTF-8&serverTimezone=Asia/Seoul&allowMultiQueries=true";
        //String url = "jdbc:mariadb://221.157.125.227:3306/dju?characterEncoding=UTF-8&serverTimezone=Asia/Seoul&allowMultiQueries=true";

        String url = "jdbc:mariadb://221.157.125.227:3306/dju?characterEncoding=UTF-8&serverTimezone=Asia/Seoul&allowMultiQueries=true";
        String url2 = "jdbc:log4jdbc:mariadb://221.157.125.227:3306/dju?characterEncoding=UTF-8&serverTimezone=Asia/Seoul&allowMultiQueries=true";

        //String username = "djacdb";
        //String password = "djzja!@34db";


        String username = "ajm";
        String password = "qweqwe12";

        String unzipPassword = "unzipdjac";


        String jasyptEncodingUrl1 = jasyptEncoding(url);
        String jasyptEncodingUrl2 = jasyptEncoding(url2);

        String jasyptUsername = jasyptEncoding(username);

        String jasyptPassword = jasyptEncoding(password);

        String jasyptUnzipPassword = jasyptEncoding(unzipPassword);


        System.out.println("jasyptEncodingUrl1 " + jasyptEncodingUrl1);
        System.out.println("jasyptEncodingUrl2 " + jasyptEncodingUrl2);

        System.out.println("jasyptUsername " + jasyptUsername);
        System.out.println("jasyptPassword " + jasyptPassword);
        System.out.println("jasyptUnzipPassword " + jasyptUnzipPassword);


    }

    public String jasyptEncoding(String value) {

        String key = "t7z4mih4dog7g0b2ps8v";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }

}