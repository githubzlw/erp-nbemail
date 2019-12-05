<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>进账审批页(财务)</title>
</head>
<style>
.usechange {
	
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 700px;
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
	width: 700px;
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
.factoryOffering {position:absolute;top:40px;left:600px;}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function batchApproval() {
	var c = document.getElementsByName("dids");
	var eids = "";
	for(var i = 0; i < c.length;i++){
		eids += ","+c[i].value;
	}
	var params = {  
			"eids":eids,
			"action":"batchApproval",
			"className":"PreparatorEntryFormServlet",
 	};
	$.ajax({  
           url:'/ERP-NBEmail/helpServlet',  
           type:"post",  
           data:params,  
           success:function(data)  
                   { 
             if(data == "YES"){
            	 window.location.href = "/ERP-NBEmail/helpServlet?action=accounEntry&className=AccountEntryFormServlet";
            		
            	 
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
			<h2>进账录入审批页(财务)</h2>
            
			<c:if test="${roleNO!=100 }"><input class="factoryOffering" type="button" value="批量确认按钮"  onclick="batchApproval();"  style="float: left;"></c:if>
				
			<table class="emanagergettable">
				<tr class="emanagergettr">
                     <td>ERP的ID</td>
					<td>合同号</td>
					<td>预计进账</td>
					<td>实际进账</td>
					<td>实际进账时间</td>
					<td>认领人</td>
					
				</tr>
				<c:forEach items="${cuslist}" var="cus" varStatus="i">
				
					<tr>
					
						<td>${cus.iid }<input type="hidden" name="dids"   value="${cus.amountClaimFormId }" /></td>
						<td>${cus.invoice }</td>
						<td>${cus.iimoney }</td>
						<td>${cus.ifmoney }</td>
						<td>${cus.ifdate }</td>
						<td>${cus.saleName }</td>
					    
						</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>