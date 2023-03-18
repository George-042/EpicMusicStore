package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.exeptions.EmailAlreadyExistsException;
import by.georgprog.epicmusicstore.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("reg")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> getRegPage() {
        return ResponseEntity.ok("Registration page");
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateUser(@PathVariable("code") String activationCode) {
        userService.activateUser(activationCode);
        return ResponseEntity.ok("Done");
    }

    @PostMapping
    public ResponseEntity<String> createNewUser(@RequestBody UserDto userDto, @RequestParam String password) {
        try {
            userService.createNewUser(userDto, password);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Msg exception");
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        return ResponseEntity.ok("Created");
    }
}
