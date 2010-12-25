package com.railinc.jook.web.secure;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.railinc.jook.domain.NewsItem;
import com.railinc.jook.service.NewsService;
import com.railinc.jook.service.ViewTrackingService;
import com.railinc.jook.web.interactions.NewsInteractionFactoryImpl;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/news")
public class NewsController extends StandardController {
	private static final String REDIRECT_NEWS_LIST = "redirect:news/list";
	public static final String VIEW_INTERACTION_LIST = ".view.newsItemList";
	private static final String REDIRECT_LIST = "redirect:list";
	public static final String VIEW_INTERACTION_FORM = ".view.newsItemForm";
	public static final String VIEW_INTERACTION_DETAILS = ".view.newsItemDetails";
	

	NewsService service = null;


	public NewsService getService() {
		return service;
	}


	public void setService(NewsService service) {
		this.service = service;
	}

	
	@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

	

	
	@RequestMapping(method=RequestMethod.GET,value="details")
	public String showNewsItemDetails(NewsItem newsItem, Model model)  {
		NewsItem productByName = service.getNewsItem(newsItem.getId());
		 
		model.addAttribute("newsItem", productByName);

		
		return VIEW_INTERACTION_DETAILS;
	}

	
	
	// show edit form
	@RequestMapping(method=RequestMethod.GET,value="update")
	public String showExistingNewsItemForm(@ModelAttribute("newsItem") NewsItem newsItem, Model model)  {
		NewsItem productByName = service.getNewsItem(newsItem.getId());
		if (productByName == null) {
			productByName = new NewsItem();
		}
		model.addAttribute("newsItem", productByName);
		return VIEW_INTERACTION_FORM;
	}



	@RequestMapping(method=RequestMethod.POST,value="update",params="_eventId_cancel")
	public String cancelUpdate(ModelMap map, HttpServletRequest r) {
		message(r,"No action has been taken");
		return REDIRECT_LIST;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="update",params="_eventId_save")
	public String saveNewsItem(@ModelAttribute("newsItem") NewsItem value, BindingResult result, HttpServletRequest r) {
		NewsItem p = getService().getNewsItem(value.getId());
		
		
		
		ValidationUtils.rejectIfEmpty(result, "title", "required");
		ValidationUtils.rejectIfEmpty(result, "description", "required");
		ValidationUtils.rejectIfEmpty(result, "launchDate", "required");
		
		if (result.hasErrors()) {
			return VIEW_INTERACTION_FORM;
		}
		
		if (p != null) {
			p.setDescription(value.getDescription());
			p.setExpirationDate(value.getExpirationDate());
			p.setLaunchDate(value.getLaunchDate());
			p.setLink(value.getLink());
			p.setModuleId(value.getModuleId());
			p.setPublished(value.isPublished());
			p.setTitle(value.getTitle());
		} else {
			p = value;
			p.setAuthoredDate(new Date());
		}
		p.setModuleId(StringUtils.defaultIfEmpty(p.getModuleId(), null));
		getService().save(p);
		message(r, String.format("Successfully saved the newsItem '%s'", p.getTitle()));
		return REDIRECT_LIST;
	}
	
	@RequestMapping(value="update",params="_eventId_delete")
	public String deleteNewsItem(@ModelAttribute("newsItem") final NewsItem value, ModelMap map, HttpServletRequest r) {
		boolean ok = doQuietly(new WebCommand() {
			@Override
			public void doIt() {
				NewsItem p = getService().getNewsItem(value.getId());
				getService().delete(p);
			}
		}, r);
		if (ok) {
			message(r, "The product was deleted.");
			return REDIRECT_LIST;
		} else {
			return VIEW_INTERACTION_FORM;
		}
	}
		
	@RequestMapping(method=RequestMethod.GET,value={""})
	public String allNewsItemsR(Model model) {
		return REDIRECT_NEWS_LIST;
	}	
	
	@RequestMapping("")
	public String redirectLanding() {
		return REDIRECT_NEWS_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"list"})
	public String allNewsItems(Model model) {
		List<NewsItem> newsItems = service.getNewsItems(0, Integer.MAX_VALUE, null, true);
		model.addAttribute("newsItems", newsItems);

		return VIEW_INTERACTION_LIST;
	}
	

	
}
