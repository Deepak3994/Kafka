package com.ericsson.inoc.alarms.streaming.common

import java.util.Date

class AlarmsV3(gentime : Date, msgval : Char)
//class Alarms(lstOcr : String, node : String, nodeType : String, x733sp : String)
{
        def getGenTime() : Date = return gentime
	
        //def getLstOcr() : String = return lstOcr
        def getMsgVal() : Char = return msgval
	override def toString = "( GENERATION TIME : "+gentime+ " , MESSAGE VALUE : "+msgval+" )"
        
}

