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
</style>
<script>
//通过预审
function preliminaryhearing(id){
	
	window.location.href = "/ERP-NBEmail/helpServlet?action=preliminaryhearing&className=CheckUpServlet&id="+id+"";
}
//直接通过审批
function directapproval(id){
	
	window.location.href = "/ERP-NBEmail/helpServlet?action=directapproval1&className=CheckUpServlet&id="+id+"";
}
</script>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<title>采购副总付款审批页面</title>
</head>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;"
				align="left" id="ykhxx">
				<h2>审批申请列表</h2>
			</div>



			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<table class="emanagergettable">
					<c:forEach items="${cusList}" var="cus" varStatus="i">
						<c:if test="${cus.state==1 || cus.state==2|| cus.state==3}">
							<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
								<td
									style="color: #9E0DE8; font-weight: bold; border: 1px solid #BBBABA;"><a
									href="/ERP-NBEmail/helpServlet?action=viewdetails&className=CheckUpServlet&&caseno=${cus.caseNo} "
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
									</c:choose> ${cus.reason }
								</td>
								<td style="border: 1px solid #BBBABA;">${cus.facMoney }</td>
								<td style="border: 1px solid #BBBABA;">${cus.mtype }</td>
								<td style="border: 1px solid #BBBABA;">${cus.facReason}</td>



								<td style="border: 1px solid #BBBABA;">${cus.state1 }</td>
								<td style="border: 1px solid; text-align: center;"><c:if
										test="${cus.statea==1}">
										<a
											href="/ERP-NBEmail/helpServlet?action=directapproval1&className=CheckUpServlet&id=${cus.id}"
											target=_blank>直接审批通过</a>
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