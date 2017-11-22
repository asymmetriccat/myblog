package blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import blog.domain.Post;
import blog.services.*;


@RestController
public class HomeController {
	@Autowired
	private PostService postService;
	
	
	@RequestMapping(value="/posts/latest10Posts", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> ShowLatest10Posts() {
		List<Post> latest10Posts = postService.findLatest10();
		HttpStatus status= latest10Posts.isEmpty()? HttpStatus.OK:HttpStatus.NOT_FOUND; 
		return new ResponseEntity<List<Post>>(latest10Posts,status);
	}
	
	
}
