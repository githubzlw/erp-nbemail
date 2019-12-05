<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户首笔到款日期</title>
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

/* margin: auto; */
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
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

var updateReason=function(reason1,sign,caseNo){
	var reason=document.getElementById(reason1).value
	reason = reason.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
	var params = { 
		   "reason":reason,
			"caseNo":caseNo,
			"action":"updateReason1",
			"className":"ItCaseIdServlet",
	};
      $.ajax({  
            url:'/ERP-NBEmail/helpServlet',  
            type:"post",  
            data:params,  
            success:function(data)  
                    { 
          	  		if(data == "YES"){
          	  		document.getElementById(sign).innerHTML="已加备注";		 
          	  		}else{
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
			<h2>客户首笔到款日期</h2>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=projectContractTime&className=ItCaseIdServlet"
					method="post">
					<table class="usectable">
						<tr>

							<td class="usermatd3">根据条件查询:</td>

							<td>
								<div class="userselediv_nor">
									<select name="condition" id="condition" class="userselein">
										<option value="1">全部</option>
										<option value="2">未上传合同项目</option>
										<option value="3">可拷贝未上传合同项目</option>
									</select>
								</div>
							</td>
							<td>
								<div class="userselediv_nor">
									<select name="condition1" id="condition1" class="userselein">
										<option value="1">按时间排序</option>
										<option value="2">按项目号排序</option>
									</select>
								</div>
							</td>

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> <a
								href="/ERP-NBEmail/download1?filename=firstparagraph.xls"
								title="firstparagraph.xls">下载首付款到账日期列表</a></td>
						</tr>
					</table>

				</form>
			</div>
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>项目号</td>
					<td>客户首笔到款日期</td>
					<td>跟单销售</td>
					<td>采购</td>
					<td>合同及工厂</td>
					<td>原因</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td><a
							href="http://117.144.21.74:33168/po/CaseInPo.aspx?id=${cus.caseNo }"
							target=_blank>${cus.caseNo }</a> <font id="sign${i.count }"
							style="color: red;" class="cusmessa"></font></td>
						<td>${cus.createTime != null ?fn:substring(cus.createTime,0,fn:indexOf(cus.createTime," ")):""}
						</td>
						<td>${cus.merchandManager1 }&nbsp;${cus.merchandising }</td>
						<td>${cus.merchandManager2 }</td>

						<td><c:forEach items="${cus.contract}" var="cus1"
								varStatus="i1">
								<a
									href="http://117.144.21.74:33168/fukuan/payment/upload/bargain/${cus1.contract }">${cus1.contract }</a>
								<br /> ${cus1.factory } <br />
							</c:forEach></td>
						<td><textarea cols="40" rows="2" id="reason${i.count }">${cus.projectContractNote }</textarea></td>
						<td><input type="button"
							onclick="updateReason('reason${i.count }','sign${i.count }','${cus.caseNo }');"
							value="添加备注" /></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>