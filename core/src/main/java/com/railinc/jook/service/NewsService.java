package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.NewsItem;

public interface NewsService {
	List<NewsItem> showNews(String moduleId);
	List<NewsItem> getNewsItems(int start, int count, String sortBy, boolean ascending);
	NewsItem save(NewsItem value);
	NewsItem getNewsItem(Long specId);
	void delete(NewsItem newsItem);
	List<?> newsItemsToShow(String moduleId);
}
