package com.matzalal.web.service;

import com.matzalal.web.entity.RestDetailView;
import com.matzalal.web.repository.RestDetailViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestDetailViewServiceImp implements RestDetailViewService {

    @Autowired
    private RestDetailViewRepository repository;

    @Override
    public List<RestDetailView> getRestDetailView(Long restId) {
        List<RestDetailView> list = repository.findRestDetailView(restId);

        return list;
    }

    @Override
    public RestDetailView getRestDetailViewByid(Long restId) {
        return repository.findById(restId);
    }
}
