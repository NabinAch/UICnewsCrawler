package com.uic.newsCrawlerAPI.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uic.newsCrawlerAPI.entity.NewsEntity;
import com.uic.newsCrawlerAPI.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public ArrayList<NewsEntity> getNews() {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "https://newsapi.org/v2/everything?q=university+of+illinois&from=2018-09-01&sortBy=publishedAt&apiKey=da5ab06cebed43a5be8d9859c885058c";
		ResponseEntity<String> response
		  = restTemplate.getForEntity(fooResourceUrl, String.class);
		
		ArrayList<NewsEntity> listNews = new ArrayList<>();
		
		try {
			JsonNode root = objectMapper.readTree(response.getBody());
			String articles = root.findValue("articles").toString();
				
			listNews = objectMapper.readValue(articles, new TypeReference<List<NewsEntity>>(){});
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return listNews;
	}

}
