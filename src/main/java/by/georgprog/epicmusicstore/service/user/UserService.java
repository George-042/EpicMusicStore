package by.georgprog.epicmusicstore.service.user;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeptions.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.service.DaoService;

import javax.mail.MessagingException;
import java.util.Optional;

public interface UserService extends DaoService<UserDto> {

    void createNewUser(UserDto userDto) throws MessagingException,
            EmailAlreadyExistsException;

    void activateUser(String activationCode);

    Optional<UserDto> findByEmail(String email);
}
