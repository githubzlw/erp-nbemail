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
<title>超过4个月 没收齐发票的合同</title>
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

	
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>超过4个月 没收齐发票的合同</h2>

		
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=more4MonthInvoiceNotReceived&className=BarginServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">查询:</td>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="vs" value="${caseno }" />

								</div>
							</td>
							<td>
								<div class="userselediv_nor">
									<select name="condition" id="se" class="userselein">
										<option value="1"
											<c:if test="${fyfy==1 }">selected="selected"</c:if>>项目编号</option>
										<option value="2"
											<c:if test="${fyfy==2 }">selected="selected"</c:if>>工厂名称</option>
									</select>
								</div>
							</td>
							<td class="usermatd3">是否尾款:</td>

							<td>
								<div class="userselediv_nor">
									<select name="condition2" id="audit" class="userselein">
										<option value="-1">全部</option>
										<option value="1"
											<c:if test="${fyfz==1 }">selected="selected"</c:if>>是尾款</option>
										<option value="0"
											<c:if test="${fyfz==0 }">selected="selected"</c:if>>不是尾款</option>
										
									</select>
								</div>
							</td>
							
							

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> <font id="ts0" class="cusmessa">(查询条件可以是项目编号、工厂名查询)</font>
								</td>
						</tr>
					</table>

				</form>
			</div>
				
				
				
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td width="300px;">合同号</td>
					<td width="300px;">工厂名</td>
					<td>合同金额</td>
					<td>发票收回金额</td>
					<td>最后一笔付款日期</td>
					<td>是否是尾款</td>
					
					
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
				 <tr>
						<td><a
							href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.projectId }"
							target=_blank>${cus.projectId }</a></td>
						<td>${cus.name }</td>
						<td>${cus.factoryName }</td>
						<td><fmt:formatNumber value="${cus.totalSum}" type="number" maxFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${cus.amountInvoiceReceived}" type="number" maxFractionDigits="2"/></td>
						<td>${cus.datePayment != null ?fn:substring(cus.datePayment,0,fn:indexOf(cus.datePayment," ")):""}</td>
                         <td><c:choose>
                         <c:when test="${cus.lastParagraph==0 }">不是尾款</c:when>
                         <c:when test="${cus.lastParagraph==1 }">是尾款</c:when>
                         </c:choose></td>
						
						
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














