<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<tiles:insertAttribute name="headContent"/>
	<tiles:insertAttribute name="additionalHeadContent"/>

	<script type="text/javascript">
	//<![CDATA[

		initPageLoaderDoneFn = function()
		{

			try
			{
				// display content div, and hide the loading message
				pageLoader.done();
			} catch( error )
			{
				var allEls = document.getElementsByTagName("body")[0].childNodes;
				var el;
				var elId;

				for ( var i = 0; i < allEls.length; i++ )
				{
					el = allEls.item(i);

					if ( el.nodeType == 1 ) // if this is an element
					{
						show( el );
					}
				}
			}
		}

		bodyOnloadFunctions.push( initPageLoaderDoneFn );

	//]]>
	</script>

</head>

<body onload="wrapperOnload();" onunload="wrapperOnunload();">

	<div id="content" class="hidden">
		<tiles:insertAttribute name="content"/>
	</div><!-- end content -->
</body>
</html>
