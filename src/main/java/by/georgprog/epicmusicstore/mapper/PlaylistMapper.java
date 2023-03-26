package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.PlaylistDto;
import by.georgprog.epicmusicstore.model.PlaylistEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistDto toDto(PlaylistEntity entity);

    PlaylistEntity toEntity(PlaylistDto dto);
}
