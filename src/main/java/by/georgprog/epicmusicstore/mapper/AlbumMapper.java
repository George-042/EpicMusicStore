package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumMapper {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    AlbumDto toDto(AlbumEntity entity);

    AlbumEntity toEntity(AlbumDto dto);
}
