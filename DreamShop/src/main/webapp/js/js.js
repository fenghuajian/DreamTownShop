//定时器返回值
var time=null;
//记录当前位子
var nexImg = 0;
//用于获取轮播图图片个数
var imgLength = $(".c-banner .banner ul li").length;
//当时动态数据的时候使用,上面那个删除
// var imgLength =0;
//设置底部第一个按钮样式
$(".c-banner .jumpBtn ul li[jumpImg="+nexImg+"]").css("background-color","white");

//页面加载
$(document).ready(function(){
  // dynamicData();
  //启动定时器,设置时间为3秒一次
  time =setInterval(intervalImg,5000);
});

//点击上一张
$(".preImg").click(function(){
  //清楚定时器
  clearInterval(time);
  var nowImg = nexImg;
  nexImg = nexImg-1;
  console.log(nexImg);
  if(nexImg<0){
    nexImg=imgLength-1;
  }
  //底部按钮样式设置
  $(".c-banner .jumpBtn ul li").css("background-color","transparent");
  $(".c-banner .jumpBtn ul li[jumpImg="+nexImg+"]").css("background-color","white");
  
  //将当前图片试用绝对定位,下一张图片试用相对定位
  $(".c-banner .banner ul img").eq(nowImg).css("position","absolute");
  $(".c-banner .banner ul img").eq(nexImg).css("position","relative");

  //轮播淡入淡出
  $(".c-banner .banner ul li").eq(nexImg).css("display","block");
  $(".c-banner .banner ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner .banner ul li").eq(nowImg).stop().animate({"opacity":0},1000,function(){
    $(".c-banner ul li").eq(nowImg).css("display","none");
  });

  //banner1
  $(".c-banner1 .banner1 ul img").eq(nowImg).css("position","absolute");
  $(".c-banner1 .banner1 ul img").eq(nexImg).css("position","relative");

  $(".c-banner1 .banner1 ul li").eq(nexImg).css("display","block");
  $(".c-banner1 .banner1 ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner1 .banner1 ul li").eq(nowImg).stop().animate({"opacity":0},1000,function(){
    $(".c-banner1 ul li").eq(nowImg).css("display","none");
  });

  //banner2
  $(".c-banner2 .banner2 ul img").eq(nowImg).css("position","absolute");
  $(".c-banner2 .banner2 ul img").eq(nexImg).css("position","relative");

  $(".c-banner2 .banner2 ul li").eq(nexImg).css("display","block");
  $(".c-banner2 .banner2 ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner2 .banner2 ul li").eq(nowImg).stop().animate({"opacity":0},1000,function(){
    $(".c-banner2 ul li").eq(nowImg).css("display","none");
  });

  //banner3
  $(".c-banner3 .banner3 ul img").eq(nowImg).css("position","absolute");
  $(".c-banner3 .banner3 ul img").eq(nexImg).css("position","relative");

  $(".c-banner3 .banner3 ul li").eq(nexImg).css("display","block");
  $(".c-banner3 .banner3 ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner3 .banner3 ul li").eq(nowImg).stop().animate({"opacity":0},1000,function(){
    $(".c-banner3 ul li").eq(nowImg).css("display","none");
  });
  
  //启动定时器,设置时间为3秒一次
  time =setInterval(intervalImg,5000);
})

//点击下一张
$(".nexImg").click(function(){
  clearInterval(time);
  intervalImg();
  time =setInterval(intervalImg,5000);
})

//轮播图
function intervalImg(){
  if(nexImg<imgLength-1){
    nexImg++;
  }else{
    nexImg=0;
  }
  
  //将当前图片试用绝对定位,下一张图片试用相对定位
  $(".c-banner .banner ul img").eq(nexImg-1).css("position","absolute");
  $(".c-banner .banner ul img").eq(nexImg).css("position","relative");
  
  $(".c-banner .banner ul li").eq(nexImg).css("display","block");
  $(".c-banner .banner ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner .banner ul li").eq(nexImg-1).stop().animate({"opacity":0},1000,function(){
    $(".c-banner .banner ul li").eq(nexImg-1).css("display","none");
  });
  $(".c-banner .jumpBtn ul li").css("background-color","transparent");
  $(".c-banner .jumpBtn ul li[jumpImg="+nexImg+"]").css("background-color","white");


  //banner1
  $(".c-banner1 .banner1 ul img").eq(nexImg-1).css("position","absolute");
  $(".c-banner1 .banner1 ul img").eq(nexImg).css("position","relative");
  
  $(".c-banner1 .banner1 ul li").eq(nexImg).css("display","block");
  $(".c-banner1 .banner1 ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner1 .banner1 ul li").eq(nexImg-1).stop().animate({"opacity":0},1000,function(){
    $(".c-banner1 .banner1 ul li").eq(nexImg-1).css("display","none");
  });


  //banner2
  $(".c-banner2 .banner2 ul img").eq(nexImg-1).css("position","absolute");
  $(".c-banner2 .banner2 ul img").eq(nexImg).css("position","relative");
  
  $(".c-banner2 .banner2 ul li").eq(nexImg).css("display","block");
  $(".c-banner2 .banner2 ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner2 .banner2 ul li").eq(nexImg-1).stop().animate({"opacity":0},1000,function(){
    $(".c-banner2 .banner2 ul li").eq(nexImg-1).css("display","none");
  });


  //banner3
  $(".c-banner3 .banner3 ul img").eq(nexImg-1).css("position","absolute");
  $(".c-banner3 .banner3 ul img").eq(nexImg).css("position","relative");
  
  $(".c-banner3 .banner3 ul li").eq(nexImg).css("display","block");
  $(".c-banner3 .banner3 ul li").eq(nexImg).stop().animate({"opacity":1},1000);
  $(".c-banner3 .banner3 ul li").eq(nexImg-1).stop().animate({"opacity":0},1000,function(){
    $(".c-banner3 .banner3 ul li").eq(nexImg-1).css("display","none");
  });
}

//轮播图底下按钮
//动态数据加载的试用应放在请求成功后执行该代码,否则按钮无法使用
$(".c-banner .jumpBtn ul li").each(function(){
  //为每个按钮定义点击事件
  $(this).click(function(){
    clearInterval(time);
    $(".c-banner .jumpBtn ul li").css("background-color","transparent");
    jumpImg = $(this).attr("jumpImg");
    if(jumpImg!=nexImg){
      var after =$(".c-banner .banner ul li").eq(jumpImg);
      var befor =$(".c-banner .banner ul li").eq(nexImg);
      
      //将当前图片试用绝对定位,下一张图片试用相对定位
      $(".c-banner .banner ul img").eq(nexImg).css("position","absolute");
      $(".c-banner .banner ul img").eq(jumpImg).css("position","relative");
      
      after.css("display","block");
      after.stop().animate({"opacity":1},1000);
      befor.stop().animate({"opacity":0},1000,function(){
        befor.css("display","none");
      });
      nexImg=jumpImg;
    }
    $(this).css("background-color","white");
    time =setInterval(intervalImg,5000);
  });
});

  //动态数据轮播图
  //动态数据加载的时候不要直接点击demo.html运行否则可能请求不到本地json数据
// function dynamicData(){
//  $.ajax({
//    url:"js/cn.guet.test.json",
//    type:"get",
//    dataType:"json",
//    success:function(data){
//      if(data.code==1){
//        var data = data.data;
//        $.each(data,function(i){
//          $(".c-banner .banner ul").append('<li><img src="'+this.img+'"></li>');
//          $(".c-banner .jumpBtn ul").append('<li jumpImg="'+i+'"></li>')
//        })
//      }
//      //获取图片总数量
//      imgLength = $(".c-banner .banner ul li").length;
//      //为底部按钮定义单击事件
//      $(".c-banner .jumpBtn ul li").each(function(){
//        //为每个按钮定义点击事件
//        $(this).click(function(){
//          clearInterval(time);
//          $(".c-banner .jumpBtn ul li").css("background-color","white");
//          jumpImg = $(this).attr("jumpImg");
//          if(jumpImg!=nexImg){
//            var after =$(".c-banner .banner ul li").eq(jumpImg);
//            var befor =$(".c-banner .banner ul li").eq(nexImg);
//            
//            //将当前图片试用绝对定位,下一张图片试用相对定位
//            $(".c-banner .banner ul img").eq(nexImg).css("position","absolute");
//            $(".c-banner .banner ul img").eq(jumpImg).css("position","relative");
//            
//            after.css("display","block");
//            after.stop().animate({"opacity":1},1000);
//            befor.stop().animate({"opacity":0},1000,function(){
//              befor.css("display","none");
//            });
//            nexImg=jumpImg;
//          }
//          $(this).css("background-color","black");
//          time =setInterval(intervalImg,3000);
//        });
//      });
//    }
//  })
// }