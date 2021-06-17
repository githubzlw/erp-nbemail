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
			
			</h2>
			<div class="btns">
			<div class="docmentary"></div>
			<h3>功能列表</h3>
				<div class="btn-list">
				<c:if test="${EmpEName=='EmmaXie' }">
					<a href="/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet" target="_blank"><button>审批申请列表</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=CheckUp1&className=CheckUpServlet" target="_blank"><button>全部审批项目列表</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=RequestPaymentInformation&className=CheckUpServlet" target="_blank"><button>老的请求付款信息</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=pressMoney&className=CheckUpServlet" ><button>催款页面</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=factoryUnpaidStatistics&className=ItCaseIdServlet" ><button>工厂未付款统计</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=queryProfit&className=InvoiceServlet" ><button>销售跟单利润统计</button></a>

					</c:if>
					<c:if test="${EmpEName=='EdwardFan' }">
					<a href="/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet" target="_blank"><button>审批申请列表</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=CheckUp1&className=CheckUpServlet" target="_blank"><button>显示未通过项目</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=salesmanagerapproval&className=CheckUpServlet" target="_blank"><button>采购副总审批通过项目</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=RequestPaymentInformation&className=CheckUpServlet" target="_blank"><button>老的请求付款信息</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=pressMoney&className=CheckUpServlet" ><button>催款页面</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=factoryUnpaidStatistics&className=ItCaseIdServlet" ><button>工厂未付款统计</button></a>
						<a target="_blank" href="/ERP-NBEmail/helpServlet?action=queryProfit&className=InvoiceServlet" ><button>销售跟单利润统计</button></a>
					</c:if>
						<c:if test="${EmpEName=='Jiangwenlong' }">
					<a href="/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet" target="_blank"><button>审批申请列表</button></a>
					</c:if>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUs&className=InvoiceServlet" ><button>欠我司发票的所有工厂</button></a>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUsNew&className=InvoiceServlet" ><button>工厂及发票信息</button></a>
					<div class="line mb10"></div>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=nonPaymentCustomers&className=ItCaseIdServlet" ><button>客户到款及欠账数据</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=customerReconciliation&className=CustomerReconciliationSystemServlet" target="_blank"><button>个人客户对账表</button></a>
					<a href="/ERP-NBEmail/helpServlet?action=queryReceiptList&className=ItCaseIdServlet" target="_blank"><button>客户到款情况的数据统计</button></a>
					<div class="line mb10"></div>
					<a target="_blank" href="/ERP-NBEmail/helpServlet?action=allRefundInterface&className=AccountRefundFormServlet" ><button>退款列表页</button></a>
					 <a href="/ERP-NBEmail/helpServlet?action=refundApprovalResult&className=AccountRefundFormServlet" target="_blank"><button>退款完成列表页</button></a>
						
				</div>
			</div>
		</div>		
	</body>
</html>
<script type="text/javascript">
/*报出去但没有成功的项目  */
function TaskSystemView(userName) {
window.open("http://117.144.21.74:43888/New-Quotation/EmailUserController/TaskSystemView?userName="+userName);
}
/*疑难项目列表  */
function difficultProject(userName,userId,roleNo) {
	window.open("/ERP-NBEmail/project/difficultItemListPage?userName="+userName+"&roleNo="+roleNo);
	}
/* 催款页面 */
function nonPaymentCustomers(userName) {
window.open("http://117.144.21.74:33169/ERP-NBEmail/helpServlet?action=nonPaymentCustomers&className=ItCaseIdServlet");
}
/*登录NBEmail  */
function loginNBEmail(userName) {
window.open("http://117.144.21.74:33168/caselist.aspx?state=0");
}
/*登录NBEmail  */
function adminLoginNBEmail(userName) {
window.open("http://117.144.21.74:33168/main.aspx?prosort=9999");
}
//退出功能
function exitlogin() {
	$.cookie('name', '', {
		path : '/'
	});
	$.cookie('pass', '', {
		path : '/'
	});
	window.location.href = "/ERP-NBEmail/index.jsp";
}
</script>
