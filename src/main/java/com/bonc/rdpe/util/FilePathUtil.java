package com.bonc.rdpe.util;

import java.io.File;

/**
 * 文件操作
 * 
 * @author Administrator
 *
 */
public class FilePathUtil {

	/**
	 * 从文件的完整路径名中提取路径
	 * 
	 * @param _sFilePathName
	 * @return
	 */
	public static String extractFilePath(String filePathName) {
		int nPos = filePathName.lastIndexOf(File.separatorChar);
		if (nPos < 0) {
			nPos = filePathName.lastIndexOf('/');
			if (nPos < 0) {
				nPos = filePathName.lastIndexOf('\\');
			}
		}
		return (nPos >= 0 ? filePathName.substring(0, nPos + 1) : "");
	}

	/**
	 * 从文件的完整路径名中提取文件名
	 * 
	 * @param filePathName
	 * @param fileSeparator
	 * @return
	 */
	public static String extractFileName(String filePathName) {
		int nPos = filePathName.lastIndexOf(File.separatorChar);
		if (nPos < 0) {
			nPos = filePathName.lastIndexOf('/');
			if (nPos < 0) {
				nPos = filePathName.lastIndexOf('\\');
			}
		}
		return filePathName.substring(nPos + 1);
	}
	
	public static void main(String[] args) {
		String s  = "/opt/beh/core/adddd.jar";
		System.out.println(FilePathUtil.extractFilePath(s));
		System.out.println(FilePathUtil.extractFileName(s));
	}
	
}
