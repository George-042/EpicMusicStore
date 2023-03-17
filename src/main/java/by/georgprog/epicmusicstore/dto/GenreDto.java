package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String name;
    private List<TrackDto> trackList;

    public GenreDto() {
    }

    public GenreDto(GenreEntity genreEntity) {
        BeanUtils.copyProperties(genreEntity, this);
    }
}
