<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>유저리스트</title>
<script src="/js/jquery-3.2.1.min.js"></script>
</head>
<script>
var AjaxUtil = function(params){
	this.params = params;
	
	getHttpXmlObj = function(){
		if(window.XMLHttpRequest){
	  		return new XMLHttpRequest();
	 	}else if(window.ActiveXObject){
	  		return new ActiveXObject("Microsoft.XMLHTTP");
	 	}
		alert("해당 브라우져가  Ajax를 지원하지 않습니다.");
	}
	this.xhr = getHttpXmlObj();
	var method = "post";
	var url = "test.user";
	var aSync = true;
	this.xhr.onreadystatechange=function(){
   		if (this.readyState==4){ // 서버가 요청을 잘 받았고 complet잘 되었어.
   			if(this.status==200){ //성공했을 때만 동작해라. // 200번대 아니면 다 에러
	   			var result = decodeURIComponent(this.responseText); //table을 그대로 가져와서 result에 담아줭
	   			$("#result_div").html(result); // #-> '아이디'가 result_div인 것을 찾아서
	   			setEvent();
   			}
   		}
	}
	this.changeCallBack = function(func){
		this.xhr.onreadystatechange = func;
	}
   	this.xhr.open(method, url+params, aSync);
   	this.send = function(){
   		this.xhr.send.arguments = this;
   	   	this.xhr.send(params); //send = summit
   	}
}



function setEvent(){
	$("input[type='button']").click(function(){
		var url = this.getAttribute("data-url");
		if(url){
			if(url.split(".")[1]=="user"){
				var param = "?command=list&name="+$("#name").val();
				var au = new AjaxUtil(param);
				au.send();
				
			}
		}else{
			var userNo = this.getAttribute("data-num"); 
			if(this.getAttribute("value")=="수정"){
				location.href="/modify.jsp?userNo=" +userNo;
			}else if(this.getAttribute("value")=="삭제"){
				var param = "?command=delete&userNo="+userNo;
				var au = new AjaxUtil(param);
				au.send();
				
			}
		}
		
		
	});
	
}

$(document).ready(function(){
	var param = "?command=list";
	var au = new AjaxUtil(param);
	au.send();
})

</script>

<body>
<div id = "result_div"></div>
이름 : <input type ="text" name = "name" id = "name" >
<input type="button" value ="검색" data-url="search.user">
</body>
</html>