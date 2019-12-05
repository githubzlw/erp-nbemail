<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询客户质检报告项目</title>
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
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
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
			<h2>查询客户质检报告项目</h2>
			<form
				action="/ERP-NBEmail/helpServlet?action=checkQualityReport&className=ItCaseIdServlet"
				method="post">
				<table class="usectable">
					<tr>

						<td class="usermatd3">根据条件查询:</td>

						<td>
							<div class="userselediv_nor">
								<select name="condition" id="condition" class="userselein">
									<option value="0"
										<c:if test="${fyzt==0 }">selected="selected"</c:if>>全部</option>
									<option value="1"
										<c:if test="${fyzt==1 }">selected="selected"</c:if>>未上传检验计划项目</option>
									<option value="2"
										<c:if test="${fyzt==2 }">selected="selected"</c:if>>未上传检验报告项目</option>
								</select>
							</div>
						</td>
						<td>
							<div class="userselediv_nor">
								<select name="condition1" id="condition1" class="userselein">
									<option value="0"
										<c:if test="${fyzt1==0 }">selected="selected"</c:if>>全部</option>
									<option value="1"
										<c:if test="${fyzt1==1 }">selected="selected"</c:if>>未上传图纸</option>

								</select>
							</div>
						</td>
						<td><input type="text" readonly class="Wdate" id="time1"
							name="time1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>

						<td class="usermatd4"><input type="submit" value="查询"
							class="usersearchbtn"></td>
					</tr>
				</table>

			</form>


			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>项目创建时间</td>
					<td>质检1</td>
					<td>质检2</td>
					<td>上传质检计划</td>
					<td>上传质检报告</td>
					<td>中文图纸</td>
					<td>英文图纸</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td><a
							href="http://117.144.21.74:33168/po/CaseInPo.aspx?id=${cus.caseNo }"
							target=_blank>${cus.caseNo }</a></td>
						<td>${cus.createTime != null ?fn:substring(cus.createTime,0,fn:indexOf(cus.createTime," ")):""}
						</td>
						<td>${cus.zhijian1 }</td>
						<td>${cus.zhijian2 }</td>
						<td><c:forEach items="${cus.accessories}" var="cus1"
								varStatus="i1">
								<c:if test="${cus1.status==0}">
									<a
										href="http://117.144.21.74:33168/upload/po/upload/${cus1.type }/${cus1.url }">${cus1.url }</a>
									<br /> ${cus1.intro } <br /> ${cus1.uploadtime }<br />
								</c:if>
							</c:forEach></td>
						<td><c:forEach items="${cus.accessories}" var="cus1"
								varStatus="i1">
								<c:if test="${cus1.status==1}">
									<a
										href="http://117.144.21.74:33168/upload/po/upload/${cus1.type }/${cus1.url }">${cus1.url }</a>
									<br /> ${cus1.intro } <br /> ${cus1.uploadtime }<br />
								</c:if>
							</c:forEach></td>
						<td><c:forEach items="${cus.tuzhi}" var="cus1" varStatus="i1">

								<a
									href="http://117.144.21.74:33168/upload/zhongwentuzhi/${cus1.cname }">${cus1.cname }</a>
								<br />${cus1.uploadtime }<br />

							</c:forEach></td>
						<td><c:forEach items="${cus.tuzhi}" var="cus1" varStatus="i1">

								<a
									href="http://117.144.21.74:33168/upload/yingwentuzhi/${cus1.ename }">${cus1.ename }</a>
								<br /> ${cus1.uploadTime1 }<br />

							</c:forEach></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>