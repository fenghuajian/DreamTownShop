$(document).ready(function () {
	totalPage=1;
	currentPage=1;
    view(1);
  
	$("#cancel").click(function(){
    	$('.bg').fadeOut(800);
    	$('.content').fadeOut(800);
	});
	
	$("#insertbt").on("click", function() {
		if(isEmpty()=="1"){	
			$("#table").ajaxSubmit({
				type:"POST",
				contentType : "multipart/form-data;charset=UTF-8",
				url:"../product?method=addProduct",
				data:$("#table").serialize(),
				success: function(data) {
					if(data=="OK"){
						$('.content').fadeOut(800);
	            		$('.bg').fadeOut(800);
	        	    	view(currentPage);
	        	    	Pop.showPop(0,"添加成功",0);
	            	}else{
	            		Pop.showPop(0,"添加失败",0);
	            	}
					$(this).resetForm(); 
	            }      
			});
		}
	});
	
	$("#savebt").click(function(){
		if(isEmpty()=="1"){
            console.log($("#table").serialize());
			$("#table").ajaxSubmit({
				type:"POST",
				contentType : "multipart/form-data;charset=UTF-8",
				url:"../product?method=addProduct",
				data:$("#table").serialize(),
               // async:false,
                success:function (json) {
				    var str = json;
                    if(json=="OK"){
                        $('.content').fadeOut(800);
                        $('.bg').fadeOut(800);
                        view(currentPage);
                        Pop.showPop(0,"修改成功",0);
                    }
                    else{
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
//显示列表
function view(currentPage){
	emptyForm();
	/*$.getJSON("../product?method=listProduct&currentPage="+currentPage, function (data) {*/
    $.getJSON("../product?method=listProduct&currentPage="+currentPage, function (data) {
		totalPage=data.totalPage;
		disabled();
        $.each(data["list"], function (index, obj) {
            var tr = $("<tr>")
            .append($("<td>").text(obj.productId))
            .append($("<td>").text(obj.categoryId)) //getOneCategory(obj.categoryId)
            .append($("<td>").text(obj.name))
            .append($("<td>").text(obj.price))
            .append($("<td>").text(obj.onlineDate))
            .append($("<td>").text(obj.descInfo))
           .append($("<td>").html('<img width="50" height="50" src="http://localhost:8080/Dream/img/'+obj.picURL+'"/>'))
               // .append($("<td>").html('<img width="50" height="50" src="http://localhost://'+obj.picURL+'"/>'))
            var td=$("<td>").html('</a> <a href="javascript:void(0)" onclick="modifyProduct(this)">修改</a> ' +
				'<a href="javascript:void(0)" onclick="deleteProduct(this)">删除</a>');
            tr.append(td);
            $("#productTb").append(tr);
        });
    });
}


//添加商品
function addProduct(){
	empty();
	$("#insertbt").show();
	$("#savebt").hide();
    $('.bg').fadeIn(200);
    $('.content').fadeIn(400);
}
//修改商品
function modifyProduct(e){
	set(e);
	productid=$(e).parent().parent().find("td:first").text();
	console.log("商品id:"+productid);
	$("#insertbt").hide();
	$("#savebt").show();
	$('.bg').fadeIn(200);
    $('.content').fadeIn(400);   
    
}
//删除商品
function deleteProduct(e){
	if(isdelete()==1){
		var id=$(e).parent().parent().find("td:first").text();
		$.ajax({
		url:'../product?method=deleteProduct',
            //url:'product?method=deleteProduct',
			data:{"id":id},
			success:function(data){
				if(data=="OK"){
					view(currentPage);
				}
			}
		});
	}
}
//是否删除
function isdelete(){
	var r=confirm("确定要删除该项吗？");
	if (r==true){
		return 1;
	}
	else{
		return 0;
	}
}
//表单是否为空
function emptyForm(){
	var table=document.getElementById("productTb");
	var rowNum=table.rows.length;
     for (i=1;i<rowNum;i++)
     {
    	 table.deleteRow(i);
         rowNum=rowNum-1;
         i=i-1;
     }
}
//表单为空
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
	$("#productid").val("");
	$("#categoryId").val("");
	$("#productName").val("");
	$("#price").val("");
	$("#onlineDate").val("");
	$("#descInfo").val("");
	$("#picURL").val("");
	$("#isJingXuan").val("true");
	$("#isReMai").val("true");
	$("#isXiaJia").val("true");
}
function set(e){
	var ele=document.getElementById("table");
	for(i=0;i<ele.length;i++){
		if(ele[i].getAttribute("class")=="select"){
			ele[i].value="true";
		}else if(ele[i].getAttribute("class")!="picURL"){
			ele[i].value=$(e).parent().parent().find("td").eq(i).text()
		}
	}
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