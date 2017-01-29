/*custom.min*/
	
jQuery(document).ready(function($){
	//portfolio - show link
	$('.fdw-background').hover(
		function () {
			$(this).animate({opacity:'1'});
		},
		function () {
			$(this).animate({opacity:'0'});
		}
	);	
});

$(document).ready(function () {
    var window_height = $(window).height();
    var page_height = $('header').height() + $('.santander_balances').height();
    var footer_height = $('footer').height();
    if (page_height < window_height) {
        margin_footer = window_height - page_height - footer_height - 25;
        if (margin_footer > 0) {
            $('footer').css('margin-top', margin_footer);
        }
        else {
            $('footer').css('margin-top', '10px');
        }
    }
});
$(window).resize(function () {
    var window_height = $(window).height();
    var page_height = $('header').height() + $('.santander_balances').height();
    var footer_height = $('footer').height();
    if (page_height < window_height) {
        margin_footer = window_height - page_height - footer_height - 25;
        if (margin_footer > 0) {
            $('footer').css('margin-top', margin_footer);
        }
        else {
            $('footer').css('margin-top', '10px');
        }
    }
});
