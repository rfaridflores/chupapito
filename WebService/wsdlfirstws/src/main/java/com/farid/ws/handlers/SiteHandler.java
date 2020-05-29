package com.farid.ws.handlers;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SiteHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("hanleMessage*************************************");

		// if true is a response message if false is a request
		Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!isResponse) {
			// Luego obtenemos el mensage completo , es decir, el request
			SOAPMessage soapMsg = context.getMessage();
			try {
				// luego obtenemos el envelope/header/childElements, como se haria en el xml
				// doom
				SOAPEnvelope envelope = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				Iterator childElements = header.getChildElements();

				while (childElements.hasNext()) {
					Node eachNode = (Node) childElements.next();

					String name = eachNode.getLocalName();
					if (name != null && name.equals("SiteName")) {
						System.out.println("SiteName is: " + eachNode.getValue());
					}
				}

			} catch (SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Response on the way");
		}
		return false;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("handleFault*************************************");

		return false;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("close*************************************");

	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("getHeaders*************************************");
		return null;
	}
}
