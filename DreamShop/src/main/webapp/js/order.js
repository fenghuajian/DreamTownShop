window.onload = function(){
	flag2=0;
	order=null;
	$.ajax({
		url:'order?method=viewOrder',
		success:function(data){
			getOrder(data);
			order=data;
		}
	});
};

function getOrder(data){
	item=document.getElementById('item');
    for(var i=0;i<Object.keys(data).length;i++){
    	var div = document.createElement('div');
    	div.innerHTML = '<div class="info warp" checked="">'+
		  					'<ul>'+
		  	  					'<li class="info_1"><input type="checkbox" name="fav" onclick="checkTest2()" value=""/></li>'+
		  	  					'<li class="info_2"><img class="pic" src="" width="80px"/></li>'+
		  	  					'<li class="info_3"><a></a></li>'+
		  	  					'<li class="info_4"><a></a></li>'+
		  	  					'<li class="info_5"></li>'+
		  	  					'<li class="info_6">'+
			  	  					'<button onclick="checkTest3(this,1),checkTest2()">-</button>'+
			  	  					'<input id="num" class="shul" type="text" name="" id="" value="1" />'+
			  	  					'<button class="bot" onclick="checkTest3(this,2),checkTest2()">+</button>'+		
		  	  					'</li>'+
		  	  					'<li class="info_7"></li>'+
		  	  					'<li class="info_8">'+
			  	  					'<a href="javascript:void(0)" onclick="checkTest4(this),checkTest2()" >删除</a><br />'+
		  	  					'</li>'+
		  					'</ul>'+
						'</div>';
    	item.appendChild(div);

    	$("#item").children("div").eq(i).find("li").eq(0).find("input").attr("value",data[i]["productId"])
    	$("#item").children("div").eq(i).find("li").eq(1).find("img").attr("src","img/"+data[i]["picURL"])
    	$("#item").children("div").eq(i).find("li").eq(2).find("a").eq(0).html(data[i]["name"]);
    	$("#item").children("div").eq(i).find("li").eq(3).find("a").eq(0).html(data[i]["descInfo"]);
    	$("#item").children("div").eq(i).find("li").eq(4).html(data[i]["price"]);
        $("#item").children("div").eq(i).find("li").eq(5).find("input").attr("value",data[i]["num"])
    	$("#item").children("div").eq(i).find("li").eq(6).html("￥"+data[i]["price"]*data[i]["num"]);
    }
}


//获得所有多选框的对象
fav=document.getElementsByName("fav");

//去除未选择的对象
function selected(){
	for (var i=1,j=1;i<=fav.length-2;i++,j++) {
		if(fav[i].checked==false){
			j--;
			order.splice(j,1);
		}
	}
}

//将数量、小计、总价包装到json中
function packaging(){
	for(var i=0;i<order.length;i++){
		order[i].num=$("#item").children("div").eq(i).find("li").eq(5).find("input").val();
		order[i].subtotal=$("#item").children("div").eq(i).find("li").eq(6).text().split("￥")[1];	
	}
	var total=JSON.parse('{"total":"'+$("#zongz").text()+'"}');
	order.push(total);
}

//提交订单
function settle(){
	if(flag2==1){
		selected();
		packaging();
		$.ajax({
			url:'order?method=getOrder',
			data:{
				"order":JSON.stringify(order)
			},
			success:function(){
				window.location.href="cn.guet.pay.html";
			}
		});
	}else{
		alert("请选择商品");
	}
}

//判断是否是全选操作
function checkTest1(th){

	//判断选项是否被勾选
	var flag=th.checked;
	//alert(flag);
	
	
	//通过forech的方法遍历名为fav的有序列表
	for (var i in fav) {
		fav[i].checked=flag;
	}
}

//单选决定全选操作
function checkTest2(){
	
	var flag=true;
	
	/*遍历出去第一个和最后一个fav，后来再处理*/
	for (var i=1;i<fav.length-1;i++) {
		if (!fav[i].checked) {
			flag=false;
			break;
		}
	}
	/*决定是否被勾选，fav[0]代表第一个全选框，fav[1]代表第二个多选框*/
	fav[0].checked=flag;
	fav[fav.length-1].checked=flag;
	//alert(flag);
	
	
	
	//商品的总价格
	var zong=0;
	//统计被勾选对象的数量
	var num=0;
	//统计商品数量
	var spNum=0;
	
	//价格是否统计,遍历fav
	for (var i=1;i<fav.length-1;i++) {
		//如果被勾选
		if (fav[i].checked) {
			num++;
			
			//获得ul父节点
			var par =fav[i].parentNode.parentNode;
			//获得指定ul下的所有li
			var li= par.getElementsByTagName("li");
			
			//单个商品的总价格:将得到的数据通过￥分开，并取第二个元素
			var z=li[6].innerText.split("￥")[1];
			//获得所有商品的总结格
			zong+=Number(z);
			document.getElementById("zongz").innerText=zong;
			
			//获得商品的数量
			var z2=li[5].getElementsByTagName("input");
			var num2=z2[0].value;
			spNum+=Number(num2);
			//获得商品数量统计对象
			document.getElementById("snum").innerText=spNum;
			
			flag2=1;
		}
	}
	if(num==0){
	 	document.getElementById("zongz").innerText=0;	
	 	document.getElementById("snum").innerText=0;
	}
}

/*控制数量的增加或减少，注意括号问题*/
function checkTest3(th,sig){
	//就是th，即this传的值
	var pre;
	if (sig=="1") {
		//获得下一个节点对象
		pre=th.nextElementSibling;
		if (Number(pre.value)>0) {
			//获得节点的value的值
			pre.value=Number(pre.value)-1;
		  }
		}else{
			//获得上个节点的对象
			pre=th.previousElementSibling;
			//获得下一个节点对象
			pre.value=Number(pre.value)+1;
		}
		
		
		//计算每个商品的价格
		//获取当前节点的父节点的上一个节点的内容，即每个商品的单价nnerText\innerHTML都可以
		var val=pre.parentNode.previousElementSibling.innerText;
		//计算总价格
		var zong=Number(val)*Number(pre.value);
		//把总价赋值给指定对象
		pre.parentNode.nextElementSibling.innerText="￥"+zong;
}


//删除指定节点
function checkTest4(th){
	//获得父节点div(<a>的父节点（li）的父节点（ul）的父节点div)
	var div=th.parentNode.parentNode.parentNode;
	
	var productId=$(th).parent().parent().find("li:first").find("input").attr("value")
	$.ajax({
		url:"order?method=deleteItem",
		data:{"productId":productId},
		success:function(){
			div.remove();
			Pop.showPop(0,"删除成功",0);
		},
		error:function(){
			Pop.showPop(0,"删除失败",0);
		}
	});
}

function removeall(){
	for (var i=1,j=1;i<=fav.length-1;i++,j++) {
		//如果被勾选
		if (fav[i].checked) {
			var div=fav[j].parentNode.parentNode.parentNode;
			div.remove();
			j--;
		}
	}
	checkTest2();
}
