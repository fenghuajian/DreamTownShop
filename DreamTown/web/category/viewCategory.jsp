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
		#updateCate{
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
		<th>商品类别ID</th>
		<th>类别名称</th>
		<th>操作</th>>
	</tr>
	<c:forEach items="${pm.list}" var="category">
		<tr>
			<td>${category.categoryid}</td>
			<td>${category.name}</td>

			<td>
				<a href="category?method=deleteCategory&categoryId=${category.categoryid}">删除</a>
				<a href="javascript:void(0)" onclick="updateCategory(this)">修改</a>

			</td>
		</tr>
	</c:forEach>
</table>
<input type="button" value="首页" onclick="goFirst()" id="first"/>
<input type="button" value="上一页" onclick="goPrev()" id="prev"/>
<input type="button" value="下一页" onclick="goNext()" id="next"/>
<input type="button" value="最后一页" onclick="goLast()" id="last"/>

<div id="updateCate">
	<div class="easyui-panel" title="修改商品类别" style="width: 100%; max-width: 400px; padding: 30px 60px;">
		<form id="ff" method="post" action="category">
			<div style="margin-bottom: 20px">
				<input type="hidden" name="method" value="saveCategory">
				<%--<input class="easyui-la" name="updateName" style="width: 100%"
					   data-options="label:'修改为:'">--%>
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
<script type="text/javascript">

	//点击"修改"按钮

	function updateCategory(e)
	{
         Cname=$(e).parent().parent().find("td").eq(1).text();
	    //alert(name);
		$("#updateCate").show();


	}
	//修改类别功能
    function submitForm() {
        /*$('#ff').form('submit', {
            ajax:false
        });*/
        Uname=$("#uname").val();
        //alert("UNAME:"+Uname+"cNAME:"+Cname);
        $.ajax({
            url:'category?method=updateCategory',
            data:{"uname":Uname,"cname":Cname},
            success:function(data){
               // console.log(data)
				//alert(data)
                if(data=="OK"){
                    alert("已经修改成功！")
                    goFirst();
                }
            }
        });
    }
    function qxForm() {
        $('#updateCate').hide();
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
    else if(totalPage==1){
        document.getElementById("first").disabled=true;
        document.getElementById("prev").disabled=true;
        document.getElementById("next").disabled=true;
        document.getElementById("last").disabled=true;
	}
    function goFirst(){
        //JavaScript如何给一个Servelt发送请求
        window.location.href="category?method=viewCategory&currentPage=1";
    }
    function goPrev(){
        currentPage=parseInt(currentPage)-1;
        window.location.href="category?method=viewCategory&currentPage="+currentPage;
    }
    function goNext(){
        currentPage=parseInt(currentPage)+1;
        if(currentPage>totalPage)
		{
            window.location.href="category?method=viewCategory&currentPage="+totalPage;
		}
		else{
            window.location.href="category?method=viewCategory&currentPage="+currentPage;
		}

    }
    function goLast(){
        window.location.href="category?method=viewCategory&currentPage="+totalPage;
    }

</script>
</body>
</html>