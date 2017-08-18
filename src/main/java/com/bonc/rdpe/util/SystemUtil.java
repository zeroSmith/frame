package com.bonc.rdpe.util;

public class SystemUtil {
	/**
	 * 判断当前操作系统是不是window
	 * 
	 * @return boolean
	 */
	public static boolean isWindows() {
		boolean flag = false;
		if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 得到当前系统分隔符
	 * @return 当前系统分隔符
	 */
	public static String getFileSeparator(){
		return System.getProperty("file.separator");
	}
	
	/**
	 * 得到系统路径分隔符
	 * @return
	 */
	public static String getPathSeparator(){
		return System.getProperty("path.separator");
	}
	
	/**
	 * 得到系统行分隔符
	 * @return
	 */
	public static String getLineSeparator(){
		return System.getProperty("line.separator");
	}
	
	/**
	 * 获取classes路径
	 * @return
	 */
	public static String getClassLoaderPath(){
		return SystemUtil.class.getClassLoader().getResource("").getPath();
	}
	
	public static void main(String[] arg){
		System.out.println(getClassLoaderPath());
	}
	
}
