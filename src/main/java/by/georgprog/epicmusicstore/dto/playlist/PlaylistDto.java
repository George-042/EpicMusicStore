package by.georgprog.epicmusicstore.dto.playlist;

import by.georgprog.epicmusicstore.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDto {

    private Long id;
    private String name;
    private UserDto owner;
    private byte[] playlistImg;
}
