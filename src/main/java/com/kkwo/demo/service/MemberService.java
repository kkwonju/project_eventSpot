package com.kkwo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.MemberRepository;
import com.kkwo.demo.vo.Member;
import com.kkwo.demo.vo.ResultData;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData doJoinMember(String loginId, String loginPw, String nickname, String email) {
		int affectedRow = memberRepository.doJoinMember(loginId, loginPw, nickname, email);
		
		if(affectedRow != 1) {
			return ResultData.buildResultData("F-J", "회원가입 실패");
		}
		
		return ResultData.buildResultData("S-1", "회원가입 성공");
	}

	public ResultData doLoginMember(String loginId, String loginPw) {
		
		Member member = memberRepository.getMemberByLoginId(loginId);
		
		if(member == null) {
			return ResultData.buildResultData("F-N", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		if(!(member.getLoginPw().equals(loginPw))) {
			return ResultData.buildResultData("F-M", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		return ResultData.buildResultData("S-1", "로그인 성공");
	}
}
