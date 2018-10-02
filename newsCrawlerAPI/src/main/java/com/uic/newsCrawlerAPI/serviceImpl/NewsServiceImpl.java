package com.uic.newsCrawlerAPI.serviceImpl;

import java.io.IOException;
import java.time.LocalDate;
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
	public ArrayList<NewsEntity> getNews(String date) {

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://newsapi.org/v2/everything?q=University+of+illinois+at+chicago&from=fromDate&to=toDate&sortBy=publishedAt&apiKey=da5ab06cebed43a5be8d9859c885058c";
		fooResourceUrl=fooResourceUrl.replace("fromDate", date);
		LocalDate toDate = LocalDate.parse(date);
		toDate = toDate.plusDays(1);
		fooResourceUrl=fooResourceUrl.replace("toDate", toDate.toString());
		System.out.println(fooResourceUrl);
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

		ArrayList<NewsEntity> listNews = new ArrayList<>();

		try {
			JsonNode root = objectMapper.readTree(response.getBody());
			String articles = root.findValue("articles").toString();

			listNews = objectMapper.readValue(articles, new TypeReference<List<NewsEntity>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		return listNews;
	}

}
