<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退款录入页</title>
</head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 300px;
	border: 1px #7D7D7D solid;
	border-collapse: collapse;
}
.inp_sel {
	width: 80px;
	height: 30px;
	margin-left: 10px;
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
	width: 300px;
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
.factoryOffering {position:absolute;top:50px;left:300px;}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function refundConfirmationButton(){

	document.getElementById("uploadForm").submit(); 
    		
}
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>退款录入页</h2>
			<div>
			1.项目退款信息录入，添加具体的invoice号及退款金额，财务确认后将修改ERP进账金额，并将款项退还给客户
			</div>
			<form action="/ERP-NBEmail/helpServlet?action=refundConfirmationButton&className=AccountRefundFormServlet"
						id="uploadForm" enctype="multipart/form-data" method="post" >
            <table class="emanagergettable">
				<tr class="emanagergettr">
                    <td>invoice号</td>
					<td>退款金额</td>
					<td>理由</td>
				</tr>
				<tr >
                    <td><input type="text" id="invoice1" name="invoice1"></td>
					<td><input type="text" id="refundAmount1" name="refundAmount1"></td>
					<td><input type="text" id="reason1" name="reason1"></td>
				</tr>
				<tr >
                    <td><input type="text" id="invoice2" name="invoice2"></td>
					<td><input type="text" id="refundAmount2" name="refundAmount2"></td>
					<td><input type="text" id="reason2" name="reason2"></td>
				</tr>	
				<tr >
                    <td><input type="text" id="invoice3" name="invoice3"></td>
					<td><input type="text" id="refundAmount3" name="refundAmount3"></td>
					<td><input type="text" id="reason3" name="reason3"></td>
				</tr>	
				<tr >
                    <td><input type="text" id="invoice4" name="invoice4"></td>
					<td><input type="text" id="refundAmount4" name="refundAmount4"></td>
					<td><input type="text" id="reason4" name="reason4"></td>
				</tr>	
				<tr >
                    <td><input type="text" id="invoice5" name="invoice5"></td>
					<td><input type="text" id="refundAmount5" name="refundAmount5"></td>
					<td><input type="text" id="reason5" name="reason5"></td>
				</tr>	
				<tr >
                    <td><input type="text" id="invoice6" name="invoice6"></td>
					<td><input type="text" id="refundAmount6" name="refundAmount6"></td>
					<td><input type="text" id="reason6" name="reason6"></td>
				</tr>	
				<tr >
                    <td><input type="text" id="invoice7" name="invoice7"></td>
					<td><input type="text" id="refundAmount7" name="refundAmount7"></td>
					<td><input type="text" id="reason7" name="reason7"></td>
				</tr>	
				<tr >
                    <td><input type="text" id="invoice8" name="invoice8" value="其他无InvoiceID的退款"></td>
					<td><input type="text" id="refundAmount8" name="refundAmount8"></td>
					<td><input type="text" id="reason8" name="reason8"></td>
				</tr>	
					</table>
					<input type="button" onclick="refundConfirmationButton();" value="退款确认按钮">
					</form>
			</div></div>
</body>
</html>