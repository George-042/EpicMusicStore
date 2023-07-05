package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.album.AlbumDto;
import by.georgprog.epicmusicstore.dto.album.CreateUpdateAlbumRequest;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumDto toDto(AlbumEntity entity);

    AlbumEntity toEntity(AlbumDto dto);

    AlbumEntity toEntity(CreateUpdateAlbumRequest dto);
}
