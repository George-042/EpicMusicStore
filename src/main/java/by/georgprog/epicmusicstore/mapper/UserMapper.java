package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.RegRequestDto;
import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);

    UserEntity toEntity(RegRequestDto dto);
}
