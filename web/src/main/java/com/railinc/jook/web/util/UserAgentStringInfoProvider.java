package com.railinc.jook.web.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class reference the apache-codec project for the base64 encoding. You can get it from http://commons.apache.org/codec/
 */
public class UserAgentStringInfoProvider {

	/**
	 * Test method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String userAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_1_2 like Mac OS X; de-de) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7D11 Safari/528.16";
		Properties props = UserAgentStringInfoProvider.getProperties(userAgent);
		for (Object key : props.keySet()) {
			System.out.println(key + "=" + props.getProperty((String) key));
		}
	}

	private static final String urlPattern = "http://{0}/rpc/rpcxml.php";
	private static final String hostName = "user-agent-string.info";
	private static final String key = "free";
	private static final String rawXml = "<?xml version=\"1.0\"?><methodCall><methodName>ua.search</methodName><params><param><value><string>{0}</string></value></param><param><value><string>{1}</string></value></param></params></methodCall>";

	/**
	 * Returns a Properties object for the given userAgent
	 * 
	 * @param userAgent
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static Properties getProperties(String userAgent) throws MalformedURLException, IOException, SAXException,
			ParserConfigurationException {
		Properties prop = new Properties();
		Document doc = getDocument(userAgent);
		NodeList list = doc.getElementsByTagName("struct").item(0).getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n1 = list.item(i);
			if ("member".equals(n1.getNodeName())) {
				String name = getValueFromChild(n1, "name");
				String val = getValueFromFirstChild(getChild(n1, "value"));
				prop.setProperty(name, val);
			}
		}
		return prop;
	}

	private static String getValueFromFirstChild(Node node) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n1 = list.item(i);
			if (n1.getNodeName() != null && !n1.getNodeName().trim().isEmpty() && n1.getChildNodes().getLength() > 0) {
				return n1.getChildNodes().item(0).getNodeValue();
			}
		}
		throw new RuntimeException("Node has no child");
	}

	private static Node getChild(Node node, String nodeName) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n1 = list.item(i);
			if (n1.getNodeName().equals(nodeName)) {
				return n1;
			}
		}
		throw new RuntimeException("Node has no child with nodeName = " + nodeName);
	}

	private static String getValueFromChild(Node node, String nodeName) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n1 = list.item(i);
			if (n1.getNodeName().equals(nodeName) && n1.getChildNodes().getLength() > 0) {
				return n1.getChildNodes().item(0).getNodeValue();
			}
		}
		throw new RuntimeException("Node has no child with nodeName = " + nodeName);
	}

	private static Document getDocument(String userAgent) throws MalformedURLException, IOException, SAXException,
			ParserConfigurationException {
		InputStream is = null;
		OutputStream os = null;
		try {
			String urlString = MessageFormat.format(urlPattern, hostName);
			
			String uaBase64 = new String(Base64.encodeBase64(userAgent.getBytes()),"UTF-8");
			String requestBody = MessageFormat.format(rawXml, uaBase64, key);
			URL url = new URL(urlString);
			URLConnection con = url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.addRequestProperty("content-type", "application/xml");
			con.addRequestProperty("content-length", "" + requestBody.length());
			os = con.getOutputStream();
			os.write(requestBody.getBytes());
			os.flush();
			is = con.getInputStream();
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}
}

