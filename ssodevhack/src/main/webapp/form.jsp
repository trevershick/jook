<%@ page language="java"%>

<%
	String appName = Config.getInstance().getProperty("appName");
	appName = appName != null ? appName : "My Application";
%>
<%
	com.railinc.sso.rt.UserService userService = com.railinc.sso.rt.UserService
			.getInstance();
	Boolean isAuthenticated = new Boolean(userService
			.isAuthenticated(request));
	pageContext.setAttribute("authenticated", isAuthenticated);
	pageContext.setAttribute("loggedUser",
			com.railinc.sso.rt.UserService.getLoggedUser(request));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.railinc.jook.ssodeveloperhack.config.Config"%>

<script language="JavaScript">

<!-- Hide the script from the old browsers.


	var checkflag = 1;
	function isSingleClick()
	{
		if (checkflag == 1)
		{
			checkflag = checkflag + 1
			return true;
		}
		else
		{
			return false;
	 	}
	}


	function goToPage(page)
	{
		window.location = "<html:rewrite page=''/>" + page;
	}
// End hiding-->

</script>


<form name="login" method="post" action="">
<div style="margin-top:15px;font-weight:bold;">
Login
</div>
<div style="margin-top:15px;font-size:10pt;">
User Id:
</div>
<div>
<jsp:include flush="true" page="userid.jsp" />
</div>
<div style="margin-top:15px;font-size:10pt;">
Site:
</div>
<div>
<select name="app" style="width:100%;margin-top:5px;font-size:10pt;" >
	<option value="/jook">Jook</option>
	<option value="/jooktest">Jook Test App</option>
</select>

</div>			
<input type="submit" class="formButton"	name="OK" value="Login" style="background-color:#00ee00;color:white;height:30px;width:75px;font-size:12pt;font-weight:bold;margin-top:15px;"/>
<span style="margin-left:100px;width:150px;line-height:12pt;font-size:9pt;color:blue;">
Forgot Password?
</span>

</form>

