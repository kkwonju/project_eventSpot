package com.kkwo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.service.ReplyService;
import com.kkwo.demo.util.Ut;
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
	public String doDelete(int id, String replaceUri) {

		Reply reply = replyService.getReply(id);

		if (reply == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 댓글은 존재하지 않습니다", id));
		}

		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack(Ut.f("%d번 댓글에 대한 권한이 없습니다", id));
		}

		ResultData deleteReplyRd = replyService.deleteReply(id);

		if (Ut.isEmpty(replaceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}
		return Ut.jsReplace(Ut.f("%d번 댓글을 삭제했습니다", id), replaceUri);
	}

	@RequestMapping("/usr/reply/list")
	@ResponseBody
	public ResultData list(String relTypeCode, int relId) {
		List<Reply> replies = replyService.getRepliesByRel(relTypeCode, relId);
		return ResultData.buildResultData("S-1", "리스트를 불러옵니다", "replies", replies);
	}
}
