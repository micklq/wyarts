package com.arts.org.service.kafka.encoder;

import com.arts.org.base.Message;
import com.arts.org.base.util.BeanUtils;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class KafkaEncoder implements Encoder<Message> {
	public KafkaEncoder(VerifiableProperties prop){
		
	}
	public byte[] toBytes(Message msg) {
		return BeanUtils.obj2Bytes(msg);
	}

}
