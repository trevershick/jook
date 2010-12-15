<h1>If you're seeing this</h1>
You have probably created your page definition in <em>tiles.xml</em> and overridden <em>.railincMain</em> but
you have not specified the <em>content</em> attribute in your definition.
<h2>Example</h2>
<pre>
	&lt;definition name="myViewDefinition" extends=".railincMain"&gt;
		&lt;put-attribute name="content" value="/WEB-INF/jsp/myTile.jsp" /&gt;
	&lt;/definition&gt;
</pre>