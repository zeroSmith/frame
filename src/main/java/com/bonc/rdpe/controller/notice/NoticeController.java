package com.bonc.rdpe.controller.notice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.util.JsonUtils;
import com.bonc.rdpe.util.ResultUtil;
import com.bonc.rdpe.util.UserUtil;
import com.bonc.rdpe.entity.notice.Notice;
import com.bonc.rdpe.service.notice.NoticeService;
import com.bonc.rdpe.vo.ResponseResult;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Resource
	private NoticeService noticeService;
	
	//公告管理主页面
	@RequestMapping("/index")
	public String index(HttpServletRequest request,String fromSign){
		request.setAttribute("fromSign", fromSign);
		return "notice/index";
	}
	
	//公告管理主页面
	@RequestMapping("/cusIndex")
	public String cusIndex(){
		return "notice/noticeReceivingCus";
	}
	
	//已发送
	@RequestMapping("/noticeSend")
	@ResponseBody
	public Map<String, Object> noticeSend(HttpServletRequest request,String start,String length){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", UserUtil.getUserId(request));
		Map<String, Object> result = noticeService.noticeSend(paramMap, start, length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Notice> resultList = (List<Notice>)result.get("data");
		for(Notice notice : resultList){
			notice.setPubTime(sdf.format(notice.getPubdate()));;
		}
		return result;
	}
	
	//已收到公告
	@RequestMapping("/noticeRec")
	@ResponseBody
	public Map<String, Object> noticeRec(HttpServletRequest request,String start,String length){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", UserUtil.getUserId(request));
		Map<String, Object> result = noticeService.noticeRec(paramMap, start, length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,String>> resultList = (List<Map<String,String>>)result.get("data");
		for(Map<String,String> map : resultList){
			map.put("pubTime",sdf.format(map.get("pubdate")));
		}
		return result;
	}
	
	//草稿
	@RequestMapping("/noticeDraft")
	@ResponseBody
	public Map<String, Object> noticeDraft(HttpServletRequest request,String start,String length){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", UserUtil.getUserId(request));
		paramMap.put("state", "-2");//草稿
		Map<String, Object> result = noticeService.getNoticeByCondition(paramMap, start, length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Notice> resultList = (List<Notice>)result.get("data");
		for(Notice notice : resultList){
			notice.setPubTime(sdf.format(notice.getPubdate()));
		}
		return result;
	}
	
	//全部标记为已读
	@RequestMapping("/markRead")
	@ResponseBody
	public ResponseResult markRead(HttpServletRequest request){
		ResponseResult result = noticeService.markRead(UserUtil.getUserId(request));
		return result;
	}
	
	
	//删除收到的公告
	@RequestMapping("/deleteRecNotice")
	@ResponseBody
	public ResponseResult deleteRecNotice(HttpServletRequest request,String noticeId) throws Exception{
		List<String> noticeIdList = new ArrayList<String>();
		noticeIdList.add(noticeId);
		noticeService.deleteRecNotice(noticeIdList, UserUtil.getUserId(request));
		return ResultUtil.createSuccessInfo();
	}
	
	//批量删除收到的公告
	@RequestMapping("/batchDeleteRecNotice")
	@ResponseBody
	public ResponseResult batchDeleteRecNotice(HttpServletRequest request,String noticeIds) throws Exception{
		List<String> noticeIdList = JsonUtils.toList(noticeIds,null);
//		List<String> noticeIdList = new ArrayList<String>();
//		Collections.addAll(noticeIdList, (String[])arr);
		noticeService.deleteRecNotice(noticeIdList, UserUtil.getUserId(request));
		return ResultUtil.createSuccessInfo();
	}
	
	//删除发布的公告或草稿
	@RequestMapping("/deleteSendNotice")
	@ResponseBody
	public ResponseResult deleteSendNotice(String noticeId) throws Exception{
		List<String> noticeIdList = new ArrayList<String>();
		noticeIdList.add(noticeId);
		noticeService.deleteSendNoticeByNoticeId(noticeIdList);
		return ResultUtil.createSuccessInfo();
	}
	
	//批量删除发布的公告或草稿
	@RequestMapping("/batchDeleteSendNotice")
	@ResponseBody
	public ResponseResult batchDeleteSendNotice(String noticeIds) throws Exception{
		List<String> noticeIdList = JsonUtils.toList(noticeIds, null);
//		List<String> noticeIdList = new ArrayList<String>();
//		Collections.addAll(noticeIdList, (String[])arr);
		noticeService.deleteSendNoticeByNoticeId(noticeIdList);
		return ResultUtil.createSuccessInfo();
	}
	
	
	//发布公告
	@RequestMapping("/pubNotice")
	@ResponseBody
	public ResponseResult pubNotice(HttpServletRequest request,Notice notice,String orgIdList) throws Exception{
		List<String> arr = JsonUtils.toList(orgIdList,null);
//		List<String> userIdList = new ArrayList<String>();
//		Collections.addAll(userIdList, (String[])arr);
		notice.setPubUserId(UserUtil.getUserResource(request).getUserId());;
		noticeService.publishNotice(notice, arr);
		return ResultUtil.createSuccessInfo();
	}
	
	//保存草稿
	@RequestMapping("/saveDraft")
	@ResponseBody
	public ResponseResult saveDraft(HttpServletRequest request,Notice notice) throws Exception{
		notice.setPubUserId(UserUtil.getUserId(request));
		if("".equals(notice.getNoticeId())){			
			noticeService.insert(notice);
		}else{
			noticeService.update(notice);
		}
		return ResultUtil.createSuccessInfo();
	}
	
	//编辑公告/发布新公告
	@RequestMapping("/editNotice")
	public String editNotice(String noticeId,String fromSign,Model model){
		model.addAttribute("fromSign", fromSign);
		if(noticeId==null || fromSign==null || "0".equals(fromSign)){//新建公告
			model.addAttribute("fromSign", "0");
			return "notice/noticeEdit";
		}
		Notice notice = noticeService.selectByNoticeId(noticeId);
		model.addAttribute("noticeTitle", notice.getNoticeTitle());
		model.addAttribute("noticeContent", notice.getNoticeContent());
		model.addAttribute("recOrgId", notice.getRecOrgId());
		model.addAttribute("noticeId", notice.getNoticeId());
		model.addAttribute("noticeType", notice.getNoticeType());
		return "notice/noticeEdit";
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
