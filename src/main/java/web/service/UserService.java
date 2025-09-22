package web.service;

import web.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    void save(User user);
    void update(User user);
    void deleteUserById(Long id);
    User get(Long id);
    User findByUsername(String username);
}