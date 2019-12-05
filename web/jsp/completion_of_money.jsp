<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/ERP-NBEmail/js/jquery.min.js"></script>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>

<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<title>到账录入完成页面</title>
</head>
<style>
.emanagergettable {
	
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
	padding: 0px 2px;
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

.mana_inbtn {
    background: none;
    border: none;
    color: #00afff;
    text-decoration: underline;
    cursor: pointer;
    font-size: 13px;
}

</style>

<script type="text/javascript">
function enterFinancialEntry(money,id,customerId) {
	window.open("/ERP-NBEmail/helpServlet?action=enterFinancialEntry&className=AmountClaimFormServlet&allMoney="+money+"&id="+id+"&customerId="+customerId);
}
function lookEnterFinancialEntry(money,id,customerId) {
	window.open("/ERP-NBEmail/helpServlet?action=lookEnterFinancialEntry&className=AmountClaimFormServlet&allMoney="+money+"&id="+id+"&customerId="+customerId);
}
function uploadFile() {
	return	$('#fileupload2').click();
}
function uploadAllFile(){
	//var fileupload2 = document.getElementById("fileupload2").value;
	///var payBank = document.getElementById("payBank").value;
	document.getElementById("uploadForm").submit(); 
    		
}

//checkbox全选、取消全选事件
function select_checkbox(obj){
	if($(obj).is(":checked")){
		$("input:checkbox[name='dids']").attr("checked",true);
	}else{
		$("input:checkbox[name='dids']").attr("checked",false);
	}
}

//查询数据库是否存在客户
var checkCustomer =function(){
	var customerName = document.getElementById("customerName").value;
	
      var params = {  
					"customerName":customerName, 
					"action":"checkCustomer",
					"className":"AccountEntryFormServlet",
		   	};
			 $.ajax({  
		             url:'/ERP-NBEmail/helpServlet',  
		             type:"post",  
		             data:params,  
		             success:function(data)  
		                     { 
		            if(data == "YES"){
		            	document.getElementById("notes").innerHTML="客户已存在";
		           	  	}else{
		           	  	document.getElementById("notes").innerHTML="客户不存在";
		           	  	 }
		                     }, 
		         }
		     ); 
		};
</script>

<body>
	<div class="cusalldiv">
	<div class="usechange">
	<h2>到账录入完成页面</h2>
		<div>
          <table >
            <tr>
					<td>汇款人名<input type="text" id="customerName" name="customerName"></td>
					<td><input type="button" value="查询" onclick="checkCustomer();"><span id="notes"></span></td>
					</tr>
				</table>
			<form action="/ERP-NBEmail/helpServlet?action=exportReceiptForm&className=AccountEntryFormServlet" method="post">
            <table >
              <tr>
				<td>数据编号<input type="text" id="fNumber" name="fNumber"></td>
				<td>当月汇率<input type="text" id="exchangeRate" name="exchangeRate"></td>
				<td>日期<input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />(全部必填)</td>
				<td><input type="submit"  value="导出金蝶Excel表格">
				</td>
				</tr>	
				</table>	
			</form>
		<form action="/ERP-NBEmail/helpServlet?action=completionOfMoney&className=AccountEntryFormServlet" method="post">
            <table >
              <tr>
				<td><input type="text" id="bank" name="bank" value="${bank }"></td>
				<td>日期查询<input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }" value="${time1 }"
								onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" /></td>
				<td><input type="submit"  value="查询"><span>可根据银行、客户、币别、日期查询</span>
				</td>
				</tr>	
				</table>	
			</form>
		
			</div>
		
			<table class="emanagergettable">
				<tr class="emanagergettr">
                    <th width="85px">日期</th>
					<th width="120px">银行交易号</th>
					<th width="55px">金额</th>
					<th width="40px">货币</th>
					<th width="350px">汇款人名</th>
					<th width="100px">银行</th>
					<th width="100px">推测认领人</th>
					<th width="100px">详情</th>
					<th width="100px">操作</th>
					<th width="100px">已认领人</th>
					<th width="100px">认领时间</th>
					<th width="100px">客户状态</th>
					<!-- <th width="150px">其他信息</th> -->
				<!-- 	
					<th >其他信息</th>
					<th>推测认领人</th>
					<th>详情</th>
					<th>操作</th>
					<th>已认领人</th>
					<th>认领时间</th>
					<th>客户状态</th> -->
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.transactionDate }</td>
						<td>${cus.transactionReferenceNumber }</td>
						<td>${cus.tradeAmount }</td>
						<td>${cus.tradeCurrency }</td>
						<td>${cus.payersName }</td>
						<td>${cus.beneficiaryAccountBank }</td>
						<td>估计客户${cus.nBEmailId},${cus.conjecture }</td>
						<td><c:forEach items="${cus.amountClaimForm}" var="cus1" varStatus="n">
						${cus1.invoice } 出口年月${cus1.exportYear }-${cus1.exportMonth } 国家<c:if test="${cus1.country!=15 }"><c:choose>
						<c:when test="${cus1.country==1 }">USA</c:when>
										<c:when test="${cus1.country==2 }">Canada</c:when>
										<c:when test="${cus1.country==3 }">France</c:when>
										<c:when test="${cus1.country==4 }">Germany</c:when>
										<c:when test="${cus1.country==5 }">Netherlands</c:when>
										<c:when test="${cus1.country==6 }">Israel</c:when>
										<c:when test="${cus1.country==7 }">Mexico</c:when>
										<c:when test="${cus1.country==8 }">Australia</c:when>
										<c:when test="${cus1.country==9 }">Italy</c:when>
										<c:when test="${cus1.country==10 }">Switzerland</c:when>
										<c:when test="${cus1.country==11 }">Finland</c:when>
										<c:when test="${cus1.country==12 }">Sweden</c:when>
										<c:when test="${cus1.country==13 }">UK</c:when>
										<c:when test="${cus1.country==14 }">Argentina</c:when>
										<c:when test="${cus1.country==15 }">Other</c:when>
										<c:when test="${cus1.country==16 }">Japan</c:when>
										<c:when test="${cus1.country==17 }">China</c:when>
										<c:when test="${cus1.country==18 }">Austria</c:when>
										<c:when test="${cus1.country==19 }">Saudi Arabia</c:when>
										<c:when test="${cus1.country==20 }">Belgium</c:when>
										<c:when test="${cus1.country==21 }">Spain</c:when>
										<c:when test="${cus1.country==22 }">New Zealand</c:when>
										<c:when test="${cus1.country==23 }">Slovenia</c:when>
										<c:when test="${cus1.country==24 }">Serbia</c:when>
										<c:when test="${cus1.country==24 }">Ireland</c:when>

									</c:choose></c:if><c:if test="${cus1.country==15 }">${cus1.state }</c:if>
						</c:forEach></td>
						<td>
						   <input type="button" onclick="lookEnterFinancialEntry(${cus.tradeAmount },${cus.id },${cus.nBEmailId});" value="查看金额分配"/>
						 </td>
						<td>${cus.claimant }</td>
						<td>${cus.claimTime }</td>
						<td><c:choose>
						<c:when test="${cus.newCustomer==0 }">老客户</c:when>
						<c:when test="${cus.newCustomer==1 }">新客户</c:when></c:choose></td>
						<%-- <td>${cus.remark }</td> --%>
						<%-- 
						<td>${cus.remark }</td>
						<td>估计客户${cus.nBEmailId},${cus.conjecture }</td>
						<td><c:forEach items="${cus.amountClaimForm}" var="cus1" varStatus="n">
						${cus1.invoice } 出口年月${cus1.exportYear }-${cus1.exportMonth } 国家<c:choose>
						<c:when test="${cus1.country==1 }">USA</c:when>
										<c:when test="${cus1.country==2 }">Canada</c:when>
										<c:when test="${cus1.country==3 }">France</c:when>
										<c:when test="${cus1.country==4 }">Germany</c:when>
										<c:when test="${cus1.country==5 }">Netherlands</c:when>
										<c:when test="${cus1.country==6 }">Israel</c:when>
										<c:when test="${cus1.country==7 }">Mexico</c:when>
										<c:when test="${cus1.country==8 }">Australia</c:when>
										<c:when test="${cus1.country==9 }">Italy</c:when>
										<c:when test="${cus1.country==10 }">Switzerland</c:when>
										<c:when test="${cus1.country==11 }">Finland</c:when>
										<c:when test="${cus1.country==12 }">Sweden</c:when>
										<c:when test="${cus1.country==13 }">UK</c:when>
										<c:when test="${cus1.country==14 }">Argentina</c:when>
										<c:when test="${cus1.country==15 }">Other</c:when>
										<c:when test="${cus1.country==16 }">Japan</c:when>
										<c:when test="${cus1.country==17 }">China</c:when>
										<c:when test="${cus1.country==18 }">Austria</c:when>
										<c:when test="${cus1.country==19 }">Saudi Arabia</c:when>
										<c:when test="${cus1.country==20 }">Belgium</c:when>
										<c:when test="${cus1.country==21 }">Spain</c:when>
										<c:when test="${cus1.country==22 }">New Zealand</c:when>
										<c:when test="${cus1.country==23 }">Slovenia</c:when>
										<c:when test="${cus1.country==24 }">Serbia</c:when>
										<c:when test="${cus1.country==24 }">Ireland</c:when>

									</c:choose>
						</c:forEach></td>
						<td>
						   <input type="button" onclick="lookEnterFinancialEntry(${cus.tradeAmount },${cus.id },${cus.nBEmailId});" value="查看金额分配"/>
						 </td>
						<td>${cus.claimant }</td>
						<td>${cus.claimTime }</td>
						<td><c:choose>
						<c:when test="${cus.newCustomer==0 }">老客户</c:when>
						<c:when test="${cus.newCustomer==1 }">新客户</c:when></c:choose></td> --%>
					</tr>
				</c:forEach>
			</table></div></div>
			
</body>
</html>