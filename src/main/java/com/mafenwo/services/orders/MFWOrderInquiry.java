package com.mafenwo.services.orders;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName(value = "searchBy")
public class MFWOrderInquiry implements Serializable {

	private static final long serialVersionUID = 7241448189723386694L;

	@NotNull(message = "searchBy can't be null", groups = Required.class)
	private MFWSearchBy searchBy;

	public MFWOrderInquiry() {
	}

	public MFWOrderInquiry(MFWSearchBy searchBy) {
		this.searchBy = searchBy;
	}
}
