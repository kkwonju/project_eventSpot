package com.kkwo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.BookmarkRepository;
import com.kkwo.demo.vo.Bookmark;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	public BookmarkService(BookmarkRepository bookmarkRepository) {
		this.bookmarkRepository = bookmarkRepository;
	}

	public List<Bookmark> getBookmarkList(int actorid) {
		return bookmarkRepository.getBookmarkList(actorid);
	}
}
