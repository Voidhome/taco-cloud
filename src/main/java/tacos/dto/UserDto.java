package tacos.dto;

import lombok.Value;

@Value
public class UserDto {

      String username;
      String password;
      String fullname;
      String street;
      String city;
      String state;
      String zip;
      String phone;
}