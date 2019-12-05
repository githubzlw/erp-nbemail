<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<style>
.usechange {
	width: 80%;
}

.emanagergettable {
	width: 100%;
}

.emanagergettable td {
	border-right: 1px solid rgb(147, 147, 147);
	padding: 0px 5px;
}

.even {
	background-color: #c7e5ff
}

.odd {
	background-color: #eaf5ff
}

.mana_inbtn {
	background: none;
	border: none;
	color: blur;
	text-decoration: underline;
	cursor: pointer;
	font-size: 15px;
}

.input_style1 {
	border: 1px #b1d8ff solid;
	width: 80px;
}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script>

//直接通过审批
function directapproval(id){
	
	window.location.href = "/ERP-NBEmail/helpServlet?action=directapproval&className=CheckUpServlet&id="+id+"";
}
var preliminaryhearing =function(id){
	
 var params = {  
			"id":id,
			"action":"preliminaryhearing",
			"className":"CheckUpServlet",
	};
    $.ajax({  
          url:'/ERP-NBEmail/helpServlet',  
          type:"post",  
          data:params,  
          success:function(data)  
                  { 
        	  		if(data == "YES"){
        	  			alert("Emma审批成功")
	   					window.location.reload();
        	  	}else{
        	  		window.location.reload();
        	  			
        	  		}
                  }, 
         
      }
  ); 
};
var directapproval =function(id){
	
	 var params = {  
				"id":id,
				"action":"directapproval",
				"className":"CheckUpServlet",
		};
	    $.ajax({  
	          url:'/ERP-NBEmail/helpServlet',  
	          type:"post",  
	          data:params,  
	          success:function(data)  
	                  { 
	        	  		if(data == "YES"){
	        	  			alert("Emma审批成功")
		   					window.location.reload();
	        	  	}else{
	        	  		window.location.reload();
	        	  			
	        	  		}
	                  }, 
	         
	      }
	  ); 
	};
	
	//审批需添加理由
var updateRemarks =function(projecttypes,id){
	 var projecttypes1 = document.getElementById(projecttypes).value;
	 var params = {  
				"id":id,
				"projecttypes":projecttypes1,
				"action":"PassTheReason",
				"className":"CheckUpServlet",
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
	//审批需添加理由
var NeedMoreInfo =function(id){
	var params = {  
				"id":id,
				"action":"PassTheReason",
				"className":"CheckUpServlet",
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

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<title>Emma付款审批页面</title>
</head>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;"
				align="left" id="ykhxx">
				<h2>审批申请列表</h2>
			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">查询:</td>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="vs" />

								</div>
							</td>
							<td>
								<div class="userselediv_nor">
									<select name="condition" id="se" class="userselein">
										<option value="1"
											<c:if test="${fyfy==0 }">selected="selected"</c:if>>项目编号</option>
										<option value="2"
											<c:if test="${fyfy==0 }">selected="selected"</c:if>>项目描述</option>
										<option value="3"
											<c:if test="${fyfy==0 }">selected="selected"</c:if>>申请资金</option>
										<option value="4"
											<c:if test="${fyfy==0 }">selected="selected"</c:if>>支付工厂</option>
									</select>
								</div>
							</td>
							<td class="usermatd3">待付款:</td>

							<td>
								<div class="userselediv_nor">
									<select name="condition2" id="audit" class="userselein">
										<option value="-1">全部</option>
										<option value="0"
											<c:if test="${fyfz==0 }">selected="selected"</c:if>>首付款</option>
										<option value="1"
											<c:if test="${fyfz==1 }">selected="selected"</c:if>>需垫付</option>
										<option value="2"
											<c:if test="${fyfz==2 }">selected="selected"</c:if>>运费</option>
										<option value="3"
											<c:if test="${fyfz==3 }">selected="selected"</c:if>>尾款</option>
									</select>
								</div>
							</td>
							<td class="usermatd3">申请时间:</td>

							<td>
								<div class="userselediv_nor">
									<select name="condition3" id="audit2" class="userselein">
										<option value="-1">全部</option>
										<option value="0"
											<c:if test="${fyfx==0 }">selected="selected"</c:if>>3天内申请</option>
										<option value="1"
											<c:if test="${fyfx==1 }">selected="selected"</c:if>>4~10天申请</option>
										<option value="2"
											<c:if test="${fyfx==2 }">selected="selected"</c:if>>10~20天申请</option>
										<option value="3"
											<c:if test="${fyfx==3 }">selected="selected"</c:if>>20天以前申请</option>
									</select>
								</div>
							</td>

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> <font id="ts0" class="cusmessa">(查询条件可以是项目编号、项目描述、申请资金或支付工厂。)共${total}有条
									正在审批中总金额${total1} 审批通过总金额${total2}</font> <!-- <a class="mana_inbtn"
								href="/ERP-NBEmail/helpServlet?action=CheckUp1&className=CheckUpServlet">显示全部项目</a>
								<a class="mana_inbtn" href="/ERP-NBEmail/helpServlet?action=Marlboro1&className=CheckUpServlet">价格1万以下审批通过</a>
                       <a class="mana_inbtn" href="/ERP-NBEmail/helpServlet?action=Marlboro&className=CheckUpServlet">全部审批通过</a>
								<a class="mana_inbtn" target=_blank
								href="/ERP-NBEmail/helpServlet?action=pressMoney&className=CheckUpServlet">查看催款页面</a>
								<a
								href="/ERP-NBEmail/helpServlet?action=customerReconciliation&className=CustomerReconciliationSystemServlet"
								target=_blank>个人客户对账表</a> <a
								href="/ERP-NBEmail/helpServlet?action=RequestPaymentInformation&className=CheckUpServlet"
								target=_blank>老的请求付款信息</a>
								 <a href="/ERP-NBEmail/helpServlet?action=completionOfMoney&className=AccountEntryFormServlet" target="_blank">退款完成列表页</a> -->
								</td>
						</tr>
					</table>

				</form>
			</div>
			<div></div>





			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<table class="emanagergettable">
					<c:forEach items="${cusList}" var="cus" varStatus="i">
						<c:if test="${cus.state==1 || cus.state==2|| cus.state==3}">
							<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
								<td
									style="color: #9E0DE8; font-weight: bold; border: 1px solid #BBBABA;"><a
									href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.caseNo} "
									target=_blank>${cus.caseNo }</a></td>
								<td style="color: #0D28E8; border: 1px solid #BBBABA;"><br>${cus.productDescE}&nbsp;
									${cus.productDescC}&nbsp;${cus.customerManager}<br>${cus.merchandManager1}<br></td>
								<td style="color: #D323D6; border: 1px solid #BBBABA;">${cus.paystate }</td>
								<td style="border: 1px solid #BBBABA;">项目负责人:<c:if
										test="${cus.checktype!=2 }">${cus.merchandManager1}&nbsp;${cus.merchandManager2}</c:if>
									<c:if test="${cus.checktype==2 }">funli</c:if><br /> 支付对象: <c:choose>
										<c:when test="${cus.checktype==1 }">支付工厂</c:when>
										<c:when test="${cus.checktype==2 }">支付物流</c:when>
										<c:when test="${cus.checktype==3 }">支付其他</c:when>
									</c:choose> ${cus.reason } <c:if test="${cus.reasonsApproval!=null }">
										<br />
										<span style="background: red;">${cus.reasonsApproval }</span>
									</c:if> <br />
								<span style="border: 0px solid #BBBABA; color: red;">
										${cus.projectnote }</span><br />
									毛利润：${cus.profit }<br />
									查看项目详情日期:${cus.loginDate }	
										</td>
								<td style="border: 1px solid #BBBABA;">${cus.facMoney }</td>
								<td style="border: 1px solid #BBBABA;">${cus.mtype }</td>
								<td style="border: 1px solid #BBBABA;">${cus.facReason}<br />
								<span style="border: 0px solid #BBBABA; color: red;"><c:if
											test="${cus.pressformoney ==1}">催款：${cus.stateDate }</c:if></span>
								</td>



								<td style="border: 1px solid #BBBABA;">${cus.state1 }</td>
								<td style="border: 1px solid; text-align: center;"><c:if
										test="${cus.statea==0 }">
										<input type="button" id="btnSubmit"
											onclick="preliminaryhearing(${cus.id})" value="预审通过"
											class="mana_inbtn">
										<!-- <form action="/ERP-NBEmail/helpServlet?action=allDrawings&className=RapidManufacturingServlet"
						id="uploadForm" enctype="multipart/form-data" method="post" > -->

										<select class="input_style1" id="projecttypes${cus.id}"
											name="projecttypes${cus.id}"
											onchange="updateRemarks('projecttypes${cus.id}',${cus.id})">
											<option value="0">审批备注</option>
											<option value="毛利润过低">毛利润过低</option>
											<option value="客户到款少">客户到款少</option>
											<option value="invoice或合同未上传或不全">invoice或合同未上传或不全</option>
											<option value="亏损项目请解释">亏损项目请解释</option>
											<option value="工厂尾款支付需客户尾款支付,特殊情况请解释">工厂尾款支付需客户尾款支付,特殊情况请解释</option>
                                             
										</select>
										<input type="button" id="btnSubmit"
											onclick="NeedMoreInfo(${cus.id})" value="需要更多信息"
											class="mana_inbtn">
										<!-- </form> -->
									</c:if> <c:if test="${cus.statea==1 }">
										<input type="button" id="btnSubmit"
											onclick="preliminaryhearing(${cus.id})" value="预审通过"
											class="mana_inbtn">
										<input type="button" id="btnSubmit"
											onclick="directapproval(${cus.id})" value="直接审批通过"
											class="mana_inbtn">
										<select class="input_style1" id="projecttypes${cus.id}"
											name="projecttypes${cus.id}"
											onchange="updateRemarks('projecttypes${cus.id}',${cus.id})">
											<option value="0">审批备注</option>
											<option value="毛利润过低">毛利润过低</option>
											<option value="客户到款少">客户到款少</option>
											<option value="invoice或合同未上传或不全">invoice或合同未上传或不全</option>
											<option value="亏损项目请解释">亏损项目请解释</option>
											<option value="工厂尾款支付需客户尾款支付,特殊情况请解释">工厂尾款支付需客户尾款支付,特殊情况请解释</option>
                                           
										</select>
										<input type="button" id="btnSubmit"
											onclick="NeedMoreInfo(${cus.id})" value="需要更多信息"
											class="mana_inbtn">
									</c:if> <c:if test="${cus.statea==2 }">
										<input type="button" id="btnSubmit"
											onclick="preliminaryhearing(${cus.id})" value="预审通过"
											class="mana_inbtn">
										<input type="button" id="btnSubmit"
											onclick="directapproval(${cus.id})" value="直接审批通过"
											class="mana_inbtn">
										<select class="input_style1" id="projecttypes${cus.id}"
											name="projecttypes${cus.id}"
											onchange="updateRemarks('projecttypes${cus.id}',${cus.id})">
											<option value="0">审批备注</option>
											<option value="毛利润过低">毛利润过低</option>
											<option value="客户到款少">客户到款少</option>
											<option value="invoice或合同未上传或不全">invoice或合同未上传或不全</option>
											<option value="亏损项目请解释">亏损项目请解释</option>
											<option value="工厂尾款支付需客户尾款支付,特殊情况请解释">工厂尾款支付需客户尾款支付,特殊情况请解释</option>
											
										</select>
										<input type="button" id="btnSubmit"
											onclick="NeedMoreInfo(${cus.id})" value="需要更多信息"
											class="mana_inbtn">
									</c:if> <c:if test="${cus.statea==3 }">
										<a
											href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.caseNo} "
											target=_blank>查 看</a>
									</c:if></td>

							</tr>
						</c:if>

					</c:forEach>
				</table>

			</div>


		</div>

	</div>
</body>
</html>