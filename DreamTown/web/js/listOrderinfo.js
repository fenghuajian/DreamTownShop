$(document).ready(function (){
    totalPage=1;
    currentPage=1;
    view(1);

    $("#cancelbt").click(function(){
        $('#mask').fadeOut(800);
        $('#alert').fadeOut(800);
    });



    $("#close").click(function(){
        tips_pop();
    })
});


function view(currentPage){
    emptyForm1();
    $.getJSON("../order?method=listOrderinfo&currentPage="+currentPage, function (data) {
    	console.log(data);
        totalPage=data.totalPage;
        disabled();
        $.each(data["list"], function (index, obj) {
            if(obj.status=='已支付,尚未发货')
            {
                var tr = $("<tr>")
                    .append($("<td>").html('<img width="50" height="50" src="http://localhost:8080/DreamTown/img/'+obj.pic+'"/>'))
                    .append($("<td>").text(obj.orderid))
                    .append($("<td>").text(obj.pname))
                    .append($("<td>").text(obj.price))
                    .append($("<td>").text(obj.num))
                    .append($("<td>").text((obj.price)*(obj.num)))
                    .append($("<td>").text(obj.orderdate))
                    .append($("<td>").text(obj.bname))
                    .append($("<td id='status'>").text(obj.status))

                var td=$("<td id='last'>").html(' </a><a id="yes" href="javascript:void(0)" onclick="confirmShipment(this)">确定发货</a> ' +
                    '<a href="javascript:void(0)" onclick="delete1(this)">删除记录</a>');
                tr.append(td);
                $("#order").append(tr);
            }
            else if(obj.status=='已发货'){
                var tr = $("<tr>")
                    .append($("<td>").html('<img width="50" height="50" src="http://localhost:8080/DreamTown/img/'+obj.pic+'"/>'))
                    .append($("<td id='orderid'>").text(obj.orderid))
                    .append($("<td>").text(obj.pname))
                    .append($("<td>").text(obj.price))
                    .append($("<td>").text(obj.num))
                    .append($("<td>").text((obj.price)*(obj.num)))
                    .append($("<td>").text(obj.orderdate))
                    .append($("<td>").text(obj.bname))
                    .append($("<td id='status'>").text(obj.status))
                    .append($("<td>").html(
                        '<a href="javascript:void(0)" onclick="delete1(this)">删除记录</a>'))
               /* var td=$("<td id='last'>").html(
                    '<a href="javascript:void(0)" onclick="deleteOrderinfo(this)">删除记录</a>');
                tr.append(td);*/
                $("#order").append(tr);
            }

        });
    });
}
//翻页清空前一页
function emptyForm1(){
    var table=document.getElementById("order");
    var rowNum=table.rows.length;
    for (i=1;i<rowNum;i++)
    {
        table.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
}
//确定发货
function confirmShipment(e){

    var orderid=$(e).parent().parent().find("td").eq(1).text();
    $(e).parent().parent().find('td[id^="status"]').html("已发货");
    $(e).hide();
   /*alert(orderid);*/
    $.ajax({
        type:"POST",
        url:"../order?method=updateOrderinfo",
        data:{"orderid":orderid,"status":"已发货"},
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
function delete1(e){

    var id=$(e).parent().parent().find("td").eq(1).text();

   $(e).parent().parent().hide();
    alert(id);
    $.ajax({
        type:"POST",
        url:"../order?method=deleteOrderinfo",
        data:{"id":id},
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
function isdelete(){
    var r=confirm("确定要删除该项吗？");
    if (r==true){
        return 1;
    }
    else{
        return 0;
    }
}
function emptyForm(){
    var table=document.getElementById("orderTb");
    var rowNum=table.rows.length;
    for (i=1;i<rowNum;i++)
    {
        table.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
}
function isEmpty(){
    var ele=document.getElementById("table");
    for(i=0;i<ele.length;i++){
        if(ele[i].value==''){
            if(ele[i].getAttribute("alias")!=null){
                alert(ele[i].getAttribute("alias")+"不能为空");
                return false;
            }
        }
    }
    return true;
}
function empty(){
    $("#ordersid").val("");
    $("#amount").val("");
    $("#status").val("");
    $("#buyerinfo").val("");
    $("#orderdate").val("");
    $("#cashinfo").val("");
    $("#expressinfo").val("");
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