package by.georgprog.epicmusicstore.mapper;

import by.georgprog.epicmusicstore.dto.UserDto;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);
}
