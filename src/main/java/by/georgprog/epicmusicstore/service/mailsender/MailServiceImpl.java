package by.georgprog.epicmusicstore.service.mailsender;

import by.georgprog.epicmusicstore.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    @Value("${email.server.domain-host}")
    private String emailDomainHost;
    @Value("${email.server.host}")
    private String emailHost;
    @Value("${email.server.port}")
    private String emailPort;
    @Value("${email.server.username}")
    private String emailUsername;
    @Value("${email.server.password}")
    private String emailPassword;
    @Value("${mail.ems.from-address}")
    private String msgFromAddress;

    @Override
    public void sendActivationMessage(UserDto userDto, String activationCode) throws MessagingException {
        String siteName = emailDomainHost.replaceAll("http://", "");
        String subject = String.format("%s activation link!", siteName);
        String message = String.format(MessageTemplates.getActivationMessage(), userDto.getName(), emailDomainHost,
                activationCode);

        sendMessage(userDto, subject, message);
    }

    private void sendMessage(UserDto userDto, String subject, String message) throws MessagingException {
        Properties props = getMailProperties();
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailUsername, emailPassword);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(msgFromAddress);
        InternetAddress[] toAddresses = {new InternetAddress(userDto.getEmail())};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject, StandardCharsets.UTF_8.toString());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(message, "text/html; charset=UTF-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        msg.setContent(multipart);

        Transport.send(msg);
    }

    private Properties getMailProperties() throws AddressException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailHost);
        properties.put("mail.smtp.port", emailPort);
        properties.put("mail.smtp.auth", Boolean.TRUE.toString());
        properties.put("mail.debug", Boolean.FALSE.toString());
        properties.put("mail.smtp.starttls.enable", Boolean.TRUE.toString());
        properties.put("mail.user", emailUsername);
        properties.put("mail.password", emailPassword);
        return properties;
    }
}
