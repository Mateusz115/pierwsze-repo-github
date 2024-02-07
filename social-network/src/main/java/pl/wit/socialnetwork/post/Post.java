package pl.wit.socialnetwork.post;

import jakarta.persistence.*;
import lombok.Data;
import pl.wit.socialnetwork.user.User;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }
}
