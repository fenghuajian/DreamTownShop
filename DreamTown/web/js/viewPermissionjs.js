$(document).ready(function (){
	totalPage=1;
	currentPage=1;
	view(1);
	
    $("#cancel").click(function(){
    	$('.bg').fadeOut(800);
    	$('.newPermission').fadeOut(800);
	});
	
	$("#addPermission").click(function(){
		if($("#pid").empty() != null){
			getParentPer();
			$('.bg').show();
			$('.newPermission').show();
		}
	});
	
	$("#savePermission").click(function(){
		$.ajax({
			type: "POST",
			url:'../permission?method=addPermission',
			data:$("#fm").serialize(),
			success: function(data) {
				if(data=="OK"){
					$('.bg').fadeOut(800);
            		$('.newPermission').fadeOut(800);
            		$("tr:not(:first)").remove();
        	    	view(currentPage);
        	    	Pop.showPop(0,"保存成功",0);
            	}else{
            		Pop.showPop(0,"保存失败",0);
            	}
				$(this).resetForm();
            }      
		})
	});
});

function getParentPer(){
	$.getJSON("../permission?method=getAllPermission", function (data) {
		 $.each(data, function (index, obj) {
		 	if(obj.isParent == "true"){
		 		$("#pid").append("<option value="+ obj.permissionid +">"+ obj.name +"</option>");
		 	}
		 });
	});
}

function view(currentPage){
	$.getJSON("../permission?method=viewPermission&currentPage="+currentPage, function (data) {
		totalPage=data.totalPage;
		disabled();
        $.each(data["list"], function (index, obj) {
            var tr = $("<tr>")
            .append($("<td>").text(obj.permissionid))
            .append($("<td>").text(obj.name))
            .append($("<td>").text(obj.icon))
            .append($("<td>").text(obj.iconSkin))
            .append($("<td>").text(obj.url));
            var td=$("<td>").html('</a> <a href="javascript:void(0)" onclick="modifyPermission(this)">修改</a> <a href="javascript:void(0)" onclick="deletePermission(this)">删除</a>');
            tr.append(td);
            $("#permissionTb").append(tr);
        });
    });
}

function modifyPermission(e){
	$("#permissionId").val($(e).parent().parent().find("td").eq(0).text());
	$("#name").val($(e).parent().parent().find("td").eq(1).text());
	$("#iconSkin").val($(e).parent().parent().find("td").eq(3).text());
	$("#url").val($(e).parent().parent().find("td").eq(4).text());
	if($("#pid").empty() != null){
		getParentPer();
		$('.bg').show();
		$('.newPermission').show();
	}
}

function deletePermission(e){
	var pid=$(e).parent().parent().find("td:first").text();
	$.ajax({
		url:'../permission?method=deletePermission&permissionId='+pid,
		success:function(data){
			if(data=="OK"){
				$("tr:not(:first)").remove();
				view(currentPage);
			}
		}
	})
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
	$("tr:not(:first)").remove();
 	view(currentPage);
 	disabled();
 }