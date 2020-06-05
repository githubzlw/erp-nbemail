<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售认领到款</title>
</head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 600px;
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
.factoryOffering {position:absolute;top:50px;left:600px;}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function recoveryInformation(id) {
	window.open("/ERP-NBEmail/helpServlet?action=recoveryInformation&className=AccountEntryFormServlet&id="+id);
}
function salesModificationInformation(id) {
	document.getElementById("uploadForm1").submit(); 
}


function updateAll() {
	var country1 = document.getElementById("country1").value;
	var country="";
	if(country1==1){
		country="USA";
	}else if(country1==2){
		country="Canada";
	}else if(country1==3){
		country="France";
	}else if(country1==4){
		country="Germany";
	}else if(country1==5){
		country="Netherlands";
	}else if(country1==6){
		country="Israel";
	}else if(country1==7){
		country="Mexico";
	}else if(country1==8){
		country="Australia";
	}else if(country1==9){
		country="Italy";
	}else if(country1==10){
		country="Switzerland";
	}else if(country1==11){
		country="Finland";
	}else if(country1==12){
		country="Sweden";
	}else if(country1==13){
		country="UK";
	}else if(country1==14){
		country="Argentina";
	}else if(country1==15){
		country="Other";
	}else if(country1==16){
		country="Japan";
	}else if(country1==17){
		country="China";
	}else if(country1==18){
		country="Austria";
	}else if(country1==19){
		country="Saudi Arabia";
	}else if(country1==20){
		country="Belgium";
	}else if(country1==21){
		country="Spain";
	}else if(country1==22){
		country="New Zealand";
	}else if(country1==23){
		country="Slovenia";
	}else if(country1==24){
		country="Serbia";
	}else if(country1==25){
		country="Ireland";
	}
	
	$("#country2").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country3").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country4").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country5").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country6").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country7").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country8").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country9").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country10").prepend("<option selected=selected   value='"+country1+"'>"+country+"</option>"); 
	$("#country11").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	$("#country12").prepend("<option selected=selected value='"+country1+"'>"+country+"</option>"); 
	if(country1==15)
	{
		var target = document.getElementById("state1");	
		target.style.display="block";
	}
	
}

function checkData() {
	var country1 = document.getElementById("country1").value;
	
	if(country1!=0){
	var invoicemoney1 = document.getElementById("invoicemoney1").value;
	var invoicemoney2 = document.getElementById("invoicemoney2").value;
	var invoicemoney3 = document.getElementById("invoicemoney3").value;
	var invoicemoney4 = document.getElementById("invoicemoney4").value;
	var invoicemoney5 = document.getElementById("invoicemoney5").value;
	var invoicemoney6 = document.getElementById("invoicemoney6").value;
	var invoicemoney7 = document.getElementById("invoicemoney7").value;
	var invoicemoney8 = document.getElementById("invoicemoney8").value;
	var invoicemoney9 = document.getElementById("invoicemoney9").value;
	var invoicemoney10 = document.getElementById("invoicemoney10").value;
	var invoicemoney11 = document.getElementById("invoicemoney11").value;
	var invoicemoney12 = document.getElementById("invoicemoney12").value;
	var invoice1 = document.getElementById("invoice1").value;
	var invoice2 = document.getElementById("invoice2").value;
	var invoice3 = document.getElementById("invoice3").value;
	var invoice4 = document.getElementById("invoice4").value;
	var invoice5 = document.getElementById("invoice5").value;
	var invoice6 = document.getElementById("invoice6").value;
	var invoice7 = document.getElementById("invoice7").value;
	var invoice8 = document.getElementById("invoice8").value;
	var invoice9 = document.getElementById("invoice9").value;
	var invoice10 = document.getElementById("invoice10").value;
	var invoice11 = document.getElementById("invoice11").value;
	var invoice12 = document.getElementById("invoice12").value;
	var allMoney = document.getElementById("allMoney").value;
	var customerId = document.getElementById("customerId").value;
	var id = document.getElementById("id").value;
		var tradeCurrency = document.getElementById("tradeCurrency").value;

	var params = {  
			"invoicemoney1":invoicemoney1,
			"invoicemoney2":invoicemoney2,
			"invoicemoney3":invoicemoney3,
			"invoicemoney4":invoicemoney4,
			"invoicemoney5":invoicemoney5,
			"invoicemoney6":invoicemoney6,
			"invoicemoney7":invoicemoney7,
			"invoicemoney8":invoicemoney8,
			"invoicemoney9":invoicemoney9,
			"invoicemoney10":invoicemoney10,
			"invoicemoney11":invoicemoney11,
			"invoicemoney12":invoicemoney12,
			"invoice1":invoice1,
			"invoice2":invoice2,
			"invoice3":invoice3,
			"invoice4":invoice4,
			"invoice5":invoice5,
			"invoice6":invoice6,
			"invoice7":invoice7,
			"invoice8":invoice8,
			"invoice9":invoice9,
			"invoice10":invoice10,
			"invoice11":invoice11,
			"invoice12":invoice12,
			"allMoney":allMoney,
			"id":id,
			"customerId":customerId,
		    "tradeCurrency":tradeCurrency,
			"action":"checkData1",
			"className":"AmountClaimFormServlet",
 	};
	$.ajax({  
           url:'/ERP-NBEmail/helpServlet',  
           type:"post",  
           data:params,  
           success:function(data)  
                   { 
             if(data == "YES"){
            	 document.getElementById("uploadForm").submit();	
            	 
         	  	}else{
         	  		document.getElementById("wrong1").innerHTML=data;		
         	  		
         	  	 
         	  	 }
                   }, 
       }
   ); 
	
	}else{
		document.getElementById("wrong1").innerHTML="国家需选择";	
	}
}





</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>销售认领到款</h2>
            <div>
          
                       估计客户Id:${account.nBEmailId }
                             总金额：${account.tradeAmount } ${account.tradeCurrency }<br/>
			<span style="color:#47d300;">${payAttentionTo }</span> 			
            </div>
            <form action="/ERP-NBEmail/helpServlet?action=checkData&className=AmountClaimFormServlet"
						id="uploadForm" enctype="multipart/form-data" method="post" >
						 <input type="hidden" id="allMoney" name="allMoney" value="${allMoney }">
						 <input type="hidden" id="tradeCurrency"  name="tradeCurrency" value="${account.tradeCurrency }">
				<input type="hidden" id="customerId"  name="customerId" value="${customerId }">
						 <input type="hidden" id="id" name="id" value="${id }">
				<div id="state1" style="display:none;"><input type="text" id="state" name="state" placeholder="国家名"/></div>		 
			<table class="emanagergettable">
				<tr class="emanagergettr">
                    <td>invoice号</td>
					<td>给该invoice分配的金额</td>
					<td style="width:20px;">出口年</td>
					<td style="width:20px;">出口月</td>
					<td>款项来源国家</td>
				</tr>
				<c:if test="${cusList!=null }">
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.invoice }</td>
						<td>${cus.sumMoney }</td>
						<td>${cus.exportYear }</td>
						<td>${cus.exportMonth }</td>
						<td><c:choose>
						<c:when test="${cus.country==1 }">USA</c:when>
										<c:when test="${cus.country==2 }">Canada</c:when>
										<c:when test="${cus.country==3 }">France</c:when>
										<c:when test="${cus.country==4 }">Germany</c:when>
										<c:when test="${cus.country==5 }">Netherlands</c:when>
										<c:when test="${cus.country==6 }">Israel</c:when>
										<c:when test="${cus.country==7 }">Mexico</c:when>
										<c:when test="${cus.country==8 }">Australia</c:when>
										<c:when test="${cus.country==9 }">Italy</c:when>
										<c:when test="${cus.country==10 }">Switzerland</c:when>
										<c:when test="${cus.country==11 }">Finland</c:when>
										<c:when test="${cus.country==12 }">Sweden</c:when>
										<c:when test="${cus.country==13 }">UK</c:when>
										<c:when test="${cus.country==14 }">Argentina</c:when>
										<c:when test="${cus.country==15 }">Other</c:when>
										<c:when test="${cus.country==16 }">Japan</c:when>
										<c:when test="${cus.country==17 }">China</c:when>
										<c:when test="${cus.country==18 }">Austria</c:when>
										<c:when test="${cus.country==19 }">Saudi Arabia</c:when>
										<c:when test="${cus.country==20 }">Belgium</c:when>
										<c:when test="${cus.country==21 }">Spain</c:when>
										<c:when test="${cus.country==22 }">New Zealand</c:when>
										<c:when test="${cus.country==23 }">Slovenia</c:when>
										<c:when test="${cus.country==24 }">Serbia</c:when>
										<c:when test="${cus.country==25 }">Ireland</c:when>

									</c:choose></td>
						
					</tr>
				</c:forEach></c:if>
				
				
				
				<c:if test="${financialAuthority !=100 }"><c:if test="${cusList==null }">
				<tr>
						<td><input type="text" id="invoice1" name="invoice1" ></td>
						<td><input type="text" id="invoicemoney1" name="invoicemoney1" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year1" name="year1" value="${year }" ></td>
						<td><input type="text" id="month1" name="month1" value="${month }" ></td>
						<td><select name="country1" id="country1" class="userselein" onchange="updateAll();">
						             <option value="0">-请选择-</option>
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				   <tr>
							<td><input type="text" id="invoice2" name="invoice2" ></td>
						<td><input type="text" id="invoicemoney2" name="invoicemoney2" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year2" name="year2" value="${year }" ></td>
						<td><input type="text" id="month2" name="month2" value="${month }" ></td>
						<td><select name="country2" id="country2" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice3" name="invoice3" ></td>
						<td><input type="text" id="invoicemoney3" name="invoicemoney3" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year3" name="year3" value="${year }" ></td>
						<td><input type="text" id="month3" name="month3" value="${month }" ></td>
						<td><select name="country3" id="country3" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice4" name="invoice4" ></td>
						<td><input type="text" id="invoicemoney4" name="invoicemoney4" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year4" name="year4" value="${year }" ></td>
						<td><input type="text" id="month4" name="month4" value="${month }" ></td>
						<td><select name="country4" id="country4" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice5" name="invoice5" ></td>
						<td><input type="text" id="invoicemoney5" name="invoicemoney5" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year5" name="year5" value="${year }" ></td>
						<td><input type="text" id="month5" name="month5" value="${month }" ></td>
						<td><select name="country5" id="country5" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice6" name="invoice6" ></td>
						<td><input type="text" id="invoicemoney6" name="invoicemoney6" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year6" name="year6" value="${year }" ></td>
						<td><input type="text" id="month6" name="month6" value="${month }" ></td>
						<td><select name="country6" id="country6" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice7" name="invoice7" ></td>
						<td><input type="text" id="invoicemoney7" name="invoicemoney7" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year7" name="year7" value="${year }" ></td>
						<td><input type="text" id="month7" name="month7" value="${month }" ></td>
						<td><select name="country7" id="country7" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice8" name="invoice8" ></td>
						<td><input type="text" id="invoicemoney8" name="invoicemoney8" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year8" name="year8" value="${year }" ></td>
						<td><input type="text" id="month8" name="month8" value="${month }" ></td>
						<td><select name="country8" id="country8" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice9" name="invoice9" ></td>
						<td><input type="text" id="invoicemoney9" name="invoicemoney9" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year9" name="year9" value="${year }" ></td>
						<td><input type="text" id="month9" name="month9" value="${month }" ></td>
						<td><select name="country9" id="country9" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice10" name="invoice10" ></td>
						<td><input type="text" id="invoicemoney10" name="invoicemoney10" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year10" name="year10" value="${year }" ></td>
						<td><input type="text" id="month10" name="month10" value="${month }" ></td>
						<td><select name="country10" id="country10" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice11" name="invoice11" ></td>
						<td><input type="text" id="invoicemoney11" name="invoicemoney11" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year11" name="year11" value="${year }" ></td>
						<td><input type="text" id="month11" name="month11" value="${month }" ></td>
						<td><select name="country11" id="country11" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				<tr>
						<td><input type="text" id="invoice12" name="invoice12" ></td>
						<td><input type="text" id="invoicemoney12" name="invoicemoney12" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');"></td>
						<td><input type="text" id="year12" name="year12" value="${year }" ></td>
						<td><input type="text" id="month12" name="month12" value="${month }" ></td>
						<td><select name="country12" id="country12" class="userselein">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
									<option value="16">Japan</option>
									<option value="17">China</option>
									<option value="18">Austria</option>
									<option value="19">Saudi Arabia</option>
									<option value="20">Belgium</option>
									<option value="21">Spain</option>
									<option value="22">New Zealand</option>
									<option value="23">Slovenia</option>
									<option value="24">Serbia</option>
									<option value="25">Ireland</option>
								</select></td>
					</tr>
				</c:if>
				</c:if>
			</table>
			
			  <c:if test="${financialAuthority!=100}"><br/> <c:if test="${cusList1==null }"> <input type="button"  value="检查"  onclick="checkData();"></c:if>
				</c:if></form>
				<c:if test="${financialAuthority==99}"><input type="button" value="去掉认领信息，复原"  onclick="recoveryInformation(${id})"></c:if>
			<span style="color:red;">${wrong }</span>
			<span style="color:red;" id="wrong1"></span>
			<div style="border-style: solid; border-width: 1px;width:600px">
			
			<span style="font-size:14px">A.本页分配的 所有金额之和 等于 到账总金额</span><br/>
            <span style="font-size:14px">B.此Invoice 是否存在</span><br/>
			<span style="font-size:14px">C.当前登录人是否是此项目成员 （可以对 助理的账号做 特殊处理， 助理都可以传）</span><br/>
			<span style="font-size:14px">D.此Invoice 已经录入的金额 + 新录入的金额是否 超过 Invoice 总金额</span><br/>
			<span style="font-size:14px">E.同一合同不允许录入两遍，金额拆分在详情中进行</span><br/>
			</div>
			<c:if test="${cusList1!=null }">
			<br/>
			<form action="/ERP-NBEmail/helpServlet?action=salesModificationInformation&className=PreparatorEntryFormServlet"
						id="uploadForm1" enctype="multipart/form-data" method="post" >
						<input type="hidden"  name="id" value="${id }">
						<input type="hidden"  name="allMoney" value="${allMoney }">
						 <input type="hidden"  name="customerId" value="${customerId }">
			<table class="emanagergettable">
				<tr class="emanagergettr">
				    <td>invoiceId</td>
                    <td>预计到账</td>
					<td>实际到账</td>
					<td>备注</td>
					</tr>
				<c:if test="${preparator.salesSubmission==0 }">	
				<c:forEach items="${cusList1}" var="cus" varStatus="i">
				<input type="hidden" id="iid${i.count }" name="iid${i.count }" value="${cus.iid }">
				<input type="hidden" id="id${i.count }" name="id${i.count }" value="${cus.id }">
					<tr>
					    <td>${cus.invoice }</td>
						<td><input type="text" id="iimoney${i.count }" name="iimoney${i.count }" value="${cus.iimoney }"></td>
						<td><input type="text" id="ifmoney${i.count }" name="ifmoney${i.count }"  value="${cus.ifmoney }"></td>
						<td><input type="text" id="remarks${i.count }" name="remarks${i.count }"  value="${cus.remarks }"></td>
					</tr>
				</c:forEach></c:if>
				<c:if test="${preparator.salesSubmission==1 }">	
				<c:forEach items="${cusList1}" var="cus" varStatus="i">
				    <tr>
					   <td>${cus.invoice }</td>
						<td>${cus.iimoney }</td>
						<td>${cus.ifmoney }</td>
						<td>${cus.remarks }</td>
					</tr>
				</c:forEach></c:if>
				</table>
				<c:if test="${preparator.salesSubmission==0 }">
				<input type="button"  value="提交"  onclick="salesModificationInformation();"></c:if>
				
				
				
				</form>
				</c:if>
				
				
			</div></div>
</body>
</html>