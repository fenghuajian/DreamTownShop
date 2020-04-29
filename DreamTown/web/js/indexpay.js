$(function(){
	     var money = decodeURI(location.search);
	     money = money.substring(5);
		 console.log("总金额："+money);
		 $('#WIDtotal_amount').val(money);
		 
});