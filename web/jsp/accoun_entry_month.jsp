<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ERP到账录入及到款页面</title>
</head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 1800px;
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
<script type="text/javascript">

//checkbox全选、取消全选事件
function select_checkbox(obj){
	if($(obj).is(":checked")){
		$("input:checkbox[name='dids']").attr("checked",true);
	}else{
		$("input:checkbox[name='dids']").attr("checked",false);
	}
}




		
		function OpenDiv(accountMoney,id){
			document.getElementById("accountMoney").value=accountMoney;
			document.getElementById("firstSumMoney").value="";
			document.getElementById("secondSumMoney").value="";
			document.getElementById("id").value=id;
			document.getElementById("div").style.display="block";
			document.getElementById("open").style.display="block";
			}
			function CloseDiv(){
				
			document.getElementById("div").style.display="none";
			document.getElementById("open").style.display="block";
			}
</script>

<body>
	<div class="cusalldiv">
     
		<div class="usechange">
			<h2>客户到款情况的数据统计</h2>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=inquireIntoAccounts&className=AccountEntryFormServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">根据名字查询:</td>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="saleName" id="saleName" />

								</div>
							</td>
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn">
								</td>
						</tr>
					</table>

				</form>
				<!-- <a href="/ERP-NBEmail/helpServlet?action=detailsOfNewCustomerArrival&className=InvoiceServlet" target="_blank">查看新客户的到账详情</a>
			<a href="/ERP-NBEmail/helpServlet?action=clientToAccountListPage&className=InvoiceServlet" target="_blank">查看客户到账列表页</a>
			<a href="/ERP-NBEmail/helpServlet?action=documentaryToAccountPage&className=InvoiceServlet" target="_blank">跟单到账列表页</a> -->
			</div>
			
            <table class="emanagergettable">
				<tr class="emanagergettr">
                    <td>日期</td>
					<td>银行交易号</td>
					<td>金额</td>
					<td>货币</td>
					<td>汇款人名</td>
					<td>银行</td>
					<td>其他信息</td>
					<td>推测客户ID/认领人</td>
					<td>进账详情</td>
					<td>已认领人</td>
					<td>财务进账确认</td>
					
					
					
					
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.transactionDate }</td>
						<td>${cus.transactionReferenceNumber }</td>
						<td>${cus.tradeAmount }</td>
						<td>${cus.tradeCurrency }</td>
						<td>${cus.payersName }</td>
						<td>${cus.beneficiaryAccountBank }</td>
						<td>${cus.remark }</td>
						<td><c:if test="${cus.nBEmailId !=0}"><c:if test="${cus.conjecture!='' }">${cus.nBEmailId}/${cus.conjecture }</c:if></c:if>
						<c:if test="${cus.nBEmailId !=0}"><c:if test="${cus.conjecture=='' }">${cus.nBEmailId}</c:if></c:if>
						</td>
						<td><c:forEach items="${cus.accessories}" var="cus1" varStatus="a">
						${cus1.invoice }，预计到账${cus1.iimoney }，现实际到账${cus1.ifmoney }
						   </c:forEach></td>
						
						<td><c:if test="${cus.claimant!=null }">${cus.claimant },${cus.claimTime != null ?fn:substring(cus.claimTime,0,fn:indexOf(cus.claimTime," ")):""}</c:if></td>
						<td><c:choose>
						<c:when test="${cus.dataProcessing==0 }">未确认</c:when>
						<c:when test="${cus.dataProcessing==1 }">已确认</c:when></c:choose></td>
						
						
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>