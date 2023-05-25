package com.kkwo.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.EventService;
import com.kkwo.demo.service.ReplyService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Event;
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

//	@RequestMapping("/usr/reply/doWrite")
//	@ResponseBody
//	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {
//
//		if (Ut.isEmpty(relTypeCode)) {
//			return Ut.jsHistoryBack("relTypeCode을(를) 입력해주세요");
//		}
//
//		if (Ut.isEmpty(relId)) {
//			return Ut.jsHistoryBack("relId을(를) 입력해주세요");
//		}
//
//		if (Ut.isEmpty(body)) {
//			return Ut.jsHistoryBack("내용을 입력해주세요");
//		}
//
//		ResultData writeReplyRd = replyService.writeReply(relTypeCode, relId, body, rq.getLoginedMemberId());
//
//		int id = (int) writeReplyRd.getData1();
//
//		if (Ut.isEmpty(replaceUri)) {
//			replaceUri = Ut.f("../article/detail?id=%d", relId);
//		}
//
//		return Ut.jsReplace(Ut.f("%d번 댓글이 생성되었습니다", id), replaceUri);
//	}

	/* 댓글 수정 폼 */
	@RequestMapping("/usr/reply/modify")
	public String showModify(Model model, int id) {

		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);

		if (reply == null) {
			return rq.jsHistoryBackOnView(Ut.f("%d번 글은 존재하지 않습니다", id));
		}

		ResultData actorCanModifyRd = replyService.actorCanModify(rq.getLoginedMemberId(), reply);

		if (actorCanModifyRd.isFail()) {
			return rq.jsHistoryBackOnView(actorCanModifyRd.getResultMsg());
		}

		Event event = eventService.getEventById(reply.getRelId());

		model.addAttribute("reply", reply);
		model.addAttribute("event", event);

		return "usr/reply/modify";
	}

	/* 댓글 수정 */
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body, String replaceUri) {

		Reply reply = replyService.getReply(id);

		if (reply == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 댓글은 존재하지 않습니다", id));
		}

		ResultData actorCanModifyRd = replyService.actorCanModify(rq.getLoginedMemberId(), reply);

		if (actorCanModifyRd.isFail()) {
			return Ut.jsHistoryBack(actorCanModifyRd.getResultMsg());
		}

		ResultData replyModifyRd = replyService.modifyReply(id, body);

		if (Ut.isEmpty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
		}

		return Ut.jsReplace(replyModifyRd.getResultMsg(), replaceUri);
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
