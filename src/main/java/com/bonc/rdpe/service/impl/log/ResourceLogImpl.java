package com.bonc.rdpe.service.impl.log;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bonc.rdpe.dao.DaoHelper;
import com.bonc.rdpe.util.IdUtil;
import com.bonc.rdpe.entity.log.ResourceLog;
import com.bonc.rdpe.service.log.ResourcesLogService;

/** 
 * @author 作者: jxw 
 * @date 创建时间: 2017年1月23日 下午5:12:43 
 * @version 版本: 1.0 
*/

@Service("resourceLogImpl")
public class ResourceLogImpl implements ResourcesLogService{
	
	@Resource
	private DaoHelper daoHelper;

	@Override
	public void doLog(ResourceLog log) {
		if(log!=null){
			log.setLogId(IdUtil.genUUID());
			log.setLogDate(new Date());
			daoHelper.insert("com.bonc.frame.web.mapper.log.ResourceLogMapper.insertSelective", log);
		}
		
	}

}

