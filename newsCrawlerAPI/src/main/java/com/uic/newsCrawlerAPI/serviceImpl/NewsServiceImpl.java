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

	private String newsApiUrl = "https://newsapi.org/v2/everything?q=University+of+illinois+at+chicago&from=fromDate&to=toDate&sortBy=publishedAt&apiKey=1551729b5ada4b89baad54697d45ff15";

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public ArrayList<NewsEntity> getNews(String date) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(addDateToResourceUrl(date), String.class);

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

	private String addDateToResourceUrl(String date) {

		String resourceUrl = newsApiUrl.replace("fromDate", date);
		LocalDate toDate = LocalDate.parse(date);
		toDate = toDate.plusDays(1);
		resourceUrl = resourceUrl.replace("toDate", toDate.toString());
		return resourceUrl;
	}

}
