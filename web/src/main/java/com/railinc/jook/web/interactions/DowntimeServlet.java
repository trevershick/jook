package com.railinc.jook.web.interactions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.Jook;
import com.railinc.jook.domain.Downtime;
import com.railinc.jook.service.DowntimeService;

/**
 * Servlet implementation class DowntimeServlet
 */
public class DowntimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext context;
	private DowntimeService downtimeService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DowntimeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("content");
		String app = request.getParameter(Jook.JOOK_PARAM_APP);
		if ("tab".equals(param)) {
			response.setContentType("text/html");
			response.getWriter().write("<h1>Upcoming Downtime</h1>");
			List<Downtime> ds = downtimeService.downtimeOverNextNDays(app, 30);
			if (ds.size() > 5) {
				ds = ds.subList(0,5);
			}
			PrintWriter writer = response.getWriter();
			for (Downtime d : ds) {
				writer.write("<ul>");
				writer.write("<li>");
	
				writer.write(d.getModuleId());
				writer.write(" on ");
				writer.write(d.getStartTime().toString());
				writer.write("</li>");
				writer.write("</ul>");
			}
			
		} else if ("popup".equals(param)) {	
			Downtime i = downtimeService.imminentDowntime(app);
			if (i != null) {
				response.setContentType("text/html");
				response.getWriter().write(i.getHtmlContent());
			} else {
				response.sendError(HttpServletResponse.SC_NO_CONTENT);
			}
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		Map beans = context.getBeansOfType(DowntimeService.class);
		this.downtimeService = (DowntimeService) (beans.values().iterator().hasNext() ? beans.values().iterator().next() : null);
	}

}
