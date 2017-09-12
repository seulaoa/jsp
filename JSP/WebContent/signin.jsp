<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<%@ include file = "common/header.jsp" %>
<title>회원가입</title>

</head>
<script>
$(document).ready(function(){
	$("input[type='button']").click(function(){
		var params = {};
		params["id"] = $("#id").val(); //pararms.id
		params["pwd"] = $("#pwd").val();
		params["name"] = $("#name").val();
		params["admin"] = $("#admin").val();
		
		var hobby = $('input[type="hobby"]:checked').map(function(){
			return $(this).val();
		}).toArray();
		
		params["hobby"] = hobby.toString();
		
		params = JSON.stringify(params);
		var param = encodeURI("?command=signin&param=" + params);
		var au = new AjaxUtil(param);
		au.send();
	})
}); 

</script>
<body>

<form action="sigin.user" method="post">

<table border="1" cellspacing="0" cellpadding="0" width="400" align="center">

<tr>

	<td colspan="2"><p align="center"> = 회원 가입 = </p></td>

</tr>

<tr>

	<td align="center">아이디</td>

	<td><input type="text" name="id" id="id" /></td>

</tr>

<tr>

	<td align="center">비밀번호</td>

	<td><input type="password" name="pwd" id="pwd" maxlength="100"/></td>

</tr>

<tr>

	<td align="center">이름</td>

	<td><input type="text" name="name" id="name" maxlength="100"/></td>

<tr>

<tr>

	<td align="center">취미</td>

	<td>

		잠자기<input type="checkbox" name="hobby" value="잠자기">

		게임<input type="checkbox" name="hobby" value="게임">

		독서<input type="checkbox" name="hobby" value="독서">

		음악<input type="checkbox" name="hobby" value="음악">

	</td>

</tr>
<tr>
	<td align = "center">관리자여부</td>
	<td><select name = "admin" id ="admin">
		<option value ="1">Y </option>
		<option value ="0">N </option>
	</select>

</tr>
<tr>

	<td colspan="2" align="center"><input type="button" value="회원가입" /></td>

</tr> 

</table>

<input type="hidden" name="command" id="command" value="signin"/><br/>

</form>

</body>

</html>
