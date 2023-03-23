package by.georgprog.epicmusicstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackDto {

    private Long id;
    private String name;
    private AlbumDto album;
    private Date publicationDate;
    private Integer duration;
    private Byte[] trackPic;
}
