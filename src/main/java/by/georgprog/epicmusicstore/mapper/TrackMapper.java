package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.TrackDto;
import by.georgprog.epicmusicstore.model.TrackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackMapper {

    TrackMapper INSTANCE = Mappers.getMapper(TrackMapper.class);

    TrackDto toDto(TrackEntity entity);

    TrackEntity toEntity(TrackDto dto);
}
