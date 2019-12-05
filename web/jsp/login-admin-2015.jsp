<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>管理员系统登录</title>
</head>

<link type="text/css" rel="stylesheet"
	href="/ERP-NBEmail/css/loginbase.css" />


<script type="text/javascript">
function check(){
		var userName = document.getElementById("un").value;
		var pwd = document.getElementById("mm").value;
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
<%
String EmpEName = null;
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

<body>
	<div id="register" class="eloginbg">
		<div class="eloginbgtop">上海凯融科技 审批申请列表管理系统</div>
		<div class="eloginmain">
			<div class="eloginmcon">
				<img src="/ERP-NBEmail/img/login/loginm_05.jpg" />
				<form id="loginForm" action="/ERP-NBEmail/helpServlet?action=Adminlogin&className=EmployeeServlet" method="post"
					onsubmit="return check();">
					<table class="eloginmc_t">
						<tr>
							<td class="eloginmc_td1">用户名：</td>
							<td class="eloginmc_td2"><input type="text"
								class="eloginmci" id="un" name="EmpEName" value="${EmpEName}"
								size="30"><br />
							<font id="ts1" color="red"></font></td>
						</tr>
						<tr>
							<td class="eloginmc_td1">密码：</td>
							<td class="eloginmc_td2"><input type="password"
								class="eloginmci" id="mm" name="EmpPWD" value="${EmpPWD}"
								size="30"><br />
							<font id="ts2" color="red"></font></td>
						</tr>
						<tr>
							<td style="text-align: center;" colspan="2" class="eloginmc_td1"><label
								class="ui-green"><input type="submit" name="submit"
									value="立即登录" class="eloginmcbtn logdengbtn" /></label> <br /></td>

						</tr>

					</table>
					<%--<input type="hidden" name="action" value="Adminlogin"> <input
						type="hidden" name="className" value="EmployeeServlet">--%>
				</form>
			</div>
		</div>
	</div>
</body>
</html>