package com.seagen.ecc.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求信息定义, 即入站信息（从客户端发送到服务端）.
 * 
 * @version	1.0 2011-5-17 
 * @author	吴天斌
 */
public class MessageInbound implements Serializable {
	private static final long serialVersionUID = -8466112609921534466L;
	
	private Long requestId;
	private String sourceAddress;	
	private byte[] packet;
	private Date occurDate;
	private Integer queueId;
	private Boolean running;
	
	public MessageInbound(){
		
	}

	public MessageInbound(String destAddress, byte[] packet)
	{
		this.sourceAddress = destAddress;
		this.packet = packet;
		this.occurDate = new Date();
	}
	
	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public Boolean isRunning() {
		return running;
	}

	public void setRunning(Boolean running) {
		this.running = running;
	}

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public byte[] getPacket() {
		return packet;
	}
	
	public void setPacket(byte[] packet) {
		this.packet = packet;
	}
	
	public Date getOccurDate() {
		return occurDate;
	}
	
	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}
	
	public String getKey() {
		return sourceAddress;
	}
	
}
