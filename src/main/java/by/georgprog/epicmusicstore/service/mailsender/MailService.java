package by.georgprog.epicmusicstore.service.mailsender;

import by.georgprog.epicmusicstore.dto.UserDto;

import javax.mail.MessagingException;

public interface MailService {

    void sendActivationMessage(UserDto userDto) throws MessagingException;
}
