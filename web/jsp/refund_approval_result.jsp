<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退款审批结果页</title>
</head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 1200px;
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
	width:1200px;
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
.factoryOffering {position:absolute;top:50px;left:900px;}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function enterFinancialEntry(money,id,customerId) {
	window.open("/ERP-NBEmail/helpServlet?action=enterFinancialEntry&className=AmountClaimFormServlet&allMoney="+money+"&id="+id+"&customerId="+customerId);
}
//批量认领
var refundCompleted =function(id){
	     var params = {  
					"id":id, 
				    "action":"refundCompleted",
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
			<h2>退款审批结果页</h2>
           <div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=refundApprovalResult&className=AccountRefundFormServlet"
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
											<c:if test="${fyfy==0 }">selected="selected"</c:if>>申请人</option>
										<option value="2"
											<c:if test="${fyfy==0 }">selected="selected"</c:if>>项目号</option>
										
									</select>
								</div>
							</td>
							<td class="usermatd3">财务操作:</td>

							<td>
								<div class="userselediv_nor">
									<select name="condition2" id="audit" class="userselein">
										<option value="0">未付款</option>
										<option value="-1"
											<c:if test="${fyfz==-1 }">selected="selected"</c:if>>全部</option>
										<option value="1"
											<c:if test="${fyfz==1 }">selected="selected"</c:if>>已完成付款</option>
										
									</select>
								</div>
							</td>
							

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> 
								<font id="ts0" class="cusmessa">(查询条件可以是项目编号申请人。)</font>
								</td>
						</tr>
					</table>

				</form>
			</div>
			<table class="emanagergettable">
				<tr class="emanagergettr">
                    <td>项目号</td>
					<td>合同号</td>
					<td>退款金额</td>
					<td>申请人</td>
					<td>申请理由</td>
					<td>预计到账金额</td>
					<td>实际到账金额</td>
					<td>修改后实际到款金额</td>
					<td>管理员审批结果</td>
					<td>审批管理员</td>
					<td>审批时间</td>
					<td>财务操作</td>
					<td>财务操作人</td>
				    <c:if test="${refundment==100 }"><td>操作</td></c:if>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td><a href="http://117.144.21.74:33168//fukuan/examine/ceoExamine.aspx?id=${cus.caseno }" target="_blank">${cus.caseno }</a></td>
						<td>${cus.invoice }</td>
						<td>${cus.refundAmount }</td>
						<td>${cus.operator }</td>
						<td>${cus.reason }</td>
						<td>${cus.iimoney }</td>
						<td>${cus.ifmoney }</td>
						<td>${cus.finalIncome }</td>
						<td><c:choose>
						<c:when test="${cus.approvalResults==1 }">审批通过</c:when>
						<c:when test="${cus.approvalResults==2 }"><span style="color:red;">审批不通过</span></c:when>
								</c:choose>
						</td>
						<td>${cus.approver }</td>
						<td>${cus.approvalTime }</td>
						<td><c:choose>
						<c:when test="${cus.financialConfirmation==0 }">未操作</c:when>
						<c:when test="${cus.financialConfirmation==1 }">已完成退款</c:when>
								</c:choose></td>
						<td>${cus.financialConfirmationPerson }</td>
						 <c:if test="${refundment==100 }">
						 <td><c:if test="${cus.financialConfirmation==0 }"><input type="button" onclick="refundCompleted(${cus.id});" value="完成退款"></c:if>
						 </td></c:if>
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>