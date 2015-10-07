package com.wesleyreisz.samples.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.*;

public class App {
    private static final String TOPIC = "zerg.hydra";
    public static final int MESSAGE_NUMBER = 100;
    
    public static void main( String[] args ){
        System.out.println( "Simple Kafka Producer!" );
        App app = new App();
        app.start();

    }

    private void start() {
        ProducerConfig config = configureApp();
        Producer<String, String> producer = new Producer<String, String>(config);
        MyMessage message = buildRandomMessage();
        sendMessage(MESSAGE_NUMBER,producer, message.getIp(),message.getMessage());
    }

    private void sendMessage(int messageNumber, Producer<String, String> producer, String ip, String message) {
        List<KeyedMessage<String, String>> messages = new ArrayList<KeyedMessage<String, String>>();
        for (int i=0;i<messageNumber;i++){
           KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC, ip, message);
           messages.add(data);
        }
        producer.send(messages);
        System.out.println( "Sent " + messages.size() + " messages." );
    }

    private MyMessage buildRandomMessage() {
        Random rnd = new Random(System.currentTimeMillis());
        long runtime = new Date().getTime();
        String ip = "192.168.2." + rnd.nextInt(255);
        return new MyMessage(ip,runtime + ",kafka-example.wesleyreisz.com," + ip);
    }

    private ProducerConfig configureApp() {
        Properties props = new Properties();

        props.put("metadata.broker.list", "kafka-demo.wesleyreisz.com:9999,kafka-demo.wesleyreisz.com:10000");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //props.put("partitioner.class", "example.producer.SimplePartitioner");
        props.put("request.required.acks", "1");

        return new ProducerConfig(props);
    }
}
