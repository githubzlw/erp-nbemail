<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>采购副总查看详情</title>
		<style type="text/css">
h1, p, div {
	padding: 0 0;
	margin: 0 0;
}

.meau {
	width: 800px;
	height: auto;
	font-size: 14px;
}

.meau_top {
	width: 100%;
	border-top: 2px #0099FF solid;
	border-left: 1px #0099FF solid;
	border-right: 1px #0099FF solid;
}

.meau_top p {
	border-bottom: 1px #0099FF solid;
	height: 20px;
	line-height: 20px;
	margin-bottom: 5px;
}

.meau_top h1 {
	height: 30px;
	font-size: 15px;
	font-weight: bold;
	line-height: 30px;
}

.meau_top p span {
	margin: 0px 10px;
}

.meau_top p span i, em {
	font-style: normal;
}

.meau_mid {
	width: 100%;
	height: auto;
	border: 1px solid #FFA4FF;
	padding-bottom: 10px;
}

.mid_money {
	background: #FFA4FF;
	width: 100%;
	float: left;
	color: #fff;
}

.mid_zdxx {
	width: 20%;
	line-height: 30px;
	float: left;
}

.mid_zd {
	background: #00C061;
	width: 79%;
	float: left;
	color: #fff;
	padding-left: 1%;
}

.mid_fap {
	width: 100%;
	border-top: 1px #e2e2e2 solid;
	border-left: 1px #e2e2e2 solid;
	text-align: center;
}

.mid_fap th, .mid_fap td {
	border-bottom: 1px #e2e2e2 solid;
	border-right: 1px #e2e2e2 solid;
}

.mid_zd p span b {
	margin-left: 10px;
	width: 80px;
}

.mid_money span {
	margin-left: 10px;
}
</style>
</head>

<body>
	<div class="meau">
		<div class="meau_top">
			<h1>
				<img src="/ERP-NBEmail/examine/img/3.jpg" />项目信息
			</h1>
			<p>
				<span><b>项目编号:</b><em>${itemcase.caseNo }</em></span><span><b>立项时间:</b><em>${itemcase.createTime != null ?fn:substring(itemcase.createTime,0,fn:indexOf(itemcase.createTime,".")):""}</em></span>
			</p>
			<p>
				<span><b>项目说明:</b><em>${itemcase.projectDescc }
						${itemcase.projectDesce }</em></span>
			</p>
			<p>
				<span><b>项目成员:</b>
				<c:if test="${itemcase.zhijian1!=null }">
						<em>${itemcase.zhijian1}</em>
						<i>(质检)</i>
					</c:if> <c:if test="${itemcase.zhijian2!=null }">
						<em>${itemcase.zhijian2}</em>
						<i>(质检)</i>
					</c:if>
					<c:if test="${itemcase.saleName1!=null }">
						<em>${itemcase.saleName1}</em>
						<i>(销售)</i>
					</c:if> <c:if test="${itemcase.saleName2!=null }">
						<em>${itemcase.saleName2}</em>
						<i>(销售)</i>
					</c:if>
					<c:if test="${itemcase.engineer1!=null }">
						<em>${itemcase.engineer1}</em>
						<i>(采购)</i>
					</c:if> <c:if test="${itemcase.merchandManager2!=null }">
						<em>${itemcase.merchandManager2}</em>
						<i>(采购)</i>
					</c:if> </span>
			</p>
		</div>
		<div class="meau_mid">
			<div>
				<div class="mid_zdxx">
					<em style="font-size: 15px; font-weight: bold;">账单信息</em></span><span>
				</div>
				<!--  <div class="mid_zd"><img src="" />
        <p><span><b>客户到款：</b><em>xxxxxxxxxxxxxxxxxx</em><span><b>支付金额：</b><em>xxxxxxxxxxxxxxxxxx</em></span></p>
        <p><span><b>毛利润：</b><em>xxxxxxxxxxxxxxxxxx</em><span><b>毛利率：</b><em>xxxxxxxxxxxxxxxxxx</em></span></p>
      </div>
      
       -->
				<div class="mid_money">
					发票<span>应收：<em>${info.allmoney }</em><i>美金</i></span><span>已收：<em>${info.allmoney1 }</em><i>美金</i></span><span>未收：<em>${remainder}</em><i>美金</i></span>
				</div>
				<table cellpadding="0" cellspacing="0" class="mid_fap">
					<thead>
						<th>发票编号</th>
						<th>预计到款</th>
						<th>&nbsp;</th>
						<th>日期</th>
						<th>实际到款</th>
						<th>&nbsp;</th>
						<th>日期</th>
						<th>付款银行</th>
						<th>汇率</th>
					</thead>
					<tbody>
						<c:forEach items="${invoiceinfo}" var="cus" varStatus="i">
							<tr>
								<td><a
									href="http://117.144.21.74:33168/fukuan/payment/upload/invoice/${cus.iUrl }"
									target=_blank>${cus.iInvNo }</a></td>
								<td>${cus.iimoney }</td>
								<td><c:choose>
										<c:when test="${cus.imoneytype==1 }">美元</c:when>
										<c:when test="${cus.imoneytype==2 }">人民币</c:when>
									</c:choose></td>
								<td>${cus.iidate != null ?fn:substring(cus.iidate,0,fn:indexOf(cus.iidate," ")):""}</td>
								<td><c:if test="${cus.ifmoney!=0 }">${cus.ifmoney }</c:if></td>
								<td><c:choose>
										<c:when test="${cus.imoneytype==1 }">美元</c:when>
										<c:when test="${cus.imoneytype==2 }">人民币</c:when>

									</c:choose></td>
								<td>${cus.ifdate != null ?fn:substring(cus.ifdate,0,fn:indexOf(cus.ifdate," ")):""}</td>
								<td>${cus.bname }</td>
								<td>${cus.exchangerate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<table>
				<thead>
					<th></th>
				</thead>
			</table>
		</div>
		<div class="meau_mid" style="border-top: 0px;">
			<div>

				<div class="mid_money" style="background: #CCC">
					工厂合同<span>应付：<em>${fund.allMoney }</em><i>RMB</i></span><span>已付：<em>${fund.allMoney1 }</em><i>RMB</i></span><span>未付：<em>${fund.allMoney-fund.allMoney1 }
					</em><i>RMB</i></span>
				</div>
				<!-- <div class="mid_money" style="background:#fff;color:#333;"><span>已支付金额<b style="color:red">1500</b>(RMB)</span><span>已回收发票金额<b style="color:red">0</b>(RMB)</span><a>查看发票情况</a></div>
      <div class="mid_money" style="background:#fff;color:#333;"><span>合13008A</span><span>总额<b>300</b>(RMB)</span>xxxxxx</div> -->
				<table cellpadding="0" cellspacing="0" class="mid_fap">

					<thead>
						<th>合同号</th>
						<th>工厂</th>
						<th>应付金额</th>
						<th>申请时间</th>
						<th>审批结果</th>
					</thead>

					<tbody>
						<c:forEach items="${factoryfund}" var="cus" varStatus="i">
							<tr>
								<td><a
									href="http://117.144.21.74:33168/fukuan/payment/upload/Bargain/${cus.bargainUrl }"
									target=_blank>${cus.bargainNo }</a></td>
								<td>${cus.factoryName }</td>
								<td>${cus.friMoney }<c:if test="${cus.moneytype==1 }">美金</c:if>
									<c:if test="${cus.moneytype==2 }">人民币</c:if>
									<c:if test="${cus.moneytype==1 }">欧元</c:if></td>
								<td>${cus.friFacDate != null ?fn:substring(cus.friFacDate,0,fn:indexOf(cus.friFacDate," ")):""}</td>
								<td><c:if test="${cus.checkReason!=null }">${cus.checkReason }<br />
									</c:if>${cus.state }</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<table>
				<thead>
					<th></th>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>
