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

		#updateUser{
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
		<th>�û�ID</th>
		<th>�û���</th>
		<th>������ɫ</th>
		<th>����</th>
	</tr>
	<c:forEach items="${pm.list}" var="user">
		<tr>
			<td>${user.usersId}</td>
			<td>${user.username}</td>
			<td>${user.role.roleName}</td>
			<td>
				<a href="user?method=deleteUser&userId=${user.usersId}">ɾ��</a>
				<a href="javascript:void(0)" onclick="updateUser(this)">�޸�</a>
				<a href="user?method=grantUser&userId=${user.usersId}&username=${user.username}">��Ȩ</a>
			</td>
		</tr>
	</c:forEach>
</table>
<input type="button" value="��ҳ" onclick="goFirst()" id="first"/>
<input type="button" value="��һҳ" onclick="goPrev()" id="prev"/>
<input type="button" value="��һҳ" onclick="goNext()" id="next"/>
<input type="button" value="���һҳ" onclick="goLast()" id="last"/>
<div id="updateUser">
	<div class="easyui-panel" title="�޸��û�" style="width: 100%; max-width: 400px; padding: 30px 60px;">
		<form id="ff" method="post" action="category">
			<div style="margin-bottom: 20px">
				<%--<input type="hidden" name="method" value="saveCategory">
				<input class="easyui-la" name="updateName" style="width: 100%"
					   data-options="label:'�޸�Ϊ:'">--%>
				�û�����<input type="text" id="uname" width="100%">
				<br><br><br>
				���룺<input type="text" id="upwd" width="100%">
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

<script type="text/javascript">

    function updateUser(e)
    {
        USERID=$(e).parent().parent().find("td").eq(0).text();
        //alert(USERID);
        $("#updateUser").show();
    }
    //�޸��û�
    function submitForm() {
        /*$('#ff').form('submit', {
            ajax:false
        });*/
        Uname=$("#uname").val();
        Upwd=$("#upwd").val();
        alert("UID:"+USERID+"UNAME:"+Uname+"upwd:"+Upwd);
        $.ajax({
            url:'user?method=SimpleUpdateUser',
            data:{"uname":Uname,"uid":USERID,"pwd":Upwd},
            success:function(data){
                // console.log(data)
                //alert(data)
                if(data=="OK"){
                    alert("�Ѿ��޸ĳɹ���")
                    window.location.href="user?method=viewUser&currentPage="+currentPage;
                }
            }
        });
    }

    function qxForm() {
        $('#updateUser').hide();
    }
    function clearForm() {
        $('#ff').form('clear');
    }
    totalPage='${pm.totalPage}';
    currentPage='${currentPage}';
    if(currentPage==1){
        document.getElementById("first").disabled=true;
        document.getElementById("prev").disabled=true;
        document.getElementById("next").disabled=false;
        document.getElementById("last").disabled=false;
    }
    else if(currentPage==totalPage){
        document.getElementById("first").disabled=false;
        document.getElementById("prev").disabled=false;
        document.getElementById("next").disabled=true;
        document.getElementById("last").disabled=true;
    }
    function goFirst(){
        //JavaScript��θ�һ��Servelt��������
        window.location.href="user?method=viewUser&currentPage=1";
    }
    function goPrev(){
        currentPage=parseInt(currentPage)-1;
        window.location.href="user?method=viewUser&currentPage="+currentPage;
    }
    function goNext(){
        currentPage=parseInt(currentPage)+1;
        window.location.href="user?method=viewUser&currentPage="+currentPage;
    }
    function goLast(){
        window.location.href="user?method=viewUser&currentPage="+totalPage;
    }
</script>
</body>
</html>