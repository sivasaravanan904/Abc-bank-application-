package com.primesoftinc.message.template;

public class BillerResponseTemplate {
	public static final String ResponseXMLTemplate ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"
			+ "<BillerResponse>\r\n"
			+ "    <CustomerId>1</CustomerId>\r\n"
			+ "    <AccountNo>123445678999999980</AccountNo>\r\n"
			+ "    <BillerResDetails>\r\n"
			+ "        <BillerId>2</BillerId>\r\n"
			+ "        <BillerAmount>10000</BillerAmount>\r\n"
			+ "        <BillerAccount>1234567876423456</BillerAccount>\r\n"
			+ "		<PaymentReqId>1</PaymentReqId>"
			+ "    </BillerResDetails>\r\n"
			+ "    <Status>approved</Status>\r\n"
			+ "    <Remarks>payment done successfully</Remarks>\r\n"
			+ "</BillerResponse>";

}
