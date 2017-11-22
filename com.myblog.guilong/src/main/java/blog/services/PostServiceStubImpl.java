package blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.PostDao;
import blog.domain.Post;
import blog.domain.User;

@Service
public class PostServiceStubImpl implements PostService {
	
	
	 private List<Post> posts = new ArrayList<Post>();
	    
	 @Autowired
	 private PostDao postDao; 
	@Override
	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return postDao.findAll();
	}

	@Override
	public List<Post> findLatest10() {
		// TODO Auto-generated method stub
		return postDao.findAll().stream().sorted((a,b)->b.getDate().compareTo(a.getDate())).limit(10).collect(Collectors.toList());
	}

	@Override
	public Post findById(Long id) {
		// TODO Auto-generated method stub
		return postDao.findAll().stream().filter(p->Objects.equals(p.getId(), id)).findFirst().orElse(null);
	}

	@Override
	public Post edit(Post post) {
		// TODO Auto-generated method stub
		for(int i=0; i<postDao.findAll().size(); i++){
			if(Objects.equals(postDao.findAll().get(i).getId(), post.getId())){
				this.posts.set(i,post);
				return post;
			}
		}
		throw new RuntimeException("Post not found" + post.getId());
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		for(int i=0; i<postDao.findAll().size();i++){
			if(Objects.equals(postDao.findAll().get(i), id)){
				this.posts.remove(i);
				return;
			}
		}
	}

	@Override
	public Post create(Post post) {
		// TODO Auto-generated method stub
		 post.setId(postDao.findAll().stream().mapToLong(p->p.getId()).max().getAsLong() +1);
		 this.posts.add(post);
		 return post;
	}
	public void savePost(Post post) {
		
	}
 
}
