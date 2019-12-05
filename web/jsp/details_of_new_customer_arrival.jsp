<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新客户列表页</title>
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
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>

<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>新客户列表页</h2>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=detailsOfNewCustomerArrival&className=InvoiceServlet"
					method="post">
					<table class="usectable">
						<tr>

							<td class="usermatd3">根据条件查询:</td>

							<td class="usermatd3">起始时间:</td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> </td>
						</tr>
					</table>

				</form>
			</div>
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>客户id</td>
					<td>客户名</td>
					<td>项目号</td>
					<td>总进账美元</td>
					<td>总进账人民币</td>
					<td>总应付</td>
					<td>毛利润</td>
					<td>利润率</td>
					<td>第一次进账日期</td>
					<td>实际进账金额</td>
					<td>跟单</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.eid }</td>
						<td>${cus.name }</td>
						<td>${cus.projectId }</td>
						<td>${cus.totalRevenueDollars } $</td>
						<td>${cus.totalProceedsRMB } RMB</td>
						<td>${cus.accountsPayable } RMB</td>
						<td>${cus.grossProfit } RMB</td>
						<td>${cus.grossProfitMargin }</td>
						<td>${cus.ifdate != null ?fn:substring(cus.ifdate,0,fn:indexOf(cus.ifdate," ")):""}
						<td>${cus.ifmoney } $</td>
						<td>${cus.merchandmanager1 }</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>