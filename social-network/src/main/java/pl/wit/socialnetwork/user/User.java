package pl.wit.socialnetwork.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import pl.wit.socialnetwork.post.Post;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String displayName;
    private Integer yearOfBirth;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Post> posts;

    public User(){
    }
}
