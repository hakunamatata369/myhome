package org.hakunamatata.myhome.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage {

	private String responseMessage;
	private int responseCode;

	public ResponseMessage() {
		super();
	}

	public ResponseMessage(String responseMessage, int responseCode) {
		super();
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
