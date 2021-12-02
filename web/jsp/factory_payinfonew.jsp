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
	width: 1500px;
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
	function updateRemarks(remarks,factoryId,caseNo){

		var remarks=  document.getElementById(remarks).value;
		remarks = remarks.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");

		caseNo = caseNo.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
		var params = {
			"remarks":remarks,
			"factoryId":factoryId,
			"caseNo":caseNo,
			"action":"updateRemarks",
			"className":"InvoiceServlet",
		};

		$.ajax({
					url:'/ERP-NBEmail/helpServlet',
					type:"post",
					data:params,
					success:function(data)
					{
						if(data == "YES"){
							alert("成功")
							window.location.reload();
						}else{
							window.location.reload();

						}
					},

				}
		);


	}
</script>

<body>
	<div class="cusalldiv">
	<div class="usechange">
			<h2>工厂项目汇总列表</h2>
		
            <table class="emanagergettable">
				<tr class="emanagergettr">
					<td width="200px">工厂名</td>
                    <td width="50px">项目号</td>
					<td width="50px">已经支付的金额</td>
					<td width="70px">已经收回发票的金额</td>
					<td width="50px">未回收发票的金额</td>
					<td width="100px">时间</td>
					<td width="50px">采购</td>
					<td width="50px">销售</td>
					<td width="50px">备注</td>
					<td width="50px"></td>
				</tr>
				<c:forEach items="${factoryPayList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.factoryName }</td>
						<td><a href="/ERP-NBEmail/helpServlet?action=factoryPayInfoDetail&className=InvoiceServlet&factoryId=${cus.bargainNo }&caseNo=${cus.caseNo }" target="_blank">${cus.caseNo }</a></td>
						<td><fmt:formatNumber value="${cus.price}" type="number" maxFractionDigits="2"/> </td>
						<td><fmt:formatNumber value="${cus.endingBalance}" type="number" maxFractionDigits="2"/> </td>

						<td><fmt:formatNumber value="${cus.amountCredit}" type="number" maxFractionDigits="2"/> </td>

						<td>${cus.createTime != null ?fn:substring(cus.createTime,0,fn:indexOf(cus.createTime," ")):""}</td>
						<td>${cus.merchandManager2}</td>
						<td>${cus.merchandManager1}</td>

							 <td style="border: 1px solid #BBBABA; width: 80px;">
								 <input type="text" style="width: 150px;" id="projectnote${i.count}"
									 value="${cus.remarks }" title="${cus.remarks }">
							 </td>
							 <td>
								 <input type="button" value="提交备注" onclick="updateRemarks('projectnote${i.count}','${cus.bargainNo }','${cus.caseNo }');"/>
							 </td>

					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery.min.js"></script>















