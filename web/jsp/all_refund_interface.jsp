<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退款详情页(待审批)</title>
</head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 900px;
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
	width: 900px;
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
.factoryOffering {position:absolute;top:50px;left:700px;}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function enterFinancialEntry(money,id,customerId) {
	window.open("/ERP-NBEmail/helpServlet?action=enterFinancialEntry&className=AmountClaimFormServlet&allMoney="+money+"&id="+id+"&customerId="+customerId);
}
//批量认领
var approvalResults =function(num,id){
	     var params = {  
					"id":id, 
				      "num":num, 
					"action":"approvalResults",
					"className":"AccountRefundFormServlet",
		   	};
			$.ajax({  
		             url:'/ERP-NBEmail/helpServlet',  
		             type:"post",  
		             data:params,  
		             success:function(data)  
		                     { 
		                 if(data == "YES"){
		           	  		window.location.reload();
		           	  	}else{
		           	  	window.location.reload();
		           	  	 
		           	  		}
		                     }, 
		         }
		     ); 
		};
</script>
<body>
	<div class="cusalldiv">
        <div class="usechange">
			<h2>退款详情页(待审批)</h2>
            <div>
            <table><tr>
            <td>
				  <!--  <a href="/ERP-NBEmail/jsp/refund_entry_page.jsp" target="_blank">退款录入页</a>
				   <a href="/ERP-NBEmail/helpServlet?action=refundApprovalResult&className=AccountRefundFormServlet" target="_blank">退款审批结果页</a>
					 --></td>
					</tr>
					</table>
				 </div>
			<table class="emanagergettable">
				<tr class="emanagergettr">
                    <td >项目号</td>
					<td >合同号</td>
					<td >退款金额</td>
					<td >申请人</td>
					<td >ERP预计到账金额</td>
					<td >ERP实际到账金额</td>
					<td >退款原因</td>
					<c:if test="${refundment==100 }">
					<td >操作</td>
					</c:if>
				
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td><a href="http://117.144.21.74:33168//fukuan/examine/ceoExamine.aspx?id=${cus.caseno }" target="_blank">${cus.caseno }</a></td>
						<td>${cus.invoice }</td>
						<td>${cus.refundAmount }</td>
						<td>${cus.operator }</td>
						<td>${cus.iimoney }</td>
						<td>${cus.ifmoney }</td>
						<td>${cus.reason }</td>
						<c:if test="${refundment==100 }">
						<td><input type="button" onclick="approvalResults(1,${cus.id});" value="审批通过">
						<input type="button" onclick="approvalResults(2,${cus.id});" value="审批不通过">
						</td>
						</c:if>
						
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>