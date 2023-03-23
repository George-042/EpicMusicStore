package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.PlaylistDto;
import by.georgprog.epicmusicstore.model.PlaylistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaylistMapper {

    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

    PlaylistDto toDto(PlaylistEntity entity);

    PlaylistEntity toEntity(PlaylistDto dto);
}
