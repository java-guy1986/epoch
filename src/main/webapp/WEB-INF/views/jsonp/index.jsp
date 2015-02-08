<%@ page language="java" pageEncoding="UTF-8"%>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.11.1.js"></script>  
<script>  


/* function processJSON (json) {
  alert(json.success);
};
// Create a new script element
var script_element = document.createElement('script');
// Set its source to the JSONP API
script_element.src = 'http://localhost/epoch/jsonp/getJsonpData.do?jsoncallback=processJSON&tags=monkey&tagmode=any&format=json';
// Stick the script element in the page <head>
document.getElementsByTagName('head')[0].appendChild(script_element); */


$.ajax({
    url: "http://localhost:8080/test/jsonp/getJsonpData.do",
    // The name of the callback parameter, as specified by the YQL service
   	jsonpCallback:"callbackFun",
    // Tell jQuery we're expecting JSONP
    dataType: "jsonp",
    // Tell YQL what we want and that we want JSON
    data: {
    },
    // Work with the response
    success: function( response ) {
    	console.log(response);
    }
});


</script>
<head>
	<title>jsonp</title>
</head>
<body>
	Jsonp Hello world! 
</body>

  
  
