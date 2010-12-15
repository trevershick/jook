package com.railinc.jook.web.support;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.railinc.jook.service.ConfigurationService;
import com.railinc.jook.web.Application;
import com.railinc.jook.web.util.StandardController;

public class SupportControllerBaseImpl extends StandardController {
	String address;
	String hostname;
	String versionTxt;
	List<String> revisionText;
	Random random = null;
	ConfigurationService configurationService;
	public SupportControllerBaseImpl() {
		random = new Random(System.currentTimeMillis());
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			hostname = localHost.getHostName();
			address = localHost.getHostAddress();
		} catch (Exception e) {
			address = hostname = e.getMessage();
		}		
	}
	
	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService() {
		if (configurationService == null) {
			throw new IllegalStateException("configurationService is not set");
		}
		return configurationService;
	}

	/**
	 * @param configurationService the configurationService to set
	 */
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@ModelAttribute("random")
	public String getNextRandom() {
		return String.valueOf(random.nextLong());
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("svnRevisionText")
	public List<String> getSvnRevisionText() {
		if (revisionText == null) {
		List<String> txt = new ArrayList<String>();
		try {
			InputStream resourceAsStream = getClass().getResourceAsStream("/svn-revision.txt");
			txt = IOUtils.readLines(resourceAsStream);
		} catch (Exception e) {
			txt.add("Not Available : " + e.getMessage());
		}
		revisionText = txt;
		}
		return revisionText;
	}
	
	@ModelAttribute("versionText")
	public String getVersionText(HttpServletRequest req) {
		if (versionTxt == null) {
			versionTxt = Application.getVersion();
		}
		return this.versionTxt;
	}
	
	@ModelAttribute("hostname")
	public String getHostname() {
		return this.hostname;
	}
	/**
	 * @return the address
	 */
	@ModelAttribute("address")
	public String getAddress() {
		return address;
	}
}
