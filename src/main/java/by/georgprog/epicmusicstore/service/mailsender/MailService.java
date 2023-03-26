package by.georgprog.epicmusicstore.service.mailsender;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeption.SendingMessageException;

public interface MailService {

    void sendActivationMessage(UserDto userDto, String activationCode) throws SendingMessageException;
}
