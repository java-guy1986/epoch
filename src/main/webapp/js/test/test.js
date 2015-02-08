$(document).ready(readyFn);


function testSetTimeout(n){
	setTimeout(function(){
		console.log("timeout");
	}, n);
}

function testSetInterval(n){
	setInterval(function(){
		console.log("interval");
	}, n)
};

$.fn.pause = function( n ) {
    return this.queue(function() {
        var el = this;
        setTimeout(function() {
            return $( el ).dequeue();
        }, n );
    });
};
 
$.fn.redify = function() {
	//here this or $(this) stand for jquery object
    this.css( "color", "red" );
    // for chain invoke, you should return jquery standing for selected elements itself.
    return this;
};
 

function readyFn(){
	/*$("#myForm input[type='submit']").on("click", function(){
		var inputStr = $("#myForm" ).serialize();
		var inputArray =  $("#myForm" ).serializeArray();
		for (var i = 0; i < inputArray.length; i++){
			var obj = inputArray[i];
			//alert(obj.name + "," + obj.value);
		}
	})*/
	
	$("a").redify();
	
	$("#myForm").submit(function(event){
		if ($("#username" ).val().length === 0 ) {
	        // usually show some kind of error message here
	        // this prevents the form from submitting
	        return false;
	    } else {
	        // run $.ajax here
	    	return true;
	    }
	});
	
/*	$( ".box" ).animate({
	    height: 20
	}, "slow" ).pause( 1000 ).animate({
	    height: 150
	});*/
	
	/*$( ".box" ).animate({
	    height: 20
	}, "slow" ).queue(function() {
	    console.log( "We're in the animation, baby!" );
	    $(this).dequeue();
	}).animate({
		height:150
	}, "fast").queue(function(){
		$(this).dequeue();
	}).animate({
		height:30
	}, "slow");*/
	//testSetTimeout(2000);
	//testSetInterval(1000);
	
	/*$("#createNewElement a").click(function(){
		var element = $("<a/>", {
			html:"百度链接",
			"class":"test",
			href:"http://www.baidu.com"
		});
		element.insertAfter("#createNewElement");
	});*/
	
	//jquery .data(key, value) method just like java setAttribute(key, value) method
	//you can set something in momery first,
	/*$("#dataMethod").click(function(){
		$("#myList li").each(function(){
			var li = $(this);
			var div = li.find("div.content");
			if (div.length > 0){
				li.data("contentDiv", div);
			}
		});
		
		var lastLiDivData = $("#myList li:last").data("contentDiv");
		$("#myList li:first").html(lastLiDivData);
	});*/
	
	$("#dataMethod").click(function(eventObject){
		//Prevent the default action of the event such as open a link, submit form 
		eventObject.preventDefault();
	});
	
	$("#register a").click(user.register);
	$("#login a").click(user.login);
	
	$("#trigger a").on("click", function(eventObject){
		eventObject.preventDefault();
		alert("click");
	});
	
	
	
}


var user = {
	register:function(){
		$("#trigger a").trigger("click");
	},
	login:function(){
		alert("login");
	}
};
