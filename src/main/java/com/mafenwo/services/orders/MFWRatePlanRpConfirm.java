package com.mafenwo.services.orders;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class MFWRatePlanRpConfirm {

	private boolean standardOccupancySpecified;
	private boolean priceWithoutSupplementSpecified;
	private boolean supplementSpecified;
	private long totalPrice;
	private boolean inventoryCountSpecified;
	private boolean maxOccupancySpecified;
	
	private Integer roomStatus;
	private Integer breakfastType;
	private Integer bedType;
	private MFWOccupancyDetail roomOccupancy;
	private List<MFWPriceItem> priceList;
	private String standardOccupancy;
	private String inventoryCount;
	private Integer maxOccupancy;
	private String currency;
	private String roomName;
	private String roomId;
	private String ratePlanName;
	private String ratePlanId;
	
}
