package com.uic.newsCrawlerAPI.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uic.newsCrawlerAPI.entity.NewsEntity;
import com.uic.newsCrawlerAPI.service.NewsService;

@RestController
@RequestMapping(path = "news", produces="application/json")
@CrossOrigin(origins="*") 
public class NewsController {

	@Autowired
	NewsService newsService;
	
	@GetMapping
	ArrayList<NewsEntity> getNews(@RequestParam String date){
		System.out.println(date);
		return newsService.getNews(date);
	}
	
}
