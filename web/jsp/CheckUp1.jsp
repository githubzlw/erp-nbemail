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

/* margin: auto; */
</style>
<script type="text/javascript">



</script>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<title>付款审批页面</title>
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
					action="/ERP-NBEmail/helpServlet?action=CheckUp1&className=CheckUpServlet"
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
										<option value="1">项目编号</option>
										<option value="2">项目描述</option>
										<option value="3">申请资金</option>
										<option value="4">支付工厂</option>
									</select>
								</div>
							</td>
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


							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> <font id="ts0" class="cusmessa">(查询条件可以是项目编号、项目描述、申请资金或支付工厂。)共${total}有条
									未通过审批总金额${total1} </font> <a class="mana_inbtn"
								href="/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet">显示通过项目</a>
							</td>
						</tr>
					</table>

				</form>
			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<table class="emanagergettable">
					<c:forEach items="${cusList}" var="cus" varStatus="i">
						<c:if test="${cus.state==3 }">
							<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
								<td
									style="color: #9E0DE8; font-weight: bold; border: 1px solid #BBBABA;">${cus.caseNo }</td>
								<td style="color: #0D28E8; border: 1px solid #BBBABA;"><br>${cus.productDescE}&nbsp;
									${cus.productDescC}&nbsp;${cus.customerManager}<br>${cus.merchandManager1}<br></td>
								<td style="color: #D323D6; border: 1px solid #BBBABA;">${cus.paystate }</td>
								<td style="border: 1px solid #BBBABA;">申请人:${cus.itemManager}
									申请日期:${cus.stateDate != null ?fn:substring(cus.stateDate,0,fn:indexOf(cus.stateDate,":")):cus.stateDate}<br />
									支付对象: <c:choose>
										<c:when test="${cus.checktype==1 }">支付工厂</c:when>
										<c:when test="${cus.checktype==2 }">支付物流</c:when>
										<c:when test="${cus.checktype==3 }">支付其他</c:when>
									</c:choose> ${cus.reason }
								</td>
								<td style="border: 1px solid #BBBABA;">${cus.facMoney }</td>
								<td style="border: 1px solid #BBBABA;">${cus.mtype }</td>
								<td style="border: 1px solid #BBBABA;">${cus.facReason}</td>

								<td style="border: 1px solid #BBBABA;">${cus.state1 }</td>
								<td style="border: 1px solid; text-align: center;"><a
									href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.id}&name=${cus.caseNo} "
									target=_blank>查 看</a></td>

							</tr>
						</c:if>

					</c:forEach>
				</table>

			</div>


		</div>

	</div>
</body>
</html>