package blog.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import blog.domain.Post;
import blog.services.PostService;


@RestController
public class PostController {
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/posts/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> getPost (@PathVariable Long id){
	Post post=postService.findById(id);
	HttpStatus status= post==null? HttpStatus.NOT_FOUND:HttpStatus.OK; 
	return new ResponseEntity<Post>(post,status);	
	}
	
	@RequestMapping(value="/user/{user_id}/post", method=RequestMethod.POST)
	public ResponseEntity<Post> creatPost(@PathVariable String user_id, @RequestBody Post input) {
		Post newPost=postService.create(input);
		HttpStatus status=HttpStatus.OK;
	 
		return new ResponseEntity<Post>(newPost, status);
   }
	
	@RequestMapping(value="/posts", method=RequestMethod.DELETE)
	public ResponseEntity<String> deletePost(@PathVariable Long post_id){
		HttpStatus status;
		Post toDelete =postService.findById(post_id);
		if (toDelete!=null) {
		postService.deleteById(post_id);
		status=HttpStatus.OK;
		}
		else status =HttpStatus.NOT_FOUND;
		return new ResponseEntity<String>("Post was deleted",status);
	}
}
