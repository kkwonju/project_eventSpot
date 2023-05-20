package com.kkwo.demo.util;

public class Ut {

	/**
	 * 해당 객체가 비었는지 체크
	 * 
	 * @param obj Object 타입
	 * 
	 * @return true: 비었음, false: 비어있지 않음
	 */
	public static boolean isEmpty(Object obj) {

		if (obj == null) {
			return true;
		}

		if (obj instanceof Integer) {
			return (int) obj == 0;
		}

		if (!(obj instanceof String)) {
			return true;
		}

		String str = (String) obj;

		try {
			return str.trim().length() == 0;
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**
	 * 지정된 형식으로 문자열 생성
	 * 
	 * @param format String 타입
	 * @param arg    Object 타입, 여러개 가능
	 * 
	 * @return String.format(format, args);
	 */
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}

	/**
	 * 자바스크립트 메세지 띄운 뒤 되돌려보내기
	 *
	 * @param resultMsg 보여줄 메세지
	 * 
	 * @return javascrpit문법 alert창 메세지 띄운 후 이전 페이지로 이동
	 */
	public static String jsHistoryBack(String resultMsg) {
		if (resultMsg == null) {
			resultMsg = "";
		}

		return Ut.f("""
				<script>
					const resultMsg = '%s'.trim();
					if( resultMsg.length > 0 ){
						alert(resultMsg);
					}
					history.back();
				</script>
				""", resultMsg);
	}

	/**
	 * 자바스크립트 메세지 띄운 뒤 특정 페이지로 이동
	 *
	 * @param resultMsg 보여줄 메세지
	 * @param uri 실행 후 이동할 uri
	 * 
	 * @return javascrpit문법 alert창 메세지 띄운 후 uri 페이지로 이동
	 */
	public static String jsReplace(String resultMsg, String uri) {
		if (resultMsg == null) {
			resultMsg = "";
		}

		if (uri == null) {
			uri = "/";
		}

		return Ut.f("""
				<script>
					const resultMsg = '%s'.trim();
					if( resultMsg.length > 0 ){
						alert(resultMsg);
					}
					location.replace('%s');
				</script>
				""", resultMsg, uri);
	}
}
