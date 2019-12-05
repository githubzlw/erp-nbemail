<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<style>
.usechange {
	width: 99%;
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

@media screen and (max-width: 1250px) {
	.emanagergettable td {
		font-size: 12px;
	}
}

/* margin: auto; */
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
function updatecustomerremarks(projectnote1,projectId,id){ 
	//var customerremarks=  document.getElementById(customerremarks1).value
	var projectnote=  document.getElementById(projectnote1).value;
	 if(projectnote != null && projectnote != undefined && projectnote != ''){
		projectnote = projectnote.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
		window.location.href="/ERP-NBEmail/helpServlet?action=updatecustomerremarks&className=CheckUpServlet&&projectId="+projectId+"&&projectNote="+projectnote+"&&id="+id;	
	}else{
		window.location.href="/ERP-NBEmail/helpServlet?action=updatecustomerremarks&className=CheckUpServlet&&projectId="+projectId+"&&id="+id;
	}
	}

function updateAll(projectnote1,projectId,id,sign) {
	var flag = confirm("确定走快速通道吗?");
	if(flag) {
        var projectnote = document.getElementById(projectnote1).value;
        if (projectnote != null && projectnote != undefined && projectnote != '') {
            projectnote = projectnote.replace(/\%/g, "%25").replace(/\#/g, "%23").replace(/\&/g, "%26").replace(/\+/g, "%2B");
			var params = {
				"projectId":projectId,
				"fastTrackReasons":projectnote,
				"id":id,
				"action":"updatecustomerremarks1",
				"className":"CheckUpServlet",
			};
			$.ajax({
						url:'/ERP-NBEmail/helpServlet',
						type:"post",
						data:params,
						success:function(data)
						{
							if(data == "YES"){
								document.getElementById(sign).innerHTML="快速通道付款项目";
							}else{
							}
						},

					}
			);
} else {
			document.getElementById(sign).innerHTML="项目未进入快速通道,理由未填写";
        }
    }
}


var updateMoney =function(caseNo,sign){
	var params = {  
			"caseNo":caseNo,
		    "action":"updateMoney",
			"className":"CheckUpServlet",
  	};
      $.ajax({  
            url:'/ERP-NBEmail/helpServlet',  
            type:"post",  
            data:params,  
            success:function(data)  
                    { 
          	  		if(data == "YES"){
          	  		document.getElementById(sign).innerHTML="催";		 
          	  		}else{
          	  		}
                    }, 
           
        }
    ); 
};
var withdraw =function(id,sign){
	var params = {
		"id":id,
		"action":"updateWithDraw",
		"className":"CheckUpServlet",
	};
	$.ajax({
				url:'/ERP-NBEmail/helpServlet',
				type:"post",
				data:params,
				success:function(data)
				{
					if(data == "YES"){
						document.getElementById(sign).innerHTML="已取消申请";
					}else{
					}
				},

			}
	);
};
</script>


<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<title>催款页面</title>
</head>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;"
				align="left" id="ykhxx">

				<h2>催款申请列表</h2>
				<c:if test="${id==1 }">
				
					<h4>Emma审批通过总价格: ${total1 }&nbsp;&nbsp;Eddie审批通过总价格:${total2 }&nbsp;&nbsp;最近3天待审批总价格:${total3 }</h4>
					<br />
					<!-- <a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=deleteBargin&className=BarginServlet"
						target=_blank>删除合同页面</a>
					<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=checkQualityReport&className=ItCaseIdServlet"
						target=_blank>查询客户质检报告项目</a>
					<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=projectContractTime&className=ItCaseIdServlet"
						target=_blank>客户首笔到款日期页面</a>
					<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=viewAllDrawings&className=ItCaseIdServlet"
						target=_blank>查看项目图纸页面</a>
					<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=viewConferenceRecords&className=ItCaseIdServlet"
						target=_blank>查看会议记录</a>
					<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=LookNewProject&className=ItCaseIdServlet"
						target=_blank>共模与选厂分析</a>
					
                    <a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=factoryUnpaidStatistics&className=ItCaseIdServlet"
						target=_blank>工厂未付款统计</a> -->
                    
					</c:if>
				

			</div>
			<%-- <a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=nonPaymentCustomers&className=ItCaseIdServlet"
						target=_blank>项目客户未付款统计</a>
			<a class="mana_inbtn"
				href="/ERP-NBEmail/helpServlet?action=examinationApprovalContracts&className=ContractApprovalFormServlet"
				target=_blank>查看全部审批合同页面</a>
				
			<c:if test="${id!=1 }"><a class="mana_inbtn"
				href="/ERP-NBEmail/helpServlet?action=accounEntry&className=AccountEntryFormServlet"
				target=_blank>客户到款情况的数据统计</a></c:if>
				<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=startProjectStatistics&className=ItCaseIdServlet"
						target=_blank>扣款页面统计</a> --%>
				
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">

			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<table class="emanagergettable">
					<c:forEach items="${cusList}" var="cus" varStatus="i">

							<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
								<td
									style="color: #9E0DE8; font-weight: bold; border: 1px solid #BBBABA;">
									<a
									href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.caseNo }"
									target=_blank>${cus.caseNo }</a> <font id="sign${i.count }"
									style="color: red;" class="cusmessa"></font>
									<font id="signa${i.count }"
										  style="color: red;" class="cusmessa"></font>
								</td>

								<td style="border: 1px solid #BBBABA;">项目负责人:<c:if
										test="${cus.checktype !=2 }">${cus.merchandManager1}&nbsp;${cus.merchandManager2}&nbsp;${cus.merchandising}</c:if>
									<c:if test="${cus.checktype==2 }">funli</c:if>
								</td>

								<td
									style="border: 1px solid #BBBABA; width: 130px; word-break: break-all;">${cus.facReason}
								</td>
								<td style="border: 1px solid #BBBABA;">发票金额：<br />${cus.amount }<c:choose>
										<c:when test="${cus.moneytype==1 }">美金</c:when>
										<c:when test="${cus.moneytype==2 }">人民币</c:when>
										<c:when test="${cus.moneytype==3 }">欧元</c:when>
										<c:when test="${cus.moneytype==5 }">英镑</c:when>

									</c:choose></td>
								<td style="border: 1px solid #BBBABA;">实际到款：<br />${cus.sumacount }<c:choose>
										<c:when test="${cus.moneytype==1 }">美金</c:when>
										<c:when test="${cus.moneytype==2 }">人民币</c:when>
										<c:when test="${cus.moneytype==3 }">欧元</c:when>
										<c:when test="${cus.moneytype==5 }">英镑</c:when>

									</c:choose>
								</td>
								<td style="border: 1px solid #BBBABA;">利润率：<br />${cus.profit }%
								</td>

								<td style="border: 1px solid #BBBABA; width: 160px;">支付对象:
									<br /> <c:choose>
										<c:when test="${cus.checktype==1 }">支付工厂</c:when>
										<c:when test="${cus.checktype==2 }">支付物流</c:when>
										<c:when test="${cus.checktype==3 }">支付其他</c:when>

									</c:choose>${cus.geldObject }</td>
								<td style="border: 1px solid #BBBABA;">${cus.stateDate != null ?fn:substring(cus.stateDate,0,fn:indexOf(cus.stateDate,":")):cus.stateDate}
									<br />${cus.facMoney }RMB
								</td>

								<td style="border: 1px solid #BBBABA; width: 160px;"><c:if
										test="${cus.reasonsApproval!=null }">
										<span style="background: red;">Emma审批时间：<br />${cus.approvalTime != null ?fn:substring(cus.approvalTime,0,fn:indexOf(cus.approvalTime,":")):cus.approvalTime}
											<br />${cus.reasonsApproval }</span>
										<br />
									</c:if> <c:choose>
										<c:when test="${cus.state==1 }">Emma<br />审批通过</c:when>
										<c:when test="${cus.state==2 }">正在审批中</c:when>
										<c:when test="${cus.state==3 }">Edward审批通过</c:when>
                                    <c:when test="${cus.state==4 }">未通过审批</c:when>
                                    <c:when test="${cus.state==5 }">已完成款项</c:when>
                                    <c:when test="${cus.state==6 }">未通过审批</c:when>
									</c:choose>
                                    <c:if test="${cus.expressLaneApproval!=2}"> <c:if test="${cus.expressLane==1}">
								<span style="color:red;">	<br/> 快速通道项目，待审批
									</span>
                                    </c:if></c:if>
                                    <c:if test="${cus.expressLaneApproval==2}"><span style="color:red;"><br/>快速通道项目	<br/>款项审批不通过</span></c:if>
                                </td>


								<td style="border: 1px solid #BBBABA; width: 80px;">项目备注： <input
									type="text" style="width: 150px;" id="projectnote${i.count}"
									value="${cus.projectnote }" title="${cus.projectnote }"></td>
								<td style="border: 1px solid #BBBABA; width: 80px;"><c:if test="${cus.facMoney<=4000 }">快速通道付款理由： <input
										type="text" style="width: 150px;" id="fastTrackReasons${i.count}"
										value="${cus.fastTrackReasons }" title="${cus.fastTrackReasons }">
								</c:if>
								</td>
								<td style="border: 1px solid #BBBABA;"><input type="button"
									value="提交备注"
									onclick="updatecustomerremarks('projectnote${i.count}','${cus.caseNo }',${cus.id });"
									class="emanagergetdel"
									style="text-align: center; background: #fff;" />
									<c:if test="${cus.state!=3 }"><c:if test="${cus.expressLane!=1}"><c:if test="${cus.facMoney<=4000 }"><input type="button"
										   value="快速通道付款"
										   onclick="updateAll('fastTrackReasons${i.count}','${cus.caseNo }',${cus.id },'signa${i.count }');"
										   class="emanagergetdel"
																									  style="text-align: center; background: #fff;" /></c:if></c:if></c:if>
									<br />
								<input type="button"
									onclick="updateMoney('${cus.id }','sign${i.count }');"
									value="催款">
								<c:if test="${cus.state==2 }">	<input type="button"
										   onclick="withdraw('${cus.id }','signa${i.count }');"
												 value="取消申请"></c:if>

								</td>
							</tr>


					</c:forEach>
				</table>

			</div>


		</div>

	</div>
</body>
</html>