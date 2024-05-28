package com.primesoftinc.message.template;

public class BillerRequestTemplate {
	public static final String RequestXMLTemplate ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"
			+ "<BillerRequest>\r\n"
			+ "    <CustomerId>1</CustomerId>\r\n"
			+ "    <AccountNo>123445678999999980</AccountNo>\r\n"
			+ "    <BillerReqDetails>\r\n"
			+ "        <BillerId>2</BillerId>\r\n"
			+ "        <BillerAmount>10000</BillerAmount>\r\n"
			+ "        <BillerDate>2023-09-13</BillerDate>\r\n"
			+ "        <BillerAccount>1234567876423456</BillerAccount>\r\n"
			+ "			<PaymentReqId>1</PaymentReqId>"
			+ "    </BillerReqDetails>\r\n"
			+ "</BillerRequest>";
}
