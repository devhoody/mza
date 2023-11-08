package com.matzalal.web.controller.commu;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matzalal.web.config.auth.MatzalalUserDetails;
import com.matzalal.web.entity.Comment;
import com.matzalal.web.entity.CommentView;
import com.matzalal.web.entity.CommuRanking;
import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;
import com.matzalal.web.service.CommentService;
import com.matzalal.web.service.PostService;

@Controller("CommuController")
@RequestMapping("/commu")
public class CommuController {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	// ---------------------- 커뮤 메인 조회 ----------------------

	@RequestMapping("main")
	public String main(
			Model model,
			Authentication authentication
	// HttpServletRequest request,
	// HttpServletResponse response
	) {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long currentUserId = userDetails.getId();

		List<PostView> postList = postService.getPostList(currentUserId);
		System.out.println("커뮤 메인 postList: " + postList);
		model.addAttribute("postList", postList);

		List<LocCategory> locCateList = postService.getCategoryList();
		System.out.println("커뮤 메인 locCateList: 	" + locCateList);
		model.addAttribute("locCateList", locCateList);

		// --------------- 랭킹 조회

		// [랭킹] 조회수 인기글 _조회수-> 좋아요 -> 댓글
		List<CommuRanking> hitBestList = postService.getHitBestList();
		System.out.println("커뮤 메인 조회수 인기글: " + hitBestList);
		model.addAttribute("hitBestList", hitBestList);

		// [랭킹] 좋아요 인기글 _ 좋아요 -> 댓글
		List<CommuRanking> likesBestList = postService.getLikesBestList();
		System.out.println("커뮤 메인 좋아요 인기글 : " + likesBestList);
		model.addAttribute("likesBestList", likesBestList);

		return "commu/main";

	}

	// ---------------------- 포스팅 등록 ----------------------

	@GetMapping("post/create")
	public String postCreate() {
		System.out.println("get");

		return "commu/post/create";
	}

	@PostMapping("post/create")
	public String postCreate(
			Authentication authentication,

			@RequestParam(name = "user_id", required = true) Long userId,
			@RequestParam(name = "area_id", required = false) Long areaId,
			@RequestParam(required = true) String title,
			@RequestParam(required = true) String content,
			@RequestParam(name = "img1", required = false) String img1

	) throws IOException {

		MatzalalUserDetails userDetails = (MatzalalUserDetails) authentication.getPrincipal();
		System.out.println("지금 접속한 user ID :::::::::" + userDetails.getId());
		Long id = userDetails.getId();

		System.out.println("post");

		Post post = Post.builder()
				.userId(id)
				.areaId(areaId)
				.title(title)
				.content(content)
				.img1(img1)
				.build();
		System.out.println(post);

		postService.add(post);

		return "redirect:/commu/main"; //
	}

	// ---------------------- 포스팅 상세조회 -> 포스팅, 댓글 출력 ----------------------

	@RequestMapping("post/detail")
	public String postdetail(
			@RequestParam(name = "post-id") Long postId,
			Model model) {

		PostView post = postService.getById(postId);
		List<CommentView> commentList = commentService.getById(postId);
		int count = postService.commentCount(postId);
		// post view 만들어둔 것에서 댓글 총 개수 뽑아오기

		System.out.println("상세조회 postId: " + postId);
		System.out.println("상세조회 post: " + post);
		System.out.println("상세조회 commentList: " + commentList);
		System.out.println("상세조회 commentCount: " + count);

		model.addAttribute("post", post);
		model.addAttribute("commentList", commentList);
		//model.addAttribute("count", count);
		return "commu/post/detail";
	}

	// ---------------------- 포스팅 수정 ----------------------

	@GetMapping("post/edit")
	public String postEdit(
			@RequestParam Long postId,
			Model model) {

		Post postDtl = postService.getByIdForEdit(postId);
		List<LocCategory> locCateList = postService.getCategoryList();

		System.out.println("수정 post: " + postDtl);
		System.out.println("수정 locCateList: " + locCateList);

		model.addAttribute("postDtl", postDtl);
		model.addAttribute("locCateList", locCateList);

		return "commu/post/edit";
	}
	// 조회 http://localhost:8000/commu/post/edit?postId=5

	@PutMapping("/postupdate")
	public String update(
			Post post) {

		postService.edit(post);
		System.out.println("수정완료");
		System.out.println(post);
		System.out.println(post.getPostId());

		return "redirect:/commu/main";
	}

	// ---------- 댓글 클릭 시 -> 댓글 조회 ----------- ( 미완성, 댓글 수정먼저 진행중 )

	@RequestMapping("comment/detail")
	public String commentdetail(
			@RequestParam(name = "comment-id") Long commentId,
			Model model) {
		CommentView comment = commentService.getDtlById(commentId);

		System.out.println("상세조회 comment: " + comment);

		model.addAttribute("comment", comment);

		return "commu/comment/edit";
	}

	// ---------- 댓글 오른쪽 확장창 클릭 시 -> 댓글 수정 -----------

	@GetMapping("comment/edit")
	public String commentEdit(
			@RequestParam Long commentId,
			Model model) {

		Comment commentDtl = commentService.getByIdForEdit(commentId);
		System.out.println("수정 commentDtl: " + commentDtl);
		model.addAttribute("commentDtl", commentDtl);

		// CommentView comment = commentService.getDtlById(commentId);
		// System.out.println("수정 comment: "+ comment);
		// model.addAttribute("comment", comment);

		return "commu/comment/edit";
	} // 조회 http://localhost:8000/commu/comment/edit?commentId=5

	@PutMapping("/commentupdate")
	public String update(
			Comment comment) {
		System.out.println("------------------------------------");
		commentService.edit(comment);
		System.out.println(" edit 후  comment: " + comment);

		System.out.println("수정완료");
		System.out.println(comment.getCommentId());

		return "redirect:/commu/main";
	}

}