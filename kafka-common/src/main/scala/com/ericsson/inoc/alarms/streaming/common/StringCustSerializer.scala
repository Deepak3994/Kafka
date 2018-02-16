package com.ericsson.inoc.alarms.streaming.common


import java.io._
import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
//import org.apache.spark.serializer.Serializer

//import scala.collection.immutable.Map

import org.apache.kafka.common.serialization.{Deserializer, Serializer}


//abstract class StringCustSerializer extends Serializer[Alarms] with Deserializer[Alarms]
class StringCustSerializer extends Serializer[Alarms] with Deserializer[Alarms]// with org.apache.spark.serializer.Serializer
{
  val CHARSET = Charset.forName("UTF-8")

  override def configure(mp: Map[String, _], b: Boolean) : Unit = { 
  }


  /*@Override
  public void configure(Map<String, ?> map, Boolean b){
  }*/


  override def serialize(s: String, mpes : Alarms) : Array[Byte] = {
    val line = String.format(Locale.ROOT, "%s|%s|%s|%s",mpes.getLstOcr(),mpes.getNode(),mpes.getNodeType,mpes.getX733sp() );
    return line.getBytes(CHARSET);
  }


  override def deserialize(topic : String, bytes : Array[Byte]) : Alarms = {

      var parts = new String(bytes, CHARSET).split("|");
      val format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm")
      var lstOcrDateTime = format.parse(parts(0))
      //var lstOcrDateTime = parts(0)
      return new Alarms(lstOcrDateTime, parts(1), parts(2),parts(3))

  }

  override def close() : Unit = {

  }

}

