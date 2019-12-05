<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>人员管理界面</title>
</head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<style type="text/css">
.right_out {
	position: absolute;
	top: 25px;
	left: 60%;
	z-index: 2;
}

.right_out img {
	vertical-align: middle;
}

.right_out a {
	color: #fff;
}
</style>
<link type="text/css" rel="stylesheet" href="/ERP-NBEmail/css/emana.css" />
<body>
	<div class="emanadiv">
		<div class="emanager_left">
			<img src="/ERP-NBEmail/img/user/userhead.jpg" />
			<div class="emananav" id="eleft">
				<div class="emaleftname">
					<span class="emaleftname1"> 登录用户: </span> <span
						class="emaleftname2"> ${user.userName}&nbsp; </span>
					<div class="clear">&nbsp;</div>
				</div>
				<ul>


					<li class="emananavline_out">
						<div class="emananavlindiv_out" id="pelist">
							<img src="/ERP-NBEmail/img/user/userall.png" /> <a
								class="emananavlite" href="/ERP-NBEmail/jsp/create_customer.jsp">创建新客户</a>
						</div>
					</li>

					<li class="emananavline_out">
						<div class="emananavlindiv_out" id="unpro">
							<img src="/ERP-NBEmail/img/emana/repe.png" /> <a
								class="emananavlite" href="/ERP-NBEmail/jsp/create_project.jsp">创建新项目</a>
						</div>
					</li>



				</ul>
			</div>
			<script type="text/javascript">
	        document.getElementById('eleft').style.height=document.body.scrollHeight>document.body.clientHeight?(document.body.scrollHeight-68)+'px':(document.body.clientHeight-68)+'px';
	        </script>
		</div>
		<div class="emanager_right" style="position: relative">
			<img style="display: block;" src="/ERP-NBEmail/img/login/logtop.jpg" />
			<div class="right_out"></div>
		</div>
		<div class="clear"></div>
	</div>

</body>
</html>