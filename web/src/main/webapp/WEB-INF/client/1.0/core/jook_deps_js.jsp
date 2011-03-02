<%@page import="com.railinc.jook.web.Constants"%>
<%@page contentType="text/javascript" %>
<%
	String theme = (String) request.getAttribute(Constants.REQUEST_ATTR_THEME_KEY);
%>
<% if (null == request.getParameter("_j") || "true".equals(request.getParameter("_j"))) {%>
	<jsp:include page="/WEB-INF/client/1.0/jquery/jquery-1.4.4.min.js"/>
<% } %>
<% if (null == request.getParameter("_u") || "true".equals(request.getParameter("_u"))) {%>
	<jsp:include page="/WEB-INF/client/1.0/jquery/ui/jquery.ui-1.8.7.custom.min.js"/>
<% } %>
<% if (null == request.getParameter("_m") || "true".equals(request.getParameter("_m"))) {%>
	<jsp:include page="/WEB-INF/client/1.0/jquery/form/jquery.form-2.5.2.js"/>
<% } %>
<% if (null == request.getParameter("_f") || "true".equals(request.getParameter("_f"))) {%>
	<jsp:include page="/WEB-INF/client/1.0/facebox/facebox-1.2_js.jsp"/>
<% } %>
<% if (null == request.getParameter("_g") || "true".equals(request.getParameter("_g"))) {%>
<jsp:include page="/WEB-INF/client/1.0/jgrowl/jquery.jgrowl-1.2.5.js"/>
<% } %>
var gadgets = {};

function gadgets_Prefs(moduleId) {
	var thedata = null;
	jQuery.ajax({
		url:"<%=request.getContextPath()%>/secured/client/preferences/1.0/" + moduleId + ".json",
		dataType:"json",success:function(data){thedata=data;},async:false});
	this.data = thedata;
	this.getString = function(preferenceName) {
		if (typeof(this.data[preferenceName]) == "undefined" || this.data[preferenceName] == null) {return null;}
		return this.data[preferenceName].value;
	};
	this.set = function(key,val) {
		this.data[key] = new Object();
		this.data[key].value = val;
		jQuery.ajax({
		url:"<%=request.getContextPath()%>/secured/client/preferences/1.0/" + moduleId + ".json",
		type:"POST", dataType:"json",data:{key:key,value:val}});
	};

	this.getBool = function(key) { return new Boolean(this.getString(key)) };
	this.getFloat = function(key) { f = parseFloat(this.getString(key)); return isNaN(f) ? 0:f; };
	this.getInt = function(key) { i = parseInt(this.getString(key)); return isNaN(i) ? 0:i; };
	this.getArray = function(key) { a = this.getString(key); return a == null ? new Array(0) : a.split("|"); };
	this.setArray = function(key,values) { if (values == null) return; this.set(key, values.join("|")) };
		
}
gadgets.Prefs = gadgets_Prefs;

var Jook = {
	duration : 100,
	jookTabCount : 0,
	jookDivCount : 0,
	shake : false,
    jooklinks : null, 
    
    
    init : function () {
    	/* setup the "railinc tab" and attach event handlers to it. */
		jQuery('body').append('<div id="jookstage"></div>');
		var jookstage = jQuery("#jookstage");
		jookstage.append("<a id='jooklinksa' href='#'><img title='Click to Toggle' class='vertical' src='<%=request.getContextPath()%>/client/resource/1.0/themes/<%=theme%>/vertical-tab.png' id='jooklinksimg'></img></a>");
		jookstage.append('<div id="jooklinks" style="display:none;"></div>');
    	Jook.jooklinks = jQuery("#jooklinks");
		jQuery("#jooklinksa").click( function() {
			var jooklinksimg = jQuery("#jooklinksimg"); 
			jQuery("#jooklinks").toggle(Jook.duration); 
			jQuery("#jooklinksa").toggleClass("active");
			jooklinksimg.toggleClass("vertical");
			if (jooklinksimg.hasClass("vertical")) {
				jooklinksimg.attr("src", "<%=request.getContextPath()%>/client/resource/1.0/themes/<%=theme%>/vertical-tab.png");
			} else {
				jooklinksimg.attr("src", "<%=request.getContextPath()%>/client/resource/1.0/themes/<%=theme%>/horizontal-tab.png");
			}
			return false; 
		});   
		jQuery("#jooklinksa").click(Jook.shakeDrawerPulls); 	
		jQuery("#jooklinksa").click(Jook.openShakeDrawers);
    },
    
    
    showLoading : function(element) {
    	element.addClass("jookloading");
    },
    doneLoading : function(element) {
    	element.removeClass("jookloading");
		Jook.updateJookPopupLinks();    	
    },
    loadingError : function(element) {
    	Jook.doneLoading(element);
    	element.addClass("jookloadingerror");
    	element.html("There was a problem loading the content for this drawer.");
    },
    
    shakeJookTab : function(element) {
    	element.effect("shake", { distance:10, direction:"up", times:3 }, 300);
    },
	
    shakeDrawerPulls : function() {
    	jQuery(".jooktrig.shake").each(function(idx,el) { Jook.shakeDrawerPull(jQuery(el)); });
    },
	
    openShakeDrawers : function() {
    	jQuery(".jooktrig.shake").each(function(idx,el) { jQuery(el).click(); });
    },
    
    shakeDrawerPull : function(element) {
    	element.effect("shake", { times:3, distance:10 }, 300);
    },
    
  	buildLinks : function(data) { 
		if (null == data) {
			return;
		}
		var originalTabCount = Jook.jookTabCount;
		var originalDivCount = Jook.jookDivCount;
	
	   	Jook.buildTab(data.services);
	   	Jook.buildPopupTab(data.services); 
	    Jook.buildPopupExt(data.services);
		Jook.attachEventHandlers(data.services, originalTabCount);
	
		Jook.displayPopups(data.services);
	    Jook.jookDivCount = Jook.jookTabCount;  
	    if (Jook.shake) {
	    	Jook.shakeJookTab(jQuery("#jooklinksa"));
	    }
	},
	
	maybeShake : function(interaction) {
		if (interaction.shake && interaction.shake == "true") {
			Jook.shake = true;
		}
	},
	
	buildPopupExt : function(services) {
		var pts = services.popupext;
	    for (var i=0;pts && i < pts.length;i++) {
	    	var tabIndex = Jook.jookTabCount;
	    	var a = jQuery("<a></a>");
	    	a.html(pts[i].title);
	    	a.attr("id", "target_trigger_" + tabIndex);
	    	a.attr("target","_new");
	    	a.attr("class", "jooktrig");
	    	a.attr("href", pts[i].url);
	    	Jook.jooklinks.append(a);
	    	Jook.jookTabCount++;
	    }
	},
	
	buildTab : function(services) {
		var ts = services.tab;
		if (!ts) return;
	    for (var i=0;i < ts.length;i++) {
	    	Jook.maybeShake(ts[i]);
	    	
	    	var tabIndex = Jook.jookTabCount;
	    	var a = jQuery("<a></a>");
	    	a.html(ts[i].title);
	    	a.attr("class", "jooktrig");
	    	if (ts[i].shake && ts[i].shake == "true") {
	    		a.attr("class", a.attr("class") + " shake");
	    	}
	    	a.attr("id", "target_trigger_" + tabIndex);
	    	a.attr("href","#");
	    	Jook.jooklinks.append(a);
	    	
	    	Jook.jookTabCount++;
	    	
	    	var divIndex = Jook.jookDivCount;
	    	a = jQuery("<div></div>");
	    	a.addClass("target");
	    	a.addClass("drawer");
	    	a.attr("id", "target_" + divIndex);
	    	a.attr("src", ts[i].url);
	    	Jook.jooklinks.append(a);
	    	Jook.jookDivCount++;
	    }	
	},
	
	buildPopupTab : function(services) {
	    var pts = services.popuptab;
	    for (var i=0;pts && i < pts.length;i++) {
	    	Jook.maybeShake(pts[i]);
	    
	    	var tabIndex = Jook.jookTabCount;
	    	var a = jQuery("<a></a>");
	    	a.html(pts[i].title);
	    	a.attr("id", "target_trigger_" + tabIndex);
	    	a.attr("href","#");
	    	a.attr("class", "jooktrig");
	    	a.attr("src", pts[i].url);
	    	Jook.jooklinks.append(a);
	    	Jook.jookTabCount++;
	    }
	},
	
	attachEventHandlers : function(services, offset) {
	    var ts = services.tab;
	    for (var i=0;ts && i < ts.length;i++) {
	    	var tmp = "#target_" + (i+offset);
	    	
	    	var trigger = jQuery("#target_trigger_" + (i+offset));
	    	trigger.click(function(ev){
			 		var t = jQuery("#" + jQuery(this).attr("id").replace("_trigger",""));
		 			t.toggle(Jook.duration);
		 			jQuery(this).removeClass("shake");
		 			if (!t.hasClass("jookloaded")) {
			 			Jook.showLoading(t);
			 			var src = jQuery("#" + jQuery(this).attr("id").replace("_trigger","")).attr("src");
			 			if (src.indexOf("/") == 0) {
			     			t.load(src,  function(response, status, xhr) {if("error"==status){Jook.loadingError(t);}else {Jook.doneLoading(t);}});
			     		} else {
			     			var a = jQuery("<iframe></iframe>");
	    					a.attr("src", src);
	    					a.attr("frameborder", "0");
	    					t.attr("scrolling","auto");
	    					t.append(a);
			     		}
			     		t.addClass("jookloaded");
		 				
		 			}
		 			jQuery(this).toggleClass("jookactive");
		 			return false;
	 			});
	    }
	    
	    offset += ts ? ts.length : 0;            
	    
	    // attach the click handlers to the popup tab
	    // i think this coul dbe redone by setting a speccifc class on the link and
	    // then just using jquery and facebox to do the work.
	    pts = services.popuptab;
	    for (var i=0;pts && i < pts.length;i++) {
	    	var tmp = "#target_" + (i+offset);
			jQuery("#target_trigger_" + (i+offset)).click(function(){
				var t = jQuery("#" + jQuery(this).attr("id").replace("_trigger",""));
			   	var theUrlToLoad = jQuery(this).attr("src");
			   	Jook.popupWithUrl(theUrlToLoad);
				return false;
			});
			offset++;
	    }                
	},
	
	displayPopups : function(services) {
	    var ps = services.popup;
	    for (var i=0;ps && i < ps.length;i++) {
	        jQuery.facebox({ ajax: ps[i].url }); // can't call Jook.popupWithUrl here.
	    }
	},
	// this is called after loading a tab so that any
	// links inside the tab can be attached and 'faceboxed'
	updateJookPopupLinks : function() {
		jQuery('a.jook_popup_link').facebox()
	},
	
	
	/* public */
	popupWithString : function(simpleStringValue) {
		jQuery.facebox(simpleStringValue); 
	},
	
	/* public */
	popupWithUrl : function(remoteUrl) {
		jQuery.facebox({ ajax:remoteUrl });
	},
	 
	/* public */
	message : function(messageText, argumentsDict) {
		jQuery.jGrowl(messageText, argumentsDict);
	},
	
	/* public */
	closeMyDrawer : function(element) {
		/** pass the jquery selector of an element in the drawer's html like '#jook_feedback_results' **/
		if ("string" == typeof(element)) {
			jQuery(element).parents(".target").prev().trigger('click',this);
		}
	}
	
}
