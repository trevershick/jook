																   <%
																   	String ssoConfig = System.getProperty("sso.configuration");
																   														   int i = 0;
																   														   String usr = com.railinc.jook.ssodeveloperhack.config.Config.getInstance().getProperty("SSO_USR_" + (i++));
																   														   java.util.List<String> usrs = new java.util.ArrayList<String>();

																   														   while (usr!=null)
																   														   {
																   														      usrs.add(usr);
																   														      usr = com.railinc.jook.ssodeveloperhack.config.Config.getInstance().getProperty("SSO_USR_" + (i++));																      
																   														   }
																   %>
																     <select name="sso.userId" style="width:100%;margin-top:5px;font-size:10pt;" >
																     	<% 
																     	for (i=0; i<usrs.size(); i++)
																     	{
																     		String usrId = (String) usrs.get(i);
																     	%>																     	
																        	<option value="<%=usrId%>"><%=usrId%></option>
																        <%
																        }
																        %>
																     </select>

<script type="text/javascript">
//<![CDATA[

	// init
	document.forms[0]["sso.userId"].focus();

//]]>
</script>