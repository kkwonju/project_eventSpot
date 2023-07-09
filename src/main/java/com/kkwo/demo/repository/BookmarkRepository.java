package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Bookmark;

@Mapper
public interface BookmarkRepository {

	public int getBookmarkCountByMemberId(int actorId, int relId);

	public int setBookmark(int actorId, int relId);

	public int unsetBookmark(int actorId, int relId);

}
