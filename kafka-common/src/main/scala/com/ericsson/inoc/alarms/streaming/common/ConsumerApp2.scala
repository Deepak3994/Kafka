package com.ericsson.inoc.alarms.streaming.consumer

import java.util.concurrent._
import java.util.{Collections, Properties}

import kafka.consumer.KafkaStream
import kafka.utils.Logging

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer,ConsumerRecords}

import scala.collection.JavaConversions._

import com.ericsson.inoc.alarms.streaming.common.Alarms

class Consumer2(topic: String, groupId: String, zookeeperConnect: String) extends Logging {

  val props = createConsumerConfig(zookeeperConnect, groupId)

  val consumer = new KafkaConsumer[String, Alarms](props)
  var executor: ExecutorService = null
  
  def shutdown() = {
    if (consumer != null)
      consumer.close();
    if (executor != null)
      executor.shutdown();
  }
  
	def createConsumerConfig(zookeeperConnect: String, groupId: String): Properties = {
	    val props = new Properties()
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
            props.put("zookeeper.connect", zookeeperConnect)
	    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
	    props.put("auto.offset.reset", "earliest")
            //props.put("auto.offset.reset", "latest")
	    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
	    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000")
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.ericsson.inoc.alarms.streaming.common.KryoCustSerializer")
            //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.knoldus.kafka.serializer.CustSerializer")
            //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
	    props
	  }
  
      
  def run(): Unit = {
  consumer.subscribe(Collections.singletonList(this.topic))
  Thread.sleep(100)
  var records : ConsumerRecords[String, Alarms] = null
    while(true){
         //val records = consumer.poll(10)
         records = consumer.poll(10)
         println(records.isEmpty())
         //for(record <- records){println(record.value())}
         for(record <- records){println(record)}
         Thread.sleep(100)
    }
  }
}


object ConsumerApp2 extends App {


  val topic = "AlarmStreams"
  val groupId = "some-topic-consumer"

 
  val consumer = new Consumer2(topic, groupId, "localhost:2181")

  consumer.run()

}
