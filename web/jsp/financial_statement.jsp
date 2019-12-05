<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>财务报表</title>
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
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
		src="My97DatePicker/WdatePicker.js"></script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>财务报表</h2>
			<a href="/ERP-NBEmail/download?filename=inancialStatement.xls"
				title="inancialStatement.xls" target=_blank>导出财务报表</a>
			<div >
				<form
						action="/ERP-NBEmail/helpServlet?action=ViewFactoryData&className=FactoryReconciliationServlet"
						method="post">
					<table class="usectable">
						<tr>

							<td class="usermatd3">时间:</td>
							<td><input type="text" readonly class="Wdate" id="time"
									   name="time" value="${time }"
									   onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />

							<td class="usermatd4"><input type="submit" value="查询"
														 class="usersearchbtn">
							</td>
						</tr>
					</table>

				</form>

			</div>
			<table class="emanagergettable">
				<tr class="emanagergettr">
					<td>金蝶号</td>
					<td>工厂名</td>
					<td>本期支付工厂</td>
					<td>本期收回发票</td>
					<td>期末余额</td>
					<td>2007期末余额</td>
				</tr>
				<c:forEach items="${list}" var="cus" varStatus="i">
					<tr >
						<td><a href="/ERP-NBEmail/helpServlet?action=enquiryDetails&className=FactoryReconciliationServlet&year=${year }&month=${month}&kingdee=${cus.kingdee}" target="_blank">${cus.kingdee }</a></td>
						<td>${cus.factoryName }</td>
						<td>${cus.amountCredit }</td>
						<td>${cus.currentDebitAmount }</td>
						<td>${cus.endingBalance }</td>
						<td>${cus.price }</td>
                 </tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>