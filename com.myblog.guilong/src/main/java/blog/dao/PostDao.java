package blog.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import blog.domain.Post;
import blog.domain.User;

public interface PostDao extends CrudRepository<Post, Long>{
	Post findByAuthor(User author);
	Post findByTitle(String title);
	List<Post> findAll();
  
}
