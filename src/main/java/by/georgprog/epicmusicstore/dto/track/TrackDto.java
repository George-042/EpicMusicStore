package by.georgprog.epicmusicstore.dto.track;

import by.georgprog.epicmusicstore.dto.album.AlbumDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {

    private Long id;
    private String name;
    private AlbumDto album;
    private Date publicationDate;
    private Integer duration;
    private byte[] trackImg;
    private byte[] file;
}
