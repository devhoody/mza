package com.matzalal.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.service.CommentService;
import com.matzalal.web.service.PostService;


@RestController("apiCommuController")
@RequestMapping("/api/commus")
public class CommuController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
// ---------------------- 포스팅 목록 제공 API ----------------------
	@GetMapping
	public List<PostView> postList(
			@RequestParam(name="q", required = false ) String query,
			@RequestParam(name="loc", required = false) Long locationPostId
			){
		
			System.out.println(query);
			List<PostView> postList = postService.getViewList(query, locationPostId);

			System.out.println("el.dataset.id: "+ locationPostId);
			System.out.println(postList);
		return postList;
	};
	
// ---------------------- 댓글 등록 API ---------------------- 
	
		@PostMapping("comments")
		public Comment reg(
				@RequestBody Comment comment) {
			
				System.out.println("Comment " +comment);

				Comment newOne = commentService.add(comment);
				
				System.out.println("newOne "+ newOne);
				
				return newOne;
		}
		
// ---------------------- 포스팅 삭제 API ----------------------  
		@DeleteMapping("posts/{post-id}")
		public void deletePost(
			@PathVariable("post-id") Long postId){
			
			System.out.println(postId);
			postService.delete(postId);
		}
		
// ---------------------- 댓 삭제 API ----------------------  
		@DeleteMapping("comments/{comment-id}")
		public void deleteComment(
				@PathVariable("comment-id") Long commentId){
			
			System.out.println(commentId);
			commentService.deleteCmt(commentId);
		}
		
		
// ---------------------- 댓글  목록 제공 API ----------------------
//@GetMapping
//public List<PostView> postList(
//		@RequestParam(name="q", required = false ) String query,
//		@RequestParam(name="loc", required = false) Long locationPostId
//		){
//	
//		System.out.println(query);
//		List<PostView> postList = postService.getViewList(query, locationPostId);
//
//		System.out.println("el.dataset.id: "+ locationPostId);
//		System.out.println(postList);
//	return postList;
//};	
		
	
}
	

