<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>最近36个月有下单 但 最近 24个月没下单的</title>
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
			<h2>最近36个月有下单 但 最近 24个月没下单的</h2>
			<form
				action="/ERP-NBEmail/helpServlet?action=Customer1&className=CustomerServlet"
				method="post">
				<table class="usectable">
					<tr>

						<td class="usermatd3">根据销售查询:</td>
						<td>
							<div class="userselediv_nor">
								<select name="condition1" id="audit" class="userselein">
									<option value="-1">全部</option>
									<option value="1"
										<c:if test="${fyfz==0 }">selected="selected"</c:if>>LynnYuan</option>
									<option value="2"
										<c:if test="${fyfz==1 }">selected="selected"</c:if>>Fionayan</option>
									<option value="3"
										<c:if test="${fyfz==2 }">selected="selected"</c:if>>Amyzhao</option>
									<option value="4"
										<c:if test="${fyfz==3 }">selected="selected"</c:if>>PaulWang</option>
									<option value="5"
										<c:if test="${fyfz==4 }">selected="selected"</c:if>>Joannahao</option>
									<option value="6"
										<c:if test="${fyfz==5 }">selected="selected"</c:if>>ChloeMa</option>
									<option value="7"
										<c:if test="${fyfz==6 }">selected="selected"</c:if>>ElaineSheng</option>
									<option value="8"
										<c:if test="${fyfz==7 }">selected="selected"</c:if>>IvyWu</option>
									<option value="9"
										<c:if test="${fyfz==8 }">selected="selected"</c:if>>JessieHan</option>
									<option value="10"
										<c:if test="${fyfz==9 }">selected="selected"</c:if>>KathyPan</option>
									<option value="11"
										<c:if test="${fyfz==10 }">selected="selected"</c:if>>SherryZhou</option>
									<option value="12"
										<c:if test="${fyfz==11 }">selected="selected"</c:if>>JuliaZeng</option>
									<option value="13"
										<c:if test="${fyfz==12 }">selected="selected"</c:if>>anna</option>
									<option value="14"
										<c:if test="${fyfz==13 }">selected="selected"</c:if>>tina</option>
								</select>
							</div>
						</td>
						<td class="usermatd3">客户状态:</td>
						<td>
							<div class="userselediv_nor">
								<select name="condition4" id="audit" class="userselein">
									<option value="-1">全部</option>
									<option value="0"
										<c:if test="${xsfp==0 }">selected="selected"</c:if>>已死</option>
									<option value="1"
										<c:if test="${xsfp==1 }">selected="selected"</c:if>>需跟进</option>
									<option value="2"
										<c:if test="${xsfp==1 }">selected="selected"</c:if>>没问题</option>
								</select>
							</div>
						</td>

						<td class="usermatd4"><input type="submit" value="过滤客户信息"
							class="usersearchbtn"></td>
					</tr>
				</table>

			</form>
			<form
				action="/ERP-NBEmail/helpServlet?action=cusList&className=CustomerServlet"
				method="post">
				<table class="usectable">
					<tr>
						<td class="usermatd4"><a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=LookCustomer&className=CustomerServlet" target="_blank">显示所有客户列表</a>
							<a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customer1&className=CustomerServlet" target="_blank">最近1个月下单的客户列表</a>
							<a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customer2&className=CustomerServlet" target="_blank">最近2个月下单的客户列表</a>
							<a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customera&className=CustomerServlet" target="_blank">最近
								4个月有下单的客户</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customerb&className=CustomerServlet" target="_blank">最近12个月有下单
								但 最近 4个月没下单的</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customerc&className=CustomerServlet" target="_blank">最近18个月有下单但最近
								12个月没下单的</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customerd&className=CustomerServlet" target="_blank">最近24个月有下单
								但 最近18个月没下单的</a> <a class="mana_inbtn"
							href="/ERP-NBEmail/helpServlet?action=Customerf&className=CustomerServlet" target="_blank">最近48个月有下单
								但 最近36个月没下单的</a></td>
					</tr>
				</table>


			</form>
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>客户ID</td>
					<td>客户名</td>
					<td>最近项目名</td>
					<td>总销售额</td>
					<td>销售</td>
					<td>跟单销售</td>
					<td>报价项目数</td>
					<td>跟单项目数</td>
					<td>是否有投诉</td>
					<td>客户的情况</td>
					<td>操作</td>




				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
						<td><a
							href="/ERP-NBEmail/helpServlet?action=getItemCase&className=ItCaseIdServlet&cid=${cus.cid }"
							target=_blank>${cus.cid }</a></td>
						<td>${cus.firstName }</td>
						<td>${cus.projectId }${cus.projectDesce } 下单时间:
							${cus.createTime != null ?fn:substring(cus.createTime,0,fn:indexOf(cus.createTime," ")):""}日
							${cus.createTime1 != null ?fn:substring(cus.createTime1,0,fn:indexOf(cus.createTime1," ")):""}日
							运单时间:${cus.deliveryTime != null ?fn:substring(cus.deliveryTime,0,fn:indexOf(cus.deliveryTime," ")):""}日</td>
						<td>${cus.saleName1 }</td>
						<td>${cus.saleName2 }</td>
						<td>${cus.quotationitem }</td>
						<td>${cus.documentaryproject }</td>
						<td><c:if test="${cus.baobiao !=0 }">
								<a
									href="http://192.168.1.2:8080/shipping/ProjectInfoServlet?id=${cus.baobiao } "
									target=_blank>${cus.baobiao }</a>
							</c:if></td>
						<td>${cus.cstatus}${cus.work} ${cus.kehudaxiao} ${cus.note}</td>
						<td style="text-align: center;"><input class="mana_inbtn"
							type="button" value="解释" onclick="explain(${cus.cid } );" /> <input
							class="mana_inbtn" type="button" value="已死"
							onclick="cstatus1(${cus.cid });" /> <input class="mana_inbtn"
							type="button" value="需跟进" onclick="cstatus2(${cus.cid });" /> <input
							class="mana_inbtn" type="button" value="没问题"
							onclick="cstatus3(${cus.cid });" /></td>

					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>