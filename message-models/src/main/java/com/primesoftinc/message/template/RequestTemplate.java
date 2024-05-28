package com.primesoftinc.message.template;

public class RequestTemplate {
	public static final String RequestXMLTemplate ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"
			+ "<ServiceRequest>\r\n"
			+ "<CustomerId>1</CustomerId>\r\n"
			+ "<AccountNumber>21345676543256563</AccountNumber>\r\n"
			+ "<RequestType>\r\n"
			+ "<TypeOfRequest>1</TypeOfRequest>\r\n"
			+ "<RequestDate>2023-09-07</RequestDate>\r\n"
			+ "<CardNumber>1425723321153045</CardNumber>\r\n"
			+"<CardType>credit</CardType>\r\n"
			+ "<StolenDate>2023-09-07</StolenDate>\r\n"
			+ "<RequestMessage>Kindly approve the cheque book request</RequestMessage>\r\n"
			+ "<RequestId>1001</RequestId>\r\n"
			+ "<RequestData>50</RequestData>\r\n"
			+ "</RequestType>\r\n"
			+ "</ServiceRequest>";

}