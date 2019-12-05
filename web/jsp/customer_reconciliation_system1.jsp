<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<style>
.usechange {
	width: 80%;
}

.emanagergettable td {
	border-right: 1px solid #c6c7c8;
	border-bottom: 1px solid #c6c7c8;
	padding: 0px 6px;
	text-align: center;
}

.even {
	background-color: #c7e5ff
}

.odd {
	background-color: #eaf5ff
}

.emanagergettr {
	background: #058fd7;
	color: #fff;
}

.emanagergettable {
	width: 100%;
	border: 1px solid #c6c7c8;
	background: #fff;
	border-right: 0 none;
	border-bottom: 0 none;
	font-size: 12px;
}
/* margin: auto; */
</style>
<script>

</script>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<title>客户对账页面</title>
</head>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;"
				align="left" id="ykhxx">
				<h2>客户对账页面</h2>
			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<table class="emanagergettable">
					<tr>
						<td>客户id:客户名</td>
						<td>项目号</td>
						<td>跟单名</td>
						<td>客户预计付款</td>
						<td>预计时间</td>

					</tr>
					<c:forEach items="${cusList}" var="cus" varStatus="i">

						<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
							<td>${cus.cid }:${cus.cusName }</td>
							<td>${cus.projectId }</td>
							<td>${cus.name }</td>
							<td>${cus.outstandingAmount }</td>
							<td>${cus.estimatedTime }</td>

						</tr>


					</c:forEach>
				</table>

			</div>


		</div>

	</div>
</body>
</html>