flag=0;
function onf(id1,id2,id3){
    if (id1!=null && id2!=null && id3!=null) {
        $("#"+id1).css("border","1px solid #333");
        $("#"+id2).attr("placeholder","");
        $("#"+id3).html($("#"+id2).attr("default"));
        $("#"+id3).css("color","rgb(204,204,204)");
        $("#"+id1).attr("state","t");
    }
    else{
        $("#country").css("border","1px solid #ddd");
        $("#country").css("border-right","none");

        $("#dphone").css("border","1px solid #333");
        $("#phone").attr("placeholder","");
        
        $("#phone-tip").html($("#phone").attr("default"));
        $("#phone-tip").css("color","rgb(204,204,204)");

        flag=1;
    }
    
}
function onb(id1,id2,id3){
    if (id1!=null && id2!=null) {
        if(id2=="form-mail" && $("#form-mail").val()==""){
            $("#checkmail").show();
            $("#checkmailform").hide();
            $("#ecode").val("");
            $("#ecode-tip").html("");
            $("#remail").hide();
            $("#rem").hide();
            $("#"+id1).attr("state","f");
        }
        $("#"+id1).css("border","1px solid #ddd");
        $("#"+id1).attr("state","f");
        $("#"+id2).attr("placeholder",$("#"+id2).attr("defplaceholder"));
        $("#"+id3).html("");
        if ($("#"+id2).val()=="") { 
        }
        else if (check(id2)==0) {
        	if (flag3==0) {
                $("#"+id3).html("<img src='img/icon2.png'>&nbsp;格式错误");
                $("#"+id3).css("color","#f91");
            }
        }else if(id2=="form-equalTopwd"){
            equalTopwd();
            if(equalTopwd()==0){
                $("#form-equalTopwd-cancel").show();
                $("#form-equalTopwd-status").hide();
            }
        }else if(id2=="form-account" && $("#form-account").attr("state")=="f"){
        	$("#form-account-tip").html("<img src='img/icon2.png'>&nbsp;用户名已注册");
            $("#form-account-tip").css("color","#f91");
        }
    }else{
        dphone.style.cssText="border:1px solid #ddd;"
        phone.placeholder="建议使用常用手机号"
        flag=0;
        if($("#phone").val()=="")
        {
            $("#phone-tip").html("");
        }else if (check("phone")==0) {
        	if(flag3==0){
                $("#phone-tip").html("<img src='img/icon2.png'>&nbsp;格式错误");
                $("#phone-tip").css("color","#f91");
            }
            $("#check").show();
            $("#checkform").hide();
            $("#code").val("");
            $("#code-tip").html("");
            $("#recode").hide();
            $("#rec").hide();
        }else{
            $("#phone-tip").html("");
        }
    }
}
function over(name){
    if(name!=null){
        $("#"+name).css("border","1px solid #333");
    }else{
        if(flag==0){
            country.style.cssText="border:1px solid #333;border-right:none;"
        }
        dphone.style.cssText="border:1px solid #333;"
    }
    flag3=1;
}
function out(name){
    if(name!=null && $("#"+name).attr("state")=="f"){
        $("#"+name).css("border","1px solid #ddd");
    }else{
        if(flag==1){
        country.style.cssText="border:1px solid #ddd;border-right:none;"
        }else{
            country.style.cssText="border:1px solid #ddd;border-right:none;"
            dphone.style.cssText="border:1px solid #ddd;"
        }
    }
    flag3=0;
}
function oni(id){
    if(check(id)==1){
    	if(id=="form-account"){
    		$.ajax({
                url: 'customer?method=checkAccount&account=' + $("#form-account").val(),
                dataType: 'text',
                method: "GET",
                success: function (data) {
                	if(data=="OK"){
                		$("#form-account").attr("state","t");
                		$("#"+id+"-cancel").hide();
                        $("#"+id+"-status").show();
                        $("#form-account-tip").html("");
                	}else{
                		$("#form-account-tip").html("<img src='img/icon2.png'>&nbsp;用户名已注册");
                        $("#form-account-tip").css("color","#f91");
                		$("#"+id+"-cancel").show();
                        $("#"+id+"-status").hide();
                	}
                }
            });
    	}else{
    		$("#"+id+"-cancel").hide();
            $("#"+id+"-status").show();
    	}
    }else{
        $("#"+id+"-cancel").show();
        $("#"+id+"-status").hide();
    }
}
function cancel(id){
	if(id=="phone"){
        $("#code-tip").html("");
        $("#code").val("");
        $("#recode").hide();
        $("#rec").hide();
    }
	if(id=="form-mail"){
        $("#ecode-tip").html("");
        $("#ecode").val("");
        $("#remail").hide();
        $("#checkmailform").hide();
        $("#rem").hide();
        $("#checkmail").show();
    }
    $("#"+id).val("");
    $("#"+id+"-tip").html("");
    $("#"+id+"-cancel").hide();
}
function check(id){
    reg=null;
    phonereg=/^[1][3,4,5,7,8][0-9]{9}$/;
    codereg=/^[A-Za-z0-9]{6,6}/;
    accountreg=/(?=^[\u4e00-\u9fa5a-zA-Z0-9\-_]{3,20}$)\w*[\u4e00-\u9fa5a-zA-Z-_]+\w*/;
    pwdreg=/^[\u4e00-\u9fa5a-zA-Z0-9]{6,20}$/;
    mailreg=/\w+@\w{2,}\.[com|net|org]{3,}/i;
    if(id=="phone"){
        reg=phonereg;
    }
    else if(id=="code" || id=="ecode"){
        reg=codereg;
    }
    else if(id=="form-account"){
        reg=accountreg;
    }
    else if(id=="form-pwd" || id=="form-equalTopwd"){
        reg=pwdreg;
    }
    else if (id=="form-mail") {
        reg=mailreg;
    }
    else{
        return 1;
    }
    if ($("#"+id).val().match(reg)!=null) {
        return 1;
    }else{
        return 0;
    }
}
function equalTopwd(){
	if ($("#form-pwd").val()!=$("#form-equalTopwd").val()) {
        $("#form-equalTopwd-tip").html("<img src='img/icon2.png'>&nbsp;两次输入的密码不一致");
        $("#form-equalTopwd-tip").css("color","#f91");
        return 0;
    }else{
        return 1;
    }
}