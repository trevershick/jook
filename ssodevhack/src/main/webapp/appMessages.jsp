<%@ page language="java" %>

<%
String ssoConfig = System.getProperty("sso.configuration");
String s = "\\target\\classes\\";
int ix = ssoConfig.indexOf(s);
if (ix > -1) {
	ssoConfig = ssoConfig.substring(0, ix) + "\\src\\main\\resources\\" + ssoConfig.substring(ix+s.length(), ssoConfig.length());
}
%>
<%
StringBuffer sb = new StringBuffer( "");
if (ssoConfig==null)
{
	%>
	<p><b><font color="red">The "sso.configuration" java system property has not been defined. It must be the full path 
	to your sso properties file. Please define it and restart the application server.
	</font></b></p><%
		}	
	sb.append("The following sso keys are not defined in the sso properties file \"<font color=\"blue\">" + ssoConfig + "</font>\": <ul>");
	String[] keys = {"appUrl", "appLogoutUrl", "SSO_USR_0","appName", "appDescription"};
	String[] msgs = {"the welcome page for your application", 
		"the SSO logout URL for your application. You must map the com.railinc.sso.rt.LogoutServlet (or a properly written subclass) to a URL in your web.xml file for your application and then provide that URL in your sso properties file. SSO will use this URL to log the user out of your application. Please read the javadocs for the LogoutServlet class for more information.",
		"a user id. You can have as many as you want using a zero-based index as the suffix. Each user id must have an accompanying auth string associated with it using the user id as the key. Read the \"SSO Development Hack\" notice below for information on constructing these auth strings.",
		"(optional) the name of your application", 
		"(optional) shows a description on the login page for your application"};
	boolean keysMissing = false;
	for (int i=0; i<keys.length; i++)
	{
		String val = com.railinc.jook.ssodeveloperhack.config.Config.getInstance().getProperty(keys[i]);
		if (val==null)
		{
			sb.append("<li><b><font color=\"blue\">" + keys[i] + "</font></b>: " + msgs[i] + "</li>");
			keysMissing = true;
		}
	}
	if (!keysMissing) { sb.append("<li>All keys have been set.</li>");}
	sb.append("</ul>");

	String appMessages = sb.toString();
	%>
<%=appMessages%>
					
<H3>SSO Development Hack - Bypassing the Use of an SSO Server</H3>

<p>SSO user data is stored in a request header called "user" and in indexed cookies
called "SSO_USR_DATA0", "SSO_USR_DATA1", etc. The UserService.getLoggedUser() method
builds an "auth" string from the header and cookie information and then uses that "auth"
string to create the LoggedUser instance.</p>

<pre>
   SSO header and cookies ==&gt; auth string ==&gt; LoggedUser
</pre>

<p>In order to bypass the use of an SSO server for accessing user information for development
purposes, you can build your own auth string to simulate a user that is logged into
SSO. Simply provide this string to your application through a properties file as described below.</p>

<p>To learn how to construct an auth string, let us start by looking at an example auth string:</p>

<pre>
   FIRST_NAME=Robert*LAST_NAME=Kainz*EMAIL=robert\.kainz@railinc\.com*TITLE=Software Developer*COMPANY=RAIL - RAILINC CORPORATION*PHONE=1\.919\.6515019*FAX=1\.919\.6515000*STATUS=ACTIVE*EMAILCONF=1*ADDRESS1=5001 Weston Parkway*ADDRESS2=Suite 200*CITY=Cary*STATE=NC*ZIP=27513*COUNTRY=US*TYPE=undef*TYPE_DESC=Unknown*EMAIL_CHANGE_DATE=08-15-2005*ACCT_REVAL_DATE=08-01-2006*USERID=rkainz*CUSTOM1=rkainz*CUSTOM2=*AUTHS=TOISCRT.BNSF&amp;EWCOMPADM.AA&amp;EWQUERY.AA&amp;ISSTAPPADM
</pre>   

<p>It contains the user's permissions as well as some limited user profile information.
The key=value pairs are delimited by asterisks (*). The permissions are stored in the "AUTHS" value. 
Here is a simplified example:</p>

<pre>
   AUTHS=TOISCRT.BNSF&amp;EWCOMPADM.AA&amp;EWQUERY.AA&amp;ISSTAPPADM
</pre>
   
<p>Each role is delimited by an ampersand (&amp;), and may or may not have entities (companies) associated
with it. If there is a company associated with the role, it will follow the role id 
immediately with a period separating the role id and company name.</p>

<p>Also, a single 
role may have multiple entities associated with it. This is not illustrated above. If there
were multiple entities, they would be delimited by a pipe (|). An example of a user
that has ISSTAPPUSR access for BNSF, AA and RAIL roadmarks would be:</p>

<pre>
   AUTHS=ISSTAPPUSR.BNSF|AA|RAIL&amp;TOISCRT.BNSF
</pre>

<p>In the first example above, the user has the Early Warning Company Admin (EWCOMPADM) role
for the roadmark AA and Early Warning Query (EWQUERY) role for the roadmark AA.</p>

<p>The user above also has Chicago Gateway Create Train Lineup access (TOISCRT) for the BNSF
roadmark, and is also an ISSTracker Application Administrator (ISSTAPPADM). Note that
ISSTAPPADM has no roadmarks associated with it.</p>

<p>To allow bypassing of SSO functionality for development purposes, the SSO runtime library allows
the user to set the auth string and user id using a properties file. In this case
the auth string is provided in its entirety (not split on multiple lines, as they are when defined in the cookies.)</p>

<p>There are several differences discussed below. Below is an example of the property file key/value pairs</p>

<H4>Example Properties File for Bypassing the SSO Server</H4>

<PRE>
   #appUrl is the URL that you should be redirected to after a successful login.
   appUrl=http://localhost:9080/myapp/welcome.jsp
   
   #appLogoutUrl is the URL that SSO uses to log the user out of the specific application. Typically, the com.railinc.sso.rt.LogoutServlet
   #is mapped to a URL in the application's web.xml file.
   appLogoutUrl=http://localhost:9080/myapp/logout

   #appName is the name of your application.
   appName=My Application

   #each user has an SSO_USR_* tag mapped to the user id and its user id mapped to the auth string.
   SSO_USR_0=rkainz
   rkainz=FIRST_NAME=Robert*LAST_NAME=Kainz*EMAIL=robert\.kainz@railinc\.com*TITLE=Software Developer*COMPANY=RAIL - RAILINC CORPORATION*PHONE=1\.919\.6515019*FAX=1\.919\.6515000*STATUS=ACTIVE*EMAILCONF=1*ADDRESS1=5001 Weston Parkway*ADDRESS2=Suite 200*CITY=Cary*STATE=NC*ZIP=27513*COUNTRY=US*TYPE=undef*TYPE_DESC=Unknown*EMAIL_CHANGE_DATE=08-15-2005*ACCT_REVAL_DATE=08-01-2006*USERID=rkainz*CUSTOM1=rkainz*CUSTOM2=*AUTHS=TOISCRT.BNSF&amp;spradm&amp;EWCOMPADM.AA&amp;EWQUERY.AA&amp;EWASSIGN.AA&amp;EWINSPECT.AA&amp;ISSTAPPUSR.AA&amp;TOISUSR.BNSF&amp;ISSTAPPADM&amp;SSOADM&amp;TOISAPPADM&amp;TOISCTCO&amp;RENWEBUSR.AA&amp;EWAPPADM
   SSO_USR_1=rkainz2
   rkainz2=FIRST_NAME=Robert*LAST_NAME=Kainz*EMAIL=robert\.kainz@railinc\.com*TITLE=Software Developer*COMPANY=RAIL - RAILINC CORPORATION*PHONE=1\.919\.6515019*FAX=1\.919\.6515000*STATUS=ACTIVE*EMAILCONF=1*ADDRESS1=5001 Weston Parkway*ADDRESS2=Suite 200*CITY=Cary*STATE=NC*ZIP=27513*COUNTRY=US*TYPE=undef*TYPE_DESC=Unknown*EMAIL_CHANGE_DATE=08-15-2005*ACCT_REVAL_DATE=08-01-2006*USERID=rkainz*CUSTOM1=rkainz*CUSTOM2=*AUTHS=EWCOMPADM.AA
</PRE>


<p>The user ids are stored in a zero-based indexed array of properties with the prefix "SSO_USR_" as seen above. Each user id has an
auth string associated with it using the user id as the key.</p>   

<p>When using a properties file, the &quot;sso.configuration&quot; JVM system property is required to tell the SSO runtime the full path to the properties file.</p>
<pre>
   sso.configuration - system property indicating the full path to the properties file.
   com.railinc.sso.rt.debug - can be either true or false to indicate whether or not to print SSO runtime debug messages.
</pre>							

