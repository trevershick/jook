<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  http://www.rss-tools.com/rss-example.htm  -->
<rss version="2.0">
<channel>
<title>RSS News Feed</title>
<!-- <description>N/A</description> -->
<!-- <link>http://www.domain.com/link.htm</link> -->
<!-- <lastBuildDate>Mon, 28 Aug 2006 11:12:55 -0400 </lastBuildDate>-->
<!-- <pubDate>Tue, 29 Aug 2006 09:00:00 -0400</pubDate> -->
<c:forEach items="${news}" var="article">
	<item>
		<title>${article.headline }</title>
		<description>${article.body }</description>
		<pubDate>${article.published }</pubDate>
	</item>
</c:forEach>
</channel>
</rss>