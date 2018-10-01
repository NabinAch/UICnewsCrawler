package com.uic.newsCrawlerAPI.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uic.newsCrawlerAPI.entity.NewsEntity;
import com.uic.newsCrawlerAPI.service.NewsService;

@RestController
@RequestMapping("news")
public class NewsController {

	@Autowired
	NewsService newsService;
	
	@GetMapping
	ArrayList<NewsEntity> getNews(){
	
		
		return newsService.getNews();
	}
	
}
