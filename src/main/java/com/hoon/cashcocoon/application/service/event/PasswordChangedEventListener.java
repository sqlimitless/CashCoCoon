package com.hoon.cashcocoon.application.service.event;

import com.hoon.cashcocoon.domain.member.event.PasswordResetEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordChangedEventListener {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Async
    @EventListener
    public void sendMail(PasswordResetEvent passwordResetEvent) throws InterruptedException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(fromMail);
        message.setRecipients(MimeMessage.RecipientType.TO, passwordResetEvent.getToAddress());
        message.setSubject("[CashCocoon] 비밀번호 초기화");
        StringBuilder body = new StringBuilder();
        body.append("<h1>" + "안녕하세요." + "</h1>")
                .append("<h1>" + "CashCocoon 입니다." + "</h1>")
                .append("<h3>" + "요청하신 초기화된 비밀번호입니다." + "</h3><br>")
                .append("<div align='center' style='border:1px solid black; font-family:verdana;'>")
                .append("<h2>" + "변경된 비밀번호입니다." + "</h2>")
                .append("<h1 style='color:blue'>").append(passwordResetEvent.getNewPassword()).append("</h1>")
                .append("</div><br>")
                .append("<h3>" + "감사합니다." + "</h3>");
        message.setText(body.toString(), "UTF-8", "html");

        javaMailSender.send(message);
    }
}
