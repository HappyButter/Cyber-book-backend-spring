package zti.cyberbook.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService userService) {
        this.authService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user) {
        HashMap<String, Object> newUser =  authService.registerUser(user);

        if(newUser == null) return ResponseEntity.status(HttpStatus.CONFLICT).body("User with such an email already exists");

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Map<String, String> reqBody) {
        HashMap<String, Object> user = authService.loginUser(reqBody.get("email"), reqBody.get("password"));

        if(user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password");

        return ResponseEntity.ok(user);
    }

}
