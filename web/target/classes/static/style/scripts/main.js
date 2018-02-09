// JavaScript Document

$(function(){

$('.all-products li').eq(1).addClass('.marginNo');


$('.signIn,.tip-login').click(function(e) {
    $('.grayDiv,#signIn').show();
    });
$('.signUp').click(function(){
    $('.grayDiv,#signUp').show();
})
$('.loginLink01').click(function(){
    $('#signUp').show();
    $('#signIn').hide();
    $('#forgetPassword').hide();
})
$('.loginLink02').click(function(){
    $('#signUp').hide();
    $('#forgetPassword').hide();
    $('#signIn').show();
})
$('.loginLink03').click(function(){
    $('#signUp').hide();
    $('#signIn').hide();
    $('#forgetPassword').show();
})
$(".closeBtn").click(function(e){
    $(".grayDiv,.loginDiv").hide();
})
$('.agreement span').click(function(){
    $(this).toggleClass('tick-bg');
})
$('.account-nav').hover(function(){
    $('.account-quick').toggle();
})

$('.cart .cart-list .cart-item span').click(function(){
    $(this).toggleClass('tick-bg');
    if($(this).hasClass("tick-bg")){
        $(this).parent().addClass('item-bg');
    }else{  
        $(this).parent().removeClass('item-bg');
    }
})

//全选框
$('.cart .cart-select-all #cart-select-all').click(function(){
    $(this).toggleClass('tick-bg');
    if($(this).hasClass("tick-bg")){
        $(".cart .cart-list .cart-item span").addClass("tick-bg");
        $(".cart .cart-list .cart-item span").parent().addClass('item-bg');  
    }else{  
        $(".cart .cart-list .cart-item span").removeClass("tick-bg");
        $(".cart .cart-list .cart-item span").parent().removeClass('item-bg');
    }
})

//单选框
$(".cart .cart-list .cart-item span").click(function(){  
    if($(".cart .cart-list .cart-item span").not(".tick-bg").size() <= 0){  
        $(".cart .cart-select-all #cart-select-all").addClass("tick-bg");  
    }else{  
        $(".cart .cart-select-all #cart-select-all").removeClass("tick-bg");
    }  
})  

/*==============产品详情detail start==============*/

// tab
$('.nav-wrap li a').click(function(){
    $(this).addClass('active').parents('li').siblings().children('a').removeClass('active');
    $('.section-content').hide();
    $('.section-content').eq($(this).parents('li').index()).show();
//    var $content = $('.section-content').eq($(this).parents('li').index();
//    console.log($content.attr('data-detail'));
})

// rate
$('.rate-photos-list img').click(function(){
    $(this).parent('li').toggleClass('current');
    $(this).parent('li').siblings().children('img').removeClass('current');
    $(this).parents('ul').siblings('.photo-viewer').eq($(this).parent('li').index()).siblings().removeClass('change-rate-photo');
    $(this).parents('ul').siblings('.photo-viewer').eq($(this).parent('li').index()).toggleClass('change-rate-photo');
})

//model
$('#section4 .qa .qa-tit .tiwen').click(function(){
	if(!isflag) {
		$('.signIn,.tip-login').click();
		return false;
	}
    $('#section4 .qa .qa-model').show();
})

$('#section4 .qa .qa-model .qa-x').click(function(){
	$(".qa_question").val("");
    $('#section4 .qa .qa-model').hide();
})

$('#section4 .qa .qa-model .qa-wrap .qa-ft .qa-btn.secondary').click(function(){
	$(".qa_question").val("");
    $('#section4 .qa .qa-model').hide();
})


/*==============产品详情detail end==============*/

    /*==============首页大Banner 呼吸灯 轮播 start==============*/
    $('.banner .con ul li').eq(0).show();
    var num = 0;
    var timer = null;
    function myTimer(){
        $('.banner .con ul li').eq(num).stop().fadeOut(1000);
            num++;
            //极值判断
            if(num > 3){
                num = 0;
            }
            //ul的li变化
            $('.banner .con ul li').eq(num).stop().fadeIn(1000);
            //ol的li变化
            $('.banner ol li').eq(num).addClass('current').siblings().removeClass('current');
    }
    timer = setInterval(function(){
        myTimer();
    },3000)
    $('.rightBtn').click(function(event) {
            myTimer();
        });
    $('.leftBtn').click(function(event) {
        $('.banner .con ul li').eq(num).stop().fadeOut(1000);
        num--;
        if(num < 0){
            num = 3;
        }
        $('.banner .con ul li').eq(num).stop().fadeIn(1000);
        $('.banner ol li').eq(num).addClass('current').siblings().removeClass('current');
    });
    $('.banner .con ol li').click(function(event) {
        num = $(this).index();
        $('.banner .con ul li').eq(num).stop().fadeIn().siblings().stop().fadeOut();
        $(this).addClass('current').siblings().removeClass('current');

    });
    $('.banner .con').hover(function(){  // 鼠标悬停事件
        clearInterval(timer);   //悬停时关闭定时器
        $('.banner .rightBtn').show();
        $('.banner .leftBtn').show();
    },function(){
        timer = setInterval(function(){
            myTimer()
        },3000);
        $('.banner .rightBtn').hide();
        $('.banner .leftBtn').hide();
    });

/*==============首页大Banner 呼吸灯轮播 end==============*/ 



/*==============在线客服 start==============*/

$('#close_im').bind('click',function(){
        $('#main-im').css("height","0");
        $('#im_main').hide();
        $('#open_im').show();
    });
    $('#open_im').bind('click',function(e){
        $('#main-im').css("height","272");
        $('#im_main').show();
        $(this).hide();
    });
    $('.go-top').bind('click',function(){
        $(window).scrollTop(0);
    });
    $(".weixing-container").bind('mouseenter',function(){
        $('.weixing-show').show();
    })
    $(".weixing-container").bind('mouseleave',function(){        
        $('.weixing-show').hide();
    });

/*==============在线客服 end==============*/

/*==============首页 导航切换 start==============*/
    $('.miannav .sud').hover(function(){
        /*$(this).siblings().children('.subnav').hide();*/
        $(this).children('.subnav').show().siblings().children('.subnav').hide();
    },function(){
        $('.miannav .sud').children('.subnav').hide();
    })

/*==============商品详情页 点击小图切换大图 ==============*/	
/*$('.small-pic li').click(){
    $('.big-pic li').eq($(this).index()).show().siblings().hide();
}*/
	$('.small-pic li').hover(function(){
        $('.big-pic li').eq($(this).index()).show().siblings().hide(); 
    },function(){
        $('.big-pic li').eq(1).show();
    })


   //加的效果
    $(".modifier-add").click(function(){
        var n = $(this).prev().val();
        var num = parseInt(n)+1;
        if(num==0){ return;}
        $(this).prev().val(num);
    });
//    //减的效果
    $(".modifier-sub").click(function(){
    var n=$(this).next().val();
    var num=parseInt(n)-1;
    if(num==0){ return;}
    $(this).next().val(num);
    });

/*==============商品详情页 切换详情与规格 ==============*/
$('.pdtc-tit ul li').click(function(){
    $(this).addClass('pdtdt-cur').siblings().removeClass('pdtdt-cur');
    $('.pdtc-page').eq($(this).index()).show().siblings().hide();
})
 
var $addAddress = $('.add-address')
$('#add-address').click(function(event) {
    /* Act on the event */
    $addAddress.show();
});
$('#add-addr').click(function(event) {
    /* Act on the event */
    $addAddress.show();
});
$('.close-addr').click(function(event) {
    /* Act on the event */
    $addAddress.hide();
});

$('#cancel-addr').click(function(event) {
    /* Act on the event */
    $addAddress.hide();
});
$('#address-revise').click(function(event) {
    /* Act on the event */
    $addAddress.show();
});

/*============== 订单页切换显示 ==============*/
$('.goods-odr-list').eq(0).show();
$('.goods-order .gd-list-title li').click(function(){
    $(this).addClass('od-current').siblings().removeClass('od-current');
    $('.goods-odr-list').eq($(this).index()).show().siblings('.goods-odr-list').hide();
})

/*============== 个人信息编辑提交 ==============*/
$(".pinfo-submit").click(function(){
	var username = $("#yonghuming").val();
	var nicheng = $("#nicheng").val();
	var sex = $("[name='RadioGroup1']:checked").val();
	var birthday = $("#test1").val();
	var email = $("#emailadd").val();
	var truename = $("#truename").val();
	var province = $("#loc_province :selected").val();
	var city = $("#loc_city :selected").val();
	var town = $("#loc_town :selected").val();
	var address = $("#loc_province :selected").val()+","+$("#loc_city :selected").val()+","+$("#loc_town :selected").val();
	/* var phone = $("input[name='Mobile']").val(); */
	var data = {phone:username,userName:nicheng,sex:sex,birthday:birthday,email:email,trueName:truename,address:address};
	if(username==''||nicheng==''||typeof(sex) == "undefined"||sex==''||birthday==''||email==''||truename==''||province==''||city==''){
		alert("必填项不能为空");return false;
	}
	$.ajax({
		type:'POST',
		url:'/user/center/saveInfo',
		data:JSON.stringify(data),
		dataType:'json',
		contentType:'application/json',
		success:function(data){
			if(data.code==200){
				//保存成功
				$(".pinfo").css("display","none");
			    $(".pinfo-ed").css("display","block");
			    location.reload([true]);
			}
		}
	});
    
});

$(".pinfo-edit").click(function(){
    $(".pinfo-ed").css("display","none");
    $(".pinfo").css("display","block");
});

/*============== 账号中心js效果 ==============*/

$('.confirm-foot-left').click(function(event) {
    /* Act on the event */
    $('.dialog-confirm').hide();
});
$('.confirm-foot-center').click(function(event) {
    /* Act on the event */
    $('.dialog-confirm').hide();
});


/*============== 我的分销 ==============*/
/*$('.my-client h4').click(function(){
    $(this).siblings('.client-list').toggle();
})*/

$('.my-client').hover(function(event) {
    /* Act on the event */
    $(this).children('.client-list').toggle();
});


  $('.gender label').click(function(){
    var radioId = $(this).attr('name');
    $('.gender label').removeAttr('class') && $(this).attr('class', 'checked');
    $('.gender input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
    $('.gender input[type="radio"]:checked').attr('value')
  });

$('#nickname-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#nickname').show();
});
$('#gender-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#gender').show();
});
$('#city-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#city-choo').show();
});
$('#birthday-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#birthday').show();
});
$('#phone-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#phone-choo').show();
});
$('#phone-next-btn').click(function(event) {
    /* Act on the event */
    $('#phone-choo').hide();
    $('#phone-choo-next').show();
});
$('#phone-new-btn').click(function(event) {
    /* Act on the event */
    $('#phone-choo-next').hide();
    $('#phone-choo-new').show();
});
$('#mail-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#mail-choo').show();
});
$('#mail-next-btn').click(function(event) {
    /* Act on the event */
    $('#mail-choo').hide();
    $('#mail-new').show();
});
$('#phone-safe-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#phone-safe').show();
});
$('#phone-safe-next-btn').click(function(event) {
    /* Act on the event */
    $('#phone-safe').hide();
    $('#phone-new').show();
});
$('#mail-safe-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#mail-safe').show();
});
$('#mail-safe-next-btn').click(function(event) {
    /* Act on the event */
    $('#mail-safe').hide();
    $('#mail-safe-new').show();
});
$('#mail-safe-new-btn').click(function(event) {
    /* Act on the event */
    $('#mail-safe-new').hide();
    $('#mail-safe-success').show();
});
$('#password-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#password-choo').show();
});
$('#password-next-btn').click(function(event) {
    /* Act on the event */
    $('#password-choo').hide();
    $('#password-success').show();
});
$('#account-out-btn').click(function(event) {
    /* Act on the event */
    $('#user-box').show();
    $('#account-out').show();
});


$('.confirm-foot-center').click(function(event) {
    /* Act on the event */
    $('#user-box').hide();
    $('#nickname').hide();
});
$('.confirm-foot-left').click(function(event) {
    /* Act on the event */
    $('#user-box').hide();
    $('#nickname').hide();
});

/*订单结算页关于地址管理的动态切换*/
$('.address-delete').click(function(){
    /*$(this).closest('li').remove();*/
    $(this).siblings('.delete-box').show();
})
$('.yes-del-btn').click(function(event) {
    /* Act on the event */
    $(this).closest('li').remove();
});
$('.no-del-btn').click(function(){
    $('.delete-box').hide();
})

$('.address-list label').click(function(){
    $(this).siblings('.address-mark').html('寄送至');
    $(this).closest('li').siblings().find('.address-mark').html('');
    $(this).closest('li').addClass('addr-current').siblings('li').removeClass('addr-current');
})
/*$('.set-default').click(function(){
    $(this).removeClass('set-default').html('默认地址').closest('li').siblings().find('.default').addClass('set-default').html('设为默认地址');
})*/
$('.default').click(function(){
    if($(this).html('设为默认地址')){
        $(this).removeClass('set-default').html('默认地址')
               .closest('li').siblings().find('.default')
                    .addClass('set-default')
                    .html('设为默认地址');
    }
})

/*个人中心 收货地址管理---删除地址操作*/
$('.addr-icon-del').click(function(){
    /*$(this).closest('li').remove();*/
    $('.group-delete-box').show();
})
$('.yes-group-btn').click(function(event) {
    /* Act on the event */
    $(this).closest('.group-address').remove();
});
$('.no-group-btn').click(function(){
    $('.group-delete-box').hide();
})
$('.addr-default').click(function(){
    if($(this).html('设为默认')){
        $(this).html('默认地址').closest('.group-address').siblings().find('.addr-default').html('设为默认');
    }
})

/*订单详情--操作 给最新添加的操作信息标红*/
$('.order-process-item').eq(0).addClass('process-current');



/*$('.goods-search-list li').hover(function(){
    $(this).find('.smart-cart').stop().slideToggle("2s");
})*/


$('.filter-more-btn').click(function(){
    $(this).siblings('ul').children('li:gt(5)').stop().slideToggle('1s');
   /* $('.filter-item li:gt(7)').show();*/
})

$('.header-search .search-text').focus(function(){
    $(this).parent('form').siblings('ul').show();
    $(this).addClass('search-border').siblings('.search-btn').addClass('search-border search-bg');
})
$('.header-search .search-text').blur(function(){
    $(this).parent('form').siblings('ul').hide();
    $(this).removeClass('search-border').siblings('.search-btn').removeClass('search-border search-bg');
})

$('.cart-right b').click(function(){
    $(this).closest('.cart-item').remove();
})


/*侧边分类导航*/
$('.cate-nav-list').hover(function(){
    $(this).toggleClass('cate-nav-current')
    $(this).children('ul').toggle();
})

/*$('.nav-height .nav-wrap li a').click(function(){
    $(this).addClass('active').parent('li').siblings().children('a').removeClass('active');
})*/

$('.pro-view-small ul li').click(function(){
    $(this).addClass('view-current').siblings().removeClass('view-current');
    var img = $(this).attr('data-url');
//    console.log(img);
//    $('.pro-view-middle ul li').eq($(this).index()).show().siblings().hide();
    $('.pro-view-middle ul img').attr('src',img);
})
/*$('.pro-view-samll li').click(function(){
    $(this).addClass('view-current').siblings().removeClass('view-current');
    $('.pro-view-middle li').eq($(this).index()).show();
})*/

//$('.pro-info-color-link a').click(function(){
//    $(this).addClass('pro-link-current').siblings().removeClass('pro-link-current');
//    $(this).parent().parent().children('span').text("").text($(this).attr("data-specName"));
//})


/*加入购物车*/
//$('.pro-info-add').click(function(){
//    alert('已加入购物车！');
//})

/*订单中心*/
//$('.gol-tip').click(function(){
//    alert('已提醒发货！');
//})
//
//$('.gol-confirm').click(function(){
//    alert('确认收货成功！');
//})
//
//$('.confirm-pay').click(function(){
//    alert('付款成功！');
//})
//
//$('.confirm-refund').click(function(){
//    alert('退款成功！');
//})
/**注册start**/
$(".code").click(function(){
	var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
	var phone = $('#reg_number').val();
	if(!phoneReg.test(phone)){
        alert('手机号码错误!');
        return false;
    }
	 $.ajax({
         type: "POST",
         url: "/pc/registerSendSmsCode",
         data: {'phone':phone},
         dataType: "json",
         success: function(data){
        	 	if(data.code==200){
        	 		
        	 	}else{
        	 		alert(data.message);
        	 	}
           }
     });
})
$('#registerBtn').click(function() {
    var pwdReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$/;//8到16位数字与字母组合
    var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
    //tick-bg
    var agree = $('#checkAgree').hasClass('tick-bg');
    var phone = $('#reg_number').val();
    var pwd = $('#reg_passWord').val();
    var pwd2 = $('#reg_passWord2').val();
    var code = $('#reg_code').val();
    var recommend = $('#reg_recommend').val();//推荐人
    
    if(phone == '') {
        alert('请先输入手机号码');
        return false;
    }else if(!phoneReg.test(phone)){
        alert('手机号码错误!');
        return false;
    }
    if(pwd == '') {
        alert('请输入密码');
        return false;
    }else if(!pwdReg.test(pwd)){
        alert('请输入6到15位数字与字母组合');
        return false;
    }
    if(pwd != pwd2) {
        alert('密码与确认密码不一致');
        return false;
    }
    if(code == '') {
        alert('请输入短信验证码');
        return false;
    }
    if(!agree) {
        alert('请先同意服务协议');
        return false;
    }
    
    $.ajax({
        type: "POST",
        url: "/pc/register",
        data: {'phone':phone,'pwd':pwd,'code':code,'recommend':recommend},
        dataType: "json",
        success: function(data){
       	 	if(data.code==200){
       	 		location.reload();
       	 	}else{
       	 		alert(data.message);
       	 	}
          }
    });
    return false;
});
/**注册end**/
//登录
$('#login').click(function() {
    var pwdReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$/;//8到16位数字与字母组合
    var phoneReg = /^1(3|4|5|7|8)\d{9}$/;
    //tick-bg
    var phone = $('#userName').val();
    var pwd = $('#passWord').val();
    
    if(phone == '') {
        alert('请先输入用户名');
        return false;
    }else if(!phoneReg.test(phone)){
        alert('手机号码错误!');
        return false;
    }
    if(pwd == '') {
        alert('请输入密码');
        return false;
    }
    $.ajax({
        type: "POST",
        url: "/pc/login",
        data: {'username':phone,'password':pwd},
        dataType: "json",
        success: function(data){
       	 	if(data.code==200){
       	 		location.reload();
       	 	}else{
       	 		alert(data.message);
       	 		return false;
       	 	}
          }
    });
    return false;
});

// 返回
$('.nav-back').click(function(){
    window.history.back();location.reload();
})

// 订单详情页
$('.order-detail-cont .gol-tit .gol-tit-more').click(function(){
    $('.order-detail-cont .gol-time').toggle();
})

// 评价页面


})//入口函数结束符


