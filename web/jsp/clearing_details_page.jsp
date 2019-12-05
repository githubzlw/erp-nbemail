<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>清账处理页</title>
</head>
<style>
.usechange {
	width: 80%;
}
.input_style2 {
	    margin-top: 10px;
    margin-left: 200px;
}
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 1800px;
	border: 1px #7D7D7D solid;
	border-collapse: collapse;
}

.emanagergettable td {
	border-right: 1px #7D7D7D solid;
	border-bottom: 1px #7d7d7d solid;
	padding: 0px 5px;
	height: 25px;
}

.even {
	background-color: #c7e5ff
}

.emanagergettr {
	font-weight: bold;
	height: 35px;
	text-align: center;
	background: #058fd7;
	color: #fff;
}

.odd {
	background-color: #eaf5ff
}
.div_style {
	width: 500px;
	height:350px;
	position: fixed;
	top: 25%;
	background: #fff;
	border: 2px #badbea solid;
	left: 15%;
	font-size: 14px;
	padding: 0.5% 1% 1%;
	box-shadow: 2px 2px 8px #e2e2e2;
	display: none;
}

.demo_line_05{
    letter-spacing: -1px;
    color: #ddd;
}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function OpenDiv(id,projectId){
	
	window.open("/ERP-NBEmail/jsp/clearing_details_page.jsp"+projectId+"&id="+id+"","windows","height=1000,width=1200,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");

	}
	function change(){
		document.getElementById("div3").style.display="block";
		
		document.getElementById("open1").style.display="block";
		}
		function CloseDiv1(){
		document.getElementById("div3").style.display="none";
		
		document.getElementById("open1").style.display="block";
		}
	function change1(){
		document.getElementById("div4").style.display="block";
		
		document.getElementById("open2").style.display="block";
		}
		function CloseDiv1(){
		document.getElementById("div4").style.display="none";
		
		document.getElementById("open2").style.display="block";
		}
	var updateExplain =function(){
		var iid=document.getElementById("mid").value;
		var money=document.getElementById("money").value;
		var reason=document.getElementById("reason").value;
	    var explain=document.getElementById("explain").value;
		var projectFinished=$("input[name='projectFinished']:checked").val();
		var params = { 
				   "iid":iid,
				   "projectFinished":projectFinished,
				   "reason":reason,
				   "explain":explain,
				   "money":money,
				    "action":"updateExplain",
					"className":"ItCaseIdServlet",
			};
		$.ajax({
			url:'/ERP-NBEmail/helpServlet',  
		            type:"post",  
		            data:params,  
		            success:function(data) {
		            	document.getElementById("mid").value="";
		        		document.getElementById("money").value="";
		        		document.getElementById("reason").value="";
		        	    document.getElementById("explain").value="";
		        	     $("#NormalCompletion").attr("checked","checked");
		        	    
				window.location.reload();			        
				},
		    error:function(){
		    	
		    
		    }
		
		} );
	
		}
	
	
</script>

<body>
	<div class="cusalldiv">

			<div id="div">
					<input type="hidden" value="${mid }" id="mid"
							name="mid">
							<span style="color:red;"><input type="text" id="projectId1" disabled="disabled" style="border:0px;background:rgba(0, 0, 0, 0);width:60px; ">注意如果 你是 把钱录入了其他项目，请在这里解释并点击正常完结</span> 
							<a href="javascript:CloseDiv();">X</a>
							 <div class="part_01" style="text-align:left;">
							<label class="radio-inline" ><input type="radio" name="projectFinished" value="未完结还在讨要" onchange="change1();">未完结还在讨要</label>
							<label class="radio-inline" ><input type="radio" name="projectFinished" value="正常完结" checked="checked">正常完结</label>
						    <label class="radio-inline" ><input type="radio" name="projectFinished" value="预计滞后3个月内收回" onchange="change1();">预计滞后3个月内收回</label>
						    </div><br/>
						    <div class="part_01" style="text-align:left;">
						    <label class="radio-inline" ><input type="radio" name="projectFinished" value="预计滞后6个月内收回" onchange="change1();">预计滞后6个月内收回</label>
						    <label class="radio-inline" ><input type="radio" name="projectFinished" value="列入坏账" onchange="change1();">列入坏账</label>
						    <label class="radio-inline" ><input type="radio" name="projectFinished" value="收不回款" onchange="change1();">收不回款</label>
						     </div><br/>
						     <div class="part_01" style="text-align:left;">
						    <label class="radio-inline" ><input type="radio" name="projectFinished" value="预计无法收回全部" onchange="change1();">预计无法收回全部</label>
						    <label class="radio-inline" ><input type="radio" name="projectFinished" value="invoice上传错误改零" onchange="change1();">invoice上传错误改零</label>
						    <label class="radio-inline" ><input type="radio" name="projectFinished" value="去掉质量扣款后完结" onchange="change();">去掉质量扣款后完结</label>
						   </div>
						   <div>
						    <div id="div3">放在哪个合同里:<input type="text" id="contractNumber" name="contractNumber" ></div>
					      <div class="usermatd1">实际扣款 :<input type="text" id="actualDeductions" name="actualDeductions">元</div>
					    <div class="demo_line_05">——————————————————————————————————————————————</div>
					    
						<div class="usermatd1">解释：</div>
						<div><textarea id="explain" name="explain" ></textarea></div></div>
						<div id="div4">
						<div class="demo_line_05">——————————————————————————————————————————————</div>
					    
						<div class="usermatd1">解释：</div>
						<div><textarea id="explain" name="explain" ></textarea></div>
						</div>
						 <span >
						 <input
							class="input_style2" value="保存" type="button"
							onclick="updateExplain()"
							style="margin-top: 10px;"></span>
				</div>
				</div>
				
				
</body>
</html>