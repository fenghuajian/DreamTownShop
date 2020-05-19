　<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>

	<meta http-equiv="Content-Type"content="text/html;charset=UTF-8"/>

	<title>Insert title here</title>
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck.js"></script>
<title>Insert title here</title>
<style type="text/css">
table, tr, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

.newPermission {
	height: 200px;
	width: 400px;
	top: 40%;
	left: 25%;
	position: absolute;
	text-align: left;
	border: 1px solid black;
	display: none;
}

.modifyPermission {
	height: 400px;
	display: none;
}

</style>
<script src="js/jquery-1.4.4.min.js"></script>
<script>
	function showSaveP() {
		$(".newPermission").show();
	}
	function savePermission() {
	    //data=$('#fm').serialize();
	    data1=params = decodeURIComponent($('#fm').serialize(),true);
		$.ajax({
			type : "POST",
			url : "permission",
            data : data1,
			//data : encodeURI(encodeURI(data)),// 序列化表单值
			async : false,
			dataType:"text",
			error : function(request) {
				alert("Connection error");
			},
			success : function(data) {
				console.log(data);
				console.log(data1);
			}
		});
		$(".newPermission").hide();
	}
</script>
</head>
<body>
<button onclick="showSaveP()">新增权限</button>

<br>
<br>
<br>

	<table>
		<tr>
			<th>权限ID</th>
			<th>权限名</th>
			<th>权限图标</th>
			<th>权限图标皮肤</th>
			<th>URL</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pm.list}" var="permission">
			<tr>
				<td>${permission.permissionid}</td>
				<td>${permission.name}</td>
				<td>${permission.icon}</td>
				<td>${permission.iconSkin}</td>
				<td>${permission.url}</td>
				<td>
					<a href="javascript:void(0)" >修改</a>
				<a href="javascript:void(0)" onclick="deletePermission(this)">删除</a>
				<%--<a href="javascript:void(0)" onclick="modifyPermission(this)">修改</a>
					--%>
					<!-- 阻止默认行为 -->
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="首页" onclick="goFirst()" id="first" />
	<input type="button" value="上一页" onclick="goPrev()" id="prev" />
	<input type="button" value="下一页" onclick="goNext()" id="next" />
	<input type="button" value="最后一页" onclick="goLast()" id="last" />

	<div class="newPermission">
		<form id="fm">
			<input type="hidden" name="method" value="savePermission" /> 权限名称：
			<input
				type="text" name="pname" /> <br>
			皮肤：<input type="text" name="pSkin" /><br>
			url：<input type="text" name="purl" /> <br>
			是否是父节点： <select
				name="isParent">
				<option value="true">是</option>
				<option value="false">否</option>
			</select> <br>
			父菜单：
			<select name="pId">
				<c:forEach var="permission" items="${permissions}">
					<c:if cn.guet.test="${permission.isParent eq 'true'}">
						<option value="${permission.permissionid}">${permission.name}</option>
					</c:if>
				</c:forEach>
			</select> <br>
			<input type="button" value="保存" onclick="savePermission()" />
		</form>
	</div>

<div class="modifyPermission">
	<form id="fm1">
		<input type="hidden" name="method" value="updatePermission" />
		权限名称：
		<input
				type="text" name="pname" id="pn"/> <br>
		皮肤：<input type="text" name="pSkin" id="ps"/><br>
		url：<input type="text" name="purl" id="pu"/> <br>
		是否是父节点： <select
			name="isParent">
		<option value="true">是</option>
		<option value="false">否</option>
	</select><br>
		父菜单：
		<select name="pId">
			<c:forEach var="permission" items="${permissions}">
				<c:if cn.guet.test="${permission.isParent eq 'true'}">
					<option value="${permission.permissionid}">${permission.name}</option>
				</c:if>
			</c:forEach>
		</select>
		<input type="button" value="修改" onclick="updatePermission()" />
	</form>
</div>
	<script type="text/javascript">

        function modifyPermission(e){
            //set(e);
            permissionid=$(e).parent().parent().find("td").eq(0).text()
          //  console.log("权限id:"+productid);
           // alert(productid);
            permissionurl=$(e).parent().parent().find("td").eq(4).text()
           // alert(permissionurl);
           // alert(productid);
            permissionname=$(e).parent().parent().find("td").eq(1).text();
            permissionskin=$(e).parent().parent().find("td").eq(3).text();
           // alert(permissionname);
            $(".modifyPermission").show();
			$("#pn").val(permissionname);
			$("#ps").val(permissionskin);
            $("#pu").val(permissionurl);


           // $(".newPermission").show();
           /* $("#insertbt").hide();
            $("#savebt").show();
            $('.bg').fadeIn(200);
            $('.content').fadeIn(400);*/

        }
       function updatePermission(){
            alert($("#fm1").serialize());
           data2=params = decodeURIComponent($('#fm1').serialize(),true);
           $.ajax({
               type : "POST",
               url : "permission",
               data : data2,
               //data : encodeURI(encodeURI(data)),// 序列化表单值
               async : false,
               dataType:"text",
               error : function(request) {
                   alert("Connection error");
               },
               success : function(data) {
                   console.log(data);
                   //console.log(data1);
               }
           });
           $(".modifyPermission").hide();
           //console.log($("#fm1").serialize());
	   }

        function deletePermission(e){
            if(isdelete()==1){
                var id=$(e).parent().parent().find("td:first").text();
                alert(id);
                $.ajax({

                    url:'permission?method=deletePermission',
                    data:{"id":id},
                    dataType:"text",
                    success:function(data){
                        if(data=="OK"){
                            //view(currentPage);
                            goFirst();
                        }
                    }
                });
            }
        }
       //是否删除
        function isdelete(){
            var r=confirm("确定要删除该项吗？");
            if (r==true){
                return 1;
            }
            else{
                return 0;
            }
        }
		totalPage = '${pm.totalPage}';
		currentPage = '${currentPage}';
		if (currentPage == 1) {
			document.getElementById("first").disabled = true;
			document.getElementById("prev").disabled = true;
			document.getElementById("next").disabled = false;
			document.getElementById("last").disabled = false;
		} else if (currentPage == totalPage) {
			document.getElementById("first").disabled = false;
			document.getElementById("prev").disabled = false;
			document.getElementById("next").disabled = true;
			document.getElementById("last").disabled = true;
		}
		function goFirst() {
			//JavaScript如何给一个Servelt发送请求
			window.location.href = "permission?method=viewPermission&currentPage=1";
		}
		function goPrev() {
			currentPage = parseInt(currentPage) - 1;
			window.location.href = "permission?method=viewPermission&currentPage="
					+ currentPage;
		}
		function goNext() {
			currentPage = parseInt(currentPage) + 1;
			window.location.href = "permission?method=viewPermission&currentPage="
					+ currentPage;
		}
		function goLast() {
			window.location.href = "permission?method=viewPermission&currentPage="
					+ totalPage;
		}
	</script>
</body>
</html>