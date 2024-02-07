package pl.wit.socialnetwork.post;

import lombok.Data;

@Data
public class PostDto {

    private String body;
    private String login;
    private String displayName;
    public PostDto() {
    }
}
