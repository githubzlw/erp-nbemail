<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<title>工厂付款列表</title>
</head>
<style>
.usechange {
	width: 80%;
}
.input_style2 {
	    margin-top: 10px;
    margin-left: 200px;
}

.part_01{overflow: hidden;}
.emanagergettable {
	width: 800px;
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
.div_style {
	width: 500px;
	height:350px;
	position: fixed;
	top: 25%;
	background: #fff;
	border: 2px #badbea solid;
	left: 15%;
	font-size: 14px;
	padding: 0.5% 1% 1%;
	box-shadow: 2px 2px 8px #e2e2e2;
	display: none;
}

.demo_line_05{
    letter-spacing: -1px;
    color: #ddd;
}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

</script>

<body>
	<div class="cusalldiv">
	<div class="usechange">
			<h2>工厂项目发票列表</h2>
		<div class="usechange">

			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
						action="/ERP-NBEmail/helpServlet?action=getPayInfo&className=InvoiceServlet"
						method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">跟单名:</td>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="fName" value="${fName }"/>

								</div>
							</td>

							<td class="usermatd4"><input type="submit" value="查询"
														 class="usersearchbtn">

							</td>
						</tr>
					</table>

				</form>
            <table class="emanagergettable">
				<tr class="emanagergettr">
                    <td width="50px">项目号</td>
					<td width="50px">工厂</td>
					<td width="50px">付款金额</td>
					<td width="70px">收票金额</td>
					<td width="50px">欠票金额</td>
					<td width="50px">跟单名</td>
				</tr>
				<c:forEach items="${factoryPayList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.caseNo }</td>
						<td>${cus.factoryName }</td>
						<td><fmt:formatNumber value="${cus.price}" type="number" maxFractionDigits="2"/> </td>
						<td><fmt:formatNumber value="${cus.endingBalance}" type="number" maxFractionDigits="2"/> </td>
						<td><fmt:formatNumber value="${cus.amountCredit}" type="number" maxFractionDigits="2"/> </td>
						<td>${cus.merchandManager1 }</td>
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery.min.js"></script>















