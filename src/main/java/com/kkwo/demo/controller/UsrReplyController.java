package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.service.ReplyService;
import com.kkwo.demo.vo.Reply;
import com.kkwo.demo.vo.ResultData;
import com.kkwo.demo.vo.Rq;

@Controller
public class UsrReplyController {
	@Autowired
	private Rq rq;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private EventService eventService;

	public UsrReplyController(ReplyService replyService, EventService eventService) {
		this.replyService = replyService;
		this.eventService = eventService;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public ResultData doWrite(String relTypeCode, int relId, String body) {

		ResultData writeReplyRd = replyService.writeReply(relTypeCode, relId, body, rq.getLoginedMemberId());

		List<Reply> replyList = replyService.getForPrintReplies(rq.getLoginedMemberId(), relTypeCode, relId);

		return ResultData.buildResultData("S-1", "", "replyList", replyList);
	}

	@RequestMapping("/usr/reply/delete")
	@ResponseBody
	public ResultData doDelete(int replyId) {
		Reply reply = replyService.getReply(replyId);
		int relId = reply.getRelId();
		
		replyService.deleteReply(replyId);
		
		ResultData Rd = ResultData.buildResultData("S-1", "댓글 삭제", "relId", relId);
		
		return Rd;
	}

	@RequestMapping("/usr/reply/list")
	@ResponseBody
	public ResultData list(String relTypeCode, int relId) {
		List<Reply> replies = replyService.getRepliesByRel(relTypeCode, relId);
		ResultData Rd = ResultData.buildResultData("S-1", "리스트를 불러옵니다", "replies", replies);
		Rd.setData2("loginedMemberId", rq.getLoginedMemberId());
		return Rd;
	}
}
