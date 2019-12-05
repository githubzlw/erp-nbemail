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
<title>查看全部到账页面</title>
</head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 800px;
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
	width: 500px;
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
.mana_inbtn {
    background: none;
    border: none;
    color: #00afff;
    text-decoration: underline;
    cursor: pointer;
    font-size: 13px;
}
.factoryOffering {position:absolute;top:50px;left:1700px;}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>


<body>
	<div class="cusalldiv">
   
		<div class="usechange">
			<h2>查看全部到账页面</h2>
			<div>
			1.中行进账${bankOfChinaId }笔，金额<fmt:formatNumber value="${bankOfChinaMoney}" type="number" maxFractionDigits="2"/><br/>
			2.渣打进账${standardCharteredId}笔，金额<fmt:formatNumber value="${standardCharteredMoney}" type="number" maxFractionDigits="2"/><br/>
			3.信用卡进账${creditCardId }笔，金额<fmt:formatNumber value="${creditCardMoney}" type="number" maxFractionDigits="2"/><br/>
			4.其他进账${otherId }笔，金额<fmt:formatNumber value="${otherMoney}" type="number" maxFractionDigits="2"/>
			
			</div>
            <div>
            <form action="/ERP-NBEmail/helpServlet?action=queryReceiptList&className=ItCaseIdServlet"
					  method="post" >
					<table><tr>
					<td class="usermatd3">起始时间:</td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
					<td class="usermatd3">截至时间:</td>
							<td><input type="text" readonly class="Wdate" id="time2"
								name="time2" value="${endtime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							
						<td class="usermatd3">截至时间:</td>
							<td><select name="ibank" id="ibank" class="userselein">
										<option value="-1">全部</option>
										<option value="5"
											<c:if test="${fyfz==5 }">selected="selected"</c:if>>中行</option>
										<option value="12"
											<c:if test="${fyfz==12 }">selected="selected"</c:if>>渣打</option>
										<option value="8"
											<c:if test="${fyfz==8 }">selected="selected"</c:if>>信用卡</option>
										<option value="0"
											<c:if test="${fyfz==0 }">selected="selected"</c:if>>其他</option>
									</select></td>
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>		
								
								</tr>
								</table>
            </form>
						
            </div>
			
				
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>进账金额</td>
					<td>进账日期</td>
					<td>汇款银行</td>
					<td>币种</td>
					
					
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.projectId }</td>
						<td>${cus.ifmoney }</td>
						<td>${cus.ifdate }</td>
						<td><c:choose>
								<c:when test="${cus.ibank==5 }">中行</c:when>
								<c:when test="${cus.ibank==0 }">中行</c:when>
								<c:when test="${cus.ibank==12 }">渣打</c:when>
								<c:when test="${cus.ibank==8 }">信用卡</c:when>

							</c:choose>
						</td>
						<td><c:choose>
								<c:when test="${cus.imoneytype==1 }">美元</c:when>
								<c:when test="${cus.imoneytype==2 }">人民币</c:when>
								<c:when test="${cus.imoneytype==3 }">欧元</c:when>
								<c:when test="${cus.imoneytype==5 }">英镑</c:when>

							</c:choose></td>
						
						
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>