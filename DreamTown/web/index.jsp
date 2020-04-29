<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>后台管理主页</title>
  <style type="text/css">
    #outer {
      width: 100%;
      height: 790px;
    }

    #north {
      width: 100%;
      height: 10%;
      background-color: rgb(179, 213, 228);
    }

    #west {
      width: 15%;
      height: 80%;
      //background-color: rgb(169, 250, 205);
      background-color: rgb(215, 244, 246);
      float: left;
    }

    #center {
      width: 85%;
      height: 80%;
      background-color: rgb(232, 241, 255);
      float: left;
    }

    #south {
      width: 100%;
      height: 10%;
     // background-color: rgb(51, 51, 51);
      background-color: rgb(179, 213, 228);
      clear: left;
    }


    #webtitle{
      width: 960px;
      height: 15px;
      color: #fff;
      font-size: 30px;
      position: absolute;
      top: 2rem;
      left: 300px;
      text-align: center;



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

      var curMenu = null, zTree_Menu = null;


     /* function beforeClick(treeId, treeNode) {
          if (treeNode.level == 0 ) {
              var zTree = $.fn.zTree.getZTreeObj("treeDemo");
              zTree.expandNode(treeNode);
              return false;
          }
          return true;
      }*/
     /* var setting = {
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
      };*/



      var setting = {
          view: {
              showLine: false,
              showIcon: false,
              selectedMulti: false,
              dblClickExpand: false,
             addDiyDom: addDiyDom
          },
          data: {
              simpleData: {
                  enable: true,
                  idKey : "permissionid",//id大小写敏感
                  pIdKey : "pid"//pid大小写敏感
              }
          },
          callback: {
             //beforeClick: beforeClick,
              onClick : zTreeOnClick
          }
      };

      function addDiyDom(treeId, treeNode) {
          var spaceWidth = 5;
          var switchObj = $("#" + treeNode.tId + "_switch"),
              icoObj = $("#" + treeNode.tId + "_ico");
          switchObj.remove();
          icoObj.before(switchObj);

          if (treeNode.level > 1) {
              var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
              switchObj.before(spaceStr);
          }
      }

      username = '${user.username}';//session中取
      shopname = '${shop.shopname}';//session中取
      console.log(username);
      console.log(shopname);
     /* $(document).ready(function() {

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
      });*/

      $(document).ready(function(){
          $.ajax({
              async : false,//发送同步请求：此代码块执行完毕，才能往下执行
              url : "user?method=getMenu&username=" + username,
              method : "GET",
              dataType : "json",
              success : function(data) {
                  zNodes = data;//全局变量
              }
          });
          var treeObj = $("#treeDemo");
          $.fn.zTree.init(treeObj, setting, zNodes);
          zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
          //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
          curMenu = zTree_Menu.getNodes()[0];
          zTree_Menu.selectNode(curMenu);
          treeObj.hover(function () {
              if (!treeObj.hasClass("showIcon")) {
                  treeObj.addClass("showIcon");
              }
          }, function() {
              treeObj.removeClass("showIcon");
          });
      });
  </script>



  <style type="text/css">
    .ztree * {font-size: 10pt;font-family:"Microsoft Yahei",Verdana,Simsun,"Segoe UI Web Light","Segoe UI Light","Segoe UI Web Regular","Segoe UI","Segoe UI Symbol","Helvetica Neue",Arial}
    .ztree li ul{ margin:0; padding:0}
    .ztree li {line-height:30px;}
    .ztree li a {width:200px;height:30px;padding-top: 0px;}
    .ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
    .ztree li a span.button.switch {visibility:hidden}
    .ztree.showIcon li a span.button.switch {visibility:visible}
    .ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
    .ztree li span {line-height:30px;}
    .ztree li span.button {margin-top: -7px;}
    .ztree li span.button.switch {width: 16px;height: 16px;}

    .ztree li a.level0 span {font-size: 150%;font-weight: bold;}
    .ztree li span.button {background-image:url("./img/left_menuForOutLook.png"); *background-image:url("./img/left_menuForOutLook.gif")}
    .ztree li span.button.switch.level0 {width: 20px; height:20px}
    .ztree li span.button.switch.level1 {width: 20px; height:20px}
    .ztree li span.button.noline_open {background-position: 0 0;}
    .ztree li span.button.noline_close {background-position: -18px 0;}
    .ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
    .ztree li span.button.noline_close.level0 {background-position: -18px -18px;}
  </style>
</head>
<body>
<div id="outer">
  <div id="north">

    <div id="webtitle">商城后台管理系统</div>
  </div>
  <div id="west">
    <div class="zTreeDemoBackground left">
      <ul id="treeDemo" class="ztree"></ul>
    </div>
  </div>
  <div id="center">
    <iframe src="" style="width: 100%; height: 100%; border: medium none;" name="center"></iframe>
  </div>
  <div id="south"></div>
</div>
</body>
</html>