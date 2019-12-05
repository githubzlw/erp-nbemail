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
<title>列出欠我司发票采购的所有工厂以及欠的未收发票金额</title>
</head>
<style>
.usechange {
	width: 80%;
}
.input_style2 {
	    margin-top: 10px;
    margin-left: 200px;
}
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 1400px;
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
			<h2>列出欠我司发票的采购名下所有工厂以及欠的未收发票金额</h2>

		
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=invoiceDocumentaryPurchaseOwnedToUs&className=InvoiceServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">查询:</td>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="vs" value="${vs }"/>

								</div>
							</td>
							<td>
								<div class="userselediv_nor">
									<select name="condition" id="se" class="userselein">
										<option value="1"
											<c:if test="${fyfy==1 }">selected="selected"</c:if>>金蝶号</option>
										<option value="2"
											<c:if test="${fyfy==2 }">selected="selected"</c:if>>工厂名称</option>
										<option value="3"
											<c:if test="${fyfy==3 }">selected="selected"</c:if>>采购</option>
										
									</select>
								</div>
							</td>
							
							

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> <font id="ts0" class="cusmessa">(查询条件可以是金蝶号、工厂名称、采购。)</font> 
								</td>
						</tr>
					</table>

				</form>
			</div>
				
				
				
			<table class="emanagergettable">
				<tr class="emanagergettr">
				    <td>数量</td>
				    <td>采购</td>
				    <td>金蝶号</td>
                    <td>工厂名</td>
					<td>所有未收发票金额</td>
					<td>尾款已请，发票尚未收回金额</td>
					<td>最近3年已付款超过4个月尾款已付未收回发票金额</td>
					<td>2007年付款</td>
					</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
				 <tr>
						<td>${i.count }</td>
						<td>${cus.merchandManager2 }</td>
						<td>${cus.kingdee }</td>
						<td>${cus.factoryName }</td>
						<td><a href="/ERP-NBEmail/helpServlet?action=allDetailedAccounts&className=InvoiceServlet&kingdee=${cus.kingdee }&num=0&MerchandManager2=${cus.merchandManager2 }" target="_blank"><fmt:formatNumber value="${cus.allUnacceptableInvoiceAmounts}" type="number" maxFractionDigits="2"/></a></td>
						<td><a href="/ERP-NBEmail/helpServlet?action=allDetailedAccounts&className=InvoiceServlet&kingdee=${cus.kingdee }&num=1&MerchandManager2=${cus.merchandManager2 }" target="_blank"><fmt:formatNumber value="${cus.amountOfNonReceiptAndReceiptOfInvoices}" type="number" maxFractionDigits="2"/></td>
						<td><a href="/ERP-NBEmail/helpServlet?action=allDetailedAccounts&className=InvoiceServlet&kingdee=${cus.kingdee }&num=2&MerchandManager2=${cus.merchandManager2 }" target="_blank"><fmt:formatNumber value="${cus.amountOfUnpaidInvoices}" type="number" maxFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${cus.payment2007}" type="number" maxFractionDigits="2"/></td>
						
						
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery.min.js"></script>

<script>
	
    $('#div3').hide();
    $('#div4').show();
	$('.normal').click(function(){
		$('#div3,#div4').hide();
	});
	$('.quality').click(function(){
		$('#div3').show();
		$('#div4').hide();
	});
	$('.explain').click(function(){
		$('#div3').hide();
		$('#div4').show();
	});
</script>














