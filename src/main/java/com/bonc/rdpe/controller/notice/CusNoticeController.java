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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.rdpe.util.JsonUtils;
import com.bonc.rdpe.util.ResultUtil;
import com.bonc.rdpe.util.UserUtil;
import com.bonc.rdpe.service.notice.NoticeService;
import com.bonc.rdpe.vo.ResponseResult;
/**
 * 客户端公告管理Controller
 * 
 * @author qxl
 * @date 2017年2月10日 上午11:04:07
 * @version 1.0.0
 */
@Controller
@RequestMapping("/cusnotice")
public class CusNoticeController {

	@Resource
	private NoticeService noticeService;
	
	//公告管理主页面
	@RequestMapping("/index")
	public String cusIndex(){
		return "notice/noticeReceivingCus";
	}
	
	//已收到公告
	@RequestMapping("/noticeRec")
	@ResponseBody
	public Map<String, Object> noticeRec(HttpServletRequest request,String start,String length){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", UserUtil.getUserId(request));
		Map<String, Object> result = noticeService.noticeRec(paramMap, start, length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,Object>> resultList = (List<Map<String,Object>>)result.get("data");
		for(Map<String,Object> map : resultList){
			map.put("pubTime",sdf.format(map.get("pubdate")));
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
		Object[] arr = JsonUtils.toArray(noticeIds);
		List<String> noticeIdList = new ArrayList<String>();
		Collections.addAll(noticeIdList, (String[])arr);
		noticeService.deleteRecNotice(noticeIdList, UserUtil.getUserId(request));
		return ResultUtil.createSuccessInfo();
	}
	
}
