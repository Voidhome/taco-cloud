package tacos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.dto.UserDto;
import tacos.mapper.UserMapper;
import tacos.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto create (UserDto dto) {
        return Optional.of(dto)
                .map(userMapper::toEntity)
                .map(user -> {user.setPassword(passwordEncoder.encode(dto.password()));
                    return user;
                })
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow();
    }
}