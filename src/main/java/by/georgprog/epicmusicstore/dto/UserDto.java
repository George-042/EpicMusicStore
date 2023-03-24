package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.model.user.UserGender;
import by.georgprog.epicmusicstore.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private UserGender gender;
    private Date dateOfBirth;
    private Byte[] userPic;
    private UserRole role;

    public UserDto(UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, this);
    }
}
