<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>

<style type="text/css">
	table{
		margin-top: 5%;
		margin-left: 10%;
		padding: 0;


	}
	#but{
		margin-top: 1%;
		margin-left: 12%;
		padding: 0;
	}
	td a{
		text-decoration:none;
	}
	th {
		font: bold 15px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
		color: #4f6b72;
		border-right: 1px solid #C1DAD7;
		border-bottom: 1px solid #C1DAD7;
		border-top: 1px solid #C1DAD7;
		letter-spacing: 2px;
		text-transform: uppercase;
		text-align: left;
		padding: 6px 6px 6px 12px;
		background: #CAE8EA no-repeat;

		display: table-cell;
		vertical-align: inherit;

		/*text-align: -internal-center;*/
	}
	td {
		border-right: 1px solid #C1DAD7;
		border-bottom: 1px solid #C1DAD7;
		background: #fff;
		font-size:14px;
		padding: 6px 6px 6px 12px;
		color: #4f6b72;
	}
	table,tr,td{
		/*border:1px solid black;
        border-collapse:collapse;*/
	}
	/*a标签*/


	td a{
		/*display: block;*/
		text-decoration: none;
		color: #4cae4c;
		/*background-color: blue;*/
	}
	td a:hover{
		background-color: #e8dfff;
	}
	#updateRole{
		display: none;
		margin-left: 10%;
		margin-top: 5%;
		width: 400px;
		height:150px;

	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>角色ID</th>
			<th>角色名</th>
			<th>操作</th>
		</tr>
		<c:forEach var="role" items="${roles}">
			<tr>
				<td>${role.rolesId}</td>
				<td>${role.roleName}</td>
				<td>
					<a href="role?method=grantRole&roleId=${role.rolesId}&roleName=${role.roleName}">授权</a>
					<a href="role?method=deleteRoLe&roleId=${role.rolesId}">删除</a>
					<a href="javascript:void(0)" onclick="updateRole(this)">修改</a>
				</td>
			</tr>
		</c:forEach>
	</table>


	<div id="updateRole">
		<div class="easyui-panel" title="修改角色名" style="width: 100%; max-width: 400px; padding: 30px 60px;">
			<form id="ff" method="post" action="category">
				<div style="margin-bottom: 20px">

					修改为：<input type="text" id="uname" width="100%">
					<br>
				</div>
			</form>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width: 80px">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width: 80px">重置</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="qxForm()" style="width: 80px">取消</a>
			</div>
		</div>
	</div>
</body>


<script type="text/javascript">

    //点击"修改"按钮

    function updateRole(e)
    {
        Cname=$(e).parent().parent().find("td").eq(1).text();
       // alert(Cname);
        $("#updateRole").show();


    }
    //修改类别功能
    function submitForm() {
        /*$('#ff').form('submit', {
            ajax:false
        });*/
        Uname=$("#uname").val();
        alert("UNAME:"+Uname+"cNAME:"+Cname);
        $.ajax({
            url:'role?method=updateRoleName',
            data:{"uname":Uname,"cname":Cname},
            success:function(data){
                if(data=="OK")
				{
                    window.location.href="role?method=viewRole";
				}

            }
        });
    }
    function qxForm() {
        $('#updateRole').hide();
    }
    function clearForm() {
        $('#ff').form('clear');
    }


</script>
</html>