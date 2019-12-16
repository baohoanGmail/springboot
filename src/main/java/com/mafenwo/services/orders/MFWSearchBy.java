package com.mafenwo.services.orders;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MFWSearchBy implements Serializable {

	private static final long serialVersionUID = -3991842610983946756L;

	@JsonProperty(value = "bookingId")
	@NotBlank(message = "bookingId can't be Empty", groups = Required.class)
	private String bookingId;

	@JsonProperty(value = "clientReference")
	@NotBlank(message = "clientReference can't be Empty", groups = Required.class)
	private String clientReference;

	public MFWSearchBy() {
		this.bookingId = "";
		this.clientReference = "";
	}

	public MFWSearchBy(String bookingId, String clientReference) {
		this.bookingId = bookingId;
		this.clientReference = clientReference;
	}
}
