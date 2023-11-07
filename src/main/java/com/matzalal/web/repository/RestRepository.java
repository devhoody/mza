package com.matzalal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matzalal.web.entity.Rest;

@Mapper

public interface RestRepository {
    // List<RestDetailView> findRestDetailView(Long restId);

    // RestDetailView findById(Long restId);
	
	List<Rest> getList();
}
