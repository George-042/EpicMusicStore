package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.TrackEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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

    public TrackDto(TrackEntity trackEntity) {
        BeanUtils.copyProperties(trackEntity, this);
    }
}
