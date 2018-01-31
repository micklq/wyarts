package com.arts.org.base.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * 工具类
 * 
 * */
public class Util {

	// Guid
	static final String guid_pat = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
	// 整数
	static final String num_pat = "^-?\\d+$";
	// 手机号
	static final String mobile_pat = "^1(\\d){10}$";
	// ip v4
	static final String ip4_pat = "^\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}$";

	static final String SecuritySalt = "caihong.com";


	/**
	 * 将list中的元素连接成字符串 <br >
	 * 默认格式：元素本身，默认连接符:,
	 * 
	 * @param list
	 *            数据源
	 * 
	 * @return 连接后的字符串
	 * */
	public static <T> String join(List<T> list) {
		return join(list, "%s", ",");
	}

	/**
	 * 将list中的元素连接成字符串
	 * 
	 * @param list
	 *            数据源
	 * 
	 * @param format
	 *            每个元素的包装格式，常用的：%s，'%s',"%s"
	 * 
	 * @param joinChar
	 *            元素之间的连接符，常用的：逗号（,），封号（;），竖线（|）等
	 * 
	 * @return 连接后的字符串
	 * */
	public static <T> String join(List<T> list, String format, String joinChar) {

		if (list == null || list.size() == 0)
			return "";

		String _format = format;
		if (isNullOrEmpty(_format))
			_format = "%s";

		String _joinChar = joinChar;
		if (isNullOrEmpty(_joinChar))
			_joinChar = ",";

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			String item = String.format(_format, list.get(i));
			builder.append(item);

			if (i < list.size() - 1) {
				builder.append(_joinChar);
			}
		}

		String result = builder.toString();

		return result;
	}

	/**
	 * 判断字符串是否为空
	 * */
	public static Boolean isNullOrEmpty(String value) {

		if (value == null || value.length() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 判断是否为Guid格式
	 * */
	public static boolean isGuid(String input) {
		return isMatch(guid_pat, input);
	}

	/**
	 * 判断是否手机号格式:1开头的11位数字
	 * */
	public static boolean isMobile(String input) {
		return isMatch(mobile_pat, input);
	}

	/**
	 * 判断是否IP:V4
	 * */
	public static boolean isIP(String input) {
		return isMatch(ip4_pat, input);
	}

	/**
	 * 转为long
	 * 
	 * @return 转换后的数值，转换失败时返回0
	 * */
	public static long toLong(String value) {
		return toLong(value, 0);
	}

	/**
	 * 转为long
	 * 
	 * @param def
	 *            :转换失败后返回的默认值
	 * */
	public static long toLong(String value, long def) {
		if (value == null || value.length() == 0)
			return def;

		if (!isNumber(value)) {
			return def;
		}

		try {
			long result = Long.parseLong(value);

			return result;
		} catch (Exception ex) {
			return def;
		}
	}

	/**
	 * 转为int
	 * 
	 * @return 转换后的数值，转换失败时返回0
	 * */
	public static int toInt(String value) {
		return toInt(value, 0);
	}

	/**
	 * 转为int
	 * 
	 * @param def
	 *            :转换失败后返回的默认值
	 * */
	public static int toInt(String value, int def) {
		if (value == null || value.length() == 0)
			return def;

		if (!isNumber(value)) {
			return def;
		}

		try {
			int result = Integer.parseInt(value);

			return result;
		} catch (Exception ex) {
			return def;
		}
	}

	/**
	 * 转为日期部分的字符串<br >
	 * 格式：yyyy-MM-dd
	 * 
	 * @param date
	 *            :日期
	 * */
	public static String toDate(Date date) {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		String dt = format1.format(date);

		return dt;
	}

	/**
	 * 转为日期的字符串<br >
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            :日期
	 * */
	public static String toDateTime(Date date) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dt = format1.format(date);

		return dt;
	}

	/**
	 * 转为日期对象<br >
	 * 尝试的格式：yyyy-MM-dd HH:mm:ss，yyyy/MM/dd HH:mm:ss，yyyy-M-d H:m:s，yyyy/M/d
	 * H:m:s
	 * 
	 * @param date
	 *            :日期
	 * */
	public static Date toDateTime(String value) {
		if (isNullOrEmpty(value))
			return null;

		Date date = null;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean success = false;
		try {
			date = format1.parse(value);
			success = true;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (!success) {
			format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				date = format1.parse(value);
				success = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!success) {
			format1 = new SimpleDateFormat("yyyy-M-d H:m:s");
			try {
				date = format1.parse(value);
				success = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!success) {
			format1 = new SimpleDateFormat("yyyy/M/d H:m:s");
			try {
				date = format1.parse(value);
				success = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!success) {
			return toDate(value);
		}
		return date;
	}

	/**
	 * 转为日期对象(只处理日期部分)<br >
	 * 尝试的格式：yyyy-MM-dd，yyyy/MM/dd，yyyy-M-d，yyyy/M/d
	 * 
	 * @param date
	 *            :日期
	 * */

	public static Date toDate(String value) {
		if (isNullOrEmpty(value))
			return null;

		Date date = null;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		boolean success = false;
		try {
			date = format1.parse(value);
			success = true;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (!success) {
			format1 = new SimpleDateFormat("yyyy/MM/dd");
			try {
				date = format1.parse(value);
				success = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!success) {
			format1 = new SimpleDateFormat("yyyy-M-d");
			try {
				date = format1.parse(value);
				success = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!success) {
			format1 = new SimpleDateFormat("yyyy/M/d");
			try {
				date = format1.parse(value);
				success = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	/**
	 * 获取日期字符串，格式：yyyy-MM-dd
	 * */
	public static String toDateString(Date date) {

		String format = "yyyy-MM-dd";

		return toString(date, format);
	}

	/**
	 * 获取时间字符串，格式：HH:mm:ss
	 * */
	public static String toTimeString(Date date) {

		String format = "HH:mm:ss";

		return toString(date, format);
	}

	/**
	 * 获取日期时间字符串，格式：yyyy-MM-dd HH:mm:ss
	 * */
	public static String toString(Date date) {

		String format = "yyyy-MM-dd HH:mm:ss";

		return toString(date, format);
	}

	/**
	 * 获取日期时间字符串
	 * 
	 * @param date
	 *            :日期
	 * 
	 * @param format
	 *            :输出格式
	 * */
	public static String toString(Date date, String format) {
		SimpleDateFormat format1 = new SimpleDateFormat(format);

		String txt = format1.format(date);

		return txt;
	}

	/**
	 * 转为字节数组
	 * */
	public static byte[] toBytes(char[] chars) {

		Charset cs = Charset.forName("UTF-8");
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);

		return bb.array();
	}

	/**
	 * 转为字符数组
	 * */
	public static char[] toChars(byte[] bytes) {
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);

		return cb.array();
	}

	/**
	 * 是否数字
	 * */
	public static boolean isNumber(String input) {

		return isMatch(num_pat, input);
	}

	/**
	 * 是否匹配正则
	 * */
	public static boolean isMatch(String pat, String input) {
		try {
			boolean b = Pattern.matches(pat, input);

			return b;
		} catch (Exception ex) {
			return false;
		}
	}



	public static <T> T firstOrDefault(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}	
	

	static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * MD5加密
	 * */
	public static String toMD5(String s) {
		s = s + SecuritySalt;
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * url编码
	 * */
	public static String urlEncode(String src) {

		String txt = src;
		try {
			txt = URLEncoder.encode(src, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return txt;
	}

	/**
	 * url解码
	 * */
	public static String urlDecode(String src) {

		String txt = src;
		try {
			txt = URLDecoder.decode(src, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return txt;
	}

	public static byte[] Base64ToBytes(String base64) {

		byte[] b = base64.getBytes();
		Base64 conv = new Base64();
		byte[] out = conv.decode(b);

		return out;
	}

	public static String trim(String input, char... cs) {

		if (Util.isNullOrEmpty(input) || cs == null || cs.length == 0) {
			return input;
		}

		char[] ss = new char[input.length()];

		input.getChars(0, input.length(), ss, 0);

		int start = 0, end = input.length() - 1;
		for (char s : ss) {
			boolean ig = false;
			for (char c : cs) {
				if (s == c) {
					ig = true;
					break;
				}
			}
			if (!ig) {
				break;// 没有找到匹配项，结束遍历
			}
			start++;
		}

		for (int j = ss.length - 1; j >= 0; j--) {
			boolean ig = false;
			char s = ss[j];
			for (char c : cs) {
				if (s == c) {
					ig = true;
					break;
				}
			}

			if (!ig) {
				break;// 没有找到匹配项，结束遍历
			}

			end = j;
		}

		String sub1 = input.substring(start, end);

		return sub1;
	}

	public static boolean isIn(String input, String... cs) {
		if (Util.isNullOrEmpty(input)) {
			return false;
		}
		for (String c : cs) {
			if (c.equalsIgnoreCase(input)) {
				return true;
			}
		}
		return false;
	}

	public static String trimStart(String input, char... cs) {
		if (Util.isNullOrEmpty(input) || cs == null || cs.length == 0) {
			return input;
		}

		char[] ss = new char[input.length()];

		input.getChars(0, input.length(), ss, 0);

		int start = 0;
		for (char s : ss) {
			boolean ig = false;
			for (char c : cs) {
				if (s == c) {
					ig = true;
					break;
				}
			}
			if (!ig) {
				break;// 没有找到匹配项，结束遍历
			}
			start++;
		}

		String sub1 = input.substring(start);

		return sub1;
	}

	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	}

	public static InputStream toStream(byte[] bytes) {
		InputStream stream = new ByteArrayInputStream(bytes);

		return stream;
	}

	public static void copyValueIncludes(Object from, Object to, Enumeration<String> includes) {
		List<String> l = new ArrayList<String>();
		while (includes.hasMoreElements()) {
			l.add(includes.nextElement());
		}

		copyValueIncludes(from, to, l);
	}

	public static void copyValueIncludes(Object from, Object to, String[] includs) {
		List<String> l = new ArrayList<String>();
		for (String s : includs) {
			l.add(s);
		}

		copyValueIncludes(from, to, l);
	}

	public static void copyValueIncludes(Object from, Object to, List<String> includesList) {

		if (includesList == null || includesList.size() == 0) {
			return;
		}

		Method[] fromMethods = from.getClass().getDeclaredMethods();
		Method[] toMethods = to.getClass().getDeclaredMethods();

		Method fromMethod = null, toMethod = null;
		String fromMethodName = null, toMethodName = null;

		for (int i = 0; i < fromMethods.length; i++) {
			fromMethod = fromMethods[i];
			fromMethodName = fromMethod.getName();

			if (!fromMethodName.contains("get"))
				continue;

			// 排除列表检测
			String str = fromMethodName.substring(3);
			if (!includesList.contains(str.substring(0, 1).toLowerCase() + str.substring(1))) {
				continue;
			}

			toMethodName = "set" + fromMethodName.substring(3);
			toMethod = findMethodByName(toMethods, toMethodName);
			if (toMethod == null)
				continue;

			Object value;
			try {
				value = fromMethod.invoke(from, new Object[0]);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				continue;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				continue;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				continue;
			}

			if (value == null)
				continue;

			// 集合类判空处理
			if (value instanceof Collection) {
				Collection newValue = (Collection) value;
				if (newValue.size() <= 0)
					continue;
			}
			try {
				toMethod.invoke(to, new Object[] { value });
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从方法数组中获取指定名称的方法
	 * 
	 * @param methods
	 * @param name
	 * @return
	 */
	static Method findMethodByName(Method[] methods, String name) {
		for (int j = 0; j < methods.length; j++) {
			if (methods[j].getName().equals(name))
				return methods[j];
		}
		return null;
	}

	public static void copyValue(Object from, Object target, Map<String, String> nameValues) {

	}

	final static String[] cStr = new String[] { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "", "十", "百", "千" };
	final static String[] unitStr = new String[] { "", "万", "亿", "万", "兆" };

	public static String getChineseString(long number) {
		String result = "";
		int len = String.valueOf(number).length();

		for (int i = 0; i < len; i++) {
			int temp = (int) ((long) (number / (long) Math.pow(10, i)) % 10);
			if (i % 4 == 0)
				result = unitStr[(int) i / 4] + result;
			result = cStr[temp] + cStr[10 + i % 4] + result;
		}

		result = result.replaceAll("(零[十百千])+", "零");
		result = result.replaceAll("零{2,}", "零");
		result = result.replaceAll("零([万亿兆])", "$1");
		if (result.length() > 1)
			result = Util.trimEnd(result, "零");
		// result = result.replaceAll("零$", "");// .trimEnd( '零' );
		if (result.startsWith("一十"))
			result = result.substring(1);
		return result;
	}

	public static String trimEnd(String input, String end) {
		String s = input;
		while (s.endsWith(end)) {
			s = s.substring(0, s.length() - 2);
		}
		return s;
	}

	
	public static String trimEndComma(String input) {
		String s = input;
		while (s.endsWith(",")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
	
	public static Long[] toLongArray(String str) {
		String[] ary =str.split(",");
		ArrayList<Long> result = new ArrayList<Long>();
		if(ary!=null&& ary.length>0)
		{
			for(String o :ary){
				Long oo = Long.parseLong(o);
				if(oo!=null&&oo>0)
				{					
					result.add(oo);
				}		
			}	
		}		
		return result.toArray(new Long[result.size()]);
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> list) {
		return (T[]) list.toArray(new Long[list.size()]);
	}

	public static <T> Set<T> toSet(List<T> list) {

		Set<T> set = new HashSet<T>();
		for (T o : list) {
			set.add(o);
		}
		return set;
	}



	public static <T> List<T> toList(T[] plist) {

		List<T> list = new ArrayList<T>();
		for (T o : plist) {
			list.add(o);
		}
		return list;
	}

	public static <T> Set<T> toHashSet(T[] plist) {

		Set<T> list = new HashSet<T>();
		for (T o : plist) {
			list.add(o);
		}
		return list;
	}
	
	public static <T> T parseJson(String json, Class<T> classOfT) {
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		T t = g.fromJson(json, classOfT);

		return t;
	}

	public static <T> List<T> parseJsonList(String json, Class<T> classOfT) {

		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		//创建一个JsonParser
		JsonParser parser = new JsonParser();

		//通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
		JsonElement el = parser.parse(json);
		
		//把JsonElement对象转换成JsonArray
		JsonArray jsonArray = null;
		if(el.isJsonArray()){
			jsonArray = el.getAsJsonArray();
		}
		

		List<T> list = new ArrayList<>();
		
		//遍历JsonArray对象
		T field = null;
		Iterator it = jsonArray.iterator();
		while(it.hasNext()){
			JsonElement e = (JsonElement)it.next();
			//JsonElement转换为JavaBean对象
			field = g.fromJson(e, classOfT);
			
			list.add(field);
		}
		
		return list;
	}

	public static String toJson(Object obj) {

		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		String json = g.toJson(obj);

		return json;
	}

	public static Map<String, Object> prepareParams(Map<String, Object> params) {
		if (params == null || params.size() == 0) {
			return params;
		}

		for (Map.Entry<String, Object> o : params.entrySet()) {
			Object val = o.getValue();
			if (val != null) {
				String cn = val.getClass().getSimpleName();
				if (cn.equals("String") && Util.isNullOrEmpty((String) val))
					params.put(o.getKey(), null);
			}
		}
		return params;
	}

	public static Size getImageSize(String urlString) {
		// 构造URL
		URL url;
		// 打开连接
		URLConnection con;
		try {

			url = new URL(urlString);
			con = url.openConnection();
			// 输入流
			InputStream stream = con.getInputStream();

			Size size = getImageSize(stream);

			return size;

		} catch (Exception e) {
			e.printStackTrace();
			return new Size();
		}
	}

	public static Size getImageSize(byte[] data) {
		Size size = new Size();
		if (data == null || data.length == 0)
			return size;

		InputStream stream = Util.toStream(data);

		size = getImageSize(stream);

		return size;
	}

	public static Size getImageSize(InputStream stream) {
		Size size = new Size();
		if (stream == null)
			return size;

		BufferedImage bufferedImage;
		try {

			bufferedImage = ImageIO.read(stream);

			size.setWidth(bufferedImage.getWidth());
			size.setHeight(bufferedImage.getHeight());

			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return size;
	}

	public static class Size {
		private int width;
		private int height;

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}

	
}
