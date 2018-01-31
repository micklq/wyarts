package com.arts.org.service.kafka.consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import org.slf4j.Logger;

import com.arts.org.base.Message;
import com.arts.org.base.util.BeanUtils;

public abstract class  BaseConsumer {
	
	public interface IConsumerMessageHandler{
		public void onMessage(Message msg);
	}
	
	public BaseConsumer(){		
	}	
	
	
	public abstract String getTopic();
	public abstract IConsumerMessageHandler getConsumerMessageHandler();
	public abstract Logger getLogger();
	
	private int partitionsNum = 1;
	private ConsumerConnector connector;
	private static String location = "consumer.properties";

	public void doConsume(){

		final String className = this.getClass().getName();
		final String topic = getTopic();
		final IConsumerMessageHandler consumerMessageHandler = getConsumerMessageHandler();
		
		System.out.println( className + ".doConsume:topic:" + topic);
		
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(location);

		System.out.println(url);

		Properties properties = new Properties();
		InputStream stream;
		try {
			stream = url.openStream();

			properties.load(stream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (properties.isEmpty()) {
			throw new RuntimeException("init kafka consumer error,check the config please");
		}
		
		for(Object key : properties.keySet() ){
			System.out.println(String.format( "Properties[%s]:%s", key, properties.get(key)) );
		}

		ConsumerConfig config = null;
		try{
			config = new ConsumerConfig(properties);

			System.out.println(this.getClass().getName() + ".ConsumerConfig ok");
		}
		catch(Exception ex){
			
			ex.printStackTrace();
			System.out.println("ConsumerConfig init Exception:" + ex.getMessage());
			return;
		}


		connector = kafka.consumer.Consumer.createJavaConsumerConnector(config);
		System.out.println(className + ":createJavaConsumerConnector ok");
		
		Map<String, Integer> topics = new HashMap<String, Integer>();
		topics.put(topic, partitionsNum);


		Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topics);
		System.out.println(className + ":createMessageStreams ok");
		
		
		List<KafkaStream<byte[], byte[]>> partitions = streams.get( topic );
		try {

			for (final KafkaStream<byte[], byte[]> partition : partitions) {

				ConsumerIterator<byte[], byte[]> it = partition.iterator();
				System.out.println( className + ":iterator() ok");
				
				while (it.hasNext()) {
					
					System.out.println( className + ":it.hasNext() ok");
					
					MessageAndMetadata<byte[], byte[]> item = it.next();
					System.out.println( className + ":it.next() ok");
					
					try {
						byte[] b = item.message();
						
						Message msg = (Message) BeanUtils.byte2Obj(b); 
						if(msg != null ){
							System.out.println(className + ":msg ----------->" + msg);
							
							if(consumerMessageHandler == null ){
								System.out.println(className + ":consumerMessageHandler is null");
							}
							else {
								consumerMessageHandler.onMessage(msg);
							}
						}
						else{
							System.out.println(className  + "########   msg is null");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						connector.commitOffsets();
					}
				}
			}
		} catch (Exception e) {
			//logger.error("error msg is {},{}",e.getMessage(),e.toString());
		}
	}
	public void close() {
		try {
			System.out.println(this.getClass().getName() + ":shutdown...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.shutdown();
		}
	}
}
