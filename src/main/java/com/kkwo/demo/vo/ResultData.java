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
	
	/** ResultData 구성, 데이터 정보를 볼 수 있도록 */
	public static ResultData buildResultData(String resultCode, String resultMsg, String data1Name, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.resultMsg = resultMsg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		
		return rd;
	}
	
	public static ResultData buildResultData(String resultCode, String resultMsg) {
		return ResultData.buildResultData(resultCode, resultMsg, null, null);
	}
	
	/** 데이터의 성공/실패 판단 */
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	/** 데이터의 성공/실패 판단 */
	public boolean isFail() {
		return isSuccess() == false;
	}
}
