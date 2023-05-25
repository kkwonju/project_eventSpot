package com.kkwo.demo.vo;

import java.util.Map;

import com.kkwo.demo.util.Ut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResultData {
	@Getter
	private String resultCode;
	@Getter
	private String resultMsg;
	@Getter
	private String data1Name;
	@Getter
	private Object data1;
	@Getter
	private String data2Name;
	@Getter
	private Object data2;
	
	
	private Map<String, Object> body;

	public ResultData(String resultCode, String resultMsg, Object... args) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.body = Ut.mapOf(args);
	}

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
	
	public void setData2(String data2Name, Object data2) {
		this.data2Name = data2Name;
		this.data2 = data2;
	}
}
