package com.arts.org.webcomn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arts.org.base.util.Util;

/**
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @Project：test
 * @Author : chenming
 * @Date ： 2014年4月20日 下午2:27:06
 * @version 1.0
 */
public class SensitiveWordInit {

	Logger logger = LoggerFactory.getLogger(SensitiveWordInit.class);

	private String ENCODING = "UTF-8"; // 字符编码

	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;

	public SensitiveWordInit() {
		super();
	}

	/**
	 * @author chenming
	 * @date 2014年4月20日 下午2:28:32
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(String filePath) {
		logger.info("SensitiveWordInit.initKeyWord:{}", filePath);
		try {
			// 读取敏感词库
			Set<String> keyWordSet = readSensitiveWordFile(filePath);
			// 将敏感词库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
			// spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = { isEnd = 0 国 = {<br>
	 * isEnd = 1 人 = {isEnd = 0 民 = {isEnd = 1} } 男 = { isEnd = 0 人 = { isEnd =
	 * 1 } } } } 五 = { isEnd = 0 星 = { isEnd = 0 红 = { isEnd = 0 旗 = { isEnd = 1
	 * } } } }
	 * 
	 * @author chenming
	 * @date 2014年4月20日 下午3:04:20
	 * @param keyWordSet
	 *            敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {

		sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		// 迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while (iterator.hasNext()) {
			key = iterator.next(); // 关键字
			nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // 转换成char型
				Object wordMap = nowMap.get(keyChar); // 获取

				if (wordMap != null) { // 如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String, String>();
					newWorMap.put("isEnd", "0"); // 不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // 最后一个
				}
			}
		}
	}

	/**
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 * 
	 * @author chenming
	 * @date 2014年4月20日 下午2:31:18
	 * @return
	 * @version 1.0
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private Set<String> readSensitiveWordFile(String filePath) throws Exception {

		logger.info("SensitiveWordInit.readSensitiveWordFile:{}", filePath);

		Set<String> set = new HashSet<String>();
		File file;

		if (filePath.startsWith("classpath")) {

			String p2 = filePath.split(":")[1];

			logger.info("SensitiveWordInit.p2:{}", p2);

			file = new File(this.getClass().getResource(p2).getFile());
		} else {

			logger.info("SensitiveWordInit.filePath:{}", filePath);

			file = new File(filePath); // 读取文件
		}

		if (file != null) {
			if (file.isFile()) {
				InputStreamReader reader = null;

				try {

					if (file.exists()) { // 文件流是否存在

						reader = new InputStreamReader(new FileInputStream(file), ENCODING);

						BufferedReader bufferedReader = new BufferedReader(reader);

						String txt = null;
						while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中

							if (!Util.isNullOrEmpty(txt)) {

								String txt2 = txt;
								//logger.info("SensitiveWordInit.txt:{}", txt);

								if (txt.indexOf("=") > 0) {
									String[] arr = txt.split("=");
									txt2 = arr[0];
								}

								//logger.info("SensitiveWordInit.txt2:{}", txt2);

								set.add(txt2);
							}
						}
					} else { // 不存在抛出异常信息
						// throw new Exception("敏感词库文件不存在");
						logger.info("SensitiveWordInit.敏感词库文件不存在:{}", file.getAbsolutePath());
					}
				} catch (Exception e) {
					//throw e;
					
					logger.info(e.getMessage());
				} finally {
					
					if (reader != null){
						reader.close(); // 关闭文件流
					}
				}
			} else {
				File[] files = file.listFiles();
				if (files != null) {
					for (File f : files) {
						Set<String> s = readSensitiveWordFile(f.getAbsolutePath());

						if (s != null && s.size() > 0) {
							set.addAll(s);
						}
					}
				}
			}
		}

		if (set != null && set.size() > 0)
			return set;

		return null;
	}
}
