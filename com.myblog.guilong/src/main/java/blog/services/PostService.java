package blog.services;

import java.util.List;

import blog.models.Post;

public interface PostService {
  List<Post> findAll();
  List<Post> findLatest5();
  Post findById(Long id);
  Post edit(Post post);
  Post create (Post post);
  void deleteById(Long id);
  
}
