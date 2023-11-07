package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Post;
import com.matzalal.web.entity.PostView;

@Mapper
public interface PostRepository {
	

	List<PostView> findPostAll();

	void save(Post post);

	Post last();

	PostView findById(Long postId);

	Post findByIdForEdit(Long postId);

	List<PostView> findViewAll(String query, Long locationPostId);

	void modify(Post post);
	
	//~~~~ 관리자용~~~~
	int count();

	List<PostView> findAllByPage(Integer offset, Integer page, Integer pageSize, String query);

	void delete(Long postId);

	void deletePosts(List<Long> postList);



}
