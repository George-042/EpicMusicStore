package by.georgprog.epicmusicstore.dto.user;

import by.georgprog.epicmusicstore.model.user.UserGender;
import by.georgprog.epicmusicstore.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private UserGender gender;
    private Date dateOfBirth;
    private Byte[] userImg;
    private UserRole role;
}
