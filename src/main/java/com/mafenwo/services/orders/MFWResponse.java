package com.mafenwo.services.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MFWResponse {

	private Integer errorCode;
	private String errorMessage;
	private Object data;

	public MFWResponse() {
		this.errorCode = 0;
		this.errorMessage = "";
		this.data = null;
	}
	public MFWResponse(Integer code, String message, Object data) {
		this.errorCode = code;
		this.errorMessage = message;
		this.data = data;
	}
	/**
	 * {@code errorCode} the Mafenwo orderStatusEnum code <br/>
	 * {@code errorMessage} the Mafenwo orderStatusEnum text <br/>
	 * 
	 * @param orderStatusEnum {@linkplain MFWOrderStatusEnum}
	 */
	public MFWResponse(MFWOrderStatusEnum orderStatusEnum) {
		this.errorCode = orderStatusEnum.getCode();
		this.errorMessage = orderStatusEnum.getText();
	}

	/**
	 * {@code errorCode} the Mafenwo orderStatusEnum code <br/>
	 * {@code errorMessage} the Mafenwo orderStatusEnum text <br/>
	 * {@code data} input data <br/>
	 * 
	 * @param orderStatusEnum {@linkplain MFWOrderStatusEnum}
	 */
	public MFWResponse(MFWOrderStatusEnum orderStatusEnum, Object data) {
		this.errorCode = orderStatusEnum.getCode();
		this.errorMessage = orderStatusEnum.getText();
		this.data = data;
	}

	/**
	 * {@code errorCode} the Mafenwo sysStatusEnum code <br/>
	 * {@code errorMessage} the Mafenwo sysStatusEnum text <br/>
	 * 
	 * @param orderStatusEnum {@linkplain MFWSysStatusEnum}
	 */
	public MFWResponse(MFWSysStatusEnum sysStatusEnum) {
		this.errorCode = sysStatusEnum.getCode();
		this.errorMessage = sysStatusEnum.getText();
	}

	/**
	 * {@code errorCode} the Mafenwo sysStatusEnum code <br/>
	 * {@code errorMessage} the Mafenwo sysStatusEnum text <br/>
	 * 
	 * @param orderStatusEnum {@linkplain MFWSysStatusEnum}
	 */
	public MFWResponse(MFWSysStatusEnum sysStatusEnum, Object data) {
		this.errorCode = sysStatusEnum.getCode();
		this.errorMessage = sysStatusEnum.getText();
		this.data = data;
	}

	/**
	 * Return true, if its error code not equals MFW success code (0)
	 * 
	 * @return boolean true or false
	 */
	public boolean hasError() {
		return !MFWSysStatusEnum.MFW0.getCode().equals(this.errorCode);
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
