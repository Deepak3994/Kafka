package com.ericsson.inoc.alarms.streaming.common

import java.util.Date

class Alarms(lstOcr : Date, node : String, nodeType : String, x733sp : String)
//class Alarms(lstOcr : String, node : String, nodeType : String, x733sp : String)
{
        def getLstOcr() : Date = return lstOcr
        //def getLstOcr() : String = return lstOcr
        def getNode() : String = return node
        def getNodeType() : String = return nodeType
        def getX733sp() : String = return x733sp
	override def toString = "LAST OCCURANCE : "+lstOcr+ " ||| NODE : " + node + " ||| NODE TYPE : " + nodeType + " ||| X733SPECIFICPROB : " + x733sp
        
}

