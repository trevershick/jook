package com.railinc.jook.sso;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;


public class SSORestService implements SSOService {
	private Log log = LogFactory.getLog(SSORestService.class);
	private HttpClient httpClient = null;
	private SAXParserFactory saxParserFactory;
	private String ssoUserBasePath;
	private String getDataServicesUrl;

	public String getGetDataServicesUrl() {
		return getDataServicesUrl;
	}

	public void setGetDataServicesUrl(String getDataServicesUrl) {
		this.getDataServicesUrl = getDataServicesUrl;
	}

	public void setSsoUserBasePath(String ssoUserBasePath) {
		this.ssoUserBasePath = ssoUserBasePath;
	}

	public synchronized HttpClient getHttpClient() {
		if (httpClient == null) {
			HttpClient tmp = new HttpClient();
			tmp.setHttpConnectionManager(new MultiThreadedHttpConnectionManager());
			httpClient = tmp;
		}
		return httpClient;
	}

	public String getDataServicesUrl() {
		return this.getDataServicesUrl;
	}

	/* (non-Javadoc)
	 * @see com.railinc.jook.sso.SSO#getAppsForUser(java.lang.String)
	 */
	@Override
	public List<Resource> getAppsForUser(String userId) {
		if (null == userId || 0 == userId.trim().length()) {
			return new ArrayList<Resource>(0);
		}
		String theUrl = MessageFormat.format(getDataServicesUrl()
				+ getSsoUserBasePath(), userId);
		log.debug("Executing " + theUrl);

		HttpMethod method = new GetMethod(theUrl);
		InputStream xml = null;

		try {
			int executeMethod = getHttpClient().executeMethod(method);
			if (HttpServletResponse.SC_OK == executeMethod) {
				xml = method.getResponseBodyAsStream();
			} else {
				log.error("Received a " + executeMethod
						+ " response code from " + theUrl + " "
						+ getResponseBodyAsStringQuietly(method));
				// if we get a 404, it's because ther eare no permissions
				// assinged to that user yet
				xml = null;
			}
		} catch (Exception re) {
			if (re instanceof RuntimeException) {
				throw (RuntimeException) re;
			}
			throw new RuntimeException(re);
		}

		try {
			return parseResources(xml);
		} catch (SAXException pe) {
			log.error("Received unparseable xml from " + theUrl + " "
					+ getResponseBodyAsStringQuietly(method));
		} catch (IOException e) {
			log.error("Received an IO Exception from " + theUrl + " "
					+ e.toString());
		} finally {
			try {
				if (xml != null) {
					xml.close();
				}
			} catch (Exception e) {
				log.error(e);
			}
			method.releaseConnection();
		}
		return new ArrayList<Resource>(0);
	}

	protected String getResponseBodyAsStringQuietly(HttpMethod method) {
		String tmp;
		try {
			tmp = method.getResponseBodyAsString();
		} catch (IOException e) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(stringWriter);
			e.printStackTrace(pw);
			pw.close();
			tmp = "Error trying to get the response body:"
					+ stringWriter.toString();
		}
		return tmp;
	}

	protected String getSsoUserBasePath() {
		return this.ssoUserBasePath;
	}

	public List<Resource> parseResources(InputStream xml) throws SAXException,
			IOException {
		List<Resource> resources = new ArrayList<Resource>();
		if (null == xml) {
			return resources;
		}
		SSOResourcesSAXHandler handler = new SSOResourcesSAXHandler(resources);

		SAXParser parser;
		try {
			parser = getParser();
			parser.parse(xml, handler);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		Collections.sort(resources);
		return resources;
	}

	private SAXParser getParser() throws ParserConfigurationException,
			SAXException {
		return getParserFactory().newSAXParser();
	}

	private SAXParserFactory getParserFactory() {
		if (null == saxParserFactory) {
			saxParserFactory = SAXParserFactory.newInstance();
			saxParserFactory.setNamespaceAware(true);
		}
		return saxParserFactory;

	}

	public synchronized void setHttpClient(HttpClient t) {
		this.httpClient = t;
	}


	@Override
	public List<Resource> getAllApplications() {
		// TODO Auto-generated method stub
		return null;
	}

}
