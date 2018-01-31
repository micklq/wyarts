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
public class KafkaProducer {

	//private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	private Producer<Message, Message> producer;	
	@Autowired
	private Properties kafkaConfig;

	private static String location = "producer.properties";

	public KafkaProducer() {
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
		producer = new Producer<Message, Message>(config);

		System.out.println("KafkaProducer:init:end");
	}

	public void send(String topicName, Message message) {
		if (topicName == null || message == null) {
			return;
		}
		KeyedMessage<Message, Message> km = new KeyedMessage<Message, Message>(topicName, message, message);

		producer.send(km);
	}
	


	public void send(String topic,Map<String,Object> dataMap){
		
		Message message = new Message();
		message.setAction(Action.UPDATE);
		message.setDataMap(dataMap);

		send(topic, message);
	}
	

	@PreDestroy
	public void close() {
		producer.close();
	}

}
