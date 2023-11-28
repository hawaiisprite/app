package ac.daejeon.app.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    //private final UsersRepository usersRepository;

    public String sendNotificationByToken() {

        //Optional<Users> user = usersRepository.findById(requestDto.getTargetUserId());

        /*if (user.isPresent()) {
            if (user.get().getFirebaseToken() != null) {*/
                Notification notification = Notification.builder()
                        .setTitle("타이틀")
                        .setBody("바디입니다")
                        // .setImage(requestDto.getImage())
                        .build();

                Message message = Message.builder()
                        .setToken("f1xk7GVFTbKKeEzP6ZMLZ5:APA91bEjqZKAp6VquHJwAiXtCZoXS3sGKpcB7flMx388yVcGcmPI-g-2JT4alOXdLPjn9xfOiAHe2z3POaNn0Cnkekfv33LG8d04shTQncAkYZqi47dBS3SM0w5dMCU6RBmcA7RDpj8x")
                        //.setNotification(notification)
                        .putData("abc", "bbc 뉴스입니당~")
                        .putData("bbc", "가나다라 abc")
                        // .putAllData(requestDto.getData())
                        .build();

                try {
                    firebaseMessaging.send(message);

                    System.out.println("성공");

                    return "알림을 성공적으로 전송했습니다. targetUserId=";
                } catch (FirebaseMessagingException e) {

                    System.out.println("실패~~");

                    e.printStackTrace();
                    return "알림 보내기를 실패하였습니다. targetUserId=";
                }
            /*} else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId();
            }*/

        /*} else {
            return "해당 유저가 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId();
        }*/


    }
}
