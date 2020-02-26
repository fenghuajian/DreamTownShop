$(document).ready(function () {

    $.getJSON({
        url:'customer?method=getInfo',
        success:function(data){
           // alert(data);
            if(data!=""){
                customerid=data.customerId;
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
    //提交商店名和用户id
    $("#determine").click(function () {
        var shopname=$("#shopname").val();
        /*alert(shopname);
        alert(customerid);*/

        $.ajax({
            url:'customer?method=addShop',
            data:{"id":customerid,"name":shopname},
            success:function(data){
                if(data=="OK"){
                    $("#tishi").show();
                    $("#tishi").text("恭喜您！"+shopname+" 开店啦!");
                   //alert("恭喜您！"+shopname+" 开店啦!");
                }
            }
        });
    })

//显示信息
    $("#showinfo").click(function () {
        $("#info").show();

    })
    $("#quxiao").click(function () {
        $("#shop").hide();
        $("#info").show();

    })
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

function  registerShop() {
   //alert(customerid);
    $("#info").hide();
    $("#shop").show();

    {

        /* if(isdelete()==1){
             var id=$(e).parent().parent().find("td:first").text();
             $.ajax({
                 url:'../product?method=deleteProduct',
                 data:{"id":id},
                 success:function(data){
                     if(data=="OK"){
                         view(currentPage);
                     }
                 }
             });
         }*/
    }
}