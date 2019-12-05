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
    <title>查看销售跟单利润页面</title>
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
        <h2>查看销售跟单利润页面</h2>

        <div>
            <form action="/ERP-NBEmail/helpServlet?action=queryProfit&className=InvoiceServlet"
                  method="post" >
                <table><tr>
                    <td class="usermatd3">时间:</td>
                    <td><input type="text" readonly class="Wdate" id="time"
                               name="time" value="${time }"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM'})" /></td>
                    <td><input type="text" readonly class="Wdate" id="time1"
                               name="time1" value="${time1 }"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM'})" /></td>
                   <td class="usermatd4"><input type="submit" value="查询"
                                                 class="usersearchbtn"></td>

                </tr>
                </table>
            </form>

        </div>


        <table class="emanagergettable">
            <tr class="emanagergettr">
                <td>用户</td>
                <td>时间1</td>
                <td>当月总进账1($)</td>
                <td>当月利润1($)</td>
                <td>时间2</td>
                <td>当月总进账2($)</td>
                <td>当月利润2($)</td>
                <td>总进账差距</td>
                <td>总利润差距</td>
            </tr>
            <c:forEach items="${cusList}" var="cus" varStatus="i">
                <tr>
                    <td>${cus.name }</td>
                    <td>${cus.time }</td>
                    <td>${cus.income }</td>
                    <td>${cus.profit }</td>
                    <td>${cus.time1 }</td>
                    <td>${cus.income1 }</td>
                    <td>${cus.profit1 }</td>
                    <td>
                        <c:if test="${cus.income1-cus.income>0 }"><fmt:formatNumber value="${cus.income1-cus.income }" type="number" maxFractionDigits="2"/></c:if>
                        <c:if test="${cus.income1-cus.income<0 }"><span style="color:red;"><fmt:formatNumber value="${cus.income1-cus.income }" type="number" maxFractionDigits="2"/></span></c:if>

                    </td>
                    <td>
                        <c:if test="${cus.profit1-cus.profit>0 }"><fmt:formatNumber value="${cus.profit1-cus.profit }" type="number" maxFractionDigits="2"/></c:if>
                        <c:if test="${cus.profit1-cus.profit<0 }"><span style="color:red;"><fmt:formatNumber value="${cus.profit1-cus.profit }" type="number" maxFractionDigits="2"/></span></c:if>

                    </td>
                </tr>
            </c:forEach>
        </table>

    </div></div>
</body>
</html>