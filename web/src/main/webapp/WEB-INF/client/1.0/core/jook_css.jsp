/* see http://spyrestudios.com/how-to-create-a-sexy-vertical-sliding-panel-using-jquery-and-css3/ */
#jookstage {
	position: fixed;
	top: 80px; 
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
	-moz-border-radius-topright: 10px;
	-webkit-border-top-right-radius: 10px;
	-moz-border-radius-bottomright: 10px;
	-webkit-border-bottom-right-radius: 10px;
	width: 330px;
	height: auto;
	padding: 5px;
	/*filter: alpha(opacity=85);*/
	opacity: .85;
}

#jooklinksa {
	/*background:black url(<%=request.getContextPath() %>/railinc-template/images/icons/arrow_right_orange.png) 95% 55% no-repeat;*/
	display:block;
	text-align:left;
}
#jooklinksa.active {
	/*background:#222222 url(<%=request.getContextPath() %>/railinc-template/images/icons/arrow_down_orange.png) 95% 55% no-repeat;*/
}

#jooklinks a.jooktrig {
	display:block;
	width:100px;
	text-decoration: none;
	font-size: 10pt;
	color:#fff;
	padding: 10px 35px 10px 25px;
	background:#444444 url(<%=request.getContextPath() %>/railinc-template/images/icons/add.png) 95% no-repeat;
	border:1px solid #333;
	-moz-border-radius-topright: 20px;
	-webkit-border-top-right-radius: 20px;
	-moz-border-radius-bottomright: 20px;
	-webkit-border-bottom-right-radius: 20px;
	-moz-border-radius-bottomleft: 0px;
	-webkit-border-bottom-left-radius: 0px;
	background-size:16px;
}

#jooklinks a.jooktrig:hover{
	color: #444;
	border-color: #33C;
	background: #FFC url(<%=request.getContextPath() %>/railinc-template/images/icons/add.png) 95%  no-repeat;
	border:1px solid #444444;
}

#jooklinks a.jookactive {
	background-color:#993300;
	background-image:url(<%=request.getContextPath() %>/railinc-template/images/icons/delete.png);
	background-position:95% ;
	background-repeat:no-repeat;
}
#jooklinks a.jookactive:hover {
	color:black;
}