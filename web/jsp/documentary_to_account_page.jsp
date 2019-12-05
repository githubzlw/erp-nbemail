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
	width: 1000px;
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

<title>跟单到账列表页</title>
</head>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;"
				align="left" id="ykhxx">
				<h2>跟单到账列表页</h2>
			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=documentaryToAccountPage&className=InvoiceServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="vs" value="${saleName }" />
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
								
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn">
								</td>
						</tr>
						<span>可根据跟单、起始时间查询</span>
					</table>

				</form>
			</div>
			<div></div>
           
               <table class="emanagergettable">
				<tr class="emanagergettr">
                    <td>项目号</td>
					<td>跟单名</td>
					<td>项目总利润</td>
					<td>查询利润</td>
					
					</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
					<td>${cus.projectId }</td>
					<td>${cus.merchandmanager1 }</td>
					<td>${cus.grossProfit }</td>
					<td>${cus.queryTimeProfit }</td>
					
					
					</tr>
				</c:forEach>
			</table>



			


		</div>

	</div>
</body>
</html>