// 메뉴 토글 눌렀을때 활성화 상태면 비활성화 아니면 활성화
$('.js_menu_toggle').click(function() {
	if ($('.js_menu_toggle_box').hasClass('active')) {
		$('.js_menu_toggle_box').removeClass('active');
	} else {
		$('.js_menu_toggle_box').addClass('active');
	}
});

// 검색 토글 누르면 검색 박스 활성화, 이외 비활성화
$('.js_search_toggle').click(function() {
	if ($('.js_search_toggle_box').hasClass('active')) {
		$('.js_search_toggle_box').removeClass('active');
	} else {
		$('.js_search_toggle_box').addClass('active');
		$('.js_search_box input').focus();
	}
});

// 사이드 메뉴 밖 누르면 검색어 active 취소
$('.center').click(function() {
	$('.js_search_toggle_box').removeClass('active');
})

// 이벤트 업로드 파일 미리보기
$('#image_input').on('change', function(e) {
	const file = e.target.files[0];
	const reader = new FileReader();
	reader.onload = function(e) {
		$('#preview_img').attr('src', e.target.result);
		$('#fileName').text(file.name);
	}

	reader.readAsDataURL(file);
});

// 검색어 값이 있다면 active 유지
var $searchKeyword = $('#js_input_searchKeyword').val().trim();

if ($searchKeyword != '') {
	$('.js_search_toggle_box').addClass('active');
}

// 상세보기 팝업
function popup(index) {
	$('#detail_bg_' + index).show();
	$('#detail_' + index).show();
	$('body').css('overflow', 'hidden');

	$('#detail_bg_' + index).on('click', function() {
		$('#detail_bg_' + index).css('display', 'none');
		$('#detail_' + index).css('display', 'none');
		$('body').css('overflow', 'auto');
	})

	$('.exit_btn').on('click', function() {
		$('#detail_bg_' + index).css('display', 'none');
		$('#detail_' + index).css('display', 'none');
		$('body').css('overflow', 'auto');
	})
}