package com.ssafy.ssafyhome.util;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

@Component
@RequiredArgsConstructor
public class EmailUtil {

  @Value("${spring.application.name}")
  private String SERVICE_NAME;

  private final JavaMailSender emailSender;

  public void sendEmail(String to, String code) {
    try {
      ClassPathResource resource = new ClassPathResource("templates/email/verification.html");
      String htmlContent = (Files.readString(resource.getFile().toPath())).replace("${verificationCode}", code);

      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setTo(to);
      helper.setSubject("[" + SERVICE_NAME + "] 인증번호 발송");
      helper.setText(htmlContent, true);

      emailSender.send(message);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
