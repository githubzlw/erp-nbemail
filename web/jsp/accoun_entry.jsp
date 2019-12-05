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
function accAdd(arg1,arg2){
	var r1,r2,m;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2));
	return (arg1*m+arg2*m)/m;
}
//替换
var splitAmounty =function(){
	var flag = confirm("确定拆分金额?");
		if(flag){
			var allmoney=document.getElementById("accountMoney").value;
			var firstSumMoney=document.getElementById("firstSumMoney").value;
			var secondSumMoney=document.getElementById("secondSumMoney").value;
			var id=document.getElementById("id").value;
			var all=accAdd(firstSumMoney,secondSumMoney);

			if(allmoney==all){
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
     <div id="div" class="div_style">
					
					<input type="hidden" value="${id }" id="id"
							name="id">
						<span style="color:red;">到款金额:<input type="text" id="accountMoney" disabled="disabled" style="border:0px;background:rgba(0, 0, 0, 0);width:60px; "></span> 
							<a href="javascript:CloseDiv();">X</a>
							
					    
						<div class="usermatd1">第一笔拆分金额：</div>
						<div><input type="text" id="firstSumMoney" name="firstSumMoney" ></div>
						
					    
						<div class="usermatd1">第二笔拆分金额：</div>
						<div><input type="text" id="secondSumMoney" name="secondSumMoney" ></div>
						  
					<br/><span >
						<input
							class="input_style2" value="拆分验证" type="button"
							onclick="splitAmounty()"
							style="margin-top: 10px;"></span>
				</div>
		<div class="usechange">
			<h2>ERP到账录入及到款页面</h2>
            <div>
            <form action="/ERP-NBEmail/helpServlet?action=UploadForm&className=AccountEntryFormServlet"
					id="uploadForm"  method="post" enctype="multipart/form-data">
            <table><tr>
           <td>
					
					<c:if test="${roleNO==100 }"><input type="button" onclick="uploadFile()" class="inp_sel" value="上传excel"/>
					<input type="file" style="display: none" multiple name="file"  id="fileupload2"  name="fileupload2"  onchange="uploadAllFile(this)">
					<!-- <a href="/ERP-NBEmail/helpServlet?action=financialContributionApproval&className=PreparatorEntryFormServlet" target="_blank">财务进账审批页</a> -->
					<!-- <a href="/ERP-NBEmail/helpServlet?action=completionOfMoney&className=AccountEntryFormServlet" target="_blank">已审批通过列表</a>
					<a href="/ERP-NBEmail/helpServlet?action=allRefundInterface&className=AccountRefundFormServlet" target="_blank">退款列表页</a>
					<a href="/ERP-NBEmail/helpServlet?action=queryReceiptList&className=ItCaseIdServlet" target="_blank">查看最近进账列表页</a>
					<a href="/ERP-NBEmail/helpServlet?action=noInvoiceReceiptHasBeenReceived&className=BarginServlet" target="_blank">工厂发票未收齐合同数据统计</a>
					<a href="/ERP-NBEmail/helpServlet?action=more4MonthInvoiceNotReceived&className=BarginServlet" target="_blank">超过4个月没收齐发票的合同</a>
					<a href="/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUs&className=InvoiceServlet" target="_blank">欠我司发票的所有工厂</a>
					<a href="/ERP-NBEmail/helpServlet?action=invoiceDocumentarySaleOwnedToUs&className=InvoiceServlet" target="_blank">跟单名下欠我司发票的所有工厂</a>
					<a href="/ERP-NBEmail/helpServlet?action=invoiceDocumentaryPurchaseOwnedToUs&className=InvoiceServlet" target="_blank">采购名下欠我司发票的所有工厂</a> -->
					</c:if>
					<c:if test="${roleNO==100 }"><input class="factoryOffering" type="button" value="批量认领"  onclick="batchClaim();"  style="float: left;"></c:if>
					</td>
					</tr>
					
					</table></form>
						
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
					<td>操作</td>
					<td>已认领人</td>
					<td>销售进账确认</td>
					<td>客户状态</td>
					<c:if test="${roleNO==100 }"><td >批量审批<input type="checkbox" name="parent_dids" onclick="select_checkbox(this)"/></td>
					<td>删除</td>
					</c:if>
					
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
						<td><c:if test="${cus.claimant==null&&roleNO==5 }">
						<input type="button" type="button" id="open" onclick="javascript:OpenDiv(${cus.tradeAmount },${cus.id });" value="拆分金额">
						<input type="button" onclick="enterFinancialEntry(${cus.tradeAmount },${cus.id },${cus.nBEmailId},'${cus.tradeCurrency}');" value="销售认领"></c:if>
						    <c:if test="${roleNO==100 }"><input type="button" onclick="lookEnterFinancialEntry(${cus.tradeAmount },${cus.id },${cus.nBEmailId},'${cus.tradeCurrency}');" value="销售认领"></c:if>
						    <c:if test="${cus.claimant!=null&&user.userName==cus.claimant&&roleNO==5}"><input type="button" onclick="lookEnterFinancialEntry(${cus.tradeAmount },${cus.id },${cus.nBEmailId},'${cus.tradeCurrency}');" value="销售认领"></c:if>
						    <c:if test="${roleNO==99}"><c:if test="${cus.claimant!=null}"><input type="button" onclick="lookEnterFinancialEntry(${cus.tradeAmount },${cus.id },${cus.nBEmailId},'${cus.tradeCurrency}');" value="销售认领"></c:if>
						    <c:if test="${cus.claimant==null}">
						    <input type="button" onclick="enterFinancialEntry(${cus.tradeAmount },${cus.id },${cus.nBEmailId},'${cus.tradeCurrency}');" value="销售认领">
						    </c:if>
						    <input type="button" id="open" onclick="javascript:OpenDiv(${cus.tradeAmount },${cus.id });" value="拆分金额">
						    </c:if>
						</td>
						<td><c:if test="${cus.claimant!=null }">${cus.claimant },${cus.claimTime != null ?fn:substring(cus.claimTime,0,fn:indexOf(cus.claimTime," ")):""}</c:if></td>
						<td><c:choose>
						<c:when test="${cus.salesConfirmationAmount==0 }">未确认</c:when>
						<c:when test="${cus.salesConfirmationAmount==1 }">已确认</c:when></c:choose></td>
						<td><c:choose>
						<c:when test="${cus.newCustomer==0 }">老客户</c:when>
						<c:when test="${cus.newCustomer==1 }">新客户</c:when></c:choose></td>
						<c:if test="${roleNO==100 }"><td><c:if test="${cus.salesConfirmationAmount==1}"><span class="s0"><input type="checkbox" name="dids" id="dids"  value="${cus.id }" /></span></c:if></td>
						<td><c:if test="${cus.claimant==null }"><input type="button" onclick="deleteAccountEntry(${cus.id});" value="删除"></c:if></td>
						</c:if>
						
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>