package com.bonc.rdpe.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/**
 * 
 * 类描述： 字符串操作方法 创建人：姚林刚 创建时间：2015年3月11日 下午5:00:43 修改人：Administrator
 * 修改时间：2015年3月11日 下午5:00:43 修改备注：
 * 
 * @version
 */
public class StringUtil {

	// 空字符串转化为"0"
	public static String nullToZero(Object str) {
		if (str == null || "".equals(nullToString(str))) {
			return "0";
		} else {
			try {
				return str.toString().trim();
			} catch (Exception e) {
				return "0";
			}
		}
	}

	// 空字符串转化为""
	public static String nullToString(Object str) {
		if (str == null) {
			return "";
		} else {
			try {
				return str.toString().trim();
			} catch (Exception e) {
				return "";
			}
		}
	}

	// 空字符串转化为 其它值
	public static String nullToOtherString(Object str, String otherValue) {
		if (str == null) {
			return otherValue;
		} else {
			try {
				return str.toString().trim();
			} catch (Exception e) {
				return otherValue;
			}
		}
	}

	/**
	 * stringToAscii asciiToString 中文转成Ascii使用时再转回string 解决中文传递乱码问题
	 * 
	 * @param value
	 * @return
	 */

	public static String stringToAscii(String value) {
		StringBuffer sbu = new StringBuffer();
		if (null != value && !value.equals("")) {
			char[] chars = value.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (i != chars.length - 1) {
					sbu.append((int) chars[i]).append(",");
				} else {
					sbu.append((int) chars[i]);
				}
			}
		}
		return sbu.toString();
	}

	/**
	 * 全角转半角 新方法 可以对字母进行处理
	 * 
	 * @param QJstr
	 * @return
	 */
	public static String SBCchange(String QJstr) {
		QJstr = StringUtil.nullToString(QJstr);
		StringBuffer outStrBuf = new StringBuffer("");
		try {
			String Tstr = "";
			byte[] b = null;
			for (int i = 0; i < QJstr.length(); i++) {
				Tstr = QJstr.substring(i, i + 1);
				// 全角空格转换成半角空格
				if (Tstr.equals("　")) {
					outStrBuf.append(" ");
					continue;
				}
				b = Tstr.getBytes("unicode");
				// 得到 unicode 字节数据
				if (b[2] == -1) {
					// 表示全角？
					b[3] = (byte) (b[3] + 32);
					b[2] = 0;
					outStrBuf.append(new String(b, "unicode"));
				} else {
					outStrBuf.append(Tstr);
				}
			}
		} catch (Exception e) {
			System.out.println("全角转半角失败,返回全角数据");
		}
		return StringUtil.nullToString(outStrBuf.toString()).replaceAll(" ", "").replaceAll("　", "");
	}

	/**
	 * 去掉字符串后面的零
	 */
	public static String cutLastZero(String swjgbm) {
		String jgbm = StringUtil.nullToString(swjgbm);
		if (swjgbm.equals("")) {
			return "";
		}
		String lastvalue = jgbm.substring(jgbm.length() - 1, jgbm.length());
		if (lastvalue.equals("0")) {
			jgbm = jgbm.substring(0, jgbm.length() - 1);
			jgbm = cutLastZero(jgbm);
		}
		return jgbm;
	}

	/**
	 * 去掉字符串前面的零
	 */
	public static String cutFirstZero(String swjgbm) {
		String jgbm = StringUtil.nullToString(swjgbm);
		if (swjgbm.equals("")) {
			return "";
		}
		String firstvalue = jgbm.substring(0, 1);
		if (firstvalue.equals("0")) {
			jgbm = jgbm.substring(1);
			jgbm = cutFirstZero(jgbm);
		}
		return jgbm;
	}

	/**
	 * 过滤input框
	 * 
	 * @param str
	 * @return
	 */
	public static String filterHtml(String tmpStr) {

		Pattern pattern = Pattern.compile("<INPUT[^>]* value=?([^ >]*)?( [^>]*)?>");
		Matcher matcher = pattern.matcher(tmpStr);
		String string = matcher.replaceAll("<u>$1</u>");

		return string;
	}

	public static String round(String value, String format) {
		String back = "";
		try {
			DecimalFormat df = new DecimalFormat(format);
			back = df.format(Double.valueOf(value));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return back;
	}

	public static boolean isNotEmpty(String str) {
		boolean bool = false;
		if (str != null && !str.trim().isEmpty()) {
			bool = true;
		}
		return bool;
	}

	public static String getSmsContent(String messages, String insertString, int size1) {
		ArrayList<String> mes = new ArrayList<String>();
		ArrayList<String> baclMes = new ArrayList<String>();
		if (messages.length() <= size1) {
			mes.add(messages);
		} else if (messages.length() > size1 && messages.length() <= size1 * 2) {
			// 字数大于30小于等于60字第一条为30字 其他字数放到第二条
			mes.add(messages.substring(0, size1));
			mes.add(messages.substring(size1));
		} else {
			int size = 0;
			// 通过字数计算出页数
			if (messages.length() % size1 == 0) {
				size = messages.length() / size1;
			} else {
				size = messages.length() / size1 + 1;
			}
			for (int s = 0; s < size; s++) {
				// 最后一条
				if (s == size - 1) {
					String lastSms = messages.substring(s * size1);
					if (lastSms.length() <= size1) {
						mes.add(lastSms);
					} else if (lastSms.length() > size1 && lastSms.length() <= size1 * 2) {
						mes.add(lastSms.substring(0, size1));
						mes.add(lastSms.substring(size1));
					}
				} else {
					mes.add(messages.substring(s * size1, (s + 1) * size1));
				}
			}
		}
		// 开始添加字符
		if (null != mes && mes.size() > 0) {
			if (mes.size() == 1) {
				baclMes.add(mes.get(0).toString());
			} else {
				for (int m = 0; m < mes.size(); m++) {
					if (m != mes.size() - 1) {
						baclMes.add(mes.get(m).toString() + insertString);
					} else {
						baclMes.add(mes.get(m).toString());
					}
				}
			}
		} else {
			baclMes.add(messages);
		}
		StringBuffer sb = new StringBuffer();
		for (String value : baclMes) {
			sb.append(value);
		}
		return sb.toString();
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 将list数据转换为xml数据,与map2xml一起将数据转换为xml字符串
	 * 
	 * @param mapList
	 * @return
	 */
	public static String mapList2Xml(List<Map> mapList) {
		StringBuffer sb = new StringBuffer();
		sb.append("<list>");
		for (Map map : mapList) {
			sb.append(map2Xml(map));
		}
		sb.append("</list>");
		return sb.toString();
	}

	/**
	 * 将map数据转换为xml数据,与mapList2Xml一起将数据转换为xml字符串
	 * 
	 * @param mapList
	 * @return
	 */
	public static String map2Xml(Map map) {
		StringBuffer sb = new StringBuffer();
		Set set = map.keySet();
		String key = null;
		Iterator<String> it = set.iterator();
		sb.append("<map>");
		while (it.hasNext()) {
			key = it.next();
			sb.append("<key>" + key + "</key>");
			if (map.get(key) instanceof String) {
				sb.append("<value>" + map.get(key) + "</value>");
			} else if (map.get(key) instanceof Map) {
				sb.append("<value>" + map2Xml((Map) map.get(key)) + "</value>");
			} else if (map.get(key) instanceof List) {
				sb.append("<value>" + mapList2Xml((List) map.get(key)) + "</value>");
			}
		}
		sb.append("</map>");
		return sb.toString();
	}

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
	 * 得到crc的压缩码
	 * 
	 * @param str
	 *            要压缩的字符串
	 * @return 压缩完后的字符串
	 */
	public static String getCrc32(String str) {
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		return "K" + crc32.getValue();
	}

	/**
	 * 通过正则表达式匹配字符串
	 * 
	 * @param source
	 * @param reg
	 * @return
	 */
	public static String matchString(String source, String reg) {
		if (source == null || reg == null) {
			return null;
		}
		Matcher m = Pattern.compile(reg).matcher(source);
		if (m.find()) {
			return m.group();
		}
		return null;
	}

	/**
	 * 对字符串进行扩展处理，是字符串长度固定，主要在加密使用，保证产生密文长度固定
	 * 
	 * @param str
	 * @param len
	 * @param c
	 * @return
	 */
	public static String getFixedLenString(String str, int len, char c) {
		if (str == null || str.length() == 0) {
			str = "";
		}
		if (str.length() == len) {
			return str;
		}
		if (str.length() > len) {
			return str.substring(0, len);
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() < len) {
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 得到指定位数随机码
	 * 
	 * @param charCount
	 * @return
	 */
	public static String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	/**
	 * 得到一个数字随机码
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	/**
	 * 获取一定长度的随机字符串
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	/**
	 * 获取8位随机码
	 * 
	 * @return
	 */
	public static String getEightUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

	/**
	 * 
	 * @param difference
	 *            与今天的日期差
	 * @param separator
	 *            分割符
	 * @return
	 */
	public static String getDateString(int difference, String separator) {
		String format = "yyyy" + separator + "MM" + separator + "dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(5, difference);
		return sdf.format(gc.getTime());
	}

	/**
	 * 
	 * @param difference
	 *            与今天的日期差
	 * @return 默认返回格式yyyy-MM-dd
	 */
	public static String getDateString(int difference) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(5, difference);
		return sdf.format(gc.getTime());
	}

	/**
	 * 
	 * @param format
	 *            日期格式
	 * @param difference
	 *            与今天的日期差
	 * @return
	 */
	public static String getDateString(String format, int difference) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(5, difference);
		return sdf.format(gc.getTime());
	}

	/**
	 * 判断字符串是否为空或空值
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}

	/**
	 * 字符串首字母转大写
	 * 
	 * @return
	 */
	public static String captureStr(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	public static void main(String[] args) {
		String s = "Abcd";
		System.out.println(captureStr(s));
	}

}
