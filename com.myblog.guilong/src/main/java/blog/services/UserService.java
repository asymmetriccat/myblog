package blog.services;

import java.util.List;
import java.util.Set;

import blog.domain.User;
import blog.domain.security.UserRole;

public interface UserService {
   public boolean authenticate(String username, String password);
   
   public User findByUsername(String username);
   public User findByEmail(String email);
   boolean checkUserExists(String username, String email);
   boolean checkUsernameExists(String username);
   boolean checkEmailExists(String email);
  
   User createUser(User user, Set<UserRole> userRoles);
   User saveUser(User user);
   List<User> findUserList();
   void enableUser(String username);
   void disableUser(String username);
  
}
