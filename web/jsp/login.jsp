<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>

<title>系统登录</title>
</head>

<%
String username_ = null;
Cookie c[]=request.getCookies();
if(c!=null)
{
for(int x=0;x<c.length;x++)
{
if(c[x].getName().equals("SESSION_LOGIN_USERNAME"))
{
//在cookie值保存时如果编码了，取cookie时就需要进行解码。
//将cookie值取出来后，赋值给变量，用以之后的显示
	request.setAttribute("EmpEName",c[x].getValue()); 
}
else if(c[x].getName().equals("SESSION_LOGIN_PASSWORD"))
{
//将cookie值取出来后，赋值给request，用以之后的显示
request.setAttribute("EmpPWD",c[x].getValue());
}
} 
}
%>


<link type="text/css" rel="stylesheet"
	href="/ERP-NBEmail/css/loginbase.css" />


<script type="text/javascript">




	function check(){
		var userName = document.getElementById("EmpEName").value;
		var pwd = document.getElementById("EmpPWD").value;
		if(userName==null || userName.length==0){
			document.getElementById("ts1").innerHTML="请输入用户名!";
			return false;
		}
		if(pwd==null || pwd.length==0){
			document.getElementById("ts2").innerHTML="请输入密码!";
			return false;
		}
	}
</script>

<body>
	<div id="register" class="eloginbg">
		<div class="eloginbgtop">上海凯融科技 客户管理系统</div>
		<div class="eloginmain">
			<div class="eloginmcon">
				<img src="/ERP-NBEmail/img/login/loginm_04.jpg" />
				<form id="loginForm" action="/ERP-NBEmail/helpServlet?action=login&className=EmployeeServlet" method="post"
					onsubmit="return check();">

					<table class="eloginmc_t">
						<tr>
							<td class="eloginmc_td1">用户名：</td>
							<td class="eloginmc_td2"><input type="text"
								class="eloginmci" id="EmpEName" name="EmpEName"
								value="${EmpEName }" size="30"><br /></td>
						</tr>
						<tr>
							<td class="eloginmc_td1">密码：</td>
							<td class="eloginmc_td2"><input type="password"
								class="eloginmci" id="EmpPWD" name="EmpPWD" value="${EmpPWD }"
								size="30"><br /></td>
						</tr>
						<tr>
							<td style="text-align: center;" colspan="2" class="eloginmc_td1"><label
								class="ui-green"><input type="submit" id="LoginBtn"
									name="LoginBtn" value="立即登录" class="eloginmcbtn logdengbtn" /></label>
								<br /></td>

						</tr>

					</table>
					<%--<input type="hidden" name="action" value="login"> <input
						type="hidden" name="IP" value=""> <input type="hidden"
						name="className" value="EmployeeServlet">--%>
				</form>
			</div>
		</div>
	</div>
</body>
</html>