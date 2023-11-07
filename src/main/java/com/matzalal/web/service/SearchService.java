package com.matzalal.web.service;

import com.matzalal.web.entity.RestView;

import java.util.List;

public interface SearchService {
    List<RestView> getViewList(String query);
}
