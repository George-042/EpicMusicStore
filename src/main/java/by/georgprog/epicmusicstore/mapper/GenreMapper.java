package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.GenreDto;
import by.georgprog.epicmusicstore.model.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto toDto(GenreEntity entity);

    GenreEntity toEntity(GenreDto dto);
}
