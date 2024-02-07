package pl.wit.socialnetwork.post;

import lombok.Data;

@Data
public class PostRequestDto {
    private String bodyPost;
    private Integer userId;
    public PostRequestDto() {
    }
}
