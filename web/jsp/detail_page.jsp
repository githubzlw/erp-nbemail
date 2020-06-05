<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>进账客户及下单项目详情页</title>
</head>

<style>
    .part_01 {
        overflow: hidden;
    }

    .emanagergettable {
        width: 300px;
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

    .factoryOffering {
        position: absolute;
        top: 50px;
        left: 1700px;
    }
    .wrap{overflow:hidden; min-width: 1650px;}
    .usechange1{width:310px;float:left;}
    .usechange1>div{height:45px;width: 300px;
    }
</style>


<script type="text/javascript" src="/ERP-NBEmail/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript"
        src="My97DatePicker/WdatePicker.js"></script>


<body>


<div class="cusalldiv">

    <div class="usechange">
        <h2>${userName}进账客户及下单项目详情页</h2>
        <div class="wrap">
            <div class="usechange1">
                <div>${starttime}至${endtime}下单客户项目详情表</div>
                <table class="emanagergettable">
                    <tr class="emanagergettr">
                        <td>项目号</td>
                        <td>客户id</td>
                    </tr>
                    <c:forEach items="${orderCustomerList}" var="cus" varStatus="i">
                        <tr>
                            <td>${cus.orderId }</td>
                            <td>${cus.customerId }</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="usechange1">
                <div>${endtime}至今未下单客户项目详情表</div>
                <table class="emanagergettable">
                    <tr class="emanagergettr">
                        <td>项目号</td>
                        <td>客户id</td>
                    </tr>
                    <c:forEach items="${noOrderCustomerList}" var="cus" varStatus="i">
                        <tr>
                            <td>${cus.orderId }</td>
                            <td>${cus.customerId }</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="usechange1">
                <div>${starttime1}至今所有A/B级下单客户详情表</div>
                <table class="emanagergettable">
                    <tr class="emanagergettr">
                        <td>项目号</td>
                        <td>客户id</td>
                    </tr>
                    <c:forEach items="${allCustomerList}" var="cus" varStatus="i">
                        <tr>
                            <td>${cus.orderId }</td>
                            <td>${cus.customerId }</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="usechange1">
                <div>${starttimea}至${endtimea}A/B级客户下单数(客户创建时间${starttime1}至今)详情表</div>
                <table class="emanagergettable">
                    <tr class="emanagergettr">
                        <td>项目号</td>
                        <td>客户id</td>
                    </tr>
                    <c:forEach items="${newCustomerList}" var="cus" varStatus="i">
                        <tr>
                            <td>${cus.orderId }</td>
                            <td>${cus.customerId }</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="usechange1">
                <div>${starttimea}至${endtimea}A/B级客户下单数(${starttime1}至今)详情表</div>
                <table class="emanagergettable">
                    <tr class="emanagergettr">
                        <td>项目号</td>
                        <td>客户id</td>
                    </tr>
                    <c:forEach items="${fixedTimeCustomersList}" var="cus" varStatus="i">
                        <tr>
                            <td>${cus.orderId }</td>
                            <td>${cus.customerId }</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>