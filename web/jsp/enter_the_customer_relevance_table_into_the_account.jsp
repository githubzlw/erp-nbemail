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
	width: 1200px;
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
	width: 1000px;
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
					<td class="usermatd3">客户名（进账、ERP、简称）:</td>
							<td><input type="text"  id="name"
								name="name" value="${name }"
								 />
						<td class="usermatd3">客户id:</td>
						<td><input type="text"  id="customerId"
								   name="customerId" value="${customerId }"
						/>
						<td class="usermatd3">项目号:</td>
						<td><input type="text"  id="projectId"
								   name="projectId" value="${projectId }"
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
					<td>国家</td>
					<td>简称</td>
                    <c:if test="${EmpEName=='mandyman'}"><td>操作</td></c:if>

				</tr>
				<c:forEach items="${cusList}" var="cus" varStatus="i">
					<tr>
						<td>${cus.name }</td>
						<td>${cus.kingName }</td>
						<td>${cus.customerId }</td>
						<td>${cus.kingdee }</td>
						<td>${cus.projectId }</td>
						<td><c:choose>
							<c:when test="${cus.country==1 }">USA</c:when>
							<c:when test="${cus.country==2 }">Canada</c:when>
							<c:when test="${cus.country==3 }">France</c:when>
							<c:when test="${cus.country==4 }">Germany</c:when>
							<c:when test="${cus.country==5 }">Netherlands</c:when>
							<c:when test="${cus.country==6 }">Israel</c:when>
							<c:when test="${cus.country==7 }">Mexico</c:when>
							<c:when test="${cus.country==8 }">Australia</c:when>
							<c:when test="${cus.country==9 }">Italy</c:when>
							<c:when test="${cus.country==10 }">Switzerland</c:when>
							<c:when test="${cus.country==11 }">Finland</c:when>
							<c:when test="${cus.country==12 }">Sweden</c:when>
							<c:when test="${cus.country==13 }">UK</c:when>
							<c:when test="${cus.country==14 }">Argentina</c:when>
							<c:when test="${cus.country==15 }">Other</c:when>
							<c:when test="${cus.country==16 }">Japan</c:when>
							<c:when test="${cus.country==17 }">China</c:when>
							<c:when test="${cus.country==18 }">Austria</c:when>
							<c:when test="${cus.country==19 }">Saudi Arabia</c:when>
							<c:when test="${cus.country==20 }">Belgium</c:when>
							<c:when test="${cus.country==21 }">Spain</c:when>
							<c:when test="${cus.country==22 }">New Zealand</c:when>
							<c:when test="${cus.country==23 }">Slovenia</c:when>
							<c:when test="${cus.country==24 }">Serbia</c:when>
							<c:when test="${cus.country==25 }">Ireland</c:when>

						</c:choose></td>
						<td>${cus.abbreviation }</td>
                        <c:if test="${EmpEName=='mandyman'}"><td>
                            <input type="button" value="编辑"/>
                          <input type="button" value="删除" onclick=""/></td></c:if>
					</tr>
				</c:forEach>
			</table>
			</div></div>
</body>
</html>