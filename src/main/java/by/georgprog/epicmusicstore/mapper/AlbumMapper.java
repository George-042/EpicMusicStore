package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumDto toDto(AlbumEntity entity);

    AlbumEntity toEntity(AlbumDto dto);
}
