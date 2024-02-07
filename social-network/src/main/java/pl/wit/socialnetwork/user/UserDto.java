package pl.wit.socialnetwork.user;

import lombok.Data;

@Data
public class UserDto {
    private String login;
    private String displayName;
    private Integer yearOfBirth;

    public UserDto() {
    }
}
