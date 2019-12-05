<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>12个月以内未出货状态合同</title>
</head>
<style>
.usechange {
	width: 80%;
}

.emanagergettable {
	width: 100%;
	border: 1px #7D7D7D solid;
	border-collapse: collapse;
}

.emanagergettable td {
	border-right: 1px #7D7D7D solid;
	border-bottom: 1px #7d7d7d solid;
	padding: 0px 5px;
	height: 25px;
}

.even {
	background-color: #c7e5ff
}

.emanagergettr {
	font-weight: bold;
	height: 35px;
	text-align: center;
	background: #058fd7;
	color: #fff;
}

.odd {
	background-color: #eaf5ff
}

/* margin: auto; */
</style>
<script type="text/javascript">
	
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>12个月以内未出货状态合同</h2>



			<table class="emanagergettable">
				<tr class="emanagergettr">
					<td>项目号</td>
					<td>合同号</td>
					<td>工厂名</td>
					<td>合同付款时间（给工厂）</td>
					<td>合同交期</td>
					<td>合同总金额</td>








				</tr>
				<c:forEach items="${list}" var="cus" varStatus="i">
					<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
						<td>${cus.caseNo }</td>
						<td>${cus.bargainNo }</td>
						<td>${cus.factory }</td>
						<td>${cus.paytime != null ?fn:substring(cus.paytime,0,fn:indexOf(cus.paytime," ")):""}</td>
						<td>${cus.completiontime != null ?fn:substring(cus.completiontime,0,fn:indexOf(cus.completiontime," ")):""}</td>

						<td>${cus.money}</td>


					</tr>
				</c:forEach>
			</table>
			<br />

		</div>
	</div>

</body>
</html>