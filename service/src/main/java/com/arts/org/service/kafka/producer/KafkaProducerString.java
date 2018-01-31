package com.arts.org.service.kafka.producer;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arts.org.base.Action;
import com.arts.org.base.Message;
import com.arts.org.base.Module;

@Service
public class KafkaProducerString {

	//private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	private Producer<String, String> producer;	
	@Autowired
	private Properties kafkaConfig;

	private static String location = "producer.properties";

	public KafkaProducerString() {
	}

	@PostConstruct
	public void init() throws Exception {
		
		System.out.println("KafkaProducer:init:begin");

		Properties properties = new Properties();

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(location);

		System.out.println(url);

		InputStream stream = url.openStream();

		try {
			properties.load(stream);
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
		
		if (properties.isEmpty()) {
			throw new RuntimeException("init kafka producer error,check the config please");
		}
		
		ProducerConfig config = new ProducerConfig(properties);
		producer = new Producer<String, String>(config);

		System.out.println("KafkaProducer:init:end");
	}

	public void send(String topicName, String message) {
		if (topicName == null || message == null) {
			return;
		}
		KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, message, message);
		producer.send(km);
	}
	

	@PreDestroy
	public void close() {
		producer.close();
	}

}
