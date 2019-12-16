package com.mafenwo.services.orders;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MFWCancellationPolicy {

	private String fromDate;
	private BigDecimal amount;
}

