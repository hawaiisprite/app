package ac.daejeon.app.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

    private static FirebaseApp singleTonFirebaseApp = null;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        //log.info("Initializing Firebase.");

        File file = ResourceUtils.getFile("classpath:etc/daejeontest-firebase-adminsdk.json");

        //FileInputStream serviceAccount = new FileInputStream("classpath:/etc/daejeontest-firebase-adminsdk.json");
        FileInputStream serviceAccount = new FileInputStream(file);


        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                //.setStorageBucket("heroku-sample.appspot.com")
                .build();


        FirebaseApp app = null;
        if(FirebaseApp.getApps().isEmpty()) {
            app = FirebaseApp.initializeApp(firebaseOptions);
        }

        return app;
    }


    /*@Bean
    public FirebaseAuth getFirebaseAuth() throws IOException {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp());
        return firebaseAuth;
    }*/
}
