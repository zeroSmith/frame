/**
 * Project Name		[rdpe]
 * File Name		[com.bonc.rdpe.service.impl.dictionary.SystemDictServiceImpl.java]
 * Creation Date	[2017年4月26日]
 * <p>
 * Copyright© 2017 www.bonc.com.cn All Rights Reserved
 * <p>
 */
package com.bonc.rdpe.service.impl.dictionary;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bonc.rdpe.dao.DaoHelper;
import com.bonc.rdpe.entity.sysdict.SystemDict;
import com.bonc.rdpe.service.dictionary.SystemDictService;

/**
 * @author 王晓星
 * 
 * 2017年4月26日 上午10:38:06
 */


@Service("systemDictService")
public class SystemDictServiceImpl implements SystemDictService{

	@Resource
	private DaoHelper daoHelper;
	
	@Override
	public int remove(String propName) {
		return daoHelper.delete("com.bonc.rdpe.dao.mapper.SystemDictMapper.deleteByPrimaryKey", propName);
	}

	@Override
	public int add(SystemDict systemDict) {
		return daoHelper.insert("com.bonc.rdpe.dao.mapper.SystemDictMapper.insert", systemDict);
	}

	@Override
	public SystemDict getByKey(String propName) {
		return (SystemDict) daoHelper.queryOne("com.bonc.rdpe.dao.mapper.SystemDictMapper.selectByPrimaryKey", propName);
	}

	@Override
	public int edit(SystemDict systemDict) {
		return daoHelper.update("com.bonc.rdpe.dao.mapper.SystemDictMapper.updateByPrimaryKeySelective", systemDict);
	}

	@Override
	public List<SystemDict> getAll() {
		return daoHelper.queryForList("com.bonc.rdpe.dao.mapper.SystemDictMapper.selectAll");
	}

	@Override
	public Map getByCondition(String start, String length, Map<String, Object> paramMap) {
		return daoHelper.queryForPageList("com.bonc.rdpe.dao.mapper.SystemDictMapper.selectAllByCondition", paramMap, start, length);
	}

}
