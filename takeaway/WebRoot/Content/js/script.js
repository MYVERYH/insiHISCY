// JavaScript Document
$('#main').jpreLoader({
		loaderVPos: '50%',
		autoClose: true,
	}, 
	function() {	
		$('#main').animate({"opacity":'1'},{queue:false,duration:700,easing:"easeInOutQuad"});
		setTimeout( function(){		
			$('.panel').slideUp(1000);
		},1200);
	});
$(function(){
	initCashemir();	
});

//页面开始动画


//导航条
function initCashemir(){
	"use strict";
	
	var ino = $('.navigation');
	var $tElems = $('#nav ul li h3>a');
	var ct = $('#nav ul li h3>a').length;
	var al = {queue:true,duration:800,easing:"easeInOutQuad"};
	var bo = $('.body-overlay');
	
	function showmenu(){
		$(".nav-button").addClass('nav-rotade');
		ino.animate({"left":'0'},al);          
		ino.removeClass("isDown");
		bo.fadeIn();		
		setTimeout( function(){		
			for (var i = 0; i <= ct; i++) {
				var cft = $tElems[i];
				$(cft).delay(150 * i).animate({'opacity' : '1',left:'0'},al); 
			}
		},100);
	}
	
// hide menu ------------------

	function hidemenu(){
		$(".nav-button").removeClass('nav-rotade');
		ino.animate({"left":'-200px'},al);   
		ino.addClass("isDown");
		bo.fadeOut();		
		setTimeout( function(){					
			for (var i = 0; i <= ct; i++) {
				var cft = $tElems[i];
				$(cft).delay(150 * i).animate({'opacity' : '0',left:'-25%'},al);				 
			}		
		},100);
	}
	
	$(".nav-button").click( function(){
		if ($('.navigation').hasClass("isDown") ) {
			showmenu();
			
		} else {
			hidemenu();
		}	
		return false;
	});
	
	
	$("#nav .nav_body a,#nav ul li#QXReport a, .body-overlay").click(function(){
		if ($('.navigation').hasClass("isDown") ) {
			showmenu();
			
		} else {
			hidemenu();
		}	
		return false;
	});
	
	
	
// navigation links ------------------

	/*$(".inner a.scroll-link , .body-overlay").bind('click', function(event) {
		event.preventDefault();
		$.scrollTo( 
			$(this).attr('href'),950,{easing:'swing',offset: 25,'axis':'y'} );
			setTimeout( function(){	
			hidemenu();				 	
		},900);	
	});*/
	
// Call plugins  ----------------------------------------
	
	$('#slides').superslides({
		animation: 'fade',			
	});
	
	/*$('#nav .nav_head').onePageNav({
		currentClass: 'current',
		changeHash: false,
		scrollSpeed: 750,
		scrollOffset: 30,
		scrollThreshold: 0.5,
		filter: '',
		easing: 'swing',	
	});*/
}
