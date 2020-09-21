<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<title>所有未收发票金额详情列表</title>
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
	width: 1500px;
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
var updateMonth=function(){
	
	var time=document.getElementById("time1").value
	
	var params = { 
		   "time":time,
		   "action":"updateMonth",
			"className":"InvoiceServlet",
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
	
</script>

<body>
	<div class="cusalldiv">
	<div class="usechange">
			<h2>所有未收发票金额详情列表</h2>
<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=allDetailedAccounts&className=InvoiceServlet"
					method="post">
					<input type="hidden" id="kingdee" name="kingdee" value=${kingdee }>
					<input type="hidden" id="num" name="num" value=${num }>
					
					<table class="usectable">
						<tr>
							<td class="usermatd3">根据月份查询数据:</td>
                               <td>
								<div class="userselediv_nor">
									<select name="month" id="month" class="userselein">
									<option value="${month }">${month }</option>
										<option value="-1">全部</option>
										<option value="2016-01">2016-01</option>
										<option value="2016-02">2016-02</option>
										<option value="2016-03">2016-03</option>
										<option value="2016-04">2016-04</option>
										<option value="2016-05">2016-05</option>
										<option value="2016-06">2016-06</option>
										<option value="2016-07">2016-07</option>
										<option value="2016-08">2016-08</option>
										<option value="2016-09">2016-09</option>
										<option value="2016-10">2016-10</option>
										<option value="2016-11">2016-11</option>
										<option value="2016-12">2016-12</option>
										<option value="2017-01">2017-01</option>
										<option value="2017-02">2017-02</option>
										<option value="2017-03">2017-03</option>
										<option value="2017-04">2017-04</option>
										<option value="2017-05">2017-05</option>
										<option value="2017-06">2017-06</option>
										<option value="2017-07">2017-07</option>
										<option value="2017-08">2017-08</option>
										<option value="2017-09">2017-09</option>
										<option value="2017-10">2017-10</option>
										<option value="2017-11">2017-11</option>
										<option value="2017-12">2017-12</option>
										<option value="2018-01">2018-01</option>
										<option value="2018-02">2018-02</option>
										<option value="2018-03">2018-03</option>
										<option value="2018-04">2018-04</option>
										<option value="2018-05">2018-05</option>
										<option value="2018-06">2018-06</option>
										<option value="2018-07">2018-07</option>
										<option value="2018-08">2018-08</option>
										<option value="2018-09">2018-09</option>
										<option value="2018-10">2018-10</option>
										<option value="2018-11">2018-11</option>
										<option value="2018-12">2018-12</option>
										<option value="2019-01">2019-01</option>
										<option value="2019-02">2019-02</option>
										<option value="2019-03">2019-03</option>
										<option value="2019-04">2019-04</option>
										<option value="2019-05">2019-05</option>
										<option value="2019-06">2019-06</option>
										<option value="2019-07">2019-07</option>
										<option value="2019-08">2019-08</option>
										<option value="2019-09">2019-09</option>
										<option value="2019-10">2019-10</option>
										<option value="2019-11">2019-11</option>
										<option value="2019-12">2019-12</option>
										<option value="2020-01">2020-01</option>
										<option value="2020-02">2020-02</option>
										<option value="2020-03">2020-03</option>
										<option value="2020-04">2020-04</option>
										<option value="2020-05">2020-05</option>
										<option value="2020-06">2020-06</option>
										<option value="2020-07">2020-07</option>
										<option value="2020-08">2020-08</option>
										<option value="2020-09">2020-09</option>
										<option value="2020-10">2020-10</option>
										<option value="2020-11">2020-11</option>
										<option value="2020-12">2020-12</option>
										
									</select>
								</div>
							</td>
							<td class="usermatd3">是否欠发票:</td>

							<td>
								<div class="userselediv_nor">
									<select name="condition2" id="audit" class="userselein">
									<option value="2" <c:if test="${fyfz==2 }">selected="selected"</c:if>>合同款已清欠发票</option>
										<option value="-1" <c:if test="${fyfz==-1 }">selected="selected"</c:if>>欠发票</option>
										<option value="0"
											<c:if test="${fyfz==0 }">selected="selected"</c:if>>全部</option>
										<option value="1"
											<c:if test="${fyfz==1 }">selected="selected"</c:if>>不欠发票</option>
										
									</select>
								</div>
							</td>

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn">
								</td>
								<%--<td>出运单报关金额从${day != null ?fn:substring(day,0,fn:indexOf(day," ")):""}后开始查询</td>
								<c:if test="${roleNo==100 }"><td>修改出运单查询日期<input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM'})" /><input type="button" value="修改"
								class="usersearchbtn" onclick="updateMonth();"></td></c:if>--%>
						</tr>
					</table>

				</form>
			</div>


		
            <table class="emanagergettable">
				<tr class="emanagergettr">
				    <td width="60px">金蝶号</td>
				    <td width="60px">客户id</td>
                    <td width="400px">工厂名</td>
					<td width="80px">项目号</td>
					<td width="400px">合同号</td>
					<td width="60px">合同金额</td>
					<td width="60px">已支付工厂金额</td>
					<td width="80px">收回发票</td>
					<td width="80px">出运单已报关金额</td>
					<td width="80px">未带票金额</td>
					<td width="80px">欠发票</td>
					<td width="80px">未付款工厂金额</td>
					<td width="80px">时间</td>
					<td width="80px">采购</td>
					<td width="80px">跟单</td>
					</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
				 <tr>
						<td>${cus.kingdee }</td>
						<td>${cus.factoryId }</td>
						<td>${cus.factoryName }</td>
						<td><a href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.caseNo }" target="_blank">${cus.caseNo }</a></td>
						<td>${cus.bargainNo }</td>
						<td>${cus.contractAmount }</td>
						<td>${cus.amountPaid }</td>
						<td>${cus.currentDebitAmount }</td>
						<td>${cus.shipmentBillAmount+cus.amountCustomsDeclaration }</td>
						<td><c:if test="${cus.contractAmount!=cus.currentDebitAmount+cus.shipmentBillAmount+cus.amountCustomsDeclaration }">
						<span style="color:red;">
						<fmt:formatNumber value="${cus.contractAmount-cus.currentDebitAmount-cus.shipmentBillAmount-cus.amountCustomsDeclaration }" type="number" maxFractionDigits="2"/>
						
						</span></c:if>
						<c:if test="${cus.contractAmount==cus.currentDebitAmount+cus.shipmentBillAmount+cus.amountCustomsDeclaration }">
					     0</c:if>
						</td>

					 <td><c:if test="${cus.contractAmount!=cus.currentDebitAmount+cus.shipmentBillAmount+cus.amountCustomsDeclaration }">
						<span style="color:red;">
						<fmt:formatNumber value="${cus.contractAmount-cus.currentDebitAmount-cus.shipmentBillAmount-cus.amountCustomsDeclaration }" type="number" maxFractionDigits="2"/>

						</span></c:if>
						 <c:if test="${cus.contractAmount==cus.currentDebitAmount+cus.shipmentBillAmount+cus.amountCustomsDeclaration }">
							 0</c:if>
					 </td>

						<td>${cus.contractAmount-cus.amountPaid }</td>
						<td>${cus.createTime != null ?fn:substring(cus.createTime,0,fn:indexOf(cus.createTime," ")):""}</td>
					 	<td>${cus.merchandManager2 }</td>
					 	<td>${cus.merchandManager1 }</td>
					</tr>
				</c:forEach>
			</table>
			</div></div>
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














