window.onload=function () {
	var oDiv=document.getElementById("onclick_div");
	var list_div=oDiv.getElementsByTagName("div");
	var list=list_div[list_div.length-1];
	var right_content=document.getElementById("right_content");
	var right_content_div=right_content.getElementsByClassName("right_content_div");
	var now_situ=document.getElementsByClassName('now_situ');
	var oUl=now_situ[0].getElementsByTagName('ul');
	//var oLi=now_situ[0].getElementsByTagName('li');

	// 首次加载页面时添加默认位置的li标签
	var li=document.createElement("li");
	    li.innerHTML="> "+list_div[0].innerHTML;
	    oUl[0].appendChild(li);
	    
	for(var i=0;i<list_div.length-1;i++){
		list_div[i].index=i;
		list_div[i].onclick=function () {
			lighten(list_div,this,right_content_div);
			leftLighten(list,this.offsetTop);
			addLi(oUl[0],this);
		}
	}
}

//左侧小块选中点亮及右侧大块的出现
function lighten(obj,_this,block_one) {
     	for(var i=0;i<obj.length-1;i++){
			obj[i].className='';
			block_one[i].style.display='none';
		}
		_this.className='active';
		block_one[_this.index].style.display='block';

}

//左侧小块导航栏小蓝条
function leftLighten(obj,iTarget) {
    	obj.style.top=iTarget+'px';
    }

    // 当前位置先删除后添加li标签
function addLi(obj,_this) {
	    obj.lastChild.remove();
       	var li=document.createElement("li");
	    li.innerHTML="> "+_this.innerHTML;
	    obj.appendChild(li);
       }