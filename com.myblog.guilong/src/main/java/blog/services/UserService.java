package blog.services;

import java.util.List;

import blog.domain.User;

public interface UserService {
   public boolean authenticate(String username, String password);
   public User findByUsername(String username);
   boolean checkUserExists(String username, String email);
   boolean checkUsernameExists(String username);
   boolean checkEmailExists(String email);
   void save(User user);
   //User createUser(User user, Set<UserRole> userRoles);
   User saveUser(User user);
   List<User> findUserList();
   void enableUser(String username);
   void disableUser(String username);
}
