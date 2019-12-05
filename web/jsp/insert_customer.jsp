<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>客户关联表</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<script type="text/javascript">
	
</script>
<body>
	<div class="cusalldiv">
		<div class="usechange">
			<h2>客户关联表</h2>
			<form
				action="/ERP-NBEmail/helpServlet?action=insertEnteryCustomer&className=AccountEntryFormServlet"
				method="post" onsubmit="return check();">
				<table class="usectable_re">
					<tr>
						<td class="usermatd1">用户名:</td>
						<td style="padding-right: 100px;">
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="customerName" name="customerName"
									size="40">

							</div>
						</td>
					</tr>
					<tr>
						<td class="usermatd1">项目号</td>
						<td>
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="CasNo" name="CasNo"
									runat="server" size="40">
							</div>
						</td>
					</tr>
					<tr>
						<td class="usermatd1">金蝶号</td>
						<td>
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="kingdee" name="kingdee"
									runat="server" size="40">
							</div>
						</td>
					</tr>
					<tr>
						<td class="usermatd1">金蝶上客户名</td>
						<td>
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="kingName" name="kingName"
									runat="server" size="40">
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