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
<title>列出欠我司发票的所有工厂以及欠的未收发票金额</title>
</head>
<style>
.input_style2 {
	    margin-top: 10px;
    margin-left: 200px;
}
}
.part_01{overflow: hidden;}
.emanagergettable {
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

function uploadAllFile(){

	document.getElementById("uploadForm").submit(); 
    		
}
function uploadFile() {
	return	$('#fileupload2').click();
}
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>工厂信息及发票金额</h2>
                <input type="hidden" id="saleName" name="saleName" value="${userName }">
		<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUsNew&className=InvoiceServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">工厂名:</td>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="vs" value="${vs }"/>

								</div>
							</td>

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn">
								
								</td>
						</tr>
					</table>

				</form>
			<form action="/ERP-NBEmail/helpServlet?action=uploadHistoricalWaybillData&className=InvoiceServlet"
					id="uploadForm"  method="post" enctype="multipart/form-data">

				
			<table class="emanagergettable">
				<tr class="emanagergettr">
                    <td width="200px;">工厂名</td>
					<td width="200px;">已经支付的金额</td>
					<td width="200px;">已经收回发票的金额</td>
					<td width="200px;">未回收发票的金额</td>
					<td width="200px;">时间</td>
					<td width="200px;">采购</td>
					<td width="200px">销售</td>
					<td width="200px">跟单</td>

					</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
						 <tr>
							<td><a href="/ERP-NBEmail/helpServlet?action=factoryPayInfoNew&className=InvoiceServlet&factoryName=${cus.factoryName }" target="_blank">${cus.factoryName }</a></td>
							<td><fmt:formatNumber value="${cus.invoiceAmount}" type="number" maxFractionDigits="2"/></td>
							<td><fmt:formatNumber value="${cus.allUnacceptableInvoiceAmounts}" type="number" maxFractionDigits="2"/></td>
							 <td><fmt:formatNumber value="${cus.allUnacceptableInvoiceAmounts1}" type="number" maxFractionDigits="2"/></td>
							 <td>${cus.startTime != null ?fn:substring(cus.startTime,0,fn:indexOf(cus.startTime," ")):""}</td>
							 <td>${cus.merchandManager2 }</td>
							 <td>${cus.merchandManager1 }</td>
							 <td>${cus.merchandising}</td>
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
	/* 最多2行显示，超过部分.... */
	$('table tr').each(function(){
        var type_length = $(this).find('.add_type').text().length;
        var type_text = $(this).find('.add_type').text();
        if(type_length > 50){
            var type1 = type_text.substring(0,45);
            $(this).find('.add_type').text(type1 + '...');
        }else{
            $(this).find('.add_type').removeAttr('title');
        }
	})

</script>














