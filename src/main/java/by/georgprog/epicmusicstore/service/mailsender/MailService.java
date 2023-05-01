package by.georgprog.epicmusicstore.service.mailsender;

import by.georgprog.epicmusicstore.dto.RegRequestDto;
import by.georgprog.epicmusicstore.exeption.badrequest.SendingMessageException;

public interface MailService {

    void sendActivationMessage(RegRequestDto dto, String activationCode) throws SendingMessageException;
}
