package food.example.online.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
    public void sendOtp(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(email);
		message.setSubject("Your OTP Code for Registration");
//		message.setText("Your 4-digit OTP is: \" + otp + \"\\n\\nThis code expires in 5 minutes. ");
		message.setText("Your 4-digit OTP is: " + otp + "\n\nThis code expires in 5 minutes.");
		mailSender.send(message);
	}
}
