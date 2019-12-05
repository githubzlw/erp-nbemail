<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="<%=basePath%>">
<title>客户信息录入</title>
</head>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>

<style type="text/css">
.container {
	width: 450px;
	border: 1px solid #6C9C2C;
	height: 25px;
}

#bar {
	background: #95CA0D;
	float: left;
	height: 100%;
	text-align: center;
	line-height: 150%;
}
</style>

<script type="text/javascript">
//回复

	
	
	
	
	
	
	
</script>


<body>
	<div class="cusalldiv">
		<jsp:include page="people_manager.jsp"></jsp:include>
		<div class="usechange" id="usechange">
			<h2>新增客户信息录入</h2>
			<form
				action="/ERP-NBEmail/helpServlet?action=CreateCustomer&className=CustomerServlet"
				method="post" id="uploadForm">
				<input type="hidden" name="recoder" value="${user.userName }">
				<table>
					<tr>
						<td class="usermatd1">最初联系人:</td>
						<td><input type="text" name="firstName" id="firstName"
							size="30" class="custdin" onblur="getVal(this.id)" /> <font
							class="cusmessa" id="ts0">(必填选项)</font></td>
					</tr>

					<tr>
						<td class="usermatd1">国家:</td>
						<td>
							<div class="userselediv_nora">
								<select name="country" id="country" class="userseleina">
									<option value="1">USA</option>
									<option value="2">Canada</option>
									<option value="3">France</option>
									<option value="4">Germany</option>
									<option value="5">Netherlands</option>
									<option value="6">Israel</option>
									<option value="7">Mexico</option>
									<option value="8">Australia</option>
									<option value="9">Italy</option>
									<option value="10">Switzerland</option>
									<option value="11">Finland</option>
									<option value="12">Sweden</option>
									<option value="13">UK</option>
									<option value="14">Argentina</option>
									<option value="15">Other</option>
								</select>
							</div>
						</td>
					</tr>

					<tr>
						<td class="usermatd1">公司大小:</td>
						<td>
							<div class="userselediv_nora">
								<select name="ddlDgree" id="ddlDgree" class="userseleina">
									<option value="小公司">小公司</option>
									<option value="有几十个人的中型公司">有几十个人的中型公司</option>
									<option value="有很多分支机构的大中型公司">有很多分支机构的大中型公司</option>
									<option value="上市公司">上市公司</option>

								</select>
							</div>
						</td>
					</tr>


					<tr>
						<td colspan="2" align="center"><input type="submit"
							id="Button1" name="Button1" value="添加客户"
							class="emanagerrmaconfnta" /></td>
					</tr>
				</table>
			</form>




		</div>
	</div>
</body>
</html>