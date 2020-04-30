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
			margin-left: 10%;
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
		/*a��ǩ*/


		td a{
			/*display: block;*/
			text-decoration: none;
			color: #4cae4c;
			/*background-color: blue;*/
		}
		td a:hover{
			background-color: #e8dfff;
		}
		.LeftArrow:hover { color: #fff; background: #fe7844; border: 1px solid #fe7844; }
		.LeftArrow, .RightArrow { background: #eafff8;font-size: 14px; padding: 5px 10px;  padding: 0 12px; cursor: pointer; line-height: 30px; }
		.LeftArrow { border-left: 1px solid #fff; border-right: 1px solid #fff; border-bottom: 1px solid #fff; border-top: 1px solid #fff; float: left; height: 30px; }
		#updateCate{
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
		<th>��Ʒ���ID</th>
		<th>�������</th>
		<th>����</th>>
	</tr>
	<c:forEach items="${pm.list}" var="category">
		<tr>
			<td>${category.categoryid}</td>
			<td>${category.name}</td>

			<td>
				<a href="category?method=deleteCategory&categoryId=${category.categoryid}">ɾ��</a>
				<a href="javascript:void(0)" onclick="updateCategory(this)">�޸�</a>

			</td>
		</tr>
	</c:forEach>
</table>
<div id="but">
<input class="LeftArrow" type="button" value="��ҳ" onclick="goFirst()" id="first"/>
<input class="LeftArrow" type="button" value="��һҳ" onclick="goPrev()" id="prev"/>
<input class="LeftArrow" type="button" value="��һҳ" onclick="goNext()" id="next"/>
<input class="LeftArrow" type="button" value="���һҳ" onclick="goLast()" id="last"/>
</div>

<div id="updateCate">
	<div class="easyui-panel" title="�޸���Ʒ���" style="width: 100%; max-width: 400px; padding: 30px 60px;">
		<form id="ff" method="post" action="category">
			<div style="margin-bottom: 20px">
				<input type="hidden" name="method" value="saveCategory">
				<%--<input class="easyui-la" name="updateName" style="width: 100%"
					   data-options="label:'�޸�Ϊ:'">--%>
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
<script type="text/javascript">

	//���"�޸�"��ť

	function updateCategory(e)
	{
         Cname=$(e).parent().parent().find("td").eq(1).text();
	    //alert(name);
		$("#updateCate").show();


	}
	//�޸������
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
                    alert("�Ѿ��޸ĳɹ���")
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
        //JavaScript��θ�һ��Servelt��������
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