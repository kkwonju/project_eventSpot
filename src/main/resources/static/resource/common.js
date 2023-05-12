$('.bars').click(function() {
	if ($('.menu_btn').hasClass('active')) {
		$('.menu_btn').removeClass('active');
	} else {
		$('.menu_btn').addClass('active');
	}
});

