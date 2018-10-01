package com.uic.newsCrawlerAPI.utility;
import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.uic.newsCrawlerAPI.entity.NewsEntity;

@JsonComponent
public class NewsEntityJsonDeserializer extends JsonDeserializer<NewsEntity> {

	@Override
	public NewsEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		JsonNode sourceNode = node.findValue("source");
		String sourceName = sourceNode.get("name").asText();
		String author = node.get("author").asText();
		String title = node.get("title").asText();
		String description = node.get("description").asText();
		String url = node.get("url").asText();
		String urlToImage = node.get("urlToImage").asText();
		String publishedAt = node.get("publishedAt").asText();
		String content = node.get("content").asText();

		return new NewsEntity(sourceName, author, title, description, url, urlToImage, publishedAt, content);
	}

}
