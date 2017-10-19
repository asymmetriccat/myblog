package blog.services;



import java.io.UncheckedIOException;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import blog.dao.RoleDao;
import blog.dao.UserDao;
import blog.domain.User;
import blog.domain.security.UserRole;

@Service
@Transactional
public class UserServiceStubImpl implements UserService{
	private static final Logger LOG= LoggerFactory.getLogger(UserService.class);
    @Autowired
	private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountService AccountService;
    
    @Autowired
    private RoleDao roleDao;
	@Override
	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		User user=userDao.findByUsername(username);
		return username==user.getUsername()&&password==user.getPassword();
		
	}
	@Override
	public User findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}
	@Override
	public boolean checkUserExists(String username, String email) {
        if(checkUsernameExists(username)||checkEmailExists(email))  return true;
        
		return false;
	}
	@Override
	public boolean checkUsernameExists(String username) {
		if(null!=findByUsername(username))return true;
		else return false;
		
	}
	@Override
	public boolean checkEmailExists(String email) {
		if(null!=findByEmail(email)) return true;
		else return false;
	}
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}
	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	@Override
	public void enableUser(String username) {
		User user=findByUsername(username);
		user.setEnabled(true);
		userDao.save(user);
		
	}
	@Override
	public void disableUser(String username) {
		// TODO Auto-generated method stub
		User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    
	}
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.findByEmail(email);
	}
	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		
			User localUser=userDao.findByUsername(user.getUsername());
		if(localUser!=null) {
			LOG.info("User with username {} already exist. Nothing will be done." , user.getUsername());
		}
		else {
			
			String encryptedPassword=passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			
			for(UserRole ur:userRoles) 
			{
				roleDao.save(ur.getRole());
			}
		
			user.getUserRoles().addAll(userRoles);
			user.setAccount(AccountService.createAccount());
			localUser=userDao.save(user);
			
		}
		return localUser;
	
	
	
	}
	
     
}
