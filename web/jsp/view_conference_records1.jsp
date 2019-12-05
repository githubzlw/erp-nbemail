<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看项目会议信息</title>
</head>
<style>
.usechange {
	width: 80%;
}

.emanagergettable {
	width: 100%;
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


window.onload=function(){
	document.getElementById('client').style.background='url(/ERP-NBEmail/img/emana/elion.png) 0 0 no-repeat';
	document.getElementById('client').getElementsByTagName('a')[0].style.color="#fff";
	document.getElementById('eleft').style.height=(document.body.scrollHeight-68)+'px';
}	
</script>

<body>
	<div class="cusalldiv">

		<div class="usechange">
			<h2>查看项目会议信息</h2>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=viewConferenceRecords&className=ItCaseIdServlet"
					method="post">
					<table class="usectable">
						<tr>

							<td class="usermatd3">根据项目号模糊查询:</td>

							<td>
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="condition" />

								</div>
							</td>
							<td><select id="conferenceMessagea"
								name="conferenceMessagea">
									<option value=""
										<c:if test="${fyzt==0 }">selected="selected"</c:if>>全部</option>
									<option value="新项目启动会"
										<c:if test="${fyzt==1 }">selected="selected"</c:if>>新项目启动会</option>
									<option value="项目周进展会"
										<c:if test="${fyzt==2 }">selected="selected"</c:if>>项目周进展会</option>
									<option value="质量分析会(样品)"
										<c:if test="${fyzt==3 }">selected="selected"</c:if>>质量分析会(样品)</option>
									<option value="大货质量会议"
										<c:if test="${fyzt==4 }">selected="selected"</c:if>>大货质量会议</option>
									<option value="质量分析会(小批量)"
										<c:if test="${fyzt==5 }">selected="selected"</c:if>>质量分析会(小批量)</option>
							</select></td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" /></td>
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>
						</tr>
					</table>

				</form>
				<form
					action="/ERP-NBEmail/helpServlet?action=viewConferenceRecords1&className=ItCaseIdServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td><select id="conferenceMessagea"
								name="conferenceMessagea">
									<option value=""
										<c:if test="${fyzt1==0 }">selected="selected"</c:if>>全部</option>
									<option value="新项目启动会"
										<c:if test="${fyzt1==1 }">selected="selected"</c:if>>新项目启动会</option>
									<option value="项目周进展会"
										<c:if test="${fyzt1==2 }">selected="selected"</c:if>>项目周进展会</option>
									<option value="质量分析会(样品)"
										<c:if test="${fyzt1==3 }">selected="selected"</c:if>>质量分析会(样品)</option>
									<option value="大货质量会议"
										<c:if test="${fyzt1==4 }">selected="selected"</c:if>>大货质量会议</option>
									<option value="质量分析会(小批量)"
										<c:if test="${fyzt1==5 }">selected="selected"</c:if>>质量分析会(小批量)</option>
							</select></td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" /></td>
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>
						</tr>
					</table>

				</form>
			</div>
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>新项目启动会</td>
					<td>项目周进展会</td>
					<td>质量分析会（样品）</td>
					<td>大货质量会议</td>
					<td>质量分析会（小批量）</td>
					<td>跟单销售</td>
					<td>需开会议</td>
					<td>会议选择</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td><a
							href="http://117.144.21.74:33168/po/CaseInPo.aspx?id=${cus.caseNo }"
							target=_blank>${cus.caseNo }</a></td>

						<td><span><c:choose>
									<c:when test="${cus.newProject==0 }">
										<span style="color: red;">空</span>
									</c:when>
									<c:when test="${cus.newProject==1 }">已召开</c:when>
								</c:choose> </span> ${cus.newProjectTime }</td>
						<td><span><c:choose>
									<c:when test="${cus.projectZhouJin==0 }">
										<span style="color: red;">空</span>
									</c:when>
									<c:when test="${cus.projectZhouJin==1 }">已召开</c:when>
								</c:choose> </span> ${cus.projectZhouJinTime }</td>
						<td><span><c:choose>
									<c:when test="${cus.qualityAnalysis==0 }">
										<span style="color: red;">空</span>
									</c:when>
									<c:when test="${cus.qualityAnalysis==1 }">已召开</c:when>
								</c:choose> </span> ${cus.qualityAnalysisTime }</td>
						<td><span><c:choose>
									<c:when test="${cus.massQualityConference==0 }">
										<span style="color: red;">空</span>
									</c:when>
									<c:when test="${cus.massQualityConference==1 }">已召开</c:when>
								</c:choose> </span> ${cus.massQualityConferenceTime }</td>
						<td><span><c:choose>
									<c:when test="${cus.smallQuantity==0 }">
										<span style="color: red;">空</span>
									</c:when>
									<c:when test="${cus.smallQuantity==1 }">已召开</c:when>
								</c:choose> </span> ${cus.smallQuantityTime }</td>
						<td><span>${cus.merchandManager1 }</span></td>
						<td><input type="text" style="width: 200px"
							id="conferenceMessage${i.count }"
							value="${cus.conferenceMessage }"></input></td>
						<td><span><select id="message${i.count }"
								class="userselein">
									<option value="新项目启动会">新项目启动会</option>
									<option value="项目周进展会">项目周进展会</option>
									<option value="质量分析会(样品)">质量分析会(样品)</option>
									<option value="大货质量会议">大货质量会议</option>
									<option value="质量分析会(小批量)">质量分析会(小批量)</option>
							</select> </span></td>
						<td><input type="button"
							onclick="messageProjectMeeting('${cus.caseNo }','conferenceMessage${i.count }','message${i.count }');"
							value="提交信息"></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>