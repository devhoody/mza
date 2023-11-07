package com.matzalal.web.service;

import java.util.List;

import com.matzalal.web.entity.LocCategory;
import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;

public interface PostService {

	
	Post add(Post post);

	List<PostView> getPostList();

	List<LocCategory> getCategoryList();

	PostView getById(Long postId);
	
	Post getByIdForEdit(Long postId);

	List<PostView> getViewList(String query, Long locationPostId);

	void edit(Post post);

	void delete(Long postId);

	
//~*~*~*~*~*관리자용~*~*~*~*~*
	
	int countPost();

	List<PostView> getPostByPage(Integer offset, Integer page, Integer pageSize, String query);

	boolean deletePost(List<Long> postIds);

	void deletePost(Long postId);

	
	

	
	
}
