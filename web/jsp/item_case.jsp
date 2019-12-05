<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询客户项目</title>
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

/* margin: auto; */
</style>
<script type="text/javascript">
function explain(cid){
	
	newWindow = window.open("/ERP-NBEmail/helpServlet?action=explain&className=CustomerServlet&cid="+cid+"","windows","height=400,width=600,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");

  
}
var url=5;
function cstatus1(cid){
	var num=1;
	
	
	window.location.href = "/ERP-NBEmail/helpServlet?action=cstatus&className=CustomerServlet&id="+cid+"&num="+num+"&url="+url+"";
	
	  
}
function cstatus2(cid){
	var num=2;
	window.location.href = "/ERP-NBEmail/helpServlet?action=cstatus&className=CustomerServlet&id="+cid+"&num="+num+"&url="+url+"";
	
	  
}
function cstatus3(cid){
	var num=3;
	window.location.href = "/ERP-NBEmail/helpServlet?action=cstatus&className=CustomerServlet&id="+cid+"&num="+num+"&url="+url+"";
	
	  
}




window.onload=function(){
	document.getElementById('client').style.background='url(/ERP-NBEmail/img/emana/elion.png) 0 0 no-repeat';
	document.getElementById('client').getElementsByTagName('a')[0].style.color="#fff";
	document.getElementById('eleft').style.height=(document.body.scrollHeight-68)+'px';
}	
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>查询客户全部项目</h2>

			<form
				action="/ERP-NBEmail/helpServlet?action=cusList&className=CustomerServlet"
				method="post">
				<table class="usectable">
					<tr>
						<td class="usermatd4"><a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=LookCustomer&className=CustomerServlet">显示所有客户列表</a>
							<a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customer1&className=CustomerServlet">最近1个月下单的客户列表</a>
							<a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customer2&className=CustomerServlet">最近2个月下单的客户列表</a>
							<a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customera&className=CustomerServlet">最近
								4个月有下单的客户</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customerb&className=CustomerServlet">最近12个月有下单
								但 最近 4个月没下单的</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customerc&className=CustomerServlet">最近18个月有下单但最近
								12个月没下单的</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customerd&className=CustomerServlet">最近24个月有下单
								但 最近18个月没下单的</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customere&className=CustomerServlet">最近36个月有下单
								但 最近24个月没下单的</a></td>
					</tr>
				</table>


			</form>
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>客户ID</td>
					<td>项目号</td>
					<td>项目中文名</td>
					<td>项目英文名</td>
					<td>销售</td>
					<td>跟单销售</td>
					<td>创建时间</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
						<td>${cus.cid }</td>
						<td>${cus.caseNo }</td>
						<td>${cus.projectDescc }</td>
						<td>${cus.projectDesce }</td>
						<td>${cus.saleName1 }</td>
						<td>${cus.saleName2 }</td>
						<td>${cus.createTime }</td>


					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>