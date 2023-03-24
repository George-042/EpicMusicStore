package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

    private Long id;
    private String name;

    public GenreDto(GenreEntity genreEntity) {
        BeanUtils.copyProperties(genreEntity, this);
    }
}
