<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户到款情况的数据统计</title>
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
function explain(cid){
	newWindow = window.open("/ERP-NBEmail/helpServlet?action=explain&className=CustomerServlet&cid="+cid+"","windows","height=400,width=600,top=100,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");
}
var url=5;
function cstatus1(cid){
	var num=1;
	window.location.href = "/ERP-NBEmail/helpServlet?action=cstatus&className=CustomerServlet&id="+cid+"&num="+num+"&url="+url+"";
}
function cstatus2(cid){
	var num=2;
	window.location.href = "/ERP-NBEmail/helpServlet?action=cstatus&className=CustomerServlet&id="+cid+"&num="+num+"&url="+url+"";
}
function cstatus3(cid){
	var num=3;
	window.location.href = "/ERP-NBEmail/helpServlet?action=cstatus&className=CustomerServlet&id="+cid+"&num="+num+"&url="+url+"";
}


var messageProjectMeeting=function(caseno,conferenceMessage,message){
	
	var conferenceMessage1=document.getElementById(conferenceMessage).value
	var message1=document.getElementById(message).value
	var params = { 
		   "conferenceMessage":conferenceMessage1,
		   "message":message1,
		   "caseno":caseno,
			"action":"reviseMeetingRecord",
			"className":"ItCaseIdServlet",
	};
      $.ajax({  
            url:'/ERP-NBEmail/helpServlet',  
            type:"post",  
            data:params,  
            success:function(data)  
                    { 
          	  		if(data == "YES"){
          	  			 
          	  		window.location.reload();	
          	  		}else {
          	  		
          	  		window.location.reload();
          	  		}
                    }, 
           
        }
    ); 
};




function OpenDiv(id,projectId){
	document.getElementById("mid").value=id;
	document.getElementById("projectId1").value=projectId;
	document.getElementById("div").style.display="block";
	document.getElementById("open").style.display="block";
	}
	function CloseDiv(){
		
	document.getElementById("div").style.display="none";
	document.getElementById("open").style.display="block";
	}
	function change(){
		document.getElementById("div1").style.display="block";
		
		document.getElementById("open1").style.display="block";
		}
		function CloseDiv1(){
		document.getElementById("div1").style.display="none";
		
		document.getElementById("open1").style.display="block";
		}
	function change1(){
		document.getElementById("div2").style.display="block";
		
		document.getElementById("open2").style.display="block";
		}
		function CloseDiv1(){
		document.getElementById("div2").style.display="none";
		
		document.getElementById("open2").style.display="block";
		}
	var updateExplain =function(){
		var iid=document.getElementById("mid").value;
		var projectId=document.getElementById("projectId1").value;
		var projectFinished=$("input[name='projectFinished']:checked").val();
		var flag = confirm("确定将客户状态转为"+projectFinished+"?");
		if(flag){
			
		if("正常完结"==projectFinished){
			var explain=document.getElementById("explain1").value;
		    var params = { 
					   "iid":iid,
					   "explain":explain,
					   "projectId":projectId,
					   "projectFinished":projectFinished,
					   "action":"updateExplain",
						"className":"ItCaseIdServlet",
				};
			$.ajax({
				url:'/ERP-NBEmail/helpServlet',  
			            type:"post",  
			            data:params,  
			            success:function(data) {
			            	document.getElementById("mid").value="";
			        		$("#open").attr("checked","checked");
			        	     if(data=="YES"){
			 					
			 				window.location.reload();
			 				}else if(data=="NO"){
			 				alert("保存数据库错误")
			 				}
					window.location.reload();			        
					},
			    error:function(){
			    	
			    
			    }
			
			} );	
		}else if("去掉质量扣款后完结"==projectFinished){
			var contractNumber=document.getElementById("contractNumber").value;
			var actualDeductions=document.getElementById("actualDeductions").value;
			var explain=document.getElementById("explain").value;
		    var params = { 
					   "iid":iid,
					   "projectId":projectId,
					   "projectFinished":projectFinished,
					   "contractNumber":contractNumber,
					   "explain":explain,
					   "money":actualDeductions,
					    "action":"updateExplain",
						"className":"ItCaseIdServlet",
				};
			$.ajax({
				url:'/ERP-NBEmail/helpServlet',  
			            type:"post",  
			            data:params,  
			            success:function(data) {
			            	document.getElementById("mid").value="";
			        		document.getElementById("contractNumber").value="";
			        		document.getElementById("actualDeductions").value="";
			        	    document.getElementById("explain").value="";
			        	     $("#open").attr("checked","checked");
			        	     if(data=="YES"){
			 					document.getElementById("actualDeductions").value="";
			 					document.getElementById("contractNumber").value="";
			 				window.location.reload();
			 				}else if(data=="NO"){
			 					document.getElementById("div").style.display="none";
			 				alert("最近一笔还未付工厂的钱，不足以扣质量款，请联系财务人员处理")
			 				
			 				}else if(data=="wrong"){
			 				alert("合同号不在该项目下")
			 				} 
					window.location.reload();			        
					},
			    error:function(){
			    	
			    
			    }
			
			} );
		}else{
			
			var explain=document.getElementById("explain1").value;
			
		    var params = { 
					   "iid":iid,
					   "projectId":projectId,
					   "projectFinished":projectFinished,
					    "explain":explain,
					   "action":"updateExplain",
						"className":"ItCaseIdServlet",
				};
			$.ajax({
				url:'/ERP-NBEmail/helpServlet',  
			            type:"post",  
			            data:params,  
			            success:function(data) {
			            	document.getElementById("mid").value="";
			        		document.getElementById("explain").value="";
			        	     $("#open").attr("checked","checked");
			        	     if(data=="YES"){
			 					
			 				window.location.reload();
			 				}else if(data=="NO"){
			 					document.getElementById("div1").style.display="none";
			 				alert("录入数据库报错")
			 				
			 				}else if(data=="wrong"){
			 				alert("合同号不在该项目下")
			 				} 
					window.location.reload();			        
					},
			    error:function(){
			    	
			    
			    }
			
			} );
		}
		
		}
		}
	
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>客户到款情况的数据统计</h2>

			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=nonPaymentCustomers&className=ItCaseIdServlet"
					method="post">
					<table class="usectable">
						<tr>

							<td class="usermatd3">根据项目号、客户名、销售名、跟单销售模糊查询:</td>

							<td>
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="condition" />

								</div>
							</td>
							<td>
							<select name="condition1" class="userselein">
						<option value="-1" selected="selected">全部</option>

						    <option  <c:if test="${fyfz==1 }">selected="selected"</c:if> value="1">按预计时间收回</option>
							<option <c:if test="${fyfz==2 }">selected="selected"</c:if> value="2">预计滞后3个月内收回</option>
							<option <c:if test="${fyfz==3 }">selected="selected"</c:if> value="3">预计滞后6个月内收回</option>
							<option <c:if test="${fyfz==4 }">selected="selected"</c:if> value="4">预计无法收回全部</option>
							<option <c:if test="${fyfz==5 }">selected="selected"</c:if> value="5">收不回款</option>
							<option <c:if test="${fyfz==6 }">selected="selected"</c:if> value="6">invoice上传错误收款改零</option>
							<option <c:if test="${fyfz==7 }">selected="selected"</c:if> value="7">未完结还在讨要</option>
							<option <c:if test="${fyfz==8 }">selected="selected"</c:if> value="8">列入坏账</option>
							<option <c:if test="${fyfz==9 }">selected="selected"</c:if> value="9">去掉质量扣款后完结</option>
								<option  <c:if test="${fyfz==0 }">selected="selected"</c:if> value="0">无原因</option>
					</select></td>
					<td>
							<select name="condition2" class="userselein">
						<option value="-1" selected="selected">全部</option>
						    <option <c:if test="${fyfx==50 }">selected="selected"</c:if> value="50">未收款超过50</option>
							<option <c:if test="${fyfx==200 }">selected="selected"</c:if> value="200">未收款超过200</option>
							<option <c:if test="${fyfx==500 }">selected="selected"</c:if> value="500">未收款超过500</option>
							<option <c:if test="${fyfx==1000 }">selected="selected"</c:if> value="1000">未收款超过1000</option>
							<option <c:if test="${fyfx==2000 }">selected="selected"</c:if> value="2000">未收款超过2000</option>
							<option <c:if test="${fyfx==5000 }">selected="selected"</c:if> value="5000">未收款超过5000</option>
					</select></td>
					
					
							<td class="usermatd3">截至时间:</td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>
						</tr>
					</table>

				</form>

			</div>
			<div id="div" class="div_style">
					<input type="hidden" value="${mid }" id="mid"
							name="mid">
							<span style="color:red;"><input type="text" id="projectId1" disabled="disabled" style="border:0px;background:rgba(0, 0, 0, 0);width:60px; ">注意如果 你是 把钱录入了其他项目，请在这里解释并点击正常完结</span> 
							<a href="javascript:CloseDiv();">X</a>
							 <div class="part_01" style="text-align:left;">
							<label class="radio-inline explain" ><input type="radio" name="projectFinished" value="未完结还在讨要" onchange="change1();">未完结还在讨要</label>
							<label class="radio-inline explain" ><input type="radio" name="projectFinished" value="正常完结" checked="checked" >正常完结</label>
						 <!--  <label class="radio-inline explain" ><input type="radio" name="projectFinished" value="预计滞后3个月内收回" onchange="change1();">预计滞后3个月内收回</label> -->
						    </div><br/>
						    <div class="part_01" style="text-align:left;">
						   <!--  <label class="radio-inline explain" ><input type="radio" name="projectFinished" value="预计滞后6个月内收回" onchange="change1();">预计滞后6个月内收回</label> -->
						    <label class="radio-inline explain" ><input type="radio" name="projectFinished" value="列入坏账" onchange="change1();">列入坏账</label>
						     <label class="radio-inline explain" ><input type="radio" name="projectFinished" value="预计无法收回全部" onchange="change1();">预计无法收回全部</label>
						    <!-- <label class="radio-inline explain" ><input type="radio" name="projectFinished" value="收不回款" onchange="change1();">收不回款</label> -->
						     </div><br/>
						     <div class="part_01" style="text-align:left;">
						   
						    <label class="radio-inline explain" ><input type="radio" name="projectFinished" value="invoice上传错误改零" onchange="change1();">invoice上传错误改零</label>
						    <label class="radio-inline quality" ><input type="radio" name="projectFinished" value="去掉质量扣款后完结" onchange="change();">去掉质量扣款后完结</label>
						   </div>
						   <div id="div3"><div>放在哪个合同里:<input type="text" id="contractNumber" name="contractNumber" ></div>
					      <div class="usermatd1">实际扣款 :<input type="text" id="actualDeductions" name="actualDeductions">元</div>
					    <div class="demo_line_05">——————————————————————————————————————————————</div>
					    
						<div class="usermatd1">解释：</div>
						<div><textarea id="explain" name="explain" ></textarea></div></div>
						 <div id="div4"> <div class="demo_line_05">——————————————————————————————————————————————</div>
					    
						<div class="usermatd1">解释：</div>
						<div><textarea id="explain1" name="explain1" ></textarea></div> </div>
						  
					<br/><span >
						<input
							class="input_style2" value="保存" type="button"
							onclick="updateExplain()"
							style="margin-top: 10px;"></span>
				</div>
				</div></div>
				
				
				
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>客户名</td>
					<td>负责人</td>
					<td>预计到款</td>
					<td>预计到款时间</td>
					<td>实际到款</td>
					<td>实际到款日期</td>
					<td>未收款</td>
					
					<td>invoice总额</td>
					<td>已到款</td>
					<td>总未收款</td>
					<td>币种</td>
					<td>结果显示</td>
					<td>原因</td>
					<td>录入人</td>
					<!-- <td>选择情况</td>
                    <td>解释</td> -->
                    <td>操作</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
				
			
					<tr>
						<td><a
							href="http://117.144.21.74:33168/po/CaseInPo.aspx?id=${cus.caseno }"
							target=_blank>${cus.caseno }</a></td>
						<td>${cus.customerName }</td>
						<td>${cus.merchandManager1 }&nbsp;${cus.merchandManager2 }&nbsp;${cus.merchandising }</td>
						<td>${cus.iimoney}</td>
						<td>${cus.iidate != null ?fn:substring(cus.iidate,0,fn:indexOf(cus.iidate," ")):""}</td>
                         <td>${cus.ifmoney}</td>
						<td>${cus.ifdate != null ?fn:substring(cus.ifdate,0,fn:indexOf(cus.ifdate," ")):""}</td>
                        <td>${cus.discrepancy }</td>
                        
                        <td>${cus.estimatedFullPayment }</td>
                        <td>${cus.actualPayment }</td>
                        <td>${cus.allArrears }</td>
						<td><c:choose>
								<c:when test="${cus.imoneytype==1 }">美元</c:when>
								<c:when test="${cus.imoneytype==2 }">人民币</c:when>
								<c:when test="${cus.imoneytype==3 }">欧元</c:when>
								<c:when test="${cus.imoneytype==5 }">英镑</c:when>

							</c:choose></td>
                       <td><c:if test="${cus.amountMoney =='去掉质量扣款后完结' }">
                       ${cus.amountMoney}<br/>
                     </c:if>
                       <c:if test="${cus.amountMoney !='去掉质量扣款后完结' }"><c:choose>
									<c:when test="${cus.reason==1 }">按预计时间收回</c:when>
									<c:when test="${cus.reason==2 }">预计滞后3个月内收回</c:when>
									<c:when test="${cus.reason==3 }">预计滞后6个月内收回</c:when>
									<c:when test="${cus.reason==4 }">预计无法收回全部</c:when>
									<c:when test="${cus.reason==5 }">收不回款</c:when>
									<c:when test="${cus.reason==6 }">invoice上传错误收款改零</c:when>
									<c:when test="${cus.reason==7 }">未完结还在讨要</c:when>
									<c:when test="${cus.reason==8 }">列入坏账</c:when>
									<c:when test="${cus.reason==9 }">去掉质量扣款后完结</c:when>
									<c:when test="${cus.reason==10 }">正常完结</c:when>
						   			<c:when test="${cus.reason==0 }">无原因</c:when>
								</c:choose></c:if></td>
								
						<td><c:if test="${cus.amountMoney =='去掉质量扣款后完结' }">
                                                              因为${cus.caseno },需要扣款 ${cus.qualityDeductions}元  时间：${cus.createTime}<br/>
                      <c:if test="${cus.contractNumber!=null}"><span style="color:red;">已录入扣款到合同:${cus.contractNumber}中</span>
                      </c:if>
                       <span>解释：${cus.outstandingNotes }</span>
                       </c:if>
                       <c:if test="${cus.amountMoney !='去掉质量扣款后完结' }"><c:if test="${cus.outstandingNotes !=null }">${cus.outstandingNotes }</c:if></c:if></td>
						<td>${cus.uploads }</td>
                      
                 <td>
                 
                 <c:if test="${cus.reason !=9 }"><input type="button" value="解释" onclick="javascript:OpenDiv(${cus.iid },'${cus.caseno }');" id="open"></c:if>
               
                 </td>
					</tr>
				</c:forEach>
			</table>
			
</body>
</html>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery.min.js"></script>

<script>
	
    $('#div3').hide();
    $('#div4').show();
	$('.normal').click(function(){
		$('#div3,#div4').hide();
	});
	$('.quality').click(function(){
		$('#div3').show();
		$('#div4').hide();
	});
	$('.explain').click(function(){
		$('#div3').hide();
		$('#div4').show();
	});
</script>














