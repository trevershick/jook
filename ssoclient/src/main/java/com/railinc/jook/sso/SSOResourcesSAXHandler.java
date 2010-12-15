package com.railinc.jook.sso;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SSOResourcesSAXHandler extends DefaultHandler {
	private static final String XML_ELEMENT_RESOURCE = "resource";
	
	private StringBuilder characters = new StringBuilder();
	private Resource currentModule = null;
	private List<Resource> list;
	
	
	public SSOResourcesSAXHandler(List<Resource> list) {
		if (list == null) {
			throw new IllegalArgumentException("list cannot be null");
		}
		this.list = list;
	}


	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (XML_ELEMENT_RESOURCE.equals(localName)) {
			currentModule = new Resource();
		}
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		characters.append(ch,start,length);
	}


	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		String elementText = characters.toString().trim();
		characters.delete(0, characters.length());

		if ("url".equals(localName)) {
			currentModule.setAppUrl(elementText);
		} else if ("name".equals(localName)) {
			currentModule.setName(elementText);
		} else if ("resourceId".equals(localName)) {
			currentModule.setId(elementText);
		} else if (XML_ELEMENT_RESOURCE.equals(localName)) {
			this.list.add(currentModule);
		}
		
	}

}