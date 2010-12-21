package com.railinc.jook.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SVGServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5418967839103738610L;
	
	private XPathFactory xpathfactory;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		xpathfactory = XPathFactory.newInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("image/svg+xml");
		PrintWriter writer = resp.getWriter();
		
		writer.write("<?xml version=\"1.0\" standalone=\"no\"?>");
		writer.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20010904//EN\" \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">");
		writer.write("<svg width=\"640px\" height=\"480px\" xmlns=\"http://www.w3.org/2000/svg\">");
		
		ServletContext ctx = getServletContext();
		
		URL resource = ctx.getResource("/WEB-INF/web.xml");
		int height = 480;
		int margin = 10;
		int nodeHeight = 50;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document webXmlDocument = db.parse(resource.openStream());
			
			
			XPathExpression compile = xpathfactory.newXPath().compile("/web-app/servlet-mapping");
			NodeList nl = (NodeList) compile.evaluate(webXmlDocument, XPathConstants.NODESET);
			nodeHeight = ( height - (nl.getLength() * margin)) / nl.getLength();
			for (int i=0;i < nl.getLength();i++) {
				Node item = nl.item(i);
				Element urlPattern = DomUtils.getChildElementByTagName((Element) item, "url-pattern");
				String servletName =  DomUtils.getChildElementByTagName((Element) item, "servlet-name").getTextContent();
				servletName = displayNameForServlet(servletName, webXmlDocument);
				String textContent = urlPattern.getTextContent() + " - " + servletName;
//				System.out.println(textContent + " @ " + servletName);
				writer.write(String.format("<rect x=\"%dpx\" y=\"%dpx\" width=\"%dpx\" height=\"%dpx\" fill=\"none\" stroke=\"black\"/>", 
						margin, 
						i* (margin+nodeHeight) + margin, 
						600, nodeHeight));
				writer.write(String.format("<text x=\"%d\" y=\"%d\">%s</text>", 
						margin * 2,
						i* (margin+nodeHeight) + margin + (nodeHeight/2) + 7,
						textContent
						));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		writer.write("<rect x=\"0.5cm\" y=\"0.5cm\" width=\"2cm\" height=\"1cm\"/>");
//		writer.write("<rect x=\"0.5cm\" y=\"2cm\" width=\"1cm\" height=\"1.5cm\"/>");
//		writer.write("<rect x=\"3cm\" y=\"0.5cm\" width=\"1.5cm\" height=\"2cm\"/>");
//		writer.write("<rect x=\"3.5cm\" y=\"3cm\" width=\"1cm\" height=\"0.5cm\"/>");

		writer.write("<rect x=\"1px\" y=\"1px\" width=\"638px\" height=\"478px\" fill=\"none\" stroke=\"#7c7c7c\" stroke-width=\".02cm\" />");
		writer.write("</svg>");
		writer.close();
	}
	
	private String displayNameForServlet(String servletName, Document webXmlDocument) {
		try {
			XPathExpression compile = xpathfactory.newXPath().compile(String.format("/web-app/servlet[servlet-name='%s']/display-name", servletName));
			String evaluate = compile.evaluate(webXmlDocument);
			if (null == evaluate) return servletName;
			return evaluate.trim();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage() + ":" +servletName;
		}
	}

}
