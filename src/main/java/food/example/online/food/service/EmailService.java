package food.example.online.food.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("your_email@gmail.com"); // same as spring.mail.username
        msg.setTo(to);
        msg.setSubject("Your OTP Code for Online Food");
        msg.setText(
                "Hello,\n\n" +
                "Your verification code is: " + otp + "\n" +
                "This code will expire in 5 minutes.\n\n" +
                "Thank you!"
        );

        mailSender.send(msg);
        System.out.println("OTP sent to " + to + " : " + otp);
    }
}
