package by.georgprog.epicmusicstore.service.auth;

import by.georgprog.epicmusicstore.dto.user.AuthUserRequest;
import by.georgprog.epicmusicstore.dto.user.RegUserRequest;
import by.georgprog.epicmusicstore.dto.user.TokenDto;
import by.georgprog.epicmusicstore.exeption.badrequest.SendingMessageException;
import by.georgprog.epicmusicstore.exeption.conflict.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.conflict.UsernameAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.unauthorized.InvalidPasswordException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UsernameOrEmailNotFoundException;
import by.georgprog.epicmusicstore.model.user.UserEntity;

public interface AuthenticationService {

    void save(UserEntity user);

    void createNewUser(RegUserRequest dto) throws EmailAlreadyExistsException, SendingMessageException,
            UsernameAlreadyExistsException;

    TokenDto authenticateUser(AuthUserRequest dto) throws UsernameOrEmailNotFoundException, InvalidPasswordException;

    void activateUser(String activationCode);
}
