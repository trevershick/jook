<html>
    <head>
        <title>Railinc Support Details</title>
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mysupportresources/css/main.css"/>
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mysupportresources/css/support.css"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/mysupportresources/images/favicon.ico" type="image/x-icon" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Technical Support Details</title>
        
        <script src="${pageContext.request.contextPath}/mysupportresources/js/prototype/prototype.js"></script>
        <script src="${pageContext.request.contextPath}/mysupportresources/js/application.js"></script>
        <script src="${pageContext.request.contextPath}/mysupportresources/js/swfobject.js"></script>
        <script>
        var winW = 630, winH = 460;

        if (parseInt(navigator.appVersion)>3) {
         if (navigator.appName=="Netscape") {
          winW = window.innerWidth;
          winH = window.innerHeight;
         }
         if (navigator.appName.indexOf("Microsoft")!=-1) {
          winW = document.body.offsetWidth;
          winH = document.body.offsetHeight;
         }
        }
                
        
        </script>
        
        <script>
        	var version = deconcept.SWFObjectUtil.getPlayerVersion();
        	function flashVersion() {
            	return version['major'];
        	}
        </script>
        
        <script>
        	var cookieEnabled=(navigator.cookieEnabled)? true : false
       		//if not IE4+ nor NS6+
       		if (typeof navigator.cookieEnabled=="undefined" && !cookieEnabled){ 
       			document.cookie="testcookie"
       			cookieEnabled=(document.cookie.indexOf("testcookie")!=-1)? true : false
       		}
        </script>
    </head>
    <body>

    	<img src="https://www.railinc.com/railinc-theme/images/theme/logo.png" style="padding:10px"/>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
        </div>
   		

        <table>
        	<tr>
        		<td>




		<div class="specifics">
			<h1>SSO User</h1>
			<div id="sso-details"><img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" /></div>
			
		</div>
		<div class="specifics">
			<h1>Product</h1>
			<div id="product"><img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" /></div>
			
		</div>
		<div class="specifics">
			<h1>Request Details</h1>
			<div id="request-details"><img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" /></div>
			
		</div>
		<div class="specifics">
			<h1>Browser</h1>
			<div id="browser">
			<img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
			</div>
		</div>
		<div class="specifics">
			<h1>Flash Version</h1>
			<div id="flash-version">
			<img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
			</div>
		</div>
		<div class="specifics">
			<h1>Cookies</h1>
			<div id="cookies-enabled">
			<img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
			</div>
		</div>		
		<div class="specifics">
			<h1>Operating System</h1>
			<div id="os">
			<img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
			</div>
		</div>
		<div class="specifics">
			<h1>Screen Resolution</h1>
			<div id="screen-resolution" >
				<img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
			</div>
		</div>
		<div class="specifics">
			<h1>Browser Size</h1>
			<div id="browser-size">
			<img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
			</div>
		</div>
		<div id="ip-address-container" class="specifics">
			<h1>IP Address</h1>
			<div id="ip-address"> 
			<img src="${pageContext.request.contextPath}/mysupportresources/images/spinner.gif" alt="Spinner" />
			</div>
		</div>


		<div class="clear"/>
    	
    	<div id="buttons" class="right">
    		<span id='createACasePlaceholder' class="right coolbutton">
    			<a onClick="return createRapidCase();" href="#">Create a Case</a>
				
			</span>
			<span id="dontCreateCase">
				<a class="right coolbutton">Log it but don't create a case</a>
			</span>
		</div>
				




	
        		</td>
        		<td width="25%">
<div class="rightbox">
    <h1>What is this?</h1>
	<p>This page collects data for your local environment and stores it so that we can better diagnose
	and document the problem you're experiencing.</p>
	</div>
	
	<div class="rightbox">
    <h1>What happens now?</h1>
	<p>
	We urge you to continue using the application.  Our support staff will review the problem and 
	fix the issue if necessary.  If you choose to Create a Case then one will be opened with our 
	support center and you can view that case in the LaunchPad.  If you choose to log the error but not
	open a case, our support center will still follow up on the case but will not contact your directly and
	you will not be able to see the case in LaunchPad.
	</p>
	</div>
	
        		</td>
        	</tr>
        </table>
    
    
    
    
    
   
    	<script language="JavaScript">
    	function createRapidCase() {
    		new Ajax.Updater('buttons', '${pageContext.request.contextPath}/mysupport/createRapidCase', { method:'get' , parameters: {}});
    		return false;
    	}
    	</script>


		

         <script language="JavaScript">
         new Ajax.Updater('screen-resolution', '${pageContext.request.contextPath}/mysupport/screenResolution', { method:'get' , parameters: { x: screen.width, y: screen.height }});
         new Ajax.Updater('browser-size', '${pageContext.request.contextPath}/mysupport/browserSize', { method:'get' , parameters: { x: winW, y: winH }});
         new Ajax.Updater('browser', '${pageContext.request.contextPath}/mysupport/browser', { method:'get' });
         new Ajax.Updater('ip-address', '${pageContext.request.contextPath}/mysupport/ipAddress', { method: 'get' });
         new Ajax.Updater('os', '${pageContext.request.contextPath}/mysupport/os', { method: 'get' });
         new Ajax.Updater('flash-version', '${pageContext.request.contextPath}/mysupport/flashVersion', { method:'get' , parameters: { v: flashVersion()}});
         new Ajax.Updater('cookies-enabled', '${pageContext.request.contextPath}/mysupport/cookiesEnabled', { method:'get' , parameters: { enabled: cookieEnabled }});
         new Ajax.Updater('sso-details', '${pageContext.request.contextPath}/mysupport/ssouser', { method:'get' });
         new Ajax.Updater('request-details', '${pageContext.request.contextPath}/mysupport/requestDetails', { method:'get' });
         new Ajax.Updater('product', '${pageContext.request.contextPath}/mysupport/product', { method:'get' });
         </script>
    </body>
</html>
