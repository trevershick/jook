<%@page import="com.railinc.jook.web.Constants"%>
<%
	String theme = (String) request.getAttribute(Constants.REQUEST_ATTR_THEME_KEY);
%>
/* see http://spyrestudios.com/how-to-create-a-sexy-vertical-sliding-panel-using-jquery-and-css3/ */
#jookstage {
	position: fixed;
	top: 20px; 
	left: 0px;
}

#jooklinks {
	text-align:left;
}

#jooklinks div.target iframe {
	width:330px;
	height:330px;
}

#jooklinks div.target {
	display: none;
	background-color: white;
	border:1px solid #111111;
	width: 330px;
	min-height:50px;
	height: auto;
	padding: 5px;
	opacity: .85;
}

#jooklinksa {
	display:block;
	text-align:left;
}
#jooklinksa.active {
	/*background:#222222 url(<%=request.getContextPath() %>/client/resource/1.0/themes/<%=theme %>/arrow_down_orange.png) 95% 55% no-repeat;*/
}

#jooklinks a.jooktrig {
	display:block;
	width:100px;
	text-decoration: none;
	font-size: 10pt;
	font-weight:bold;
	color:#CBCBCB;
	padding: 5px 35px 5px 5px;
	background:#020968;
	border:1px solid #333;
	background-size:16px;
}

#jooklinks a.jooktrig:hover {
	color: #444;
	border-color: #33C;
	background: #E9B87F;
	border:1px solid #444444;
}

#jooklinks a.jookactive {
	background-color:#DE953E;
	background-position:95% ;
	background-repeat:no-repeat;
	color:black;
}

#jooklinks a.jookactive:hover {
	color:black;
}

#jooklinks a.shake {
	background-color:#990000;
}

#jooklinks .jookloading {
	
}
#jooklinks div.jookloadingerror {
	font-weight:bold;
	color:red;
	padding-left:10px;
	padding-top:10px;
}