package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.PlaylistEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDto {

    private Long id;
    private String name;
    private UserDto owner;
    private Byte[] playlistPic;

    PlaylistDto(PlaylistEntity playlistEntity) {
        BeanUtils.copyProperties(playlistEntity, this);
    }
}
