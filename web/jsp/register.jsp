<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>用户注册</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<script type="text/javascript">
	
	function check(){
		
		var uname = document.getElementById("uname").value;
	
		var mm = document.getElementById("mm").value;
		
		if(uname == null || uname.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			alert("用户名不能为空");
			return false;
		}
		
		if(mm == null || mm.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			alert("密码不能为空");
			return false;
		}
		
	
	  
		
		
		
		
		return true;
	}
	window.onload=function(){
		document.getElementById('pelist').style.background='url(/ERP-NBEmail/img/emana/elion.png) 0 0 no-repeat';
		document.getElementById('pelist').getElementsByTagName('a')[0].style.color="#fff";
		document.getElementById('eleft').style.height=(document.body.scrollHeight-68)+'px';
	}
</script>
<body>
	<div class="cusalldiv">
		<div class="usechange">
			<h2>新增人员</h2>
			<form
				action="/ERP-NBEmail/helpServlet?action=registererp&className=EmployeeServlet"
				method="post" onsubmit="return check();">
				<table class="usectable_re">
					<tr>
						<td class="usermatd1">角色赋予:</td>
						<td colspan="1">
							<div class="userselediv_nor">
								<select id="r" name="role" onchange="change();"
									class="userselein">
									<option value="1">销售</option>
									<option value="2">跟单销售</option>
									<option value="3">质检</option>
									<option value="4">采购</option>
									<option value="5">报价员</option>
									<option value="6">技术人员</option>

								</select>
							</div>
						</td>
					<tr>
						<td class="usermatd1">用户名:</td>
						<td style="padding-right: 100px;">
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="uname" name="EmpEName"
									size="40">

							</div>
						</td>
					</tr>
					<tr>
					<tr style="vertical-align: top;">
						<td>&nbsp;</td>
						<td><font class="cusmessa">(用户名要求2~12位数字和字母组成)</font></td>
					</tr>
					<tr>
						<td class="usermatd1">登录密码:</td>
						<td>
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="mm" name="EmpPWD"
									runat="server" size="40">
							</div>
						</td>
					</tr>
					<tr style="vertical-align: top;">
						<td>&nbsp;</td>
						<td><font class="cusmessa">(密码长度必须是6~20位)</font></td>
					</tr>
					<tr>
						<td class="usermatd1">邮箱:</td>
						<td>
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="email" name="email"
									runat="server" size="40">
							</div>
						</td>

					</tr>
					<tr>
						<td class="usermatd1">邮箱密码:</td>
						<td>
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="emailpwd"
									name="emailpwd" runat="server" size="40">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="usermatd_btn"><input
							class="emanagerrmaconfnta" type="submit" value="提交"></br></td>
					</tr>
				</table>
			</form>
		</div>
	</div>


</body>
</html>