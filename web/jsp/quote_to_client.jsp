<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>对外报价上传</title>
</head>
<style>
.userselediv_nora {
	display: inline-block;
	width: 129px;
	height: 23px;
	border: 1px solid #cbd1df;
	position: relative;
}

.userseleina {
	width: 128px;
	height: 21px;
	line-height: 19px;
	border: 0 none;
	padding: 3px;
	margin: 1px 0 0 0px;
	background: transparent;
}
</style>
<script type="text/javascript">
var i = 0;
var j = 0;
function fnAddFile(){
	$("#fileupload").after("<tr id='"+j+"'><td></td><td><input type='file'  name='emailfile"+i+"'><input type='button' value='删除' class='emanagerrmaconfnt ereinleft' onclick='delTR("+j+")'></td></tr>");
	i++;j++;
	document.getElementById('eleft').style.height=(parseInt(document.getElementById('usechange').offsetHeight)+20)+'px';
}
function delTR(j) {
   $("tr[id="+j+"]").remove();
   i--;
   document.getElementById('eleft').style.height=(parseInt(document.getElementById('usechange').offsetHeight)-20)+'px';
} 
	function show(){
	   document.getElementById("isok").removeAttribute('disabled');
	}
	
	
	function clearMethod1() 
	{ 
		document.getElementById('fileupload2').value = ""	

	}
	
	function createXMLHttpRequest() {  
	    try {  
	        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");//IE高版本创建XMLHTTP  
	    }  
	    catch(E) {  
	        try {  
	            XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");//IE低版本创建XMLHTTP  
	        }  
	        catch(E) {  
	            XMLHttpReq = new XMLHttpRequest();//兼容非IE浏览器，直接创建XMLHTTP对象  
	        }  
	    }  
	}
	function history() {
		window.close();
	}
	
</script>
<body>
	<div class="usechange" id="usechange">
		<h2 style="text-align: center;">Quote to Client</h2>
		<form
			action="/ERP-NBEmail/helpServlet?action=quoteClient&className=QuoteClientServlet"
			method="post" enctype="multipart/form-data"
			onsubmit="return check();">
			<table align="center">
				<tr>
					<td class="usermatd1">Client：</td>
					<td><input type="text" id="client" name="client" size="30"
						class="custdin" /> <font id="ts0" class="cusmessa"></font></td>
				</tr>
				<tr id="fileupload">
					<td class="usermatd1"><h3>Attachment：</h3></td>
					<td style="padding-top: 20px;"><input type="file"
						id="fileupload2" name="fileupload" onclick="find();"></input>
						<div id="r"
							style="display: none; margin-top: -65px; margin-left: 60px;">
							<input type="button" onclick="clearMethod1();" id="fileupload1"
								name="fileupload1" value="删除" class="emanagerrmaconfnt"></input>
							<input id="isok" style="margin-top: 10px; margin-left: 60px;"
								type="button" name="fileupload" onclick="fnAddFile();"
								value="添加多个附件" class="emanagerrmaconfnt"></input>
						</div></td>
				</tr>
				<tr>
					<td class="usermatd1">Remarks：</td>
					<td><input type="text" id="remark" name="remark" size="30"
						class="custdin" /> <font id="ts1" class="cusmessa"></font></td>
				</tr>
				<tr>
					<td class="usermatd1">Margin：</td>
					<td><input type="text" id="margin" name="margin" size="30"
						class="custdin" /> <font id="ts2" class="cusmessa">%(
							number only )</font></td>

				</tr>

				<tr>
					<td class="usermatd1">Estimated Annual Order：</td>
					<td><input type="text" id="money" name="money" size="30"
						class="custdin" /> <font id="ts3" class="cusmessa">(USD)</font></td>
				</tr>


				<tr>
					<td class="usermatd1">Value Of This Order:</td>
					<td><input type="text" name="money1" id="money1" size="30"
						class="custdin" /> <font id="ts4" class="cusmessa">(USD)</font></td>
				</tr>
				<tr>
					<td class="usermatd1">Not Rated:</td>
					<td>
						<div class="userselediv_nora">
							<select name="grade" id="grade" class="userseleina">
								<option value="1">A+</option>
								<option value="2">A</option>
								<option value="3">B</option>
								<option value="4">C</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						id="loading" value="Submit" />
				</tr>
			</table>
		</form>


	</div>

</body>
</html>