package zti.cyberbook.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/follow")
    boolean followUser(@RequestBody Map<String, String> reqBody) {
        return userService.follow(reqBody.get("userId"), reqBody.get("userToFollowId"));
    }
    @PostMapping("/unfollow")
    boolean unfollowUser(@RequestBody Map<String, String> reqBody) {
        return userService.unfollow(reqBody.get("userId"), reqBody.get("userToFollowId"));
    }

    @PostMapping("/name")
    List<Map<String, Object>> getUsersByName(@RequestBody Map<String, String> reqBody) {
        String name = reqBody.get("name");
        String userId = reqBody.get("userId");
        return userService.getAllUsersByName(name, userId);
    }

    @PostMapping("/followed")
    List<Map<String, Object>> getFollowedUsersReviews(@RequestBody Map<String, String> reqBody) {
        String userId = reqBody.get("userId");
        return userService.getFollowedUsersReviews(userId);
    }
}
