<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>进账客户数及下单项目数</title>
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
function enterFinancialEntry(money,id,customerId,tradeCurrency) {
	window.open("/ERP-NBEmail/helpServlet?action=enterFinancialEntry&className=AmountClaimFormServlet&allMoney="+money+"&id="+id+"&customerId="+customerId+"&tradeCurrency="+tradeCurrency);
}
function lookEnterFinancialEntry(money,id,customerId,tradeCurrency) {
	window.open("/ERP-NBEmail/helpServlet?action=lookEnterFinancialEntry&className=AmountClaimFormServlet&allMoney="+money+"&id="+id+"&customerId="+customerId+"&tradeCurrency="+tradeCurrency);
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
//批量审批
var batchClaim =function(){
	var c = document.getElementsByName("dids");
	
			var eids = "";
			var flag = true;
			for(var i = 0; i < c.length;i++){
				if(c[i].checked){
					flag = false;
					eids += ","+c[i].value;
				}
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
		           	  		window.location.reload();
		           	  		
			   					 
		           	  	}else{
		           	  	window.location.reload();
		           	  	 
		           	  		}
		                     }, 
		         }
		     ); 
		};
//删除
var deleteAccountEntry =function(id){
	
	 var flag = confirm("确定删除?");
		if(flag){
			
			var params = {  
					
					"id":id,
					"action":"deleteAccountEntry",
					"className":"AccountEntryFormServlet",
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
		}
		};
//替换
var update =function(id,num){
	var flag = confirm("确定修改客户状态?");
		if(flag){
			var params = {  
					"id":id,
					"num":num,
					"action":"updateAccountEntry",
					"className":"AccountEntryFormServlet",
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
		}
		};
//替换
var splitAmounty =function(){
	var flag = confirm("确定拆分金额?");
		if(flag){
			var allmoney=document.getElementById("accountMoney").value;
			var firstSumMoney=document.getElementById("firstSumMoney").value;
			var secondSumMoney=document.getElementById("secondSumMoney").value;
			var id=document.getElementById("id").value;
			if(allmoney==firstSumMoney*1+secondSumMoney*1){
			var params = {  
					"id":id,
					"firstSumMoney":firstSumMoney,
					"secondSumMoney":secondSumMoney,
					"action":"splitAmounty",
					"className":"AccountEntryFormServlet",
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
		}else{
			alert("本次拆分所有金额不等于到账金额");
			document.getElementById("firstSumMoney").value="";
			document.getElementById("secondSumMoney").value="";
		}
		}
		
		
		};
		
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
			<h2>进账客户数及下单项目数</h2>
            <div>
				<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
					<form
							action="/ERP-NBEmail/helpServlet?action=inquiryDocumentaryAccountin&className=ItCaseIdServlet"
							method="post">
						<table class="usectable">
							<tr>

								<td class="usermatd3">根据时间查询:</td>
								<td class="usermatd3">下单客户起始时间:</td>
								<td><input type="text" readonly class="Wdate" id="time1"
										   name="time1" value="${starttime }"
										   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />

								<td class="usermatd3">下单客户截止时间:</td>
								<td><input type="text" readonly class="Wdate" id="time2"
										   name="time2" value="${endtime }"
										   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />

								<td class="usermatd3">所有客户存量起始时间:</td>
								<td><input type="text" readonly class="Wdate" id="time3"
										   name="time3" value="${starttime1 }"
										   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								<td class="usermatd3">上季度起始时间:</td>
								<td><input type="text" readonly class="Wdate" id="time4"
										   name="time4" value="${starttimea }"
										   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								<td class="usermatd3">上季度结束始时间:</td>
								<td><input type="text" readonly class="Wdate" id="time5"
										   name="time5" value="${endtimea }"
										   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />

								<td class="usermatd4"><input type="submit" value="查询"
															 class="usersearchbtn"></td>
							</tr>
						</table>

					</form>

				</div>
						
            </div>
			
				
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>跟单</td>
					<td>${starttime}-${endtime}下单客户数</td>
					<td>${starttime2}至今未下单客户数</td>
					<td>${starttime3}至今未下单客户数</td>
					<td>${starttimea}-${endtimea}A/B级客户下单数</td>
					<td>所有A/B级下单客户数</td>
					<td>${starttimea}-${endtimea}A/B级客户下单数(${starttime1}至今)</td>
					<td>所有A/B级下单客户数(${starttime1}至今)</td>
					
				</tr>
				<c:forEach items="${userList}" var="cus" varStatus="i">

					<tr>
						<td><a href="/ERP-NBEmail/helpServlet?action=detailPage&className=ItCaseIdServlet
						&starttime=${starttime}&endtime=${endtime}&starttime2=${starttime2}&starttime3=${starttime3}&starttimea=${starttimea}&endtimea=${endtimea}&starttime1=${starttime1}&userName=${cus.userName }" target="_blank">${cus.userName }</a></td>
						<td>${cus.incomingCustomers }</td>
						<td>${cus.incomingCustomers1 }</td>
						<td>${cus.incomingCustomers2 }</td>
						<td>${cus.listedBelow }</td>
						<td>${cus.allCustomer }</td>
						<td>${cus.newlistedBelow }</td>
						<td>${cus.allnewlistedBelow }</td>

					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>