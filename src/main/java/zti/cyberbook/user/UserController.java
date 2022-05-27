package zti.cyberbook.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user) {
        HashMap<String, Object> newUser =  userService.registerUser(user);

        if(newUser == null) return ResponseEntity.status(HttpStatus.CONFLICT).body("User with such an email already exists");

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Map<String, String> reqBody) {
        HashMap<String, Object> user = userService.loginUser(reqBody.get("email"), reqBody.get("password"));

        if(user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password");

        return ResponseEntity.ok(user);
    }

}
