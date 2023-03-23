package by.georgprog.epicmusicstore.service.user;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeption.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.mapper.UserMapper;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.model.user.UserRole;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.service.mailsender.MailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class JPAUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public JPAUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(UserEntity entity) {
        userRepository.deleteById(entity.getId());
    }

    @Override
    public void save(UserEntity entity) {
        userRepository.save(entity);
    }

    @Override
    public void createNewUser(UserDto userDto, String password) throws MessagingException,
            EmailAlreadyExistsException {
        if (userRepository.findByEmail(userDto.getEmail().trim()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        userDto.setEmail(userDto.getEmail().trim());
        String activationCode = UUID.randomUUID().toString();
        mailService.sendActivationMessage(userDto, activationCode);

        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);
        userEntity.setActivationCode(activationCode);
        userEntity.setRole(UserRole.USER);
        userEntity.isConfirmed(false);
        userEntity.setPassword(passwordEncoder.encode(password));
        save(userEntity);
    }

    @Override
    public void activateUser(String activationCode) {
        Optional<UserEntity> optionalUser = userRepository.findByActivationCode(activationCode);
        if (optionalUser.isPresent() && !optionalUser.get().isConfirmed()) {
            UserEntity userEntity = optionalUser.get();
            userEntity.isConfirmed(true);
            save(userEntity);
        }
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(UserMapper.INSTANCE::toDto);
    }
}
