package com.railinc.test.testjook.web.global;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {

	@RequestMapping
	public String rss(ModelMap model) {
		
		News news = new News();
		news.setBody("the body");
		news.setHeadline("The Headline");
		
		List<News> n = new ArrayList<News>();
		n.add(news);
		model.addAttribute("news", n);
		
		return "news.rss";
	}
	
	@RequestMapping
	public String html(ModelMap model) {
		
		News news = new News();
		news.setBody("the body");
		news.setHeadline("The Headline");
		
		List<News> n = new ArrayList<News>();
		n.add(news);
		model.addAttribute("news", n);
		
		return "news.html";
	}
	@RequestMapping
	public String json(ModelMap model) {
		
		News news = new News();
		news.setBody("the body");
		news.setHeadline("The Headline");
		
		List<News> n = new ArrayList<News>();
		n.add(news);
		model.addAttribute("news", n);
		
		return "news.json";
	}
}
