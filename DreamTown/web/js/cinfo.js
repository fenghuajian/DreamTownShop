$(document).ready(function () {

    $.getJSON({
        url:'customer?method=getInfo',
        success:function(data){
            if(data!=""){
                $("#cid").val(data.customerId);
                $("#cname").val(data.username);
                $("#dname").val(data.defaultName);
                $("#pwd").val(data.password);
                $("#phone").val(data.phone);
                $("#dphone").val(data.defaultPhone);
                $("#mail").val(data.mailBox);
                $("#addr").val(data.defaultAddr);
            }
        }
    });
//修改信息
    $("#updateInfo").click(function () {
        alert($("#table").serialize());
        console.log($("#table").serialize());
        $.ajax({
            url:'customer?method=updateCustomer',
            data:$("#table").serialize(),
            success:function(data){
                if(data=="OK"){
                   alert("用户信息已经修改成功！");
                    window.location.reload();
                }
            }
        });
    })

})


