package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.PlaylistEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaylistDto {

    private Long id;
    private String name;
    private List<TrackDto> trackList;
    private UserDto owner;
    private Byte[] playlistPic;

    public PlaylistDto() {
    }

    PlaylistDto(PlaylistEntity playlistEntity) {
        BeanUtils.copyProperties(playlistEntity, this);
    }
}
