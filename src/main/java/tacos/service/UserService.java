package tacos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.dto.UserDto;
import tacos.entity.User;
import tacos.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerNewUser(UserDto userDto) {
        User user=  User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .fullname(userDto.getFullname())
                .street(userDto.getStreet())
                .city(userDto.getCity())
                .state(userDto.getState())
                .zip(userDto.getZip())
                .phoneNumber(userDto.getPhone())
                .build();

        userRepository.save(user);
    }
}