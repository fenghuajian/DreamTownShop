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
	table,tr,th,td{
		border:1px solid black;
		border-collapse:collapse;
	}

	#updateRole{
		display: none;
		margin-left: 500px;
		margin-top: 100px;
		width: 400px;
		height:150px;

	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>��ɫID</th>
			<th>��ɫ��</th>
			<th>����</th>
		</tr>
		<c:forEach var="role" items="${roles}">
			<tr>
				<td>${role.rolesId}</td>
				<td>${role.roleName}</td>
				<td>
					<a href="role?method=grantRole&roleId=${role.rolesId}&roleName=${role.roleName}">��Ȩ</a>
					<a href="role?method=deleteRoLe&roleId=${role.rolesId}">ɾ��</a>
					<a href="javascript:void(0)" onclick="updateRole(this)">�޸�</a>
				</td>
			</tr>
		</c:forEach>
	</table>


	<div id="updateRole">
		<div class="easyui-panel" title="�޸Ľ�ɫ��" style="width: 100%; max-width: 400px; padding: 30px 60px;">
			<form id="ff" method="post" action="category">
				<div style="margin-bottom: 20px">

					�޸�Ϊ��<input type="text" id="uname" width="100%">
					<br>
				</div>
			</form>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width: 80px">����</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width: 80px">����</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="qxForm()" style="width: 80px">ȡ��</a>
			</div>
		</div>
	</div>
</body>


<script type="text/javascript">

    //���"�޸�"��ť

    function updateRole(e)
    {
        Cname=$(e).parent().parent().find("td").eq(1).text();
       // alert(Cname);
        $("#updateRole").show();


    }
    //�޸������
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