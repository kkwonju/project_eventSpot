package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkwo.demo.vo.Bookmark;

@Mapper
public interface BookmarkRepository {

	public List<Bookmark> getBookmarkList(int actorid);

}
