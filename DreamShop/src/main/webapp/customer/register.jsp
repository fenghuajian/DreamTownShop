<%@page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
	String path=config.getServletContext().getContextPath();
%>
<html>
    <head>
        <title>个人注册</title>
        <link rel="stylesheet" type="text/css" href="./css/register.css">
        <meta http-equiv="Content-Type" content="text/html; charset=GBK" /> 
        <script type="text/javascript" src="http://localhost:8080<%=path %>/jquery-3.3.1.js"></script>
        <script type="text/javascript">
        	$(document).ready(function(){
        		$("#check").on("click",function(){
        			/*
        			对后台的Servlet发送请求
        			(1)调用sendVerifyCode方法
        			(2)获取手机号
        			*/
        			//取文本框的值
        			$.ajax({
        				url:'http://localhost:8080<%=path %>/customer?method=sendVerifyCode&phone='+$("#key").val(),
        				method:"GET",
        				dataType:'text',
        				success:function(){
        				}
        			});
        		});
        	});
        </script>
    </head>
    <body>
        <div class="header">
            <div class="logo-con w clearfix">
                <a href="" class="logo "><img src="./images/JDlogo.jpg" alt="京东图标" width="160px" height="90px" id="JDlogo">
                </a>
                <div id="logo-title">欢迎注册</div>
    
                <div id="have-account" style="color:rgb(153, 153, 153)">已有账号？ <a href="" style="color:red;text-decoration: none;">请登录&gt;</a></div>
   
            </div>
        </div>
        
        <div id="cheek">
        </div>
    
        <div id="center">
            <img src="./images/tips.PNG" alt="流程" id="tips" >
            <div id="word">
                <ul id="word">
                    <li id="word1">验证手机号</li>
                    <li id="word2">填写账号信息</li>
                    <li id="word3">注册成功</li>
                </ul>
            </div>
            <div id="number">
                <input id="key" type="text" class="itxt"  style="color: rgb(153, 153, 153);width: 290px;height: 50px;">
                <input id="local" type="button" class="button" value=" 中国 0086" style="width:80px; height:50px;">
                <input id="check" type="button" class="itxt" value="点击按钮进行验证" style="color: black;width: 370px;height: 50px;">
                <input id="next" type="button" class="itxt" value="下一步" style="color: white;width: 370px;height: 50px;">
                <li id="user1"><a href="" style="text-decoration: none;">企业用户注册</a></li>
                <li id="user2"><a href="" style="text-decoration: none;">海外用户注册</a></li>
            </div>
        </div>
    </body>
</html>