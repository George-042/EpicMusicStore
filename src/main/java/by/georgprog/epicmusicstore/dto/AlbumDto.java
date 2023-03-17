package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.AlbumEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@AllArgsConstructor
public class AlbumDto {

    private Long id;
    private String name;
    private List<TrackDto> trackList;
    private UserDto owner;
    private Byte[] albumPic;

    public AlbumDto() {
    }

    public AlbumDto(AlbumEntity albumEntity) {
        BeanUtils.copyProperties(albumEntity, this);
    }
}
