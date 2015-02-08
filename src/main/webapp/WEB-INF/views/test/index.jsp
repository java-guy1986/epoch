<%@ page language="java" pageEncoding="UTF-8"%>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.11.1.js"></script>  
  
<script type="text/javascript">  

function jsonMutiArguments(){
	 var data = {
			//id:"1",
			//name:"zhangsan",
			userList:[{username:"zhangsan", password:"123"},{username:"wangwu", password:"fuck"}]
		};
	var url = "<%=request.getContextPath()%>"+"/test/jsonMutiArguments.do";
	$.ajax({
		 	url : url,
        type : "POST",
        contentType: "application/json; charset=utf-8",
        datatype:"json",
        data : JSON.stringify(data),
        success : function(data, stats) {
        }
    });
}

	function passIdArrays(){
            var data = {ids:['1','2','3']};
    		var url = "<%=request.getContextPath()%>"+"/test/getArrayData.do";
    		$.ajax({
    			 	url : url,
    	            type : "POST",
    	            contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	            datatype:"json",
    	            data : data,
    	            success : function(data, stats) {
    	            }
                });
	}
	
	function passObjectArrays(){
        var data = [{id:"123", modelId:"444", username:"zhangsan",password:"123"},{id:"333", modelId:"555", username:"lisi", password:"234"}];
		var url = "<%=request.getContextPath()%>"+"/test/getObjectArrayData.do";
		$.ajax({
			 	url : url,
	            type : "POST",
	            contentType: "application/json; charset=utf-8",
	            datatype:"json",
	            data : JSON.stringify(data),
	            success : function(data, stats) {
	            }
            });
	}
	
	function passMutiObjectArrays(){
        var data = {
       				id:"id_123",
       				name:"username_123",
       				createTime:"2014-08-21 13:12:13",
       				offset:"0",
       				size:"20",
                   	userList:[{username:"zhangsan", password:"123"},{username:"wangwu", password:"fuck"}],
                   	roleList:[{name:"role", description:"desc1"},{name:"wangwu", description:"desc2"}]
        			};
		var url = "<%=request.getContextPath()%>"+"/test/getMutiObjectArrayData.do";
		$.ajax({
			 	url : url,
	            type : "POST",
	            contentType: "application/json; charset=utf-8",
	            datatype:"json",
	         	data : JSON.stringify(data),
	            success : function(data, stats) {
	            }
            });
	}
	
	function passMutiTypeMap(){
		 var data = {
 				id:"id_123",
 				name:"username_123",
 				createTime:"2014-08-21 13:12:13",
             	userList:[{username:"zhangsan", password:"123"},{username:"wangwu", password:"fuck"}],
             	roleList:[{name:"role", description:"desc1"},{name:"wangwu", description:"desc2"}]
 			};
		var url = "<%=request.getContextPath()%>"+"/test/getMutiTypeMapData.do";
		$.ajax({
			 	url : url,
	         type : "POST",
	         contentType: "application/json; charset=utf-8",
	         datatype:"json",
	         data : JSON.stringify(data),
	         success : function(data, stats) {
	         }
	     });
	}
	
	function passSimpleTypeMap(){
		 var data = {
				username:"zhangsan",
				password:"lisi",
				createTime:"2014-10-12 12:00:00"
			};
		var url = "<%=request.getContextPath()%>"+"/test/getSimpleTypeMapData.do";
		$.ajax({
			 	url : url,
	         type : "POST",
	         contentType: "application/json; charset=utf-8",
	         datatype:"json",
	         data : JSON.stringify(data),
	         success : function(data, stats) {
	         }
	     });
	}
	
	function getApplicationContext(){
		 var data = {};
			var url = "<%=request.getContextPath()%>"+"/test/getApplicationContext.do";
			$.ajax({
				 	url : url,
		         type : "POST",
		         contentType: "application/json; charset=utf-8",
		         datatype:"json",
		         data : data,
		         success : function(data, stats) {
		         }
		     });
	}
	
	function passSimpleDateParameter(){
		 var data = {beginTime:"2014-12-10 12:00:00"};
			var url = "<%=request.getContextPath()%>"+"/test/passSimpleDateParameters.do";
			$.ajax({
				 url : url,
		         type : "POST",
		        // contentType: "application/json; charset=utf-8",
		         datatype:"json",
		         data : data,
		         success : function(data, stats) {
		         }
		     });
	}
	
	function passJsonString(){
		 var data = {
					username:"zhangsan",
					password:"lisi",
					createTime:"2014-10-12 12:00:00"
				};
			var url = "<%=request.getContextPath()%>"+"/test/passJsonString.do";
			$.ajax({
				 	url : url,
		         type : "POST",
		         contentType: "application/json; charset=utf-8",
		         datatype:"json",
		         data : JSON.stringify(data),
		         success : function(data, stats) {
		         }
		     });
	}
	
	
	
</script>  
<input type="button" value="自定义入参标签" onclick="jsonMutiArguments();"/> <br> 
<input type="button" value="id数组传递测试" onclick="passIdArrays();"/> <br> 
<input type="button" value="对象数组传递测试" onclick="passObjectArrays();"/>  <br>
<input type="button" value="多种对象数组传递测试" onclick="passMutiObjectArrays();"/> <br>
-------------------------------------------------------------------------------------<br>

<input type="button" value="简单Map传递测试" onclick="passSimpleTypeMap();"/> <br>
<input type="button" value="复杂Map传递测试" onclick="passMutiTypeMap();"/> <br> 
<input type="button" value="单一日期参数传递" onclick="passSimpleDateParameter();"/><br>
----------------------------------------------------------------------------------------<br>

<input type="button" value="自定义标签jsonString传递测试" onclick="passJsonString();"/><br>

-----------------------------------------------------------------------------------------<br>
<input type="button" value="applicationContext获取" onclick="getApplicationContext();"/> <br>
