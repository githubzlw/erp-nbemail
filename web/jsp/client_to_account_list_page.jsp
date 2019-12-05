<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 1500px;
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
<script>

</script>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>

<title>客户列表页</title>
</head>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;"
				align="left" id="ykhxx">
				<h2>客户列表页</h2>
			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=clientToAccountListPage&className=InvoiceServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="vs" value="${ name}" />
                                </div>
							</td>
							
							<td class="usermatd3">第一起始时间:</td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                             <td class="usermatd3">截止时间:</td>
							<td><input type="text" readonly class="Wdate" id="time2"
								name="time2" value="${endtime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								&nbsp;|&nbsp;
								<td class="usermatd3">第二起始时间:</td>
							<td><input type="text" readonly class="Wdate" id="time3"
								name="time3" value="${starttime1 }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									<td class="usermatd3">截止时间:</td>
							<td><input type="text" readonly class="Wdate" id="time4"
								name="time4" value="${endtime1 }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn">
								</td>
						</tr>
						<span>可根据客户id,客户名查询，起始时间、截止日期必填</span>
					</table>

				</form>
			</div>
			<div></div>
           
               <table class="emanagergettable">
				<tr class="emanagergettr">

					<td>客户id</td>
					<td>客户名</td>
					<td>客户国家</td>
					<td>项目号</td>
					<td>第一时段进账</td>
					<td>第二时段进账</td>
					<td>减少进账</td>
					<td>销售/跟单</td>
					
					
					
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
					<td>${cus.eid }</td>
					<td>${cus.name }</td>
						<td><c:choose>
							<c:when test="${cus.countryid==1 }">USA</c:when>
							<c:when test="${cus.countryid==2 }">Canada</c:when>
							<c:when test="${cus.countryid==3 }">France</c:when>
							<c:when test="${cus.countryid==4 }">Germany</c:when>
							<c:when test="${cus.countryid==5 }">Netherlands</c:when>
							<c:when test="${cus.countryid==6 }">Israel</c:when>
							<c:when test="${cus.countryid==7 }">Mexico</c:when>
							<c:when test="${cus.countryid==8 }">Australia</c:when>
							<c:when test="${cus.countryid==9 }">Italy</c:when>
							<c:when test="${cus.countryid==10 }">Switzerland</c:when>
							<c:when test="${cus.countryid==11 }">Finland</c:when>
							<c:when test="${cus.countryid==12 }">Sweden</c:when>
							<c:when test="${cus.countryid==13 }">UK</c:when>
							<c:when test="${cus.countryid==14 }">Argentina</c:when>
							<c:when test="${cus.countryid==15 }">Other</c:when>
							<c:when test="${cus.countryid==16 }">Japan</c:when>
							<c:when test="${cus.countryid==17 }">China</c:when>
							<c:when test="${cus.countryid==18 }">Austria</c:when>
							<c:when test="${cus.countryid==19 }">Saudi Arabia</c:when>
							<c:when test="${cus.countryid==20 }">Belgium</c:when>
							<c:when test="${cus.countryid==21 }">Spain</c:when>
							<c:when test="${cus.countryid==22 }">New Zealand</c:when>
							<c:when test="${cus.countryid==23 }">Slovenia</c:when>
							<c:when test="${cus.countryid==24 }">Serbia</c:when>
							<c:when test="${cus.countryid==25 }">Ireland</c:when>

						</c:choose></td>
					<td>${cus.projectId }</td>
					<td>${cus.firstEntry }<c:choose>
						<c:when test="${cus.currencyUnit1==1 }">美元</c:when>
						<c:when test="${cus.currencyUnit1==2 }">人民币</c:when>
						<c:when test="${cus.currencyUnit1==3 }">欧元</c:when>
						<c:when test="${cus.currencyUnit1==5 }">英镑</c:when></c:choose></td>
					<td>${cus.secondEntry }<c:choose>
						<c:when test="${cus.currencyUnit2==1 }">美元</c:when>
						<c:when test="${cus.currencyUnit2==2 }">人民币</c:when>
						<c:when test="${cus.currencyUnit2==3 }">欧元</c:when>
						<c:when test="${cus.currencyUnit2==5 }">英镑</c:when></c:choose></td>
					<td><c:if test="${cus.secondEntry-cus.firstEntry <0  }" ><span style="color:red;">${cus.secondEntry-cus.firstEntry }</span></c:if><c:if test="${cus.secondEntry-cus.firstEntry >0 }" ><span>${cus.secondEntry-cus.firstEntry }</span></c:if></td>
					<td>${cus.customerManager} ${cus.merchandmanager1 }</td>
					</tr>
				</c:forEach>
			</table>



			


		</div>

	</div>
</body>
</html>