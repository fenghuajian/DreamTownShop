/**
 *
 */
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

function addProduct1(a){
    var productId=$(a).parent().parent().parent().find("tr").eq(0).text();
    var name=$(a).parent().parent().parent().find("tr").eq(5).text();
    var info=$(a).parent().parent().parent().find("tr").eq(7).text();
    var pic=$(a).parent().parent().parent().find("tr").eq(1).text();
    var price=  $(a).parent().parent().parent().find("tr").eq(9).text();
    $("#top-product .p-img-1 .p-name a").text(name);
    $("#top-product .p-img-1 .p-name a").attr('title',name);
    $("#top-product .p-img-1 .p-extra span").eq(0).text(info);
    $("#spjg").text("/价格："+price);
    $("#top-product .p-img img").attr('src',"img/"+pic);
   // alert(pic);
   $.ajax({
         type:"POST",
         url:"product?method=addCar1",
         data:{"productId":productId },
         success:function(data){
             //console.log(data);
             if(data =="ok") {
             	//alert(price);
                 //$("#top-product .p-img-1 .p-extra span").eq(1).text("/数量："+data);
			 }

         }
     })
    /* $.ajax("product?method=addCar",{ "productId": productId}, function (data) {

         if(data=="OK") window.location.href="success.html?productid="+productId;
     })*/
}
$(document).ready(function() {
    var url = window.location.href;

    productid=url.split("?")[1].split("=")[1];
    //alert(productid);
  $.getJSON("product?method=showProduct&productid="+productid,function(data){
		//alert(data.price)
        $("#top-product .p-img-1 .p-name a").text(data.name);
        $("#top-product .p-img-1 .p-name a").attr('title',data.name);
        $("#top-product .p-img-1 .p-extra span").eq(0).text(data.descInfo);
     // $("#top-product .p-img-1 .p-extra span").eq(0).text(data.price);
        $("#spjg").text("/价格："+data.price);
      //  $("#top-product .p-img-1 .p-extra span").eq(1).text("/数量："+num);
        $("#top-product .p-img img").attr('src',"img/"+data.picURL);


    });
    $("#top-product").show();

    $.getJSON("product?method=showOtherproduct&productid="+productid,function(data){
        /*i=1;j=1;
         $.each(data,function(index,obj){
             changepro(i,j,obj);
            j++;
            console.log(i);
            if(j == 9){
                i++;
                j=1;
                if(i == 5)
                    i=1;
                     success = true;
            };
         });

        */
        $.each(data, function (index, obj) {
            //alert(obj.productId);
			productid1=obj.productId;
            if(obj.productId!=null)
            {
                var table = $("<table>").attr("class","table123")
                    .append($("<tr>").text(obj.productId).hide())
                    .append($("<tr>").text(obj.picURL).hide())
                    .append($("<tr>").attr("height","5px"))
                    .append($("<tr>").html('<img width="100" height="100" src="img/'+obj.picURL+'"/>'))


                    .append($("<tr>").attr("height","5px"))
                    .append($("<tr>").text(obj.name).css("font-size","14px").css("color","#666"))
                    .append($("<tr>").attr("height","5px"))
                    //.append($("<tr>").text(obj.descInfo).css("font-size","14px").css("color","#666"))
                    .append($("<tr>").html("<p style='width:220px;'>"+obj.descInfo+"</p>").css("font-size","14px").css("color","#666"))
                    .append($("<tr>").attr("height","5px"))
                    .append($("<tr>").text('￥'+(obj.price)).attr("class","num11"))
                    .append($("<tr>").attr("height","5px"))

                var tr1=$("<tr>").html('<div class="jia11"> <a href="javascript:void(0) text-decoration:none" onclick="addProduct1(this)">加入购物车</a></div>');

                var tr3=$("<tr>").html('<div style="height:5px"></div>');

                table.append(tr1);
                table.append(tr3);
                $("#m-1").append(table);
            }
        })
        $("#m-1").show();
    });


    $.getJSON("product?method=showOtherproduct&productid=c6469b1b5bc747209838ed6325023d5d",function(data){
        i=5;j=1;
        $.each(data,function(index,obj){
            changepro(i,j,obj);
            j++;
            console.log(i);
            if(j == 9){
                i++;
                j=1;
                if(i == 9)

                    success = true;
            };
        });

        $("#m-2").show();
    });
    $.getJson("order?method=viewOrder",function(data){
        $("#che2").text(data.lengh());

    });
});
function changepro(b,c,e){
    //$("#"+b+"pn"+c).find("a").text("");
    //alert(e.name);
    $("#"+b+"pn"+c).find("a").eq(0).text(e.name);
    $("#"+b+"pn"+c).find("a").eq(1).text("");
    $("#"+b+"pb"+c).find("a").attr('href',"product?method=addCar1&productid="+e.productId);
    $("#"+b+"pi"+c).find("img").attr('src',"img/"+e.picURL);
    $("#"+b+"pi"+c).find("a").attr('href',"");
    $("#"+b+"pn"+c).find("a").attr('title',e.name);
    $("#"+b+"pr"+c).find("i").text(e.price);
    $("#"+b+"pi"+c).parent().show();
//
}
function pro(a){

    if(a<5){
        for(i=1;i<5;i++){
            $("#pro"+i).hide();
            $("#pli"+i).attr('class','s-nav-item');

        }
        $("#pli"+a).attr('class','s-nav-item curr');
        $("#pro"+a).show();

    }
    else{
        for(i=5;i<9;i++){
            $("#pro"+i).hide();
            $("#pli"+i).attr('class','s-nav-item');

        }
        $("#pli"+a).attr('class','s-nav-item curr');
        $("#pro"+a).show();
    }
};