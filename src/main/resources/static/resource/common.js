$('.js_menu_toggle').click(function() {
	if ($('.js_menu_toggle_box').hasClass('active')) {
		$('.js_menu_toggle_box').removeClass('active');
	} else {
		$('.js_menu_toggle_box').addClass('active');
	}
});

$('.js_search_toggle').click(function() {
	if ($('.js_search_toggle_box').hasClass('active')) {
		$('.js_search_toggle_box').removeClass('active');
	} else {
		$('.js_search_toggle_box').addClass('active');
		$('.js_search_box input').focus();
	}
});

$('.center').click(function() {
	$('.js_search_toggle_box').removeClass('active');
})

$('#image_input').on('change', function(e) {
	const file = e.target.files[0];
	const reader = new FileReader();
	reader.onload = function(e) {
		$('#preview_img').attr('src', e.target.result);
		$('#fileName').text(file.name);
	}

	reader.readAsDataURL(file);
});

var $searchKeyword = $('#js_input_searchKeyword').val().trim();

if ($searchKeyword != '') {
	$('.js_search_toggle_box').addClass('active');
}

function popup(index) {

	$('.detail_bg').show();
	$('#detail_' + index).show();
	$('body').css('overflow', 'hidden');

	$('.detail_bg').on('click', function() {
		$('.detail_bg').css('display', 'none');
		$('#detail_' + index).css('display', 'none');
		$('body').css('overflow', 'auto');
	})

	$('.exit_btn').on('click', function() {
		$('.detail_bg').css('display', 'none');
		$('#detail_' + index).css('display', 'none');
		$('body').css('overflow', 'auto');
	})
}