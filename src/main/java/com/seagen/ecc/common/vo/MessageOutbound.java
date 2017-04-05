package com.seagen.ecc.common.vo;

import java.io.Serializable;

/**
 * 答复信息定义, 即出站信息(从服务端发送到客户端).
 * 
 * @version	1.0 2011-5-17 
 * @author	吴天斌
 */
public class MessageOutbound implements Serializable {
	private static final long serialVersionUID = -2896780492607526383L;
	
	private int messageType;	
	private String destAddress;
	private byte[] packet;
	private String packetString;
	private int linkMode;
	private int messageCode;
	
	/** 信息的网关ID */
	private String msgGatewayId;  
	
	public String getMsgGatewayId() {
		return msgGatewayId;
	}
	
	public void setMsgGatewayId(String gatewayId) {
		this.msgGatewayId = gatewayId;
	}
	
	public int getLinkMode() {
		return linkMode;
	}
	
	public void setLinkMode(int linkMode) {
		this.linkMode = linkMode;
	}
	
	public int getMessageCode() {
		return messageCode;
	}
	
	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}
	
	public int getMessageType() {
		return messageType;
	}
	
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	public String getDestAddress() {
		return destAddress;
	}
	
	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}
	
	public byte[] getPacket() {
		return packet;
	}
	
	public void setPacket(byte[] packet) {
		this.packet = packet;
	}

	public String getPacketString() {
		return packetString;
	}

	public void setPacketString(String packetString) {
		this.packetString = packetString;
		this.packet = packetString.getBytes();
	}
	
}
