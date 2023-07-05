package by.georgprog.epicmusicstore.service.mailsender;

import by.georgprog.epicmusicstore.dto.user.RegUserRequest;
import by.georgprog.epicmusicstore.exeption.badrequest.SendingMessageException;

public interface MailService {

    void sendActivationMessage(RegUserRequest dto, String activationCode) throws SendingMessageException;
}
