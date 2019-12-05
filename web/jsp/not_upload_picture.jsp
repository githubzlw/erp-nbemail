<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>15未上传图纸或30天未上传质检报告项目</title>
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

window.onload=function(){
	document.getElementById('client').style.background='url(/ERP-NBEmail/img/emana/elion.png) 0 0 no-repeat';
	document.getElementById('client').getElementsByTagName('a')[0].style.color="#fff";
	document.getElementById('eleft').style.height=(document.body.scrollHeight-68)+'px';
}	
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>15未上传图纸或30天未上传质检报告项目</h2>



			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>销售名</td>
					<td>质检名</td>
					<td>跟单时间</td>
				</tr>
				<c:forEach items="${list}" var="cus" varStatus="i">
					<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
						<td>${cus.caseNo }</td>
						<td>${cus.customerManager}${cus.merchandManager1}
							${cus.merchandManager2}</td>
						<td>${cus.zhijian }${cus.zhijian1 }</td>
						<td>${cus.iidate != null ?fn:substring(cus.iidate,0,fn:indexOf(cus.iidate," ")):""}</td>


					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>

		</div>
	</div>

</body>
</html>