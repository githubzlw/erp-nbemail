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
						<td class="usermatd1">国家</td>
						<td>
							<select name="country" id="country" class="userselein">
								<option value="0">-请选择-</option>
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
								<option value="16">Japan</option>
								<option value="17">China</option>
								<option value="18">Austria</option>
								<option value="19">Saudi Arabia</option>
								<option value="20">Belgium</option>
								<option value="21">Spain</option>
								<option value="22">New Zealand</option>
								<option value="23">Slovenia</option>
								<option value="24">Serbia</option>
								<option value="25">Ireland</option>
							</select></td>
					</tr>
					<tr>
						<td class="usermatd1">简称</td>
						<td>
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="abbreviation" name="abbreviation"
									   runat="server" size="40">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="usermatd_btn"><input
							class="emanagerrmaconfnta" type="submit" value="提交"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>


</body>
</html>