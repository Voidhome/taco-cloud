package tacos.service;

import tacos.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto dto);
}