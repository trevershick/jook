<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Railinc Single Sign On</title>
<link rel="stylesheet" type="text/css" href="css/reset.css"/>
<link rel="stylesheet" type="text/css" href="css/layout.css"/>
<link rel="stylesheet" type="text/css" href="css/color.css"/>
<link rel="stylesheet" type="text/css" href="css/typography.css"/>


</head>

<body>
<div id="wrapper">
	<div id="header">
    	
        <ul id="util-nav" class="horizontal-menu">
        	<li class="userName">signed in as ipacp01</li>
           
            <li><a href="/rportal">your workspace</a></li>
            
            <li><a href="/sso">user services</a></li>
           
            <li><a href="/help">help</a></li>
           
            <li><a href="/contactus">contact us</a></li>
        </ul>
        <div id="logoArea">
        <a href="http://www.railinc.com" class="logo"><img src="images/Railinc-Logo.png" alt="Go to www.railinc.com" /></a>
        <h1 class="logoText">Railinc</h1>
        <h2 class="headerDescription">Railinc Single Sign On</h2>        
        </div>
    </div>
        <div id="container">
	    	<div style="width:880px">
		    	<div style="margin-left:auto;margin-right:auto;margin-bottom:50px;margin-top:50px;width:300px;border:1px solid grey;padding:20px;">
		    	<jsp:include flush="true" page="form.jsp"/>
		    	</div>
		    	
		    	<div style="margin-left:auto;margin-right:auto;width:600px;height:180px;overflow:auto;border:1px solid #3c3c3c;font-size:9pt;padding:20px;">
		    		<jsp:include flush="true" page="appMessages.jsp"/>
		    	</div>
	    	</div>
    	</div>

    

    <div id="footer">
    	
   		<div class="footer-information">
    		<ul class="horizontal-menu">
     			<li><a href="/legal" class="first">legal notices</a></li>
     			<li><a href="/privacy">privacy rights</a></li>
        		<li><a href="/terms">terms of service</a></li>
                <li><a href="/contactus">contact us</a></li>
    		</ul>
    		<div class="copyright">
    			 Copyright &copy; 2010 Railinc. All rights reserved.
    		</div>	
  		</div>	
 	

    </div>
</div>


</body>
</html>
