package com.primesoftinc.message.template;

public class ResponseTemplate {
	public static final String ResponseXMLTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"
			+ "<ServiceResponse>\r\n"
			+ "<CustomerId>1</CustomerId>\r\n"
			+ "<AccountNumber>21345676543256563</AccountNumber>\r\n"
			+ "<Response>\r\n"
			+ "<ProcessId>2000</ProcessId>\r\n"
			+ "<ResponseDate>2023-09-07</ResponseDate>\r\n"
			+"<CardNumber>1224633851532525</CardNumber>\r\n"
			+"<CardType>credit</CardType>\r\n"
			+ "<ResponseMessage>Data verified approved successfully</ResponseMessage>\r\n"
			+ "<RequestId>1001</RequestId>\r\n"
			+ "<Status>approved</Status>\r\n"
			+"<ServiceReqId>1</ServiceReqId>"
			+ "</Response>\r\n"
			+ "</ServiceResponse>";
}
