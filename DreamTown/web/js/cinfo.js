

//获取用户信息
$(function () {
    customer=null;
    customerid=null;
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"customer?method=getInfo",
        success:function(data){
            customer=data;
            customerid=data.customerId;
            username=customer.username;
            $("#reg111").hide();
            $("#loginbt").text(username+",梦想小镇欢迎您！");
            $.ajax({
                type:"POST",
                data:{"customerid":customerid},
                url:"user?method=getRoleId",
                success:function(data){
                    console.log("roles:"+data)
                    //alert(data)
                    if(data=="969323d6ff9a4acf8aabc8d367474d14" || data=="d26a3b7228d14a518df62e30f9fb2df1")
                    {
                        $(".fore7").show();

                    }
                    else{
                        $(".fore8").show();
                    }

                }
            })


            console.log("customerid:"+data.customerId)
            console.log("name:"+customer.username)
            //console.log(data)
        }
    })
})

//获取角色id
$(function () {



})
//获取用户信息
$(document).ready(function () {
    totalPage=1;
    currentPage=1;
  /*  $("body").css({"color": "#666666"});*/

    $.getJSON({
        url:'customer?method=getInfo',
        success:function(data){
           // alert(data);
            if(data!=""){
                customerid=data.customerId;
                pwd=data.password;
                cname=data.username;
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


        $("#shop").hide();
        $("#collection1").hide();
        $("#stream1").hide();

    })
    $("#quxiao").click(function () {
        $("#shop").hide();
        $("#info").show();



        $("#collection1").hide();
        $("#stream1").hide();

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


    $("#collection1").hide();
    $("#stream1").hide();

}
//显示订单
function  stream() {
    //alert(customerid);
    $("#info").hide();
    $("#shop").hide();
    $("#collection1").hide();
    $("#stream1").show();
    view(1);

}

function view(currentPage){
    emptyForm();
    $.getJSON("order?method=getOrderinfo&currentPage="+currentPage+"&customerid="+customerid, function (data) {
        console.log(data);
        totalPage=data.totalPage;
       /* disabled();*/
        $.each(data["list"], function (index, obj) {

            if(obj.customerid==customerid) {
                if (obj.status == '已支付,尚未发货' ) {
                    var tr = $("<tr>")
                        .append($("<td>").html('<img width="50" height="50" src="img/' + obj.pic + '"/>'))
                        .append($("<td>").text(obj.orderid))
                        .append($("<td>").text(obj.pname))
                        .append($("<td>").text(obj.price))
                        .append($("<td>").text(obj.num))
                        .append($("<td>").text((obj.price) * (obj.num)))
                        .append($("<td>").text(obj.orderdate))
                        .append($("<td>").text(obj.bname))
                        .append($("<td id='status'>").text("尚未发货"))

                    var td = $("<td id='last'>").html(
                        '<a href="javascript:void(0)" onclick="cancel1(this)">取消订单</a>');
                    tr.append(td);
                    $("#order").append(tr);
                }
                if (obj.status == '已收货' ) {
                    var tr = $("<tr>")
                        .append($("<td>").html('<img width="50" height="50" src="http://localhost:8080/DreamTown/img/' + obj.pic + '"/>'))
                        .append($("<td>").text(obj.orderid))
                        .append($("<td>").text(obj.pname))
                        .append($("<td>").text(obj.price))
                        .append($("<td>").text(obj.num))
                        .append($("<td>").text((obj.price) * (obj.num)))
                        .append($("<td>").text(obj.orderdate))
                        .append($("<td>").text(obj.bname))
                        .append($("<td id='status'>").text(obj.status))
                        .append($("<td>").html(
                            '<a href="javascript:void(0)" onclick="delete1(this)">删除订单</a>'))
                    $("#order").append(tr);
                }
                else if (obj.status == '已发货' || obj.status == '商家已发货,并删除订单') {
                    var tr = $("<tr>")
                        .append($("<td>").html('<img width="50" height="50" src="http://localhost:8080/DreamTown/img/' + obj.pic + '"/>'))
                        .append($("<td id='orderid'>").text(obj.orderid))
                        .append($("<td>").text(obj.pname))
                        .append($("<td>").text(obj.price))
                        .append($("<td>").text(obj.num))
                        .append($("<td>").text((obj.price) * (obj.num)))
                        .append($("<td>").text(obj.orderdate))
                        .append($("<td>").text(obj.bname))
                        .append($("<td id='status'>").text("已发货"))
                        .append($("<td id='last'>").html(
                            '<a id="yes" href="javascript:void(0)" onclick="confirmShipment(this)">确定收货</a>'+
                            '<a id="d" style="display: none" href="javascript:void(0)" onclick="delete1(this)">删除记录</a>'))
                    /* var td=$("<td id='last'>").html(
                         '<a href="javascript:void(0)" onclick="deleteOrderinfo(this)">删除记录</a>');
                     tr.append(td);*/
                    $("#order").append(tr);
                }
            }

        });
    });
}
//显示收藏
function viewCollection(currentPage) {
    $("#info").hide();
    $("#shop").hide();
    $("#stream1").hide();
    $("#collection1").show();
    emptyForm();
   // console.log(customerid);
   // alert(customerid)
    $.ajax({
        type:"POST",
        url:"product?method=viewCollection",
        data:{"id":customerid,"cu":currentPage},
        success: function(data) {

                //alert("信息:"+data["list"])
                  totalPage=data.totalPage;
               // alert("to:"+totalPage);
                currentPage=data.currentPage;
               // alert("cu："+currentPage);
                $.each(data["list"],function(index,obj){
                    var tr = $("<tr>")
                        .append($("<td>").html('<img width="50" height="50" src="img/' + obj.picURL + '"/>'))
                        .append($("<td id='productid'>").text(obj.productId))
                        .append($("<td>").text(obj.shopname))
                        .append($("<td>").text(obj.name))
                        .append($("<td>").text(obj.descInfo))
                        .append($("<td>").text(obj.price))
                    .append($("<td id='last'>").html(
                        '<a id="yes" href="javascript:void(0)" onclick="addCar(this)">加入购物车</a>'+'&nbsp;&nbsp;'+
                       '<a id="d" href="javascript:void(0)" onclick="deleteCollection(this)">删除收藏</a>'))
                    $("#collection").append(tr);
                })
        }
    });
}
//把收藏加入购物车

function addCar(e) {
    alert("该商品已经加入购物车！")
    var id=$(e).parent().parent().find("td").eq(1).text();
    $.ajax({
        type: "POST",
        url: "product?method=addCar",
        data: {"productId": id, "customerId": customerid},
        success: function (data) {
            if(data=="OK")
                console.log("已经加入购物车")
        }
    })
}
//删除收藏
function deleteCollection(e) {

    var id=$(e).parent().parent().find("td").eq(1).text();
    $.ajax({
        type: "POST",
        url: "product?method=deleteCollection",
        data: {"productId": id, "customerId": customerid},
        success: function (data) {
            if(data=="OK")
            {
                viewCollection(currentPage);
            }
        }
    })
}

//取消订单

function cancel1(e){

    var id=$(e).parent().parent().find("td").eq(1).text();
    var status=$(e).parent().parent().find("td").eq(8).text();
    $(e).parent().parent().hide();
    $.ajax({
        type:"POST",
        url:"order?method=cancelOrderinfo",
        data:{"id":id},
        success: function(data) {
            if(data=="OK"){

                console.log("cancel:yes");
            }else{
                Pop.showPop(0,"删除失败",0);
            }
            $(this).resetForm();
        }
    });

}
//删除订单

function delete1(e){

    var id=$(e).parent().parent().find("td").eq(1).text();
    var status=$(e).parent().parent().find("td").eq(8).text();
    $(e).parent().parent().hide();
    var s=3;

    $.ajax({
        type:"POST",
        url:"order?method=deleteOrderinfo",
        data:{"id":id,"status":s},
        success: function(data) {
            if(data=="OK"){
                /*$('#mask').fadeOut(800);
                 $('#alert').fadeOut(800);
                 $("tr:not(:first)").remove();
                 view(currentPage);*/

                console.log("delete:yes");
            }else{
                Pop.showPop(0,"删除失败",0);
            }
            $(this).resetForm();
        }
    });

}
//表单是否为空
function emptyForm(){
    var table=document.getElementById("order");
    var rowNum=table.rows.length;
    for (i=1;i<rowNum;i++)
    {
        table.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
    var table1=document.getElementById("collection");
    var rowNum1=table1.rows.length;
    for (i=1;i<rowNum1;i++)
    {
        table1.deleteRow(i);
        rowNum1=rowNum1-1;
        i=i-1;
    }
}

function  manage() {
    /*alert("username:"+cname);
    alert(("pwd:"+pwd));*/
    $.ajax({
        url:'user?method=returnindex',
        data:{"username":cname,"password":pwd},
        success:function(data){
            if(data=="OK"){
                alert("准备跳转到后台管理系统！");
                window.location.href="index.jsp";
            }
        }
    });

}

//确定收货
function confirmShipment(e){

    var orderid=$(e).parent().parent().find("td").eq(1).text();
    $(e).parent().parent().find('td[id^="status"]').html("已收货");
    $(e).hide();
    $(e).parent().parent().find('td[id^="last"]').find('a[id^="d"]').show();
    /*alert(orderid);*/
    $.ajax({
        type:"POST",
        url:"order?method=updateOrderinfo",
        data:{"orderid":orderid,"status":"已收货"},
        success: function(data) {
            if(data=="OK"){
                /*$('#mask').fadeOut(800);
                $('#alert').fadeOut(800);
                $("tr:not(:first)").remove();
                view(currentPage);*/

                console.log("yes");
            }else{
                Pop.showPop(0,"修改失败",0);
            }
            $(this).resetForm();
        }
    });

}
function disabled(){
    if(totalPage==1){
        document.getElementById("first").disabled=true;
        document.getElementById("prev").disabled=true;
        document.getElementById("next").disabled=true;
        document.getElementById("last").disabled=true;
    }
    else if(currentPage==1){
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
    else{
        document.getElementById("first").disabled=false;
        document.getElementById("prev").disabled=false;
        document.getElementById("next").disabled=false;
        document.getElementById("last").disabled=false;
    }
}

function goFirst(){
    currentPage=1;
    turn(currentPage);
}
function goPrev(){
    currentPage=parseInt(currentPage)-1;
    turn(currentPage);
}
function goNext(){
    currentPage=parseInt(currentPage)+1;
    turn(currentPage);
}
function goLast(){
    currentPage=totalPage;
    turn(currentPage);
}
function turn(currentPage){
    if(currentPage<1) currentPage=1;
    if(currentPage>totalPage) currentPage=totalPage;
    view(currentPage);
  // viewCollection(currentPage);
    disabled();
}

function goFirst1(){
    currentPage=1;
    turn1(currentPage);
}
function goPrev1(){
    currentPage=parseInt(currentPage)-1;
    turn1(currentPage);
}
function goNext1(){
    currentPage=parseInt(currentPage)+1;
    turn1(currentPage);
}
function goLast1(){
    currentPage=totalPage;
    turn1(currentPage);
}
function turn1(currentPage){
    if(currentPage<1) currentPage=1;
    if(currentPage>totalPage) currentPage=totalPage;
    //view(currentPage);
     viewCollection(currentPage);
    disabled();
}