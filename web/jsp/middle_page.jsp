<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<title>功能选择</title>
	<script type="text/javascript"> 
    </script>
	<link rel="stylesheet" type="text/css" href="/ERP-NBEmail/css/index.css"/>
	<link rel="stylesheet" href="/ERP-NBEmail/css/add.css">
    <script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="/ERP-NBEmail/js/cookie.js"></script>	
    <script type="text/javascript" src="/ERP-NBEmail/js/common.js"></script>	
	</head>
   <body class="bg">
		<div class="login-main select_fun">			
			<h2 class="login-tit">
			<img src="/ERP-NBEmail/img/logo.png" class="logo">
			<span>ERP辅助系统</span>
				<button class="ext" onclick="exitlogin();">登出</button>
			</h2>
			<div class="btns">
			<div class="docmentary"></div>
			<h3>功能列表</h3>
				<div class="btn-list">
				
				<c:if test="${EmpEName=='ninazhao' }">
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=deleteBargin&className=BarginServlet" ><button>删除合同页面</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=examinationApprovalContracts&className=ContractApprovalFormServlet" ><button>查看全部审批合同页面</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=viewConferenceRecords&className=ItCaseIdServlet" ><button>会议记录</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=viewAllDrawings&className=ItCaseIdServlet" ><button>项目图纸</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=checkQualityReport&className=ItCaseIdServlet" ><button>质检报告</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=LookNewProject&className=ItCaseIdServlet" ><button>共模选厂分析</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=factoryUnpaidStatistics&className=ItCaseIdServlet" ><button>工厂未付款统计</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=completionOfMoney&className=AccountEntryFormServlet" target="_blank"><button>退款完成列表页</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=projectContractTime&className=ItCaseIdServlet" ><button>客户首笔到款页面</button></a>
					</c:if>
					<c:if test="${EmpEName=='OwenCui' }">
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=LookNewProject&className=ItCaseIdServlet" ><button>共模与选厂分析</button></a>
					</c:if>
					<c:if test="${EmpEName=='jerrylong' }">
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=inquireIntoAccounts&className=AccountEntryFormServlet" ><button>客户到款情况的数据统计</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=detailsOfNewCustomerArrival&className=InvoiceServlet" ><button>新客户到账详情</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=clientToAccountListPage&className=InvoiceServlet" ><button>客户到账列表页</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=documentaryToAccountPage&className=InvoiceServlet" ><button>跟单到账列表页</button></a>
						<a target="_blank" href="/ERP-NBEmail/helpServlet?action=queryProfit&className=InvoiceServlet" ><button>销售跟单利润统计</button></a>
					</c:if>
					<c:if test="${EmpEName=='mandyman' || EmpEName=='lisali' ||EmpEName=='ShiGuoJuan'||EmpEName=='roseli' }">
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=completionOfMoney&className=AccountEntryFormServlet" ><button>已审批通过列表</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=queryReceiptList&className=ItCaseIdServlet" ><button>查看最近进账列页</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=enterTheCustomerRelevanceTableIntoTheAccount&className=InvoiceServlet" target="_blank"><button>到账录入客户关联表</button></a>
					<!-- <a target="_blank" href="/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUs&className=InvoiceServlet" ><button>欠我司发票的所有工厂</button></a>
					 --><!-- <a target="_blank" href="/ERP-NBEmail/helpServlet?action=noInvoiceReceiptHasBeenReceived&className=BarginServlet" ><button>工厂发票未收齐合同数据统计</button></a>
					 --><a target="_blank" href="/ERP-NBEmail/helpServlet?action=more4MonthInvoiceNotReceived&className=BarginServlet" ><button>超过4个月没收齐发票的合同</button></a>
						<a target="_blank" href="/ERP-NBEmail/helpServlet?action=ViewFactoryData&className=FactoryReconciliationServlet" ><button>财务对账表</button></a>
					</c:if>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUs&className=InvoiceServlet" ><button>欠我司发票的所有工厂</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUsNew&className=InvoiceServlet" ><button>工厂及发票信息</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=clientToAccountListPage&className=InvoiceServlet" ><button>客户到账列表页</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=getPayInfo&className=InvoiceServlet" ><button>发票信息查询</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=factoryGetInfoDetail&className=InvoiceServlet" ><button>工厂发票明细</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=casePayInfoNew&className=InvoiceServlet" target="_blank"><button>项目发票明细</button></a>
					<c:if test="${notes!=''}">
					<div class="line mb10"></div>
					<span style="color:red;font-size:16px;">${notes}</span>
					</c:if>
					<div class="line mb10"></div>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=pressMoney&className=CheckUpServlet" ><button>催款页面</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=nonPaymentCustomers&className=ItCaseIdServlet" ><button>客户到款及欠账数据</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=startProjectStatistics&className=ItCaseIdServlet" ><button>扣款页面统计</button></a>
					<a  href="/ERP-NBEmail/helpServlet?action=accounEntry&className=AccountEntryFormServlet" target=_blank><button>ERP到账录入及到款页面</button></a>
                    <a  href="/ERP-NBEmail/helpServlet?action=inquiryDocumentaryAccountin&className=ItCaseIdServlet" target=_blank><button>进账客户数及下单项目数统计</button></a>
                    
					
                    <div class="line mb10"></div>
					<a href="/ERP-NBEmail/jsp/refund_entry_page.jsp" target="_blank"><button>退款录入页</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=allRefundInterface&className=AccountRefundFormServlet" ><button>退款列表页</button></a>
				    <a href="/ERP-NBEmail/helpServlet?action=refundApprovalResult&className=AccountRefundFormServlet" target="_blank"><button>退款完成列表页</button></a>
					</div>
			</div>
		</div>		
	</body>
</html>
<script type="text/javascript">

//退出功能
function exitlogin() {

	window.location.href = "/ERP-NBEmail/jsp/login.jsp";
}
</script>
