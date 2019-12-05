<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<style>
.usechange {
	width: 80%;
}

.emanagergettable {
	width: 100%;
}

.emanagergettable td {
	border-right: 1px solid rgb(147, 147, 147);
	padding: 0px 5px;
}

.even {
	background-color: #c7e5ff
}

.odd {
	background-color: #eaf5ff
}
</style>
<script>
var deleteBargin1 =function(id,sign){
	var params = {  
			"id":id,
		    "action":"deleteBargin1",
			"className":"BarginServlet",
  	};
      $.ajax({  
            url:'/ERP-NBEmail/helpServlet',  
            type:"post",  
            data:params,  
            success:function(data)  
                    { 
          	  		if(data == "YES"){
          	  		document.getElementById(sign).innerHTML="已删";		 
          	  		}else{
          	  		window.location.reload();
          	  		}
                    }, 
           
        }
    ); 
};
function searchBargin(){
	var projectId=  document.getElementById("projectId").value
	window.open("/ERP-NBEmail/helpServlet?action=searchBargin&className=BarginServlet&&projectId="+projectId);
};
</script>


<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<title>删除合同页面</title>
</head>
<body>
	<div class="cusalldiv">

		<div class="usechange">
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;"
				align="left" id="ykhxx">
				<h2>删除合同页面</h2>
			</div>
			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">

				<table class="usectable">
					<tr>
						<td class="usermatd3">根据项目号查询:</td>
						<td class="usermatd1">
							<div class="userselediv_nor">
								<input type="text" class="userselein" id="projectId"
									name="projectId" />

							</div>
						</td>
						<td class="usermatd4"><input type="button"
							onclick="searchBargin();" value="查询" class="usersearchbtn">
						</td>
					</tr>
				</table>


			</div>
			<div></div>





			<div style="border: 1px solid #BBBABA; width: 100%; margin: auto;">
				<table class="emanagergettable">

					<c:forEach items="${list}" var="cus" varStatus="i">
						<tr>
							<td style="border: 1px solid #BBBABA;">编号:<br />${cus.id} <font
								id="sign${i.count }" style="color: red;" class="cusmessa"></font>
							</td>
							<td style="border: 1px solid #BBBABA;">项目号:<br />
							<a
								href="http://117.144.21.74:33168/fukuan/examine/ceoExamine.aspx?id=${cus.projectId }"
								target=_blank>${cus.projectId }</a>
							</td>
							<td style="border: 1px solid #BBBABA;">合同号:<br />${cus.name}
							</td>
							<td style="border: 1px solid #BBBABA;">合同文件:<br />
							<a
								href="http://117.144.21.74:33168/fukuan/payment/upload/Bargain/${cus.contractDocuments}"
								target=_blank>${cus.contractDocuments}</a>
							</td>
							<td style="border: 1px solid #BBBABA;"><input type="button"
								onclick="deleteBargin1(${cus.id},'sign${i.count }');"
								value="删除合同页面"></td>
						</tr>

					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>