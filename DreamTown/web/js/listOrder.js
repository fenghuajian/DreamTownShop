$(document).ready(function (){
	totalPage=1;
	currentPage=1;
    view(1);

    $("#cancelbt").click(function(){
    	$('#mask').fadeOut(800);
    	$('#alert').fadeOut(800);
	});
    
    $("#savebt").click(function(){
		if(isEmpty()=="1"){	
			$.ajax({
                type:"POST",
				url:"../order?method=addOrder",
				data:$("#table").serialize(),
				success: function(data) {
					if(data=="OK"){
						$('#mask').fadeOut(800);
	            		$('#alert').fadeOut(800);
	            		$("tr:not(:first)").remove();	
	        	    	view(currentPage);
	        	    	Pop.showPop(0,"修改成功",0);
	            	}else{
	            		Pop.showPop(0,"修改失败",0);
	            	}
					$(this).resetForm();
	            }      
			});
		}
    });
    
	$("#close").click(function(){
		tips_pop();
	})
});

function view(currentPage){
    $.getJSON("../order?method=listAllOrder&currentPage="+currentPage, function (data) {
        totalPage=data.totalPage;
        disabled();
        $.each(data["list"], function (index, obj) {
            var tr = $("<tr>")
            .append($("<td>").text(obj.ordersId))
            .append($("<td>").text(obj.customerId))
            .append($("<td>").text(obj.amount))
            .append($("<td>").text(obj.status))
            .append($("<td>").text(obj.buyerInfo))
            .append($("<td>").text(obj.orderDate))
            .append($("<td>").text(obj.cashInfo))
            .append($("<td>").text(obj.expressInfo));
            
            var td=$("<td>").html('</a> <a href="javascript:void(0)" onclick="modifyOrder(this)">修改</a> <a href="javascript:void(0)" onclick="deleteOrder(this)">删除</a>');
            tr.append(td);
            $("#orderTb").append(tr);
        });
    });
}

function modifyOrder(e){
    $("#ordersid").val($(e).parent().parent().find("td:first").text());
    $("#customerid").val($(e).parent().parent().find("td").eq(1).text());
    $("#amount").val($(e).parent().parent().find("td").eq(2).text());
    $("#status").val($(e).parent().parent().find("td").eq(3).text());
    $("#buyerinfo").val($(e).parent().parent().find("td").eq(4).text());
    $("#orderdate").val($(e).parent().parent().find("td").eq(5).text());
    $("#cashinfo").val($(e).parent().parent().find("td").eq(6).text());
    $("#expressinfo").val($(e).parent().parent().find("td").eq(7).text());
	//$("#table").attr("action","../product?method=modifyProduct&id="+productid);
	$('#mask').fadeIn(200);
    $('#alert').fadeIn(400);   
    
}
function deleteOrder(e){
	if(isdelete()==1){
		var id=$(e).parent().parent().find("td:first").text();
		$.ajax({
			url:'../order?method=deleteOrder',
			data:{"id":id},
			success:function(data){
				if(data=="OK"){
					view(currentPage);
				}
			}
		});
	}
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