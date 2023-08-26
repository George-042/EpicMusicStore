package by.georgprog.epicmusicstore.dto.user;

import by.georgprog.epicmusicstore.model.user.UserGender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RegUserRequest {

    private String name;
    private String email;
    private String password;
    private UserGender gender;
    private Date dateOfBirth;
}
