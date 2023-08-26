package by.georgprog.epicmusicstore.dto.album;

import by.georgprog.epicmusicstore.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

    private Long id;
    private String name;
    private UserDto owner;
    private byte[] albumImg;
}
