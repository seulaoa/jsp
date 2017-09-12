<%@ page import = "java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String rootPath = request.getContextPath();
if(session.getAttribute("user") == null){
	RequestDispatcher dis = request.getRequestDispatcher("/login.jsp");
	dis.forward(request, response);
}
%>
<script src ="/js/jquery-3.2.1.min.js"></script>
<script>
var AjaxUtil = function(params) {
	this.params = params;
 
	getHttpXmlObj = function() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
		alert("해당 브라우져가  Ajax를 지원하지 않습니다.");
	}
	this.xhr = getHttpXmlObj();
	var method = "post";
	var url = "test.user";
	var aSync = true;
	this.xhr.callfunc = null;
	this.xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				var result = decodeURIComponent(this.responseText);
				var re = JSON.parse(result);
				if(this.callfunc){
					this.callfunc(re);
				}else{
					alert(re.msg);
					location.href=re.url;
				}
			}
		}
	}
	this.changeCallBack = function(func) {
		this.xhr.callfunc = func;
	}
	this.xhr.open(method, url + params, aSync);
	this.send = function() {
		this.xhr.send.arguments = this;
		this.xhr.send(params);
	}
}
 



</script>
