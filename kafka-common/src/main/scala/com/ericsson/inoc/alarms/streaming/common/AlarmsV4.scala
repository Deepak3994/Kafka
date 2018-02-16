package com.ericsson.inoc.alarms.streaming.common

import java.util.Date

class AlarmsV4(gentime : Long, invoiceNo : String, item : String)
//class Alarms(lstOcr : String, node : String, nodeType : String, x733sp : String)
{
        def getGenTime() : Long = return gentime
	
        //def getLstOcr() : String = return lstOcr
	def getInvoiceNo() : String = return invoiceNo
        def getItem() : String = return item
	override def toString = "( GENERATION TIME : "+gentime+ " , INVOICE NO : "+invoiceNo+" , ITEM : "+item+" )"
        
}

