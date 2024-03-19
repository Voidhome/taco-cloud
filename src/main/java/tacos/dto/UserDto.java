package tacos.dto;

public record UserDto(

        String username,
        String password,
        String fullname,
        String street,
        String city,
        String state,
        String zip,
        String phoneNumber) {
}