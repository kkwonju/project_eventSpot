package com.kkwo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.MemberRepository;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Member;
import com.kkwo.demo.vo.ResultData;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MailService mailService;
	
	@Value("${custom.siteMainUri}")
	private String siteMainUri;
	@Value("${custom.siteName}")
	private String siteName;
	
	// 회원 서비스 클래스
	
	// MemberRepository 객체를 주입받는 생성자
	public MemberService(MailService mailService, MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		this.mailService = mailService;
	}

	/** 
	 * 
	 *  */
	public ResultData doJoinMember(String loginId, String loginPw, String nickname, String email) {
		Member member = getMemberByLoginId(loginId);
		if(member != null) {
			return ResultData.buildResultData("F-E", "이미 존재하는 아이디입니다");
		}
		
		member = getMemberByEmail(email);
		if(member != null) {
			return ResultData.buildResultData("F-E", "이미 존재하는 이메일입니다");
		}
		
		String encryptedPassword = Ut.sha256(loginPw);
		
		int affectedRow = memberRepository.doJoinMember(loginId, encryptedPassword, nickname, email);
		
		if(affectedRow != 1) {
			return ResultData.buildResultData("F-J", "회원가입 실패");
		}
		
		return ResultData.buildResultData("S-1", "회원가입 성공");
	}

	public ResultData doLoginMember(String loginId, String loginPw) {
		
		Member member = getMemberByLoginId(loginId);
		
		if(member == null) {
			return ResultData.buildResultData("F-N", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		if(!(member.getLoginPw().equals(Ut.sha256(loginPw)))) {
			return ResultData.buildResultData("F-M", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		return ResultData.buildResultData("S-1", "로그인 성공");
	}
	

	public ResultData doLoginAdmin(String loginId, String loginPw) {
		Member member = getAdminByLoginId(loginId);
		
		if(member == null) {
			return ResultData.buildResultData("F-N", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		if(!(member.getLoginPw().equals(Ut.sha256(loginPw)))) {
			return ResultData.buildResultData("F-M", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		return ResultData.buildResultData("S-1", "로그인 성공");
	}
	
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	public Member getAdminByLoginId(String loginId) {
		return memberRepository.getAdminByLoginId(loginId);
	}
	
	public Member getMemberByEmail(String email) {
		return memberRepository.getMemberByEmail(email);
	}

	public Member getMemberByNickname(String nickname) {
		return memberRepository.getMemberByNickname(nickname);
	}
	
	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	public List<Member> getForPrintMembers(String searchKeyword) {
		return memberRepository.getForPrintMembers(searchKeyword);
	}
	
	public int deleteMember(int id) {
		int affectedRow = memberRepository.deleteMember(id);
		
		if(affectedRow != 1) {
			return -1;
		}
		
		return affectedRow;
	}

	public ResultData doModify(int id, String newNickname, String newLoginPw) {
		Member member = getMemberById(id);
		
		if(member == null) {
			return ResultData.buildResultData("F-1", "탈퇴했거나 가입하지 않은 회원입니다");
		}
		
		int affectRow = memberRepository.doModify(id, newNickname, newLoginPw);
		
		if(affectRow != 1) {
			return ResultData.buildResultData("F-2", "수정 실패");
		}

		return ResultData.buildResultData("S-1", "회원정보를 수정했습니다");
	}

	public ResultData notifyTempLoginPwByEmail(Member actor) {
		String title = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Ut.getTempPassword(6);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a href=\"" + siteMainUri + "/usr/member/login\" target=\"_blank\">로그인 하러가기</a>";

		ResultData sendResultData = mailService.send(actor.getEmail(), title, body);

		if (sendResultData.isFail()) {
			return sendResultData;
		}

		setTempPassword(actor, tempPassword);

		return ResultData.buildResultData("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다.");
	}
	
	private void setTempPassword(Member actor, String tempPassword) {
		memberRepository.doModify(actor.getId(), null, Ut.sha256(tempPassword));
	}

}
