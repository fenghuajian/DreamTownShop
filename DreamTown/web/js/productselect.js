function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

$(function () {
    var searchUrl =window.location.href;
    var searchData =searchUrl.split("=");        //截取 url中的“=”,获得“=”后面的参数
      name2 =decodeURI(searchData[1]);//name2是为了在网址传值，name1是为了传给后台

    name1=GetQueryString("search")
    console.log(name1);
    //alert(name2);

    $.ajax({
        type:"POST",
        dataType:"json",
        url:"product?method=viewProduct1",
        data:{ "currentPage": 1,"categoryId":name1 },
        success:function(data){
            //console.log(data);
            // alert(data);
            xs(data);
        }
    })

    $.ajax({
        type:"POST",
        dataType:"json",
      //  url:"product?method=viewProduct2",
       // data:{ "currentPage": 1,"categoryId":name1 },
        url:"product?method=viewProductRemai",
        data:{ "categoryId":name1 },
        success:function(dataremai){
           xsrm(dataremai);
            //alert(dataremai)
        }
    })
})
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

           console.log("customerid:"+data.customerId)
            console.log("name:"+customer.username)
           //console.log(data)
        }
    })
})



function xsrm(dataremai) {
    //list=data["list"];
    //alert(list)
    $.each(dataremai, function (index, obj) {
        var tablerm = $("<table>").attr("class", "table1ReMai")
            .append($("<tr>").text(obj.productId).hide())
            .append($("<tr>").attr("height", "2px"))
            .append($("<tr>").html('<img width="120" height="120"  src="img/' + obj.picURL + '"/>'))
            .append($("<tr>").attr("height", "2px"))
            .append($("<tr>").text('￥' + (obj.price)).attr("class", "num12"))
            .append($("<tr>").attr("height", "2px"))
            .append($("<tr>").text(obj.name).css("font-size", "9px").css("color", "#666"))
            .append($("<tr>").attr("height", "2px"))
            //.append($("<tr>").text(obj.descInfo).css("font-size","14px").css("color","#666"))
            .append($("<tr>").html("<p style='width:220px;'>" + obj.descInfo + "</p>").css("font-size", "9px").css("color", "#666"))
            .append($("<tr>").attr("height", "2px"))
            .append($("<tr>").text('已有999+人评价').css("font-size", "9px").css("color", "#A7A7A7"))
            .append($("<tr>").attr("height", "2px"))
            .append($("<tr>").text(obj.shopname).css("font-size", "9px").css("color", "#999999"))
            .append($("<tr>").attr("height", "2px"))
        var tr1 = $("<tr>").html('<div class="addcar"> <a  href="javascript:void(0) text-decoration:none;" onclick="addProduct(this)">加入购物车</a></div>');
        var tr2 = $("<tr>").html('<div class="collect"> <a  href="javascript:void(0) text-decoration:none;" onclick="collect(this)">加入收藏</a></div>');
        var tr3 = $("<tr>").html('<div style="height:5px"></div>');
        tablerm.append(tr2);
        tablerm.append(tr3);
        tablerm.append(tr1);
        $("#remai").append(tablerm);
    })
}
function xs(data) {
    currentPage=data["currentPage"];
    totalPage = data["totalPage"];
    // totalPage1=totalPage;
    list=data["list"];
    $("#shuzi").html(currentPage+"/"+totalPage);
    console.log("cu:"+currentPage);
    console.log("to:"+totalPage);
    console.log("list:"+list);
    disabled();

    $(".table123").remove();
    console.log("object1:");

    $.each(data["list"], function (index, obj) {
        //  console.log("object2:");
        if(!(obj.name== '')){
            console.log("object:"+obj);
            var table = $("<table>").attr("class","table123")
                .append($("<tr>").text(obj.productId).hide())
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").html('<img width="220" height="220"  src="img/'+obj.picURL+'"/>'))
                .append($("<tr>").attr("height","5px"))

                .append($("<tr>").text('￥'+(obj.price)).attr("class","num11"))
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").text(obj.name).css("font-size","14px").css("color","#666"))
                .append($("<tr>").attr("height","5px"))
                //.append($("<tr>").text(obj.descInfo).css("font-size","14px").css("color","#666"))
                .append($("<tr>").html("<p style='width:220px;'>"+obj.descInfo+"</p>").css("font-size","14px").css("color","#666"))
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").text('已有100+人评价').css("font-size","14px").css("color","#A7A7A7"))
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").text(obj.shopname).css("font-size","14px").css("color","#999999"))
                .append($("<tr>").attr("height","5px"))




            var tr1=$("<tr>").html('<div class="jia11"> <a  href="javascript:void(0) text-decoration:none;" onclick="addProduct(this)">加入购物车</a></div>');
            var tr2=$("<tr>").html('<div class="collect"> <a  href="javascript:void(0) text-decoration:none;" onclick="collect(this)">加入收藏</a></div>');
            var tr3=$("<tr>").html('<div style="height:5px"></div>');
            table.append(tr2);
            table.append(tr3);
            table.append(tr1);
            $("#center11").append(table);
            //console.log(productId);
        }

    });
}

/*
* 收藏
* */

function collect(a) {
    var productId = $(a).parent().parent().parent().find("tr:first").text();
   // alert(productId);
    // alert("确定收藏吗")

    var productId = $(a).parent().parent().parent().find("tr:first").text();
    // alert(productId);
    if (customerid == null)
    {
        alert("您好，请您先登录，谢谢！");
       // alert(name1);
        //var name1= jQuery.trim(name1);
        var searchUrl =encodeURI("customerLogin.html?search="+name2);   //使用encodeURI编码
        window.location.href =searchUrl;
    }
    else
    {
        alert("您已收藏成功！");
       // alert("productid:"+productId+"........"+"customerid:"+customerid);
        $.ajax({
            type:"POST",
            url:"product?method=addCollection",
            data:{"productid":productId},
            success:function(data){
                //console.log(data);
                if(data=="OK")console.log("收藏成功");
                else{
                    console.log("已经收藏过");
                }
            }
        })
    }


}

/*下面·代码多余*/
/*  $(document).ready(function () {
      //对后台发送请求
      //获取json数据
      var url = window.location.href;
      categoryId = url.split("?")[1].split("=")[1];
      //catecategoryId=document.getAttribute(catecategoryId);
      //console.log(categoryId);


      $.getJSON("product?method=viewProduct", { "currentPage": 1,"categoryId":categoryId }, function (data) {
          cz(data);
      });

  });*/

function cz(data){

    currentPage=data["currentPage"];
    totalPage = data["totalPage"];
    disabled();

    $(".table123").remove();

    $.each(data["list"], function (index, obj) {
        if(obj.categoryId== categoryId){
            var table = $("<table>").attr("class","table123")
                .append($("<tr>").text(obj.productId).hide())
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").html('<img width="220" height="220" src="http://localhost:8080/JdShop/upload/'+obj.picURL+'"/>'))
                .append($("<tr>").attr("height","5px"))

                .append($("<tr>").text('￥'+(obj.price)).attr("class","num11"))
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").text(obj.name).css("font-size","14px").css("color","#666"))
                .append($("<tr>").attr("height","5px"))
                //.append($("<tr>").text(obj.descInfo).css("font-size","14px").css("color","#666"))
                .append($("<tr>").html("<p style='width:220px;'>"+obj.descInfo+"</p>").css("font-size","14px").css("color","#666"))
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").text('已有0人评价').css("font-size","14px").css("color","#A7A7A7"))
                .append($("<tr>").attr("height","5px"))
                .append($("<tr>").text(obj.shopname).css("font-size","14px").css("color","#999999"))
                .append($("<tr>").attr("height","5px"))




            var tr1=$("<tr>").html('<div class="jia11"> <a  href="javascript:void(0) text-decoration:none;" onclick="addProduct(this)">加入购物车</a></div>');
            var tr2=$("<tr>").html('<div class="send111" style="width: 35px;background-color: red;text-align: center;"> <a style="color: white;font-size: 12px;text-decoration: none;" >自营</a></div>');
            var tr3=$("<tr>").html('<div style="height:5px"></div>');
            table.append(tr2);
            table.append(tr3);
            table.append(tr1);
            $("#center11").append(table);
            //console.log(productId);
        }

    });
}

$(document).ready(function () {
    $("#first").click(function () {
        //对后台发送请求

        $.ajax({
            type:"POST",
            dataType:"json",
            url:"product?method=viewProduct1",
            data:{ "currentPage": 1,"categoryId":name1 },
            success:function(data){
                //console.log(data);
                //alert(data);
                xs(data);
            }
        })
    });

});



$(document).ready(function () {
    $("#next").click(function () {
        //对后台发送请求
        currentPage=parseInt(currentPage)+1;
        if(currentPage<=totalPage){
            $.ajax({
                type:"POST",
                dataType:"json",
                url:"product?method=viewProduct1",
                data:{ "currentPage": currentPage,"categoryId":name1 },
                success:function(data){
                    //console.log(data);
                    //alert(data);
                    xs(data);
                }
            })
        }
        else {
            $.ajax({
                type:"POST",
                dataType:"json",
                url:"product?method=viewProduct1",
                data:{ "currentPage": totalPage,"categoryId":name1 },
                success:function(data){
                    //console.log(data);
                    //alert(data);
                    xs(data);
                }
            })

        }

    });

});

$(document).ready(function () {
    $("#prev").click(function () {
        //对后台发送请求
        currentPage=parseInt(currentPage)-1;
        if(!(currentPage==0))
        {
            $.ajax({
                type:"POST",
                dataType:"json",
                url:"product?method=viewProduct1",
                data:{ "currentPage": currentPage,"categoryId":name1 },
                success:function(data){
                    //console.log(data);
                    //alert(data);
                    xs(data);
                }
            })
        }
        else
        {
            $.ajax({
                type:"POST",
                dataType:"json",
                url:"product?method=viewProduct1",
                data:{ "currentPage": 1,"categoryId":name1 },
                success:function(data){
                    //console.log(data);
                    //alert(data);
                    xs(data);
                }
            })
        }

    });

});

$(document).ready(function () {
    $("#last").click(function () {
        //对后台发送请求


        $.ajax({
            type:"POST",
            dataType:"json",
            url:"product?method=viewProduct1",
            data:{ "currentPage": totalPage,"categoryId":name1 },
            success:function(data){
                //console.log(data);
                //alert(data);
                xs(data);
            }
        })
    });

});

//把商品加入购物车
function addProduct(a){
    var productId=$(a).parent().parent().parent().find("tr:first").text();
   // alert(productId);
    $.ajax({
        type:"POST",

        url:"product?method=addCar",
        data:{"productId":productId },
        success:function(data){
            //console.log(data);
            if(data=="OK") window.location.href="success.html?productid="+productId;
            else{
                alert("您好，请您先登录，谢谢！");
                var searchUrl =encodeURI("customerLogin.html?search="+name2);   //使用encodeURI编码
                window.location.href =searchUrl;

            }
        }
    })
    /* $.ajax("product?method=addCar",{ "productId": productId}, function (data) {

         if(data=="OK") window.location.href="success.html?productid="+productId;
     })*/
}

function disabled(){

    if(totalPage==1){
        document.getElementById("first").disabled=false;
        document.getElementById("prev").disabled=true;
        document.getElementById("next").disabled=true;
        document.getElementById("last").disabled=false;
    }
    if(currentPage<1 && currentPage==1){
        currentPage=1;
        document.getElementById("first").disabled=true;
        document.getElementById("prev").disabled=true;
        document.getElementById("next").disabled=false;
        document.getElementById("last").disabled=false;
    }

    else if( currentPage>totalPage && currentPage==totalPage){
        currentPage=totalPage;
        document.getElementById("first").disabled=false;
        document.getElementById("prev").disabled=false;
        document.getElementById("next").disabled=true;
        document.getElementById("last").disabled=true;
    }


}