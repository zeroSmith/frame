package com.bonc.rdpe.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 
* 类描述：   配置文件读取类
* 创建人：Administrator   
* 创建时间：2015年3月11日 下午4:57:55   
* 修改人：Administrator   
* 修改时间：2015年3月11日 下午4:57:55   
* 修改备注：   
* @version
 */
public class PropertyUtil {
	/**
	 * 读取配置文件,以map方式返回所有配置信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> readProperties(String propName){
		Properties props = new Properties();
		Map<String,String> map = new HashMap<String,String>();
		try {
	         InputStream in = StringUtil.class.getClassLoader().getResourceAsStream(propName);
	         props.load(in);
	         Enumeration en = props.propertyNames();
		         while (en.hasMoreElements()) {	 
		              String key = (String) en.nextElement();
		              String Property = props.getProperty (key);
		              map.put(key,Property);
		         }
	    } catch (Exception e) {
	         e.printStackTrace();
	    }
		return map;
	}
	
	/**
	 * 读取制定配置文件的制定属性信息
	 * @param propertiesname
	 * @param key
	 * @return
	 */
	public static String getPropertiesValue(String propertiesname, String key){
		String back = "";
		try {
			back = StringUtil.nullToString(ResourceBundle.getBundle(propertiesname).getString(key));
		} catch (Exception e) {
			System.out.println("获取"+propertiesname+".properties文件的"+key+"的值失败");
		}
		
		return back;
	}
	
	public static String getProductPath(){
		return PropertyUtil.class.getClassLoader().getResource("").toString();
	}

	/**
	 * 得到包含字符开头的属性信息
	 * @param preStr
	 * @return
	 */
	public static Map getPreProperties(String path,String preStr){
		Map map=PropertyUtil.readProperties(path);
		Map resMap = new HashMap();
		Iterator iterator = map.keySet().iterator();
		String key = null;
		while(iterator.hasNext()){
			key = (String) iterator.next();
			if(key.indexOf(preStr)==0){
				resMap.put(key, map.get(key));
			}
		}
		return resMap;
	}
	
}
