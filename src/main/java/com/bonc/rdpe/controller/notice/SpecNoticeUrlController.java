package com.bonc.rdpe.controller.notice;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.util.OnlineUserList;
import com.bonc.rdpe.util.UserUtil;
import com.bonc.rdpe.entity.notice.Notice;
import com.bonc.rdpe.service.notice.NoticeService;

/** 
 * 此controller里面的url是公告管理客户端和管理端通用的，不在拦截范围内
 * @author qxl
 * @date 2017年2月10日 下午12:37:06 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/specnotice")
public class SpecNoticeUrlController {
	
	@Resource
	private NoticeService noticeService;
	
	//查询未读公告
	@ResponseBody
	@RequestMapping("/selectUnreadNotice")
	public Map selectUnreadNotice(HttpServletRequest request){
		return noticeService.selectUnreadNotice(UserUtil.getUserId(request));
	}

	@ResponseBody
	@RequestMapping("/selectOnlineUser")
	public int selectOnlineUser(HttpServletRequest request){
		return OnlineUserList.getInstance().getUserMap().size();
	}
	
	//查看公告详情
	@RequestMapping("/noticeDetail")
	public String noticeDetail(String noticeId,String fromSign,HttpServletRequest request,Model model){
		model.addAttribute("fromSign", fromSign);
		Notice notice = noticeService.selectNoticeDetail(noticeId,UserUtil.getUserId(request));
		model.addAttribute("noticeTitle", notice.getNoticeTitle());
		model.addAttribute("noticeContent", notice.getNoticeContent());
		model.addAttribute("pubdate", notice.getPubdate());
		model.addAttribute("pubPerson", notice.getPubPerson());
		return "notice/noticeDetail";
	}
}
