<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>进账关联客户表</title>
</head>
<style>
.usechange {
	width: 80%;
}
.part_01{overflow: hidden;}
.emanagergettable {
	width: 800px;
	border: 1px #7D7D7D solid;
	border-collapse: collapse;
}
.inp_sel {
	width: 80px;
	height: 30px;
	margin-left: 10px;
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
.mana_inbtn {
    background: none;
    border: none;
    color: #00afff;
    text-decoration: underline;
    cursor: pointer;
    font-size: 13px;
}
.factoryOffering {position:absolute;top:50px;left:1700px;}
</style>
<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>


<body>
	<div class="cusalldiv">
   
		<div class="usechange">
			<h2>进账关联客户表</h2>
            <div>
            <span><a href="/ERP-NBEmail/helpServlet?action=insertCustomer&className=AccountEntryFormServlet" target="_blank"><button>录入客户</button></a></span>			
            
            <form action="/ERP-NBEmail/helpServlet?action=enterTheCustomerRelevanceTableIntoTheAccount&className=InvoiceServlet"
					  method="post" >
					<table><tr>
					<td class="usermatd3">进账客户名:</td>
							<td><input type="text"  id="name"
								name="name" value="${name }"
								 />
								<td> <input type="submit" value="查询"
								class="usersearchbtn">
								
								 </td>
					</tr>
								</table>
            </form>
			</div>
			
				
			<table class="emanagergettable">
				<tr class="emanagergettr">

					<td>进账客户名</td>
					<td>ERP客户名</td>
					<td>ERP客户id</td>
					<td>金蝶号</td>
					<td>项目名</td>
					
					
				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.name }</td>
						<td>${cus.kingName }</td>
						<td>${cus.customerId }</td>
						<td>${cus.kingdee }</td>
						<td>${cus.projectId }</td>
						
						
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>