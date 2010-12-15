/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.jook.web.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author sdtxs01
 *
 */
@Controller
@RequestMapping("secure/support/logging")
public class LoggingController extends SupportControllerBaseImpl implements MessageSourceAware {
	
	
	private MessageSource messageSource;

	private void addMessage(ModelMap map, String msgKey, Object ... args) {
		String msg = msgKey;
		if (messageSource != null) {
			msg = messageSource.getMessage(msgKey, args, Locale.getDefault());
		}
		@SuppressWarnings("unchecked")
		List<String> msgs = (List<String>) map.get("messages");
		if (msgs == null) {
			msgs = new ArrayList<String>();
			map.addAttribute("messages",msgs);
		}
		msgs.add(msg);
	}
	
	@RequestMapping("update")
	public String update(@RequestParam(value="n",required=true) String name,@RequestParam(value="l",required=true) String level, ModelMap map) {
		if (StringUtils.isNotBlank(name)) {
			Logger logger = "root".equals(name) ? LogManager.getRootLogger() : LogManager.getLogger(name);
			if (logger != null) {
				logger.setLevel(Level.toLevel(level));
				addMessage(map, "support.logging.controller.level.updated", name, level);
			}
		}
		return list(map);
	}
	@RequestMapping("list")
	public String list(ModelMap map) {
		Enumeration<?> currentLoggers = LogManager.getCurrentLoggers();
		ArrayList<Logger> arrayList = new ArrayList<Logger>();
		while (currentLoggers.hasMoreElements()) {
			arrayList.add((Logger) currentLoggers.nextElement());
		}
		
		Collections.sort(arrayList, 
				new Comparator<Logger>() {public int compare(Logger o1, Logger o2) {return o1.getName().compareTo(o2.getName());}});
		arrayList.add(0,LogManager.getRootLogger());
		map.addAttribute("currentLoggers", arrayList);
		return ".view.support.logging";
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		
	}
}
