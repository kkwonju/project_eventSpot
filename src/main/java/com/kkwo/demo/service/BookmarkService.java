package com.kkwo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.BookmarkRepository;
import com.kkwo.demo.vo.ResultData;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkRepository bookmarkRepository;

	public BookmarkService(BookmarkRepository bookmarkRepository) {
		this.bookmarkRepository = bookmarkRepository;
	}

	public boolean actorCanMakeBookmark(int actorId, int relId) {
		if (actorId == 0) {
			return false;
		}
		return bookmarkRepository.getBookmarkCountByMemberId(actorId, relId) == 0;
	}

	public ResultData setBookmark(int actorId, int relId) {
		int affectRow = bookmarkRepository.setBookmark(actorId, relId);

		if (affectRow != 1) {
			return ResultData.buildResultData("F-1", "북마크 저장 실패");
		}
		
		return ResultData.buildResultData("S-1", "북마크 저장되었습니다");
	}
	
	public ResultData unsetBookmark(int actorId, int relId) {
		int affectRow = bookmarkRepository.unsetBookmark(actorId, relId);
		
		if (affectRow != 1) {
			return ResultData.buildResultData("F-1", "북마크 제거 실패");
		}
		
		return ResultData.buildResultData("S-1", "북마크 제거되었습니다");
	}
}
