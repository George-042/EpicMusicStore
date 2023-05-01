package by.georgprog.epicmusicstore.service.auth;

import by.georgprog.epicmusicstore.dto.AuthRequestDto;
import by.georgprog.epicmusicstore.dto.RegRequestDto;
import by.georgprog.epicmusicstore.dto.TokenDto;
import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeption.badrequest.SendingMessageException;
import by.georgprog.epicmusicstore.exeption.conflict.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.conflict.UsernameAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.unauthorized.InvalidPasswordException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UsernameOrEmailNotFoundException;
import by.georgprog.epicmusicstore.model.user.UserEntity;

import java.util.Optional;

public interface AuthenticationService {

    void save(UserEntity user);

    Optional<UserDto> findByEmail(String email);

    Optional<UserDto> findByName(String name);

    void createNewUser(RegRequestDto dto) throws EmailAlreadyExistsException, SendingMessageException,
            UsernameAlreadyExistsException;

    TokenDto authenticateUser(AuthRequestDto dto) throws UsernameOrEmailNotFoundException, InvalidPasswordException;

    void activateUser(String activationCode);
}
