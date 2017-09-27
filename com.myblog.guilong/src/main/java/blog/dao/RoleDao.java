package blog.dao;

import org.springframework.data.repository.CrudRepository;

import blog.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer>{
	   Role findByName(String name);
	}
