package blog.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
	@Size(min=2, max=30, message="Username size should be in the range of [2...30]")
  private String email;
  
	@NotNull
	@Size(min=1, max=50)
  private String password;
  
  public String getEmail(){
	  return email;
  }
  public void setEmail(String email){
	  this.email=email;
	  
  }
  public String getPassword(){
	  return password;
  }
  public void setPassword(String password){
	  this.password=password;
  }
}
