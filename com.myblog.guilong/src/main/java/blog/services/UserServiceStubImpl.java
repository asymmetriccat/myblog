package blog.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class UserServiceStubImpl implements UserService{

	@Override
	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return Objects.equals(username, password);
	}
     
}
