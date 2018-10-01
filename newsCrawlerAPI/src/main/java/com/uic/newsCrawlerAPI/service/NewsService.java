package com.uic.newsCrawlerAPI.service;

import java.util.ArrayList;

import com.uic.newsCrawlerAPI.entity.NewsEntity;

public interface NewsService {

	ArrayList<NewsEntity> getNews();
}
