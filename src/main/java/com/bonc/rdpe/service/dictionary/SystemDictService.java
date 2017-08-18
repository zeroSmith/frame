/**
 * Project Name		[rdpe]
 * File Name		[com.bonc.rdpe.service.dictionary.SystemDictService.java]
 * Creation Date	[2017年4月25日]
 * <p>
 * Copyright© 2017 www.bonc.com.cn All Rights Reserved
 * <p>
 */
package com.bonc.rdpe.service.dictionary;

import java.util.List;
import java.util.Map;

import com.bonc.rdpe.entity.sysdict.SystemDict;

/**
 * @author 王晓星
 * 
 * 2017年4月25日 下午1:55:09
 */
public interface SystemDictService {
	
	int remove(String propName);

    int add(SystemDict systemDict);

    SystemDict getByKey(String propName);

    int edit(SystemDict systemDict);
    
    List<SystemDict> getAll();
    
    public Map getByCondition(String start, String length, Map<String, Object> paramMap);
}
