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
<title>工厂发票未收齐数据统计</title>
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
<script>
var search =function(bargain,sign,date){
	
	var params = {  
			"bargain":bargain,
			"date":date,
		    "action":"search",
			"className":"BarginServlet",
  	};
      $.ajax({  
            url:'/ERP-NBEmail/helpServlet',  
            type:"post",  
            data:params,  
            success:function(data)  
                    { 
          	  		
          	  		document.getElementById(sign).innerHTML=data;		 
          	  		
                    }, 
           
        }
    ); 
};

</script>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	
</script>

<body>
	<div class="cusalldiv">
	
	
	

		<div class="usechange">
			<h2>工厂发票未收齐合同数据统计</h2>
			<div>
			1.合同总金额:<fmt:formatNumber value="${contractAmount}" type="number" maxFractionDigits="2"/>,已支付总金额:<fmt:formatNumber value="${totalAmountPaid}" type="number" maxFractionDigits="2"/>,发票收回金额:<fmt:formatNumber value="${amountRecoveredFromInvoice}" type="number" maxFractionDigits="2"/>
			</div>
			
          <div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<form
					action="/ERP-NBEmail/helpServlet?action=noInvoiceReceiptHasBeenReceived&className=BarginServlet"
					method="post">
					<table class="usectable">
						<tr>
							<td class="usermatd3">查询:</td>
							<td class="usermatd1">
								<div class="userselediv_nor">
									<input type="text" class="userselein" name="vs" value="${vs }" />

								</div>
							</td>
							<td>
								<div class="userselediv_nor">
									<select name="condition" id="se" class="userselein">
										<option value="1"
											<c:if test="${fyfy==1 }">selected="selected"</c:if>>项目号</option>
										<option value="2"
											<c:if test="${fyfy==2 }">selected="selected"</c:if>>工厂名称</option>
										<option value="3"
											<c:if test="${fyfy==3 }">selected="selected"</c:if>>合同号</option>
										
									</select>
								</div>
							</td>
							
							<td>
								<div class="userselediv_nor">
									<select name="invoiceCollected" id="invoiceCollected" class="userselein">
										<option value="-1">发票未收齐</option>
										<option value="0"
											<c:if test="${fyfz==0 }">selected="selected"</c:if>>全部</option>
										<option value="1"
											<c:if test="${fyfz==1 }">selected="selected"</c:if>>发票已收齐</option>
										
									</select>
								</div>
							</td>
							<td class="usermatd3">起始时间:</td>
							<td><input type="text" readonly class="Wdate" id="time1"
								name="time1" value="${starttime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
					<td class="usermatd3">截至时间:</td>
							<td><input type="text" readonly class="Wdate" id="time2"
								name="time2" value="${endtime }"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>

							<td class="usermatd4"><input type="submit" value="查询"
								class="usersearchbtn"> <font id="ts0" class="cusmessa">(查询条件可以是项目号、工厂名称、合同号。)</font> 
								</td>
						</tr>
					</table>

				</form>
			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=noInvoiceReceiptHasBeenReceived&className=BarginServlet&overtime=1"
						target=_blank>超过1个月发票未收齐合同数据统计</a>
	           <a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=noInvoiceReceiptHasBeenReceived&className=BarginServlet&overtime=2"
						target=_blank>超过2个月发票未收齐合同数据统计</a>
							<a class="mana_inbtn"
						href="/ERP-NBEmail/helpServlet?action=noInvoiceReceiptHasBeenReceived&className=BarginServlet&overtime=3"
						target=_blank>超过3个月发票未收齐合同数据统计</a>
			</div>
			
				
				
				
			<table class="emanagergettable">
				<tr class="emanagergettr">
                    <td>项目号</td>
					<td style="width: 200px;">合同号</td>
					<td style="width: 240px;">工厂名</td>
					<td>合同金额</td>
					<td>已支付总金额</td>
					<td>发票收回金额</td>
					<td>最后一笔付款日期</td>
					<td>未开票金额</td>
					<td>报关品名</td>
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
				 <tr>
						<td><a
							href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.projectId }"
							target=_blank>${cus.projectId }</a></td>
						<td>${cus.name }</td>
						<td>${cus.factoryName }</td>
						<td><fmt:formatNumber value="${cus.totalSum}" type="number" maxFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${cus.payMoeny}" type="number" maxFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${cus.amountInvoiceReceived}" type="number" maxFractionDigits="2"/></td>
						
						
						<td>${cus.datePayment != null ?fn:substring(cus.datePayment,0,fn:indexOf(cus.datePayment," ")):""}</td>
                         <%-- <td><c:choose><c:when test="${cus.whetherToDeclare==1 }">已报关</c:when>
                         <c:when test="${cus.whetherToDeclare==0 }">未报关</c:when>
                         </c:choose></td> --%>
                           <td><fmt:formatNumber value="${cus.totalSum-cus.amountInvoiceReceived }" type="number" maxFractionDigits="2"/></td>
						<td><input type="button" value="查看报关名" onclick="search('${cus.name }','sign${i.count }','${cus.datePayment}');"><span id="sign${i.count }"></span></td>
						
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














