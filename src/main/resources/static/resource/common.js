$('.bars').click(function() {
	if ($('.menu_btn').hasClass('active')) {
		$('.menu_btn').removeClass('active');
	} else {
		$('.menu_btn').addClass('active');
	}
});

$('.search_btn').click(function() {
	if ($('.search_btn').hasClass('active')) {
		$('.search_btn').removeClass('active');
	} else {
		$('.search_btn').addClass('active');
		$('.search_box input').focus();
	}
});

$('.center').click(function() {
	$('.search_btn').removeClass('active');
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

var $searchKeyword = $('#input_keyword').val().trim();

if ($searchKeyword != '') {
	$('.search_btn').addClass('active');
}