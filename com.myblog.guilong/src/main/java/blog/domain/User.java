package blog.domain;



import java.util.Collection;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;

import blog.domain.security.Authority;
import blog.domain.security.UserRole;


@Entity
@Table(name="users")
public class User implements UserDetails{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userId")
   public Long id;
	
	@Column(name="email", nullable=false, unique=true)
   private String email;
   private String phoneNumber;
   private String username;
   private String password;
   private String fullName;
   @OneToOne
   private Account account;
   @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   @JsonIgnore
   private Set<UserRole> userRoles=new HashSet<>();
   
   private boolean enabled=true;
   public Set<UserRole> getUserRoles() {
	return userRoles;
}
public void setUserRoles(Set<UserRole> userRoles) {
	this.userRoles = userRoles;
}
public Account getAccount() {
	return account;
}
public void setAccount(Account account) {
	this.account = account;
}
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
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
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
public User(String username, String fullName, String email, String phoneNumber) {
	
	
	this.username = username;
	this.fullName = fullName;
	this.email=email;
	this.phoneNumber=phoneNumber;
}

@Override
public String toString() {
	return "User [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", userName=" + username
			+ ", password=" + password + ", fullName=" + fullName + ", account=" + account + ", userRoles=" + userRoles
			+ ", enabled=" + enabled + ", posts=" + posts + "]";
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	Set<GrantedAuthority> authorities=new HashSet<>();
	userRoles.forEach(ur->authorities.add(new Authority(ur.getRole().getName())));
	return authorities;
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}






   
}
