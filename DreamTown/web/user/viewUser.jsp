<%@ page language="java" contentType="text/html; charset=GB18030"
		 pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
	<title>Insert title here</title>

	<%--<link href="css/reset1.css" rel="stylesheet" />
	<link href="css/iconfont1.css" rel="stylesheet" />
	<link href="css/index1.css" rel="stylesheet" />--%>

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

		/*按钮*/

		/*.LeftArrow, .RightArrow { background:#dddddd ;font-size: 14px; padding: 5px 10px; color: #000000; padding: 0 12px; cursor: pointer; line-height: 30px; }
		.LeftArrow { border-left: 1px solid #fff; border-right: 1px solid #fff; border-bottom: 1px solid #fff; border-top: 1px solid #fff; float: left; height: 30px; }
		.RightArrow { border-left: 0px solid #ccc; border-right: 1px solid #ccc; border-bottom: 1px solid #ccc; border-top: 1px solid #ccc; float: right; }
		.LeftArrow:hover { color: #fff; background: #fe7844; border: 1px solid #fe7844; }
		.RightArrow:hover { color: #fff; background: #fe7844; border: 1px solid #fe7844; border-left: 0; }*/
		.LeftArrow:hover { color: #fff; background: #fe7844; border: 1px solid #fe7844; }
		.LeftArrow, .RightArrow { background: #eafff8;font-size: 14px; padding: 5px 10px;  padding: 0 12px; cursor: pointer; line-height: 30px; }
		.LeftArrow { border-left: 1px solid #fff; border-right: 1px solid #fff; border-bottom: 1px solid #fff; border-top: 1px solid #fff; float: left; height: 30px; }
		#updateUser{
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
		<th>用户ID</th>
		<th>用户名</th>
		<th>所属角色</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pm.list}" var="user">
		<tr>
			<td>${user.usersId}</td>
			<td>${user.username}</td>
			<td>${user.role.roleName}</td>
			<td>
				<%--<div class="nav">
					<ul>
						<li><a href="user?method=deleteUser&userId=${user.usersId}">删除</a></li>
						<li><a href="javascript:void(0)" onclick="updateUser(this)">修改</a></li>
						<li><a href="user?method=grantUser&userId=${user.usersId}&username=${user.username}">授权</a></li>
					</ul>
				</div>--%>

					<a href="user?method=deleteUser&userId=${user.usersId}">删除</a>
					<a href="javascript:void(0)" onclick="updateUser(this)">修改</a>
					<a href="user?method=grantUser&userId=${user.usersId}&username=${user.username}">授权</a>






			</td>
		</tr>
	</c:forEach>
</table>
<div id="but">
<input  class="LeftArrow" type="button" value="首页" onclick="goFirst()" id="first"/>
<input  class="LeftArrow" type="button" value="上一页" onclick="goPrev()" id="prev"/>
<input   class="LeftArrow" type="button" value="下一页" onclick="goNext()" id="next"/>
<input   class="LeftArrow" type="button" value="最后一页" onclick="goLast()" id="last"/>
</div>
<div id="updateUser">
	<div class="easyui-panel" title="修改用户" style="width: 100%; max-width: 400px; padding: 30px 60px;">
		<form id="ff" method="post" action="category">
			<div style="margin-bottom: 20px">
				<%--<input type="hidden" name="method" value="saveCategory">
				<input class="easyui-la" name="updateName" style="width: 100%"
					   data-options="label:'修改为:'">--%>
				用户名：<input type="text" id="uname" width="100%">
				<br><br><br>
				密码：<input type="text" id="upwd" width="100%">
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

<script type="text/javascript">

    function updateUser(e)
    {
        USERID=$(e).parent().parent().find("td").eq(0).text();
        //alert(USERID);
        $("#updateUser").show();
    }
    //修改用户
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
                    alert("已经修改成功！")
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
        //JavaScript如何给一个Servelt发送请求
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