<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>后台管理主页</title>
	<link rel="stylesheet" href="css/demo.css" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core.js"></script>
	<script type="text/javascript">
		var setting = {
			data: {
				simpleData: {
					enable: true,
					idKey: "permissionid",
					pIdKey: "pid"
				}
			}
		};
		/*var zNodes = [//真实数据
			{ "isParent": "true", "name": "商城后台管理", "permissionid": "e655e297c3a34569b46b455f3885477f" },
			{ "isParent": "false", "name": "查看角色", "permissionid": "03093bb14519489bba2d171853766cc5", "pid": "bd6c942889b14db692d4574b616ea22a", "url": "http://www.baidu.com", target: "right" },
			{ "isParent": "false", "name": "添加角色", "permissionid": "2698f7c8e855400ea64adbd15776b7aa", "pid": "bd6c942889b14db692d4574b616ea22a", "url": "http://www.bing.com", target: "right" },
			{ "isParent": "true", "name": "权限管理", "permissionid": "5fa99265d0a4495dbeac13bd1a0efe8d", "pid": "e655e297c3a34569b46b455f3885477f" },
			{ "isParent": "false", "name": "查看用户", "permissionid": "9b8b82ea9c0c4fcd85667e90df846770", "pid": "9a1ec10b285646f580d47780b4e5896c", "url": "viewUser.do" },
			{ "isParent": "true", "name": "角色管理", "permissionid": "bd6c942889b14db692d4574b616ea22a", "pid": "e655e297c3a34569b46b455f3885477f" },
			{ "isParent": "false", "name": "添加权限", "permissionid": "d16a9f0b13954b519d81a9dafcdc7a2d", "pid": "5fa99265d0a4495dbeac13bd1a0efe8d", "url": "addPermission.do" },
			{ "isParent": "false", "name": "添加用户", "permissionid": "05b4373c22da4eeb9762df9eea7f26bb", "pid": "9a1ec10b285646f580d47780b4e5896c", "url": "addUser.do" },
			{ "isParent": "false", "name": "查看权限", "permissionid": "85d1819ccab84720a491703f676e2c21", "pid": "5fa99265d0a4495dbeac13bd1a0efe8d", "url": "viewPermission.do" },
			{ "isParent": "true", "name": "用户管理", "permissionid": "9a1ec10b285646f580d47780b4e5896c", "pid": "e655e297c3a34569b46b455f3885477f" }
		];
		*/
		$(document).ready(function () {
			username='${user.username}';
			$.ajax({
				async:false,//发送同步请求：此代码块执行完毕，才能往下执行
				url:"getMenu.do?username="+username,
				method:"GET",
				dataType:"json",
				success:function(data){
					zNodes=data;//全局变量
				}
			});
		});
		$(document).ready(function () {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>
</head>

<body>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
</body>

</html>