package com.railinc.jook.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;

public class SupportFormTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;
	
	String supportUrl = "/jook/mysupport/landing";
	String product = "Unspecified";
	String moduleId = "";

	public String getSupportUrl() {
		return supportUrl;
	}

	public void setSupportUrl(String supportUrl) {
		this.supportUrl = supportUrl;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().append("</form>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		try {
			// interpolate the string with the arguments.
			out.append(evaluateTemplate());
		} catch (IOException e1) {
			throw new JspException(e1);
		}
		
		return EVAL_BODY_INCLUDE;
	}
	
	
	
	protected String evaluateTemplate() throws IOException {
		String template = loadTemplate("SupportForm");
		StringTemplate stringTemplate = new StringTemplate(template);
		return stringTemplate.withArguments(createArgs()).toString();
	}

	protected Map<String,Object> createArgs() {
		Map<String,Object> args = new HashMap<String, Object>();
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		LoggedUser u = UserService.getLoggedUser(request );
		args.put("supportUrl", supportUrl);
		args.put("ssoUserId", u.getUserId());
		args.put("ssoRoles", u.getPermissions().toString());
		args.put("phone", u.getPhoneNumber());
		args.put("company", u.getEmployer());
		args.put("name", u.getName());
		args.put("userEmail", u.getEmailAddress());
		args.put("product", product);
		args.put("moduleId", moduleId);
		Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");
		if (t != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			try {
				sw.close();
			} catch (IOException e) {
			}
			args.put("stackTrace",sw.toString());
		}

		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		sw.append(String.valueOf(request.getAttribute("javax.servlet.error.request_uri")));
		
		Enumeration<?> e = request.getParameterNames();
		pw.append("\nParameters\n--------------------------");
		while (e.hasMoreElements()) {
			String nm = e.nextElement().toString();
			pw.append("\n").append(nm).append("=");
			
			String[] vals = request.getParameterValues(nm);
			for (int i=0;i < vals.length;i++) {
				if (i > 0) pw.append(",");
				pw.append(vals[i]);
			}
		}
		
		e = request.getHeaderNames();
		pw.append("\n\n\nHeaders\n--------------------------");
		while (e.hasMoreElements()) {
			String nm = e.nextElement().toString();
			pw.append("\n").append(nm).append("=");
			
			Enumeration<?> vals = request.getHeaders(nm);
			int i = 0;
			while (vals.hasMoreElements()) {
				if (i > 0) pw.append(",");
				pw.append(vals.nextElement().toString());
				i++;
			}
		}
		
		pw.close();
		try {
			sw.close();
		} catch (IOException e1) {
		}
		args.put("requestDetails", sw.toString());
		return args;
	}
	
	protected String loadTemplate(String templateBaseName) throws IOException  {
		if (null == templateBaseName) {
			throw new IllegalArgumentException("templateBaseName cannot be null");
		}
		String resourcePath = templateBaseName + ".txt";
		 
		InputStream resourceAsStream = SupportFormTag.class.getResourceAsStream(resourcePath);
		if (resourceAsStream == null) {
			throw new MissingResourceException(templateBaseName.concat(".txt"),
					getClass().getName(),
					resourcePath);
		}

		InputStreamReader reader = new InputStreamReader(resourceAsStream);
		char[] buffer = new char[1024];
		int read = 0;
		StringBuilder sb = new StringBuilder();
		while ((read = reader.read(buffer)) > 0) {
			sb.append(buffer,0,read);
		}
		return sb.toString();
	}

}
