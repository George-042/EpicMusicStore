package by.georgprog.epicmusicstore.service.auth;

import by.georgprog.epicmusicstore.dto.AuthRequestDto;
import by.georgprog.epicmusicstore.dto.RegRequestDto;
import by.georgprog.epicmusicstore.dto.TokenDto;
import by.georgprog.epicmusicstore.exeption.badrequest.SendingMessageException;
import by.georgprog.epicmusicstore.exeption.conflict.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.conflict.UsernameAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.unauthorized.InvalidPasswordException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UsernameOrEmailNotFoundException;
import by.georgprog.epicmusicstore.model.user.UserEntity;

public interface AuthenticationService {

    void save(UserEntity user);

    void createNewUser(RegRequestDto dto) throws EmailAlreadyExistsException, SendingMessageException,
            UsernameAlreadyExistsException;

    TokenDto authenticateUser(AuthRequestDto dto) throws UsernameOrEmailNotFoundException, InvalidPasswordException;

    void activateUser(String activationCode);
}
