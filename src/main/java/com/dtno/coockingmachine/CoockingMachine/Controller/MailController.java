package com.dtno.coockingmachine.CoockingMachine.Controller;


import com.dtno.coockingmachine.CoockingMachine.DAOPackage.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;


import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Controller
public class MailController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public ModelAndView setProperties(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("mailToSend") Mail mail) {


        if(mail.getSubject().length() > 100){
            return new ModelAndView("error")
                    .addObject("error", "Subject is too long");
        }

        if(mail.getSenderMail().length() > 50){
            return new ModelAndView("error")
                    .addObject("error", "Your eMail is too long");
        }

        if(mail.getText().length() > 15000){
            return new ModelAndView("error")
                    .addObject("error", "eMail text is too long");
        }
        Map<String, Object > map = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        map.put("user", userDAO.findByLogin(auth.getName()).getUserName());

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", 465);
        System.out.println("in propert");

        Session session = Session.getDefaultInstance(properties,

                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("cooking.machine.dtno@gmail.com", "cookingmachinedtno1111");
                    }});
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("someuser@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("cooking.machine.dtno@gmail.com"));
            message.setSubject(mail.subject);
            message.setText("From: " + mail.senderMail + "\n"+  mail.text);
            System.out.println("start send");
            Transport.send(message);
        }catch (Exception ex){
            //TODO: error page
            System.out.println("fail");
            return new ModelAndView("error")
                    .addObject("error", ex);
        }
        //TODO: sent page вообще ни
        return new ModelAndView("index", map);
    }


    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public ModelAndView sentSomeMail(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, String> map = new HashMap<>();
        map.put("user", userDAO.findByLogin(auth.getName()).getUserName());
        return new ModelAndView("mail", map).addObject("mailToSend", new Mail());
    }

}


class Mail{
    public String subject;
    public String text;
    public String senderMail;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderMail() {
        return senderMail;
    }

    public void setSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }
}
