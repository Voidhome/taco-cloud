package tacos.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.entity.User;

@Data
public class RegistrationForm {

      //TODO
      private final String username;
      private final String password;
      private final String fullname;
      private final String street;
      private final String city;
      private final String state;
      private final String zip;
      private final String phoneNumber;

      public User toUser(PasswordEncoder passwordEncoder) {
            return new User(
                    username,
                    passwordEncoder.encode(password),
                    fullname,
                    street,
                    city,
                    state,
                    zip,
                    phoneNumber
            );
      }
}