package blog.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.UserDao;
import blog.domain.User;

@Service
public class UserServiceStubImpl implements UserService{
    @Autowired
	private UserDao userDao;
	@Override
	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return Objects.equals(username, password);
	}
	@Override
	public User findByUsername(String username) {
		
		return userDao.findByUserName(username);
	}
	@Override
	public boolean checkUserExists(String username, String email) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean checkUsernameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean checkEmailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void enableUser(String username) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disableUser(String username) {
		// TODO Auto-generated method stub
		
	}
     
}
