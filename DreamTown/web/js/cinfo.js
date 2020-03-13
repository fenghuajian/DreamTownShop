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

}
//显示订单
function  stream() {
    //alert(customerid);
    $("#info").hide();
    $("#shop").hide();
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
                        .append($("<td>").html('<img width="50" height="50" src="http://localhost:8080/DreamTown/img/' + obj.pic + '"/>'))
                        .append($("<td>").text(obj.orderid))
                        .append($("<td>").text(obj.pname))
                        .append($("<td>").text(obj.price))
                        .append($("<td>").text(obj.num))
                        .append($("<td>").text((obj.price) * (obj.num)))
                        .append($("<td>").text(obj.orderdate))
                        .append($("<td>").text(obj.bname))
                        .append($("<td id='status'>").text("尚未发货"))

                    var td = $("<td id='last'>").html(' </a><a id="yes" href="javascript:void(0)" onclick="confirmShipment(this)">确定收货</a> ' +
                        '<a href="javascript:void(0)" onclick="delete1(this)">删除订单</a>');
                    tr.append(td);
                    $("#order").append(tr);
                }
                else if (obj.status == '已发货' || obj.status == '已收货') {
                    var tr = $("<tr>")
                        .append($("<td>").html('<img width="50" height="50" src="http://localhost:8080/DreamTown/img/' + obj.pic + '"/>'))
                        .append($("<td id='orderid'>").text(obj.orderid))
                        .append($("<td>").text(obj.pname))
                        .append($("<td>").text(obj.price))
                        .append($("<td>").text(obj.num))
                        .append($("<td>").text((obj.price) * (obj.num)))
                        .append($("<td>").text(obj.orderdate))
                        .append($("<td>").text(obj.bname))
                        .append($("<td id='status'>").text(obj.status))
                        .append($("<td>").html(
                            '<a href="javascript:void(0)" onclick="delete1(this)">删除订单</a>'))
                    /* var td=$("<td id='last'>").html(
                         '<a href="javascript:void(0)" onclick="deleteOrderinfo(this)">删除记录</a>');
                     tr.append(td);*/
                    $("#order").append(tr);
                }
            }

        });
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
    view(currentPage);
    disabled();
}