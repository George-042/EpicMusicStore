package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.user.RegUserRequest;
import by.georgprog.epicmusicstore.dto.user.UserDto;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);

    UserEntity toEntity(RegUserRequest dto);
}
