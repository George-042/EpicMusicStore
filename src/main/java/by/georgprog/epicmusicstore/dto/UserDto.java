package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.model.user.UserGender;
import by.georgprog.epicmusicstore.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String activationCode;
    private UserGender gender;
    private Date dateOfBirth;
    private List<PlaylistDto> playlists;
    private List<AlbumDto> albums;
    private List<TrackDto> trackList;
    private Byte[] userPic;
    private UserRole role;
    private Boolean isConfirmed;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, this);
    }
}
