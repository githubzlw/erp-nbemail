<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>对客户解释</title>
</head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript">
	
	function check(){
		
		var uname = document.getElementById("uname").value;
	
		var ybc = document.getElementById("ybc").value;
		
		
		if(uname == null || uname.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			alert("潜力不能为空");
			return false;
		}
		
		if(ybc == null || ybc.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			alert("说明不能为空");
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
		<%-- <jsp:include page="people_manager.jsp"></jsp:include> --%>

		<div class="usechange">
			<h2>新增人员</h2>


			<form
				action="/ERP-NBEmail/helpServlet?action=explain1&className=CustomerServlet"
				method="post" onsubmit="return check();">
				<input type="hidden" name="cid" value="${cid}" />
				<table class="usectable_re">

					<tr>
						<td class="usermatd1">客户职位:</td>

						<td colspan="1">

							<div class="userselediv_nor">
								<select id="r" name="role" onchange="change();"
									class="userselein">
									<option value="1">发明家</option>
									<option value="2">公司采购</option>
									<option value="3">其他</option>


								</select>
							</div>

						</td>
					<tr>
						<td class="usermatd1">潜力:</td>
						<td style="padding-right: 100px;">
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="uname"
									name="potential" value="${ info.kehudaxiao}" size="40">

							</div>
						</td>
					</tr>
					<tr>


						<!-- <td colspan="3"><div style="height:30px;"> <span id="eee1"></span> </div></td> -->
						<td class="usermatd1">说明:</td>
						<td>
							<div class="userselediv_nor">
								<textarea cols="80" rows="10" id="ybc" name="explain">${ info.note }</textarea>

							</div>
						</td>

					</tr>





					<tr>
						<td colspan="4" class="usermatd_btn" align="center"><input
							class="emanagerrmaconfnta" type="submit" value="提交"></br></td>
					</tr>
				</table>
			</form>
		</div>
	</div>


</body>
</html>