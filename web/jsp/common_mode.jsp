<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>共模与选厂分析</title>
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


var updateReason=function(sign1,reason1,caseNo){
	var reason=document.getElementById(reason1).value
	var sign=document.getElementById(sign1).value
	
	reason = reason.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
	sign = sign.replace(/\%/g,"%25").replace(/\#/g,"%23").replace(/\&/g,"%26").replace(/\+/g,"%2B");
	var params = { 
		   "reason":reason,
			"sign":sign,
			"caseNo":caseNo,
			"action":"updateCommonMode",
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
var updateFactory=function(caseNo,factory){
	var factory1=document.getElementById(factory).value
	var params = { 
		   "factory":factory1,
			"caseNo":caseNo,
			"action":"updateFactory",
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
			<h2>共模与选厂分析</h2>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=LookNewProject&className=ItCaseIdServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">根据项目号模糊查询:</td>

							<td>
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="condition" />

								</div>
							</td>


							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"></td>
						</tr>
					</table>

				</form>
			</div>
			<table class="emanagergettable">
				<tr class="emanagergettr">
					<td>项目号</td>
					<td>创建日期</td>
					<td>是否需要共模</td>
					<td>理由</td>
					<td>是否共模操作</td>
					<td>选厂是否通过</td>
					<td>选厂分析操作</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td><a
							href="http://117.144.21.74:33168/po/CaseInPo.aspx?id=${cus.caseNo }"
							target=_blank>${cus.caseNo }</a> <font id="sign${i.count }"
							style="color: red;" class="cusmessa"></font></td>
						<td>${cus.createTime != null ?fn:substring(cus.createTime,0,fn:indexOf(cus.createTime," ")):""}
						</td>
						<td><select class="input_style1" id="commonMode${i.count }"
							name="commonMode${i.count }">
								<option value="${cus.commonMode }" selected="selected">
									<c:choose>
										<c:when test="${cus.commonMode==1 }">是</c:when>
										<c:when test="${cus.commonMode==2 }">否</c:when>
									</c:choose></option>
								<option value="0"></option>
								<option value="1">是</option>
								<option value="2">否</option>
						</select></td>



						<td><textarea cols="60" rows="2" id="reason${i.count }">${cus.commonModeResult }</textarea></td>

						<td><input type="button"
							onclick="updateReason('commonMode${i.count }','reason${i.count }','${cus.caseNo }');"
							value="添加共模备注" /></td>
						<td><select class="input_style1" id="factory${i.count }"
							name="factory${i.count }">
								<option value="${cus.plantAnalysis }" selected="selected">
									<c:choose>
										<c:when test="${cus.plantAnalysis==1 }">是</c:when>
										<c:when test="${cus.plantAnalysis==2 }">否</c:when>
									</c:choose></option>
								<option value="0"></option>
								<option value="1">是</option>
								<option value="2">否</option>
						</select></td>
						<td><input type="button"
							onclick="updateFactory('${cus.caseNo }','factory${i.count }');"
							value="选厂操作" /></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>${pager}</div>
		</div>
	</div>

</body>
</html>