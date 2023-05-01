package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.AuthRequestDto;
import by.georgprog.epicmusicstore.dto.RegRequestDto;
import by.georgprog.epicmusicstore.dto.TokenDto;
import by.georgprog.epicmusicstore.exeption.badrequest.SendingMessageException;
import by.georgprog.epicmusicstore.exeption.conflict.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.conflict.UsernameAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.unauthorized.InvalidPasswordException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UsernameOrEmailNotFoundException;
import by.georgprog.epicmusicstore.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> getRegPage() {
        return new ResponseEntity<>("Reg page", HttpStatus.OK);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateUser(@PathVariable("code") String activationCode) {
        authenticationService.activateUser(activationCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegRequestDto dto) throws
            EmailAlreadyExistsException, SendingMessageException, UsernameAlreadyExistsException {
        authenticationService.createNewUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authenticate(@RequestBody AuthRequestDto dto) throws InvalidPasswordException,
            UsernameOrEmailNotFoundException {
        return new ResponseEntity<>(authenticationService.authenticateUser(dto), HttpStatus.OK);
    }
}
