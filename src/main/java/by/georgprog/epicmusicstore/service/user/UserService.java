package by.georgprog.epicmusicstore.service.user;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeption.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.service.DaoService;

import javax.mail.MessagingException;
import java.util.Optional;

public interface UserService extends DaoService<UserEntity> {

    void createNewUser(UserDto userDto, String password) throws MessagingException,
            EmailAlreadyExistsException;

    void activateUser(String activationCode);

    Optional<UserDto> findByEmail(String email);
}
