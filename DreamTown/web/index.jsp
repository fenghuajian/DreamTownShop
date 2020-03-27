<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>后台管理主页</title>
  <style type="text/css">
    #outer {
      width: 100%;
      height: 900px;
    }

    #north {
      width: 100%;
      height: 10%;
      background-color: rgb(179, 213, 228);
    }

    #west {
      width: 10%;
      height: 80%;
      background-color: rgb(169, 250, 205);
      float: left;
    }

    #center {
      width: 90%;
      height: 80%;
      background-color: rgb(232, 241, 255);
      float: left;
    }

    #south {
      width: 100%;
      height: 10%;
      background-color: rgb(51, 51, 51);
      clear: left;
    }
  </style>
  <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css"
        type="text/css">
  <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="js/jquery.ztree.core.js"></script>
  <script type="text/javascript">
      function zTreeOnClick(event, treeId, treeNode) {
          if(treeNode.isParent){//如果是父菜单，则展开expand
              var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
              treeObj.expandNode(treeNode);
          }
      };
      var setting = {
          data : {
              simpleData : {
                  enable : true,
                  idKey : "permissionid",//id大小写敏感
                  pIdKey : "pid"//pid大小写敏感
              }
          },
          callback : {
              onClick : zTreeOnClick
          }
      };

      username = '${user.username}';//session中取
      shopname = '${shop.shopname}';//session中取
      console.log(username);
      console.log(shopname);
      $(document).ready(function() {

          $.ajax({
              async : false,//发送同步请求：此代码块执行完毕，才能往下执行
              url : "user?method=getMenu&username=" + username,
              method : "GET",
              dataType : "json",
              success : function(data) {
                  zNodes = data;//全局变量
              }
          });
          $.fn.zTree.init($("#treeDemo"), setting, zNodes);
      });
  </script>
</head>
<body>
<div id="outer">
  <div id="north"></div>
  <div id="west">
    <div class="zTreeDemoBackground left">
      <ul id="treeDemo" class="ztree"></ul>
    </div>
  </div>
  <div id="center">
    <iframe src="" style="width: 100%; height: 100%;" name="center"></iframe>
  </div>
  <div id="south"></div>
</div>
</body>
</html>