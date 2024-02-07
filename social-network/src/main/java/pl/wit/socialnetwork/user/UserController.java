package pl.wit.socialnetwork.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        try {
            UserDto updateUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok().body(updateUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }

    @PatchMapping("{id}")
    public ResponseEntity<UserDto> partiallyUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        try {
            UserDto updateUser = userService.partiallyUser(id, userDto);
            return ResponseEntity.ok().body(updateUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }
}
