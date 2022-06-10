package zti.cyberbook.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean follow(String userId, String userToFollowId) {
        return userRepository.followUser(userId, userToFollowId) != null;
    }

    public boolean unfollow(String userId, String userToFollowId) {
        return userRepository.unfollowUser(userId, userToFollowId) == null;
    }

    List<Map<String, Object>> getAllUsersByName(String name, String userId) {

        List<String> ids = getUserIdsWithName(name);
        ids.removeIf(id -> id.equals(userId));

        User clientUser = userRepository.findUserById(userId);

        return userRepository.findUsersByIdIn(ids)
                .stream()
                .map(user -> {
                    Map<String, Object> mappedUser = new HashMap<>();

                    boolean isFollowed = isFollowedUserByClient(clientUser, user);

                    mappedUser.put("isFollowed", isFollowed);
                    mappedUser.put("firstName", user.getFirstName());
                    mappedUser.put("lastName", user.getLastName());
                    mappedUser.put("id", user.getId());

                    return mappedUser;
                } )
                .collect(Collectors.toList());
    }

    private List<String> getUserIdsWithName(String name) {
        List<String> ids = userRepository.findAllUsersByName(name)
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());
        return ids;
    }

    private boolean isFollowedUserByClient(User clientUser, User user) {
        return clientUser.getFollowedUsers()
                .stream()
                .anyMatch(followedUser -> followedUser.getId().equals(user.getId()));
    }

}
