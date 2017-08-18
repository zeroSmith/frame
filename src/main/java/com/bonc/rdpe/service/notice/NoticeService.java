package com.bonc.rdpe.service.notice;

import java.util.List;
import java.util.Map;

import com.bonc.rdpe.entity.notice.Notice;
import com.bonc.rdpe.vo.ResponseResult;

/**
 * 公告管理service接口，此接口内包含对公告信息的一些常用操作的封装
 * @author 作者: 吕一凡 
 * @date 2017年2月7日 下午4:19:27 
 * @version 版本: 1.0
 */
public interface NoticeService {
	
	/**
	 * 分页查询所有的公告
	 * @param paramMap
	 * @param start
	 * @param length
	 * @return
	 */
	public Map<String, Object> getNoticeByCondition(Map<String,Object> paramMap,String start,String length);
	
	/**
	 * 根据公告Id查询该公告信息
	 * @param noticeId 公告Id
	 * @return
	 */
	public Notice selectByNoticeId(String noticeId);
	
	/**
	 * 新增公告信息
	 * @param notice 公告对象
	 * @return
	 * @throws Exception
	 */
	public int insert(Notice notice)throws Exception;
	
	/**
	 * 更新公告信息
	 * @param notice 公告对象
	 * @return
	 */
	public int update(Notice notice);
	
	/**
	 * 根据公告Id删除已发布的公告
	 * @param noticeId 公告Id
	 * @return
	 * @throws Exception
	 */
	public int deleteSendNoticeByNoticeId(List<String> noticeIdList)throws Exception;
	
	/**
	 * 发布公告
	 * @return
	 * @throws Exception
	 */
	public void publishNotice(Notice notice,List<String> userIdList)throws Exception;
	
	/**
	 * 根据公告Id删除已收到的公告
	 * @return
	 * @throws Exception
	 */
	public int deleteRecNotice(List<String> noticeIdList,String userId)throws Exception;
	
	/**
	 * 查询未读公告
	 * @param userId 用户Id
	 * @return
	 */
	public Map<String,Object> selectUnreadNotice(String userId);

	/**
	 * 全部标记为已读
	 * @param loginUserId
	 * @return
	 */
	public ResponseResult markRead(String loginUserId);

	/**
	 * 已发送公告
	 * @param paramMap
	 * @param start
	 * @param length
	 * @return
	 */
	public Map<String, Object> noticeSend(Map<String, Object> paramMap, String start, String length);

	/**
	 * 已收到公告
	 * @param paramMap
	 * @param start
	 * @param length
	 * @return
	 */
	public Map<String, Object> noticeRec(Map<String, Object> paramMap, String start, String length);

	/**
	 * 查询公告详情
	 * @param noticeId
	 * @return
	 */
	public Notice selectNoticeDetail(String noticeId,String loginUserId);
}
