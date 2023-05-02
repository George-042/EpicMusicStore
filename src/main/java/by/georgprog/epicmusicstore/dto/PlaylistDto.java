package by.georgprog.epicmusicstore.dto;

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
    private Byte[] playlistImg;
}
