<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看全部合同审批</title>
</head>
<style>
.usechange {
	width: 80%;
}

.emanagergettable {
	width: 100%;
	border: 1px #7D7D7D solid;
	border-collapse: collapse;
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
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">



window.onload=function(){
	document.getElementById('client').style.background='url(/ERP-NBEmail/img/emana/elion.png) 0 0 no-repeat';
	document.getElementById('client').getElementsByTagName('a')[0].style.color="#fff";
	document.getElementById('eleft').style.height=(document.body.scrollHeight-68)+'px';
}	
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>查看全部合同审批</h2>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=examinationApprovalContracts&className=ContractApprovalFormServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">根据项目号模糊查询:</td>

							<td>
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="condition" />

								</div>
							</td>
							<%-- <td><select  id="conferenceMessagea" name="conferenceMessagea">
							<option value="-1" <c:if test="${fyzt==0 }">selected="selected"</c:if>>全部</option>
							<option value="0" <c:if test="${fyzt==1 }">selected="selected"</c:if>>未打印</option>
							<option value="1" <c:if test="${fyzt==2 }">selected="selected"</c:if>>暂停</option>
							<option value="2" <c:if test="${fyzt==3 }">selected="selected"</c:if>>已打印</option>
							<option value="3" <c:if test="${fyzt==4 }">selected="selected"</c:if>>信息不全</option>
							
					</select>
                   </td> --%>

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>
						</tr>
					</table>

				</form>
			</div>

			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<table class="emanagergettable">
					<c:forEach items="${cusList}" var="cus" varStatus="i">

						<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
							<td
								style="color: #9E0DE8; font-weight: bold; border: 1px solid #BBBABA;"
								width="100"><a
								href="http://117.144.21.74:33168/po/contract_approval.aspx?id=${cus.bargainNo }"
								target=_blank>${cus.bargainNo }</a></td>
							<td style="color: #0D28E8; border: 1px solid #BBBABA;">公司需要垫付吗？<c:choose>
									<c:when test="${cus.attribute==0 }">否</c:when>
									<c:when test="${cus.attribute==1 }">是</c:when>

								</c:choose></td>
							<td style="color: #D323D6; border: 1px solid #BBBABA;">项目启动时间:${cus.projectStartupTime }</td>
							<td style="border: 1px solid #BBBABA;">要求的交货期是:${cus.pODeliveryDate}
								<br /> 客户预付款到款时间:${cus.ifdate }
							</td>
							<td style="border: 1px solid #BBBABA;">客户一年总采购额估计:${cus.totalCustomerPurchases }</td>
							<td style="border: 1px solid #BBBABA;">客户对交期和工厂有要求吗:<c:choose>
									<c:when test="${cus.deliveryRequirement==0 }">否</c:when>
									<c:when test="${cus.deliveryRequirement==1 }">是</c:when>

								</c:choose></td>
							<td style="border: 1px solid #BBBABA; word-wrap: break-word:">当前毛利率:${cus.grossProfitMargin}</td>

							<td style="border: 1px solid #BBBABA; word-wrap: break-word:"><c:choose>
									<c:when test="${cus.dealWith==0 }">未打印</c:when>
									<c:when test="${cus.dealWith==1 }">暂停</c:when>
									<c:when test="${cus.dealWith==2 }">已打印</c:when>
									<c:when test="${cus.dealWith==3 }">信息不全</c:when>
								</c:choose></td>
							<td style="border: 1px solid #BBBABA;">大货毛利率:${cus.bigFreightRate }
							</td>


						</tr>
					</c:forEach>
				</table>

			</div>

			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>