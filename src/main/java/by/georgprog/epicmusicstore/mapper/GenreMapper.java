package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.genre.GenreDto;
import by.georgprog.epicmusicstore.model.GenreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(GenreEntity entity);

    GenreEntity toEntity(GenreDto dto);
}
