package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.TrackDto;
import by.georgprog.epicmusicstore.model.TrackEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackMapper {

    TrackDto toDto(TrackEntity entity);

    TrackEntity toEntity(TrackDto dto);
}
