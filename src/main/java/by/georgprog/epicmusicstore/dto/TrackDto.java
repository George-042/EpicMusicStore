package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.TrackEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TrackDto {

    private Long id;
    private String name;
    private List<UserDto> author;
    private List<GenreDto> genre;
    private AlbumDto album;
    private List<PlaylistDto> inPlaylists;
    private Date publicationDate;
    private Integer duration;
    private Byte[] trackPic;

    public TrackDto() {
    }

    public TrackDto(TrackEntity trackEntity) {
        BeanUtils.copyProperties(trackEntity, this);
    }
}
