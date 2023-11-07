package com.matzalal.web.service;

import com.matzalal.web.entity.RestView;
import com.matzalal.web.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImp implements SearchService{

    @Autowired
    private SearchRepository repository;
    @Override
    public List<RestView> getViewList(String query) {
        List<RestView> list =repository.findListByWord(query);
        return list;
    }
}
