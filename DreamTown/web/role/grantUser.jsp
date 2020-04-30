<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck.js"></script>
	<style type="text/css">
		#out{
			margin-left: 10%;
		}
		.LeftArrow:hover { color: #fff; background: #fe7844; border: 1px solid #fe7844; }
		.LeftArrow, .RightArrow { background: #a7c9ff;font-size: 14px; padding: 5px 10px;  padding: 0 12px; cursor: pointer; line-height: 30px; }
		.LeftArrow { border-left: 1px solid #fff; border-right: 1px solid #fff; border-bottom: 1px solid #fff; border-top: 1px solid #fff; float: left; height: 30px; }
	</style>
	<script type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true,
					idKey : "permissionid",//id��Сд����
					pIdKey : "pid"//pid��Сд����
				}
			}
		};
		
		$(document).ready(function(){
			//����Ajax���󣬻�ȡ����Permission������
			roleId='${roleId}';
			$.ajax({
				url:"permission?method=getAllPermission&roleId="+roleId,
				async:false,
				dataType:"json",
				success:function(data){
					zNodes=data;
				}
			});
			/*
			ajax����Ĭ�Ϸ��͵��ǡ��첽��������ʱ����Ҫ�ı�Ĭ������ʽ����Ϊ��ͬ��������
			*/
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		function grant(){
			var permissionIds=new Array();//�洢ѡ�е�Ȩ�޵�permissionid
			
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			//console.log($.isArray(nodes));
			//console.log(nodes.constructor.toString().match(/array/i));
			for(var i=0;i<nodes.length;i++){
				permissionIds.push(nodes[i].permissionid);
			}
			/*console.log("��ɫId��"+roleId);
			for(var i=0;i<permissionIds.length;i++){
				console.log("Ȩ��ID��"+permissionIds[i]);
			}
			*/
			$.ajax({
				url:'role?method=saveGrant',
				data:{"roleId":roleId,"permissionIds":permissionIds},
				dataType:"text",
				success:function(data){
					alert(data);
				}
			});
		}
	</script>
</head>
<body>
	<div id="out">
	����Ȩ�Ľ�ɫ��${roleName}
	<div>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
	<input class="LeftArrow" type="button" value="��Ȩ" onclick="grant()"/>
	</div>
</body>
</html>