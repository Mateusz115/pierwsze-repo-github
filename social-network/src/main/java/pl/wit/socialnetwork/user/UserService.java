package pl.wit.socialnetwork.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUser(Integer id) {
        return userRepository.findById(id)
                .map(this::mapToDto);
    }

    public UserDto addUser(UserDto userDto) {
        User user = new User();

        user.setLogin(userDto.getLogin());
        user.setDisplayName(userDto.getDisplayName());
        user.setYearOfBirth(userDto.getYearOfBirth());

        return mapToDto(userRepository.save(user));
    }

    public UserDto updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        user.setLogin(userDto.getLogin());
        user.setDisplayName(userDto.getDisplayName());
        user.setYearOfBirth(userDto.getYearOfBirth());

        return mapToDto(userRepository.save(user));

    }


    public UserDto partiallyUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);


        if (userDto.getLogin() != null) user.setLogin(userDto.getLogin());
        if (userDto.getDisplayName() != null) user.setDisplayName(userDto.getDisplayName());
        if (userDto.getYearOfBirth() != null) user.setYearOfBirth(userDto.getYearOfBirth());

        User save = userRepository.save(user);

        return mapToDto(save);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        userRepository.deleteById(id);
    }
    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setLogin(user.getLogin());
        userDto.setDisplayName(user.getDisplayName());
        userDto.setYearOfBirth(user.getYearOfBirth());

        return userDto;
    }
}
