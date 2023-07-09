package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Member;

@Mapper
public interface MemberRepository {

	// INSERT
	int doJoinMember(String loginId, String loginPw, String nickname, String email);

	// SELECT
	int getLastInsertId();

	// SELECT
	Member getMemberById(int id);

	// SELECT
	Member getMemberByLoginId(String loginId);

	// SELECT
	Member getMemberByEmail(String email);

	// SELECT
	List<Member> getMembers();

	// DELETE
	int deleteMember(int id);

	// SELECT
	List<Member> getForPrintMembers(String searchKeyword);

	// UPDATE
	int doModify(int id, String newNickname, String newLoginPw);

	// SELECT
	Member getMemberByNickname(String nickname);

	// SELECT
	Member getAdminByLoginId(String loginId);
}