<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>统计外贸处罚流程数据</title>
</head>
<style>
.usechange {
	width: 80%;
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
          	  		}else{
          	  		
          	  		window.location.reload();
          	  		}
                    }, 
           
        }
    ); 
};
var updateOutstandingNotes=function(outstandingNotes,iid,reason){

	var outstandingNotes1=document.getElementById(outstandingNotes).value;
	var reason1=document.getElementById(reason).value;
	var params = { 
		   "outstandingNotes":outstandingNotes1,
		   "reason":reason1,
		   "iid":iid,
			"action":"outstandingNotes",
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
          	  		}else{
          	  		
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
	function OpenDiv1(id,projectId){
		document.getElementById("mid2").value=id;
		document.getElementById("projectId2").value=projectId;
		document.getElementById("div1").style.display="block";
		document.getElementById("open1").style.display="block";
		}
		function CloseDiv1(){
			
		document.getElementById("div1").style.display="none";
		document.getElementById("open1").style.display="block";
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
				
				window.location.reload();			        
				},
		    error:function(){
		    	
		    
		    }
		
		} );
	
		}
	
	
	
	var addContractWithdrawing =function(){
		var iid=document.getElementById("mid2").value;
		var projectId2=document.getElementById("projectId2").value;
		var actualDeductions=document.getElementById("actualDeductions").value;
		var contractNumber=document.getElementById("contractNumber").value;
		
		var params = { 
				   "iid":iid,
				   "projectId":projectId2,
				   "actualDeductions":actualDeductions,
				   "contractNumber":contractNumber,
				    "action":"addContractWithdrawing",
					"className":"ItCaseIdServlet",
			};
		
		
		$.ajax({
			 url:'/ERP-NBEmail/helpServlet',  
		            type:"post",  
		            data:params,  
		            success:function(data) {
				if(data=="YES"){
				window.location.reload();
				}else if(data=="NO"){
					document.getElementById("div1").style.display="none";
				alert("最近一笔还未付工厂的钱，不足以扣质量款，请联系财务人员处理")
				
				}
				},
		    error:function(){
		    	
		     }
		} );
	    
	}
	var deleteItem =function(caseno){
		var flag = confirm("确定清除"+caseno+"该项目？");
		if(flag){
		var params = { 
				   "caseno":caseno,
				    "action":"deleteItem",
					"className":"ItCaseIdServlet",
			};
		$.ajax({
			 url:'/ERP-NBEmail/helpServlet',  
		            type:"post",  
		            data:params,  
		            success:function(data) {
				if(data=="YES"){
				window.location.reload();
				}else if(data=="NO"){
					window.location.reload();
				}
				},
		    error:function(){
		    	
		     }
		} );
		}
		
					        
	
	}
	var updateRemarks =function(caseno,remark){
		var remarks=document.getElementById(remark).value;
		
		var params = { 
				   "caseno":caseno,
				   "remarks":remarks,
				    "action":"updateRemarks",
					"className":"ItCaseIdServlet",
			};
		$.ajax({
			 url:'/ERP-NBEmail/helpServlet',  
		            type:"post",  
		            data:params,  
		            success:function(data) {
				if(data=="YES"){
				window.location.reload();
				}else if(data=="NO"){
					window.location.reload();
				}
				},
		    error:function(){
		    	
		     }
		} );
		}
		
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>统计外贸处罚流程数据(数据来源选厂阶段、生产准备、跟单项目，新项目需转到这3个状态才可显示在该页面)</h2>

			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=startProjectStatistics&className=ItCaseIdServlet"
					method="post">
					<table class="usectable">
						<tr>

							<td class="usermatd3">根据项目号模糊查询:</td>

							<td>
								<div class="userselediv_nor">
									<input type="text" class="userselein" id="condition" name="condition" />

								</div>
							</td>
							
					       <td class="usermatd3">月份:</td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM'})" /></td>
					
					
                            <td>
							<select name="condition1" class="userselein">
						<option value="-1" selected="selected">全部项目</option>
						   <option  <c:if test="${fyfz==1 }">selected="selected"</c:if> value="1">未上传</option>
							<option <c:if test="${fyfz==2 }">selected="selected"</c:if> value="2">已上传</option>
							</select></td>
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>
							<td><a href="/ERP-NBEmail/download?filename=ExportPenaltyProcessData.xlsx">下载当前记录</a></td>
						</tr>
						 
					</table>

				</form>
                   
			</div>
			
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>项目名</td>
					<td>跟单</td>
					<td>采购</td>
					<td>项目等级</td>
					<td>客户类型</td>
					<td>启动会议是否开</td>
					<td>需求汇总是否上传</td>
					<td>po表是否上传</td>
					<td>销售合同上传</td>
					<td>合同是否做出</td>
					<td>A/B级项目及延期项目 的项目计划是否做出</td>
					<td>图纸是否上传</td>
                    <td>零件图纸是否上传</td>
					<td>技术文档上传</td>
					<td>项目是否延期</td>
                    <td>检验计划是否上传</td>
                    <td>样品交期</td>
                    <td>大货交期</td>
                    <td>检验报告</td>
                    <td>检验计划更新</td>
                    <td>样品分析会</td>
                    <td>大货分析会</td>
                    <td>质量反馈报告</td>
					<td>项目状态</td>
					<td>投诉引发图纸更新样品或小批量</td>
                    <td>解释</td>
                   <td>操作</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
					<td>${cus.caseNo }</td>	
					<td>${cus.projectDescc }${cus.projectDesce }</td>	
					<td>${cus.merchandManager1 }</td>	
					<td>${cus.merchandManager2 }</td>
					<td><c:choose>
						<c:when test="${cus.projectLevel==1 }">A</c:when>
						<c:when test="${cus.projectLevel==2 }">B</c:when>
						<c:when test="${cus.projectLevel==3 }">C</c:when>
						
						</c:choose></td>	
					<td><c:choose>
						<c:when test="${cus.productState==1 }">新客户</c:when>
						<c:when test="${cus.productState==2 }">老客户</c:when>
						</c:choose></td>	
					<td><c:if test="${cus.qpId !=null }">${cus.qpId}</c:if><c:if test="${cus.qpId==null }"><span style="color:red;">未开</span></c:if></td>	
				    <td><c:if test="${cus.pdId1!=null }">已上传</c:if><c:if test="${cus.pdId1==null }"><span style="color:red;">未上传</span></c:if></td>	
					 <td><c:if test="${cus.po!=null }">已上传</c:if><c:if test="${cus.po==null }"><span style="color:red;">无</span></c:if></td>	
				    <td><c:choose>
						<c:when test="${cus.salesContract==0 }">进账未超过1万美元,不需要</c:when>
						<c:when test="${cus.salesContract==1 }">已上传</c:when>
						<c:when test="${cus.salesContract==2 }">未上传(${cus.customerManager!=''?cus.customerManager:(cus.merchandManager1!=null?cus.merchandManager1:'')})</c:when>

					</c:choose></td>
					<td>
						<c:if test="${cus.bargainNo!=null }">${cus.bargainNo}</c:if><c:if test="${cus.bargainNo==null }"><span style="color:red;">合同:无</span></c:if><br/>
						<c:forEach items="${cus.picture}" var="num" varStatus="j">
                         <c:if test="${num.category==1}">采购的分析报告：已传<br/></c:if>
							<c:if test="${num.category==2}">样品评测报告：已传<br/></c:if>
							<c:if test="${num.category==3}">量产预备报告：已传<br/></c:if>
							<c:if test="${num.category==4}">包装方案：已传<br/></c:if>
							<c:if test="${num.category==5}">首件产品质量和效率报告：已传<br/></c:if>
							<c:if test="${num.category==6}">选厂分析表：已传<br/></c:if>
						</c:forEach>
					</td>
					<td><c:if test="${cus.projectLevel==1 || cus.projectLevel==2||cus.projectLevel==0 }"><c:if test="${cus.pdId!=0 }">已上传</c:if><c:if test="${cus.pdId==0 }">未上传</c:if></c:if><c:if test="${cus.projectLevel==3  }">C</c:if></td>	
					<td><c:if test="${cus.remark!=null }">${cus.remark}</c:if><c:if test="${cus.remark==null }"><span style="color:red;">未上传</span></c:if></td>
                        <td><c:if test="${cus.partTuzhi!=null }">${cus.partTuzhi}</c:if><c:if test="${cus.partTuzhi==null }"><span style="color:red;">未上传</span></c:if></td>

					<td>
						<c:if test="${cus.projectLevel==1 || cus.projectLevel==2||cus.projectLevel==0 }">
							<c:if test="${cus.technicalDocumentation!=null }">${cus.technicalDocumentation}</c:if>
							<c:if test="${cus.technicalDocumentation==null || cus.technicalDocumentation=='' }"><span style="color:red;">未上传</span></c:if>
						</c:if>
						<c:if test="${cus.projectLevel==3  }">C</c:if>
					</td>

					<td><c:if test="${cus.projectLevel==1 || cus.projectLevel==2||cus.projectLevel==0 }"><c:if test="${cus.delay!=0 }">项目延期</c:if><c:if test="${cus.delay==0 }">无延期</c:if></c:if><c:if test="${cus.projectLevel==3  }">C</c:if></td>
<%--					<td><c:if test="${cus.poId!=null }">${cus.poId }</c:if><c:if test="${cus.poId==null }"><span style="color:red;">未上传</span></c:if></td>--%>
						<td><c:if test="${cus.poId!=null }">${cus.poId }</c:if>
						<td><c:if test="${cus.dateSample!=null }">${cus.dateSample != null ?fn:substring(cus.dateSample,0,fn:indexOf(cus.dateSample," ")):""}</c:if><c:if test="${cus.dateSample==null }"><span style="color:red;">无</span></c:if></td>
					<td><c:if test="${cus.completiontime!=null }">${cus.completiontime != null ?fn:substring(cus.completiontime,0,fn:indexOf(cus.completiontime," ")):""}</c:if><c:if test="${cus.completiontime==null }"><span style="color:red;">无</span></c:if></td>		
					<td><c:if test="${cus.intro!=null }">${cus.intro}</c:if><c:if test="${cus.intro==null }"><span style="color:red;">无</span></c:if></td>	
					<td><c:if test="${cus.poId2!=null }">${cus.poId2}</c:if><c:if test="${cus.poId2==null }"><span style="color:red;">未更新</span></c:if></td>
						<td><c:if test="${cus.qpId1!=null }">${cus.qpId1}</c:if><c:if test="${cus.qpId1==null }"><span style="color:red;">未开</span></c:if></td>
					<td><c:if test="${cus.qpId2!='0'&&cus.qpId2!='1'&&cus.qpId2!='2'&&cus.qpId2!='3' }">${cus.qpId2}</c:if>
					<c:if test="${cus.qpId2=='0' }"><span style="color:red;">有问题，大于5000美元，未开</span></c:if>
					<c:if test="${cus.qpId2=='1' }"><span style="color:red;">大于5000美元，未开</span></c:if>
					<c:if test="${cus.qpId2=='2' }"><span style="color:red;">有问题，未开</span></c:if>
					<c:if test="${cus.qpId2=='3' }"><span style="color:red;">无</span></c:if>
					</td>
                        <td>${feedbacktime}${quality_picture}</td>
						<td><c:choose>
							<c:when test="${cus.project_status==0 }">新项目</c:when>
							<c:when test="${cus.project_status==1 }">进行中项目</c:when>
							<c:when test="${cus.project_status==2 }">大货完结项目</c:when>
							<c:when test="${cus.project_status==6 }">样品完结项目</c:when>

						</c:choose></td>
						<td>
							<c:if test="${cus.complaint_id!=0}">
								<c:if test="${cus.verification!=0}"><a href="https://www.kuaizhizao.cn/complaint/queryComplaint?id=${cus.complaint_id}">投诉#${cus.complaint_id},已验证</a></c:if>
								<c:if test="${cus.verification==0}"><a href="https://www.kuaizhizao.cn/complaint/queryComplaint?id=${cus.complaint_id}">投诉#${cus.complaint_id},未验证</a></c:if>
							</c:if>
							<c:if test="${cus.complaint_id==0}">无</c:if>
						</td>
					<td><input type="text" id="remarks${i.count }" value="${cus.remarks }"></td>
					<td><c:if test="${loginName=='ninazhao' }"><input type="button" onclick="deleteItem('${cus.caseNo }');" value="清除项目"></c:if>
					<input type="button" onclick="updateRemarks('${cus.caseNo }','remarks${i.count }')" value="保存备注">
					</td>
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>