package com.arts.org.webcomn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(DefaultListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// logger.info("DefaultListener.contextInitialized");  -DconfigEnv=-dev

		logger.info("------------------------------------>DefaultListener.contextInitialized.begin<-------------------------------------");
		Properties props = System.getProperties();

		Set<Entry<Object, Object>> set = System.getProperties().entrySet();

		List<Entry<Object, Object>> setList = new ArrayList<Entry<Object, Object>>(set);
		
		Collections.sort(setList, new Comparator<Entry<Object, Object>>() {
			@Override
			public int compare(Entry<Object, Object> o1, Entry<Object, Object> o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		
		Set<Entry<Object, Object>> set2 = new LinkedHashSet<Entry<Object, Object>>(setList);// 这里注意使用LinkedHashSet

		for (Entry<Object, Object> a : set2) {
			logger.info(String.format("%s------------>%s", a.getKey(), a.getValue()));
		}
		
		if( System.getProperty("configEnv") == null ){
			System.setProperty("configEnv", "");
		}
		if( System.getProperty("java.io.tmpdir") == null ){
			System.setProperty("java.io.tmpdir", "/var/tmp");
		}

		logger.info("------------------------------------>DefaultListener.contextInitialized.end<-------------------------------------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("DefaultListener.contextDestroyed");
	}

}
