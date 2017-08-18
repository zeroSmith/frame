package com.bonc.rdpe.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 数据库查询DAO，封装了基本的查询方法，包括增删改查分页查等。 使用此类时，使用@Resource注解注入。
 * 
 * @author qxl
 * @date 2017年1月10日 上午10:23:07
 * @version 1.0.0
 */
public class DaoHelper {

	private SqlSessionTemplate sqlSessionTemplate;

	public DaoHelper(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	/**
	 * 根据条件删除记录
	 * 
	 * @param mybitsId
	 * @param parameter
	 * @return
	 */
	public int delete(String mybitsId, Object parameter) {
		return sqlSessionTemplate.delete(mybitsId, parameter);
	}

	/**
	 * 将指定的对象插入到数据库
	 * 
	 * @param mybitsId
	 * @param parameter
	 * @return
	 */
	public int insert(String mybitsId, Object parameter) {
		return sqlSessionTemplate.insert(mybitsId, parameter);
	}

	/**
	 * 更新
	 * 
	 * @param mybitsId
	 * @param parameter
	 * @return
	 */
	public int update(String mybitsId, Object parameter) {
		return sqlSessionTemplate.update(mybitsId, parameter);
	}

	/**
	 * 无条件查询结果集，返回List<T>
	 * 
	 * @param mybitsId
	 * @return
	 */
	public <T> List<T> queryForList(String mybitsId) {
		return sqlSessionTemplate.selectList(mybitsId);
	}

	/**
	 * 查询结果集，查询参数为Map，返回List<T>
	 * 
	 * @param mybitsId
	 * @param params
	 * @return
	 */
	public <T> List<T> queryForList(String mybitsId, Map<String, Object> params) {
		return sqlSessionTemplate.selectList(mybitsId, params);
	}

	/**
	 * 查询结果集，查询参数为Object，返回List<T>
	 * 
	 * @param mybitsId
	 * @param parameter
	 * @return
	 */
	public <T> List<T> queryForList(String mybitsId, Object parameter) {
		return sqlSessionTemplate.selectList(mybitsId, parameter);
	}

	/**
	 * 分页查询
	 * 
	 * @param mybitsId
	 * @param params
	 * @param start
	 * @param size
	 * @return
	 */
	public <T> Map<String, Object> queryForPageList(String mybitsId, Object params, String start, String size) {
		int pageSize = Integer.parseInt((size == null || "0".equals(size)) ? "5" : size);
		int currentPage = Integer.parseInt((start == null) ? "0" : start) / pageSize + 1;
		PageHelper.startPage(currentPage, pageSize);
		List<T> result = sqlSessionTemplate.selectList(mybitsId, params);
		PageInfo<T> page = new PageInfo<T>(result);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", page.getTotal());
		resultMap.put("data", page.getList());
		return resultMap;
	}

	/**
	 * 分页查询--默认从第一页开始查，每页5条
	 * 
	 * @param mybitsId
	 * @param params
	 * @return
	 */
	public <T> Map<String, Object> queryForPageList(String mybitsId, Object params) {
		return queryForPageList(mybitsId, params, null, null);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param mybitsId
	 * @param parameter
	 * @return
	 */
	public <T> Object queryOne(String mybitsId, Object parameter) {
		return sqlSessionTemplate.selectOne(mybitsId, parameter);
	}
}
