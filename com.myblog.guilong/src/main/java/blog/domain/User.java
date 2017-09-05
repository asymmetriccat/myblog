package blog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="users")
public class User {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userId")
   public Long id;
   private String email;
   private String phoneNumber;
   private String userName;
   private String passwordHash;
   private String fullName;
   
   public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

   
   @OneToMany(mappedBy="author", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  
   @JsonIgnore
   private Set<Post> posts=new HashSet<>();
public long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPasswordHash() {
	return passwordHash;
}
public void setPasswordHash(String passwordHash) {
	this.passwordHash = passwordHash;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public Set<Post> getPosts() {
	return posts;
}
public void setPosts(Set<Post> posts) {
	this.posts = posts;
}

public User() {
	// TODO Auto-generated constructor stub
}
public User(String userName, String fullName, String email, String phoneNumber) {
	
	
	this.userName = userName;
	this.fullName = fullName;
	this.email=email;
	this.phoneNumber=phoneNumber;
}
@Override
public String toString() {
	return "User [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", userName=" + userName
			+ ", passwordHash=" + passwordHash + ", fullName=" + fullName + ", posts=" + posts + "]";
}





   
}
