<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Explicitly Mapped Page</title>
<script src="/testsupport/jquery-1.4.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/testsupport/jquery.form.js"></script> 
<link href="/testsupport/facebox/facebox.css" media="screen" rel="stylesheet" type="text/css"/>
<script src="/testsupport/facebox/facebox.js" type="text/javascript"></script> 

</head>
<body>
<form id="myForm" name="simpleForm" method="post" action="/testsupport/main/secure/example/query">
<div class="formContainer">
	<div class="section">
		<label class="mandatory">Search For</label>
		<input type="text" name="q" value="${param.q }"/>
		<button type="submit">Search</button>
	</div>
</div>
<div id="theResults">
</div>
</form>

    <script type="text/javascript"> 
    $(document).ready(function() { 
        // wait for the DOM to be loaded 
		var options = { 
	        target:        '#theResults'   // target element(s) to be updated with server response 
   		}; 
 
    	// bind form using 'ajaxForm' 
    		$('#myForm').ajaxForm(options); 
        }); 
    </script> 
</body>
</html>