//package food.example.online.food.service;
//
//import org.apache.hc.client5.http.classic.methods.HttpPost;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.core5.http.ContentType;
//import org.apache.hc.core5.http.io.entity.StringEntity;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Value("${resend.api.key}")
//    private String apiKey;
//
//    public void sendOtpEmail(String to, String otp) throws Exception {
//        try (CloseableHttpClient client = HttpClients.createDefault()) {
//            HttpPost post = new HttpPost("https://api.resend.com/emails");
//            post.setHeader("Authorization", "Bearer " + apiKey);
//            post.setHeader("Content-Type", "application/json");
//
//            String json = String.format("""
//                {
//                  "from": "Online Food <onboarding@resend.dev>",
//                  "to": ["%s"],
//                  "subject": "Your OTP Code",
//                  "html": "<h3>Your verification code is:</h3><h2>%s</h2><p>It expires in 5 minutes.</p>"
//                }
//            """, to, otp);
//
//            post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
//            client.execute(post);
//        }
//    }
//}
