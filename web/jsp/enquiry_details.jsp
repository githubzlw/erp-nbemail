<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>当月详情表</title>
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
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
		src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var updateCreateTime=function(id,createTime){
     var createTime=document.getElementById(createTime).value;
      var params = {
			"id":id,
			"createTime":createTime,
			"action":"updateCreateTime",
			"className":"FactoryReconciliationServlet",
		};
		$.ajax({
					url:'/ERP-NBEmail/helpServlet',
					type:"post",
					data:params,
					success:function(data)
					{
						if(data == "YES"){

							window.location.reload();
						}else{

							alert("输入日期格式错误");
						}
					},

				}
		);
	};






</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>${kingdee }当月详情表</h2>
			

			<table class="emanagergettable">
				<tr class="emanagergettr">
					<td>金蝶号</td>
					<td>工厂Id</td>
					<td>工厂名</td>
					<td>项目号</td>
					<td>付给工厂款</td>
					<td>收回发票</td>
					<td>时间</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${list}" var="cus" varStatus="i">
					<tr <c:if test="${i.count%2==0}">bgcolor="#cff7c9"</c:if>>
						<td>${cus.kingdee }</td>
						<td>${cus.factoryId }</td>
						<td>${cus.factoryName }</td>
						<td>${cus.caseNo }</td>
						<td>${cus.amountCredit }</td>
						<td>${cus.currentDebitAmount }</td>
						<td><c:if test="${cus.currentDebitAmount==0 }">${cus.createTime }</c:if>
							<c:if test="${cus.currentDebitAmount>0 }"><input type="text"  class="Wdate" id="createTime${i.count}"
																			 name="createTime${i.count}" value="${cus.createTime }"
																			 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></c:if>
						</td>
						<td><c:if test="${cus.currentDebitAmount>0 }"><input type="button" value="修改收发票时间" onclick="updateCreateTime(${cus.id},'createTime${i.count}');"></c:if></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>