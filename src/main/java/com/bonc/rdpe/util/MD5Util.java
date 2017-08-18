package com.bonc.rdpe.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;


public class MD5Util {
	
	private static final Logger log = Logger.getLogger(MD5Util.class);
	
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static String Bit32(String SourceString) {
		byte[] messageDigest = new byte[]{};
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(SourceString.getBytes());
			messageDigest = digest.digest();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return toHexString(messageDigest);
	}

	public static String Bit16(String SourceString) {
		return Bit32(SourceString).substring(8, 24);
	}
}