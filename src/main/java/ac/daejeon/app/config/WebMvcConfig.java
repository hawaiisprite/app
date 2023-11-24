package ac.daejeon.app.config;



import ac.daejeon.app.aspectAndInterceptor.AppInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AppInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/js/lib/**", "/*.ico", "/error-page/**", "/img/**", "/pdf/**");

    }


    private  String uploadImagesPath = "";

    public WebMvcConfig(@Value("${custom.path.upload-images}") String uploadImagesPath) {
        //System.out.println("업로드 이미지 패스 " + uploadImagesPath);
        this.uploadImagesPath = uploadImagesPath;
    }


    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/download/**")
                .addResourceLocations("file:///" + uploadImagesPath)
                // 접근 파일 캐싱 시간
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES));



    }
}
