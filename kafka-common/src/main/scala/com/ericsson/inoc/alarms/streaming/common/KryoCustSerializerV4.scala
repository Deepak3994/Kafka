package com.ericsson.inoc.alarms.streaming.common

import java.io.Closeable;
import java.util.Map;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Date

import com.esotericsoftware.kryo.Kryo;

import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;


import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;


class KryoCustSerializerV4 extends Serializer[AlarmsV4] with Deserializer[AlarmsV4]
{
        var c1 = 0
        var c2 = 0
        println("Executing Kryo Serializer ....")
        val kryos = new ThreadLocal[Kryo]() {
        override def initialValue() : Kryo = {
            val kryo = new Kryo();
            println("Kryo Registration......")

            kryo.register(classOf[AlarmsV4],  new KryoInternalSerializerV4)
            //kryo.addDefaultSerializer(classOf[Alarms], new KryoInternalSerializer);
                        
            return kryo;
        };
    };

    
    override def configure(map : Map[String, _], b : Boolean) : Unit ={
    println("Configure Method got called....")
    }

    
    override def serialize(s : String, al : AlarmsV4) : Array[Byte] = {
        
        val output = new Output(500);
        //c1 = c1 + 1
        //println("Serialize Method is being Executed ....  "+c1)

        kryos.get().writeObjectOrNull(output, al ,al.getClass)
        //println("writeObjectOrNull got Executed ....  "+c1)
        return output.toBytes();
        
    }

    
    override def deserialize(s : String, bytes : Array[Byte]) : AlarmsV4 = {
            //c2 = c2 + 1
            //println("De-serialize Method is being Executed ....  "+c2)
            return kryos.get().readObjectOrNull(new Input(bytes),classOf[AlarmsV4]);
            
    }

    
    override def close() : Unit = {
    println("Close Method is being Executed ....")
    }

    
}
class KryoInternalSerializerV4 extends com.esotericsoftware.kryo.Serializer[AlarmsV4] {
        var c3 = 0
        var c4 = 0
        val CHARSET = Charset.forName("UTF-8")
        override def write(kryo : Kryo, output : Output, al : AlarmsV4) : Unit = {
        
            output.writeLong(al.getGenTime());
            output.writeString(al.getInvoiceNo());
	    output.writeString(al.getItem());
            //c3 = c3 + 1
            //println("Kryo-Write Method is being Executed ....  "+c3)
            
            
        }

        val format = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
	
        var lstOcrDateTime : java.util.Date = null
        override def read(kryo : Kryo, input : Input, al : Class[AlarmsV4] ) : AlarmsV4 = {
        
            
            //val genDateTime = format.parse(input.readString())
            //c4 = c4 + 1
            //println("Kryo-Read Method is being Executed ....  "+c4)
            return new AlarmsV4(input.readLong(),input.readString(),input.readString())
	    
            
        }
}
