$(function() {
       
        //判断浏览器是否使用ie内核
        //ie下图片滚动的兼容
        if (window.ActiveXObject || "ActiveXObject" in window){
        	 
        	var oDiv=document.getElementsByClassName('moving');  
          var oUl=$(oDiv).find('ul');  
          var ali=$(oUl).find('li'); 
          var hasStarted = false; //是否已经开始滚动
          
          oUl.html(oUl.html()+oUl.html());
          
          oUl.width(152*ali.length+'px');//li的宽度+padding+margin为152
         
          fun = function(){  
         	 
	          if(oUl.offset().left<-oUl.outerWidth(true)/2){//offsetLeft是负数，而offsetWidth是一个最小大于0的数，所以加个-号才能进行比较  
	            oUl.css('left','0');//当所有图片滚动完之后将left设置为0意味着后面无缝地接着第一张图片，  
	          }  
            oUl.offset({left:oUl.offset().left-2});//每次向左移动2px  
//      console.log(oUl.offset().left)
          }
//       oUl.css('background','red')
          var timer=setInterval(fun,30);
          oUl.mouseover(function(){
            clearInterval(timer);
//          alert(1)
          	hasStarted=false;
          })
         oUl.mouseout(function(){
         	
						if(!hasStarted) {
							hasStarted = true;
						timer=setInterval(fun,30);
						
						}
	
        })
}



//轮播图start();
	var currentIndex = 0;
	var interval;
	var hasStarted = false; //是否已经开始轮播 
	var length = $('.slider-img').length;

	var picInfo = ['我校成功举办广东大学生学习贯彻发的含量十八大精神演讲比赛复赛', '校学生会协奏毕业生之歌', '我校学生在广东省第七届模拟商务谈判中获奖']

	//将除了第一张图片隐藏 
	$('.slider-img:not(:first)').hide();
	//将第一个slider-item设为激活状态 
	$('.slider-item:first').addClass('active');
	$('.slider-img').hover(function() {
		stop();
	}, function() {
		start();
	});

	$('.slider-item').hover(function(e) {
		stop();
		var preIndex = $(".slider-item").filter(".active").index();
		currentIndex = $(this).index();
		play(preIndex, currentIndex);
	}, function() {
		start();
	});


	/** 
	 * 向前翻页 
	 */
	function pre() {
		var preIndex = currentIndex;
		currentIndex = (--currentIndex + length) % length;
		play(preIndex, currentIndex);
	}
	/** 
	 * 向后翻页 
	 */
	function next() {
		var preIndex = currentIndex;
		currentIndex = ++currentIndex % length;
		play(preIndex, currentIndex);
	}
	/** 
	 * 从preIndex页翻到currentIndex页 
	 * preIndex 整数，翻页的起始页 
	 * currentIndex 整数，翻到的那页 
	 */
	function play(preIndex, currentIndex) {
		$('.slider-img').eq(preIndex).fadeOut(500)
			.parent().children().eq(currentIndex).fadeIn(1000);
		$('.slider-item').removeClass('active');
		$('.slider-item').eq(currentIndex).addClass('active');

		$('.slider-info span').text(picInfo[currentIndex]);

	}

	//开始轮播 

	function start() {
		if(!hasStarted) {
			hasStarted = true;
			interval = setInterval(next, 3000);

		}
	}
	// 停止轮播 
	function stop() {
		clearInterval(interval);
		hasStarted = false;
	}
	//开始轮播 
	start();
	
	var wid = $('.slider-info').width();
  $('.slider .slider-img img').css('width',wid+'px'); 
	
	//右边绿色悬浮
	var distance;
	var moveGreen =function(){
	var a = document.getElementById("teacherList").offsetTop;
	var topValue = 0,// 上次滚动条到顶部的距离  
	interval = null;// 定时器  
    distance = $('#right').height()-$('.greenBac').height();
    if(interval == null)// 未发起时，启动定时器，
        interval = setInterval(function(){
	        if($(this).scrollTop() == topValue) {  
                var top1=$(document).scrollTop(); 
		        if (a >= $(window).scrollTop() && a < ($(window).scrollTop()+$(window).height())) {
//                              alert(1)
                    if($(".greenBac").position().bottom!=0){
                      	$('.greenBac').animate({bottom:"0px"},400);//下来
                    }
	            }else{
                	    if($(".greenBac").position().top!=0 ){
      	                $('.greenBac').animate({bottom:distance+'px'},400);//上去
            	    }
                }
        		clearInterval(interval);  
        		interval = null;  
            }  
	    }, 500);
    	topValue =$(this).scrollTop();  
	}
	document.onscroll = function() {  
        moveGreen();     
    }  

	window.onresize=function(){
		moveGreen();  
		wid =$('.slider-info').width();
        $('.slider .slider-img img').css('width',wid+'px');    
	}
	 moveGreen();    
	 
//校内链接
 	$("#ulyqlj").hide();
    $("#btnyqlj").click(function () {
    	preventBubble();
      $("#ulyqlj").toggle(300);
//    alert(1)
    });
  function preventBubble(event){  
  	var e=arguments.callee.caller.arguments[0]||event; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容  
  	if (e && e.stopPropagation) {  
   	 e.stopPropagation();  
  } else if (window.event) {  
    window.event.cancelBubble = true;  
  }  
}
    $('body').click(function (e) {
        if ($(e.target).attr('id') != "btnyqlj" &&
            $(e.target).attr('id') != "ulyqlj") {
            $("#ulyqlj").hide(300);
        }
    });
})
