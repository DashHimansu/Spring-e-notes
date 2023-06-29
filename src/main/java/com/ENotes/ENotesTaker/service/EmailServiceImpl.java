package com.ENotes.ENotesTaker.service;

import com.ENotes.ENotesTaker.entity.UserInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender sender;

    public String sendMail(UserInfo userInfo){
        String body1 = "<html> & <body>" +
                "Hii "+userInfo.getName()+"</br>"+
                "Your Email : "+userInfo.getEmail()+"</br>"+
                "Your Password : "+userInfo.getPassword()+
                "About role : "+userInfo.getRole()+
                "I hope ENotes Taker serving best performance for managing to you Daily Notes.</br>"+
                " Developing note taking skills should help you organize information into an understandable " +
                "format </br> that will assist in your studying process."+
                "</body> & </html>";
        String body ="Hii "+userInfo.getName()+"\n"+
                "Your Email : "+userInfo.getEmail()+"\n"+
                "Your Password : "+userInfo.getPassword()+"\n"+
                "About role : "+userInfo.getAbout()+"\n"+
                "I hope ENotes Taker serving best performance for managing to you Daily Notes.\n"+
                "Developing note taking skills should help you organize information into an understandable " +
                "format that will \nassist in your studying process.";
        Optional<String> response = sendMailToUserEmailId(userInfo.getEmail(), "ENotes-Taker Mails",body);
        if(response.isEmpty()) return "Something Missing cause Mail Not sent to your Email Id";
        return response.get();
    }
    @Override
    public Optional<String> sendMailToUserEmailId(String to, String subject, String body) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setText(body);
            helper.setSubject(subject);
            //message.setContent(htmlContent, "text/html; charset=utf-8");
        } catch (MessagingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        sender.send(message);
        return Optional.of("Mail Sent your registered Email Id successFully");
    }
}
