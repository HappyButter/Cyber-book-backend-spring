package zti.cyberbook.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public HashMap<String, Object> registerUser(User user) {
        if (user == null) return null;
        if (authRepository.existsByEmail(user.getEmail())) return null;

        HashMap<String, Object> result = new HashMap<>();
        User newUser = authRepository.save(user);

        result.put("id",newUser.getId());
        result.put("email",newUser.getEmail());
        result.put("firstName",newUser.getFirstName());
        result.put("lastName",newUser.getLastName());

        return result;
    }

    public HashMap<String, Object> loginUser(String email, String password) {

        HashMap<String, Object> result = new HashMap<>();
        User user = authRepository.findUserByEmailAndPassword(email, password);

        if (user == null) return null;

        result.put("id", user.getId());
        result.put("email", user.getEmail());
        result.put("firstName", user.getFirstName());
        result.put("lastName", user.getLastName());

        return result;
    }
}
