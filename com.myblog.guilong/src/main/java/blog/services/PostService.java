package blog.services;

import java.util.List;

import blog.domain.Post;

public interface PostService {
  List<Post> findAll();
  List<Post> findLatest10();
  Post findById(Long id);
  Post edit(Post post);
  Post create (Post post);
  void deleteById(Long id);
  void savePost(Post post);
  
}
