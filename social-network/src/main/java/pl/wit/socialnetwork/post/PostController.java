package pl.wit.socialnetwork.post;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> allPosts = postService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id) {
        return postService.getPost(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<PostDto> addPost(@RequestBody PostRequestDto postRequestDto) {
        try {
            PostDto savePost = postService.addPost(postRequestDto);
            return ResponseEntity.ok().body(savePost);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer id, @RequestBody PostRequestDto postRequestDto) {
        try {
            PostDto updatePost = postService.updatePost(id, postRequestDto);
            return ResponseEntity.ok().body(updatePost);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<PostDto> partiallyPost(@PathVariable Integer id, @RequestBody PostRequestDto postRequestDto) {
        try {
            PostDto updatePost = postService.partiallyPost(id, postRequestDto);
            return ResponseEntity.ok().body(updatePost);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
