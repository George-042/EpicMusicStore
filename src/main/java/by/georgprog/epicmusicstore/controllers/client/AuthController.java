package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeption.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.exeption.SendingMessageException;
import by.georgprog.epicmusicstore.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reg")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> getRegPage() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateUser(@PathVariable("code") String activationCode) {
        userService.activateUser(activationCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewUser(@RequestBody UserDto userDto, @RequestParam String password) throws
            EmailAlreadyExistsException, SendingMessageException {
        userService.createNewUser(userDto, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
