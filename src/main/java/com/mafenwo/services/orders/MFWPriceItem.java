package com.mafenwo.services.orders;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MFWPriceItem {
	private String stayDate;
	private Integer inventoryCount;
	private boolean inventoryCountSpecified;
	private BigDecimal price;
}
