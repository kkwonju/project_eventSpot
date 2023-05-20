package com.kkwo.demo.vo;

import lombok.Getter;

public class ResultData {
	@Getter
	private String resultCode;
	@Getter
	private String resultMsg;
	@Getter
	private String data1Name;
	@Getter
	private Object data1;

	/**
	 * ResultData 구성, 데이터 정보를 볼 수 있도록
	 * 
	 * @param resultCode 성공 : 'S-'; 실패 : 'F-';
	 * @param resultMsg  결과 메세지
	 * @param data1Name  데이터 이름
	 * @param data1      데이터
	 * 
	 * @return ResultData 객체
	 */
	public static ResultData buildResultData(String resultCode, String resultMsg, String data1Name, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.resultMsg = resultMsg;
		rd.data1Name = data1Name;
		rd.data1 = data1;

		return rd;
	}

	/**
	 * (Overloading) ResultData 구성, 데이터 정보를 볼 수 있도록
	 * 
	 * @param resultCode 성공 : 'S-'; 실패 : 'F-';
	 * @param resultMsg  결과 메세지
	 * 
	 * @return ResultData.buildResultData(resultCode, resultMsg, null, null);
	 */
	public static ResultData buildResultData(String resultCode, String resultMsg) {
		return ResultData.buildResultData(resultCode, resultMsg, null, null);
	}

	/**
	 * resultCode 성공 실패 판단
	 * 
	 * @return true : 'S-', false: 이외
	 */
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	/** resultCode 성공 실패 판단 */
	public boolean isFail() {
		return isSuccess() == false;
	}
}
