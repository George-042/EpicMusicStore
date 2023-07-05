package by.georgprog.epicmusicstore.service.auth;

import by.georgprog.epicmusicstore.dto.user.AuthUserRequest;
import by.georgprog.epicmusicstore.dto.user.RegUserRequest;
import by.georgprog.epicmusicstore.dto.user.TokenDto;
import by.georgprog.epicmusicstore.exeption.badrequest.SendingMessageException;
import by.georgprog.epicmusicstore.exeption.conflict.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.conflict.UsernameAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.unauthorized.InvalidPasswordException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UsernameOrEmailNotFoundException;
import by.georgprog.epicmusicstore.mapper.UserMapper;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.model.user.UserRole;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.service.jwt.JwtService;
import by.georgprog.epicmusicstore.service.mailsender.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void createNewUser(RegUserRequest dto) throws EmailAlreadyExistsException,
            SendingMessageException, UsernameAlreadyExistsException {
        if (userRepository.findByEmail(dto.getEmail().trim()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        if (userRepository.findByName(dto.getName().trim()).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }
        dto.setEmail(dto.getEmail().trim());
        dto.setName(dto.getName().trim());
        dto.setPassword(dto.getPassword().trim());
        String activationCode = UUID.randomUUID().toString();
        mailService.sendActivationMessage(dto, activationCode);

        UserEntity userEntity = userMapper.toEntity(dto);
        userEntity.setActivationCode(activationCode);
        userEntity.setRole(UserRole.USER);
        userEntity.isConfirmed(false);
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        save(userEntity);
    }

    @Override
    public TokenDto authenticateUser(AuthUserRequest dto) throws UsernameOrEmailNotFoundException,
            InvalidPasswordException {
        if (userRepository.findByNameOrEmail(dto.getPrincipal().trim(), dto.getPrincipal().trim()).isEmpty()) {
            throw new UsernameOrEmailNotFoundException();
        }
        if (!passwordEncoder.matches(dto.getCredentials().trim(), userRepository
                .findByNameOrEmail(dto.getPrincipal().trim(), dto.getPrincipal().trim()).get().getPassword())) {
            throw new InvalidPasswordException();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getPrincipal().trim(),
                dto.getCredentials().trim()));
        return new TokenDto(jwtService.generateToken(dto));
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
}
