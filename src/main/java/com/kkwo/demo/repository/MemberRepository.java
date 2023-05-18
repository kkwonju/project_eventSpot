package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Member;

@Mapper
public interface MemberRepository {

	int doJoinMember(String loginId, String loginPw, String nickname, String email);

	int getLastInsertId();

	Member getMemberById(int id);

	Member getMemberByLoginId(String loginId);

	Member getMemberByEmail(String email);

	List<Member> getMembers();

	int deleteMember(int id);

	List<Member> getForPrintMembers(String searchKeyword);

}
