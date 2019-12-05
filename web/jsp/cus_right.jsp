<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>客户创建成功页面</title>
</head>
<body>
	<div class="cusalldiv">
		<jsp:include page="people_manager.jsp"></jsp:include>
		<input type="button" id="rbutton" onclick="history.back();" value="返回"
			class="emanagergetdel" />&nbsp;

		<div class="usechange">

			<h2>
				<%
out.println(request.getAttribute("name"));
%>
			</h2>


		</div>
	</div>
</body>
</html>