package com.kkwo.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Member;

@Mapper
public interface MemberRepository {

	int doJoinMember(String loginId, String loginPw, String nickname, String email);

	int getLastInsertId();

	Member getMemberByid(int id);

	Member getMemberByLoginId(String loginId);

}
