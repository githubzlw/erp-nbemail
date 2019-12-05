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
			<h2>客户到款情况的数据统计</h2>

			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=CustomerPaymentStatistics&className=ItCaseIdServlet"
					method="post">
					<table class="usectable">
						<tr>

							<td class="usermatd3">根据项目号、客户名、销售名、跟单销售模糊查询:</td>

							<td>
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="condition" />

								</div>
							</td>
							<td class="usermatd3">开始时间:</td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<td class="usermatd3">截至时间:</td>
							<td><input type="text" readonly class="Wdate" id="time2"
								name="time2" value="${endtime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>
						</tr>
					</table>

				</form>

			</div>
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>客户名</td>
					<td>负责人</td>
					<td>收钱日期</td>
					<td>实际已收金额</td>
					<td>毛利润</td>
					<td>币种</td>

				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td><a
							href="http://117.144.21.74:33168/po/CaseInPo.aspx?id=${cus.caseno }"
							target=_blank>${cus.caseno }</a></td>
						<td>${cus.customerName }</td>
						<td>${cus.merchandManager1 }${cus.merchandManager2 }</td>
						<td>${cus.ifdate }</td>
						<td>${cus.ifmoney}</td>
						<td>${cus.grossProfit}</td>
						<td><c:choose>
								<c:when test="${cus.imoneytype==1 }">美元</c:when>
								<c:when test="${cus.imoneytype==2 }">人民币</c:when>
								<c:when test="${cus.imoneytype==3 }">欧元</c:when>
								<c:when test="${cus.imoneytype==5 }">英镑</c:when>

							</c:choose></td>



					</tr>
				</c:forEach>
			</table>
</body>
</html>