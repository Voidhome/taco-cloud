package tacos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tacos.dto.UserDto;
import tacos.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto dto);

    UserDto toDto(User user);
}
