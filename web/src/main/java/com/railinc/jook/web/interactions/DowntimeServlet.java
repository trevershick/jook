package com.railinc.jook.web.interactions;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.domain.Downtime;
import com.railinc.jook.service.DowntimeService;
import com.railinc.jook.service.ViewTracker;
import com.railinc.jook.service.ViewTrackingService;
import com.railinc.jook.web.Constants;

/**
 * Servlet implementation class DowntimeServlet
 */
public class DowntimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext context;
	private DowntimeService downtimeService;
	private ViewTrackingService viewTracking;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DowntimeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("content");
		String app = request.getParameter(Constants.HTTP_PARAM_JOOK_APP);
		String user = viewTracking != null ? request.getRemoteUser() : null;
		ViewTracker viewTracker = new ViewTracker(this.viewTracking,DowntimeInteractionFactoryImpl.VIEWTRACKING_APPNAME, user);
		
		
		if (request.getParameter("id") != null) {
			Downtime downtimeById = this.downtimeService.getDowntimeById(Long.valueOf(request.getParameter("id")));
			PrintWriter writer = response.getWriter();
			writer.write("<div id='jook_downtime_popup'>");
			writer.write(String.format("<h1>%s</h1>", downtimeById.getTitle()));
			writer.write(String.format("<p>%s</p>", downtimeById.getHtmlContent()));
			writer.write("</div>");
			writer.close();
			
		} else if ("tab".equals(param)) {
			response.setContentType("text/html");
			response.getWriter().write("<h1>Upcoming Downtime</h1>");
			List<Downtime> ds = downtimeService.downtimeOverNextNDays(app, 30);
			
			List<String> idsseen = viewTracker.whatHasUserSeen();
			
			PrintWriter writer = response.getWriter();
			writer.write("<ul>");
			DateFormat df = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT);
			for (Downtime d : ds) {
				String liClass = "";
				if (idsseen.contains(d.getId().toString())) {
					liClass = "jook_seenit";
				} else {
					viewTracker.justSawId(d.getId());
				}
				writer.write(String.format("<li class=\"%s\">", liClass));
				writer.write(df.format(d.getStartTime()));
				writer.write(String.format("<a class='jook_popup_link' href='%s?id=%s'>%s</a>", request.getRequestURI(), d.getId(), d.getTitle()));
				writer.write("</li>");
			}
			writer.write("</ul>");
			
			viewTracker.execute();
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

	@SuppressWarnings("rawtypes")
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		
		Map beans = context.getBeansOfType(DowntimeService.class);
		this.downtimeService = (DowntimeService) (beans.values().iterator().hasNext() ? beans.values().iterator().next() : null);
		
		beans = context.getBeansOfType(ViewTrackingService.class);
		this.viewTracking = (ViewTrackingService) (beans.values().iterator()
				.hasNext() ? beans.values().iterator().next() : null);		
	}

}
