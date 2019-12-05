<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>客户信息录入</title>
</head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="./js/progressBar.js"></script>
<link type="text/css" rel="stylesheet" href="./css/progressBar.css">
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

    function reply(){
    var target = document.getElementById("d");
	
	
	if (target.style.display=="none"){
		target.style.display="block";
		
		
		
	}  
    
    }   
</script>

<script type="text/javascript">
function find(){
	var target = document.getElementById("r");
	
	if (target.style.display=="block"){
		target.style.display="none";
		
	} else {
		target.style.display="block";	
	}
}
</script>
<script type="text/javascript">
//回复



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
	

	
	
	
	
	
	
	function check(){
		var cid = document.getElementById("cid").value;
		var content = document.getElementById("content").value;
		var saleName = document.getElementById("saleName").value;
		var saleName1 = document.getElementById("saleName1").value;
		
		
		var projectDesce = document.getElementById("projectDesce").value;
		var projectDescc = document.getElementById("projectDescc").value;
		
		
		
		if(cid==null || cid.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			document.getElementById("ts0").innerHTML="客户联系人id!";
			return false;
		}
		if(content==null || content.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			document.getElementById("ts1").innerHTML="邮件内容不为空";
			return false;
		}
		if(saleName==null || saleName.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			document.getElementById("ts2").innerHTML="现有销售不为空!";
			return false;
		}
		
		if(projectDesce==null || projectDesce.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			document.getElementById("ts3").innerHTML="项目英文名不为空!";
			return false;
		}
		if(projectDescc==null || projectDescc.replace(/^\s\s*/, '').replace(/\s\s*$/, '').length==0){
			document.getElementById("ts4").innerHTML="项目中文名不能为空!";
			return false;
		}
		
		
		
		
		
		var myDate = new Date();
		startTime = myDate.getTime();
		$(this).attr("disabled", true);
		//$("#uploadForm").submit();
		$("#progress").show();
		window.setTimeout("getProgressBar()", 1000);
		return true;
	}
		$(document).ready(function(){
			$('#unpro').addClass('line_on');
			$('#unpro').find('a').css('color','#fff');
			document.getElementById('eleft').style.height=document.getElementById('usechange').offsetHeight;
		}
				
		)
	
	
</script>


<body>
	<div class="cusalldiv">
		<jsp:include page="people_manager.jsp"></jsp:include>
		<div class="usechange" id="usechange">
			<h2>新项目录入</h2>
			<form
				action="/ERP-NBEmail/helpServlet?action=createProject&className=ItCaseIdServlet"
				method="post" enctype="multipart/form-data" id="uploadForm"
				onsubmit="return check();">
				<input type="hidden" name="recoder" value="${user.userName }">
				<input type="hidden" name="caseNo" value="${caseNo }">
				<table>
					<%--  <tr>
			        <td class="usermatd1">项目编号:</td>
			        <td>${caseNo}</td>
			           
			      </tr>   --%>
					<tr>
						<td class="usermatd1">客户编号:</td>
						<td><input type="text" name="cid" id="cid" size="30"
							class="custdin" /> <font id="ts0" class="cusmessa">(必填选项)</font></td>
					</tr>

					<tr>
					<tr>
						<td class="usermatd1">现有销售:</td>
						<td><input type="text" id="saleName" name="saleName"
							size="30" class="custdin" /> <font id="ts2" class="cusmessa">(必填选项)</font>
						</td>
					</tr>
					<tr>
						<td class="usermatd1">跟单销售:</td>
						<td><input type="text" id="saleName1" name="saleName1"
							size="30" class="custdin" /></td>
					</tr>
					<tr>
						<td class="usermatd1">现有报价员:</td>
						<td><input type="text" id="quotername" name="quotername"
							size="30" class="custdin" /></td>
					</tr>

					<tr>
						<td class="usermatd1">项目中文名:</td>
						<td><input type="text" id="projectDescc" name="projectDescc"
							size="30" class="custdin" /> <font id="ts3" class="cusmessa">(必填选项)</font></td>
					</tr>
					<tr>
						<td class="usermatd1">项目英文名:</td>
						<td><input type="text" id="projectDesce" name="projectDesce"
							size="30" class="custdin" /> <font id="ts4" class="cusmessa">(必填选项)</font></td>

					</tr>
					<tr>
						<td class="usermatd1">项目类别:</td>
						<td>
							<!-- <input type="text" name="country" size="30"> -->
							<div class="userselediv_nora">
								<select name="ddlType" id="ddlType" class="userseleina">
									<option value="11">工程技术类</option>

									<option value="4">商贸类</option>

									<option value="10">其他</option>

								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td class="usermatd1">项目大小</td>
						<td><input type="radio" name="OrderGroup" id="Radiobutton3"
							value="大于5W美金">大于5W美金 <input type="radio"
							name="OrderGroup" id="Radiobutton4" value="小于5W美金"
							checked="checked">小于5W美金</td>
					</tr>


					<tr>
						<td class="usermatd1">客户类别</td>
						<td><input type="radio" name="customerGroup"
							id="ddlCustomerType_new" value="新客户" checked="checked">新客户
							<input type="radio" name="customerGroup" id="ddlCustomerType_old"
							value="老客户">老客户</td>
					</tr>

					<tr>
						<td class="usermatd1">选择报价天数:</td>
						<td>
							<!-- <input type="text" name="country" size="30"> -->
							<div class="userselediv_nora">
								<select name="ddlSelectPriceDays" id="ddlSelectPriceDays"
									class="userseleina">
									<option value="3">三天</option>

									<option value="5">五天</option>

									<option value="8">八天</option>
									<option value="12">十二天</option>

								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td class="usermatd1">邮件内容</td>
						<td><textarea cols="80" rows="10" id="content" name="content"></textarea><font
							id="ts1" class="cusmessa">(必填选项)</font></td>
					</tr>

					<tr id="fileupload">
						<td class="usermatd1"><h3>上传附件：</h3></td>
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
						<td colspan="2" align="center"><input type="submit"
							id="Button" name="Button" value="提交" class="emanagerrmaconfnta" />
						</td>
					</tr>
				</table>
			</form>
			<div id="progress">
				<div id="title">
					<span id="text">上传进度</span>
					<div id="close" style="display: none;">X</div>
				</div>
				<div id="progressBar">
					<div id="uploaded"></div>
				</div>
				<div id="info"></div>
			</div>



		</div>
	</div>
</body>
</html>