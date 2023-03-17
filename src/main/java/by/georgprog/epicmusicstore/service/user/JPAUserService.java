package by.georgprog.epicmusicstore.service.user;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeptions.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.model.user.UserRole;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.service.mailsender.MailService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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
    public List<UserDto> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getById(long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(UserDto::new);
    }

    @Override
    public void delete(UserDto dto) {
        userRepository.delete(convertDtoToEntity(dto));
    }

    @Override
    public void save(UserDto dto) {
        userRepository.save(convertDtoToEntity(dto));
    }

    @Override
    public void createNewUser(UserDto userDto) throws MessagingException,
            EmailAlreadyExistsException {
        if (userRepository.findByEmail(userDto.getEmail().trim()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        userDto.setActivationCode(UUID.randomUUID().toString());
        userDto.setEmail(userDto.getEmail().trim());
        mailService.sendActivationMessage(userDto);
        userDto.setRole(UserRole.USER);
        userDto.setIsConfirmed(false);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        save(userDto);
    }

    @Override
    public void activateUser(String activationCode) {
        Optional<UserEntity> optionalUser = userRepository.findByActivationCode(activationCode);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userEntity.setIsConfirmed(true);
            userRepository.save(userEntity);
        }
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(UserDto::new);
    }

    private UserEntity convertDtoToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        if (userDto.getId() != null) {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(userDto.getId());
            if (optionalUserEntity.isPresent()) {
                UserEntity userEntity = optionalUserEntity.get();
                BeanUtils.copyProperties(userDto, userEntity);
                return userEntity;
            }
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return userEntity;
    }
}
