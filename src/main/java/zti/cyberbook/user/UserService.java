package zti.cyberbook.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HashMap<String, Object> registerUser(User user) {
        if (user == null) return null;
        if (userRepository.existsByEmail(user.getEmail())) return null;

        HashMap<String, Object> result = new HashMap<>();
        User newUser = userRepository.save(user);

        result.put("id",newUser.getId());
        result.put("email",newUser.getEmail());
        result.put("firstName",newUser.getFirstName());
        result.put("lastName",newUser.getLastName());

        return result;
    }

    public HashMap<String, Object> loginUser(String email, String password) {

        HashMap<String, Object> result = new HashMap<>();
        User user = userRepository.findUserByEmailAndAndPassword(email, password);

        if (user == null) return null;

        result.put("id", user.getId());
        result.put("email", user.getEmail());
        result.put("firstName", user.getFirstName());
        result.put("lastName", user.getLastName());

        return result;
    }
}
