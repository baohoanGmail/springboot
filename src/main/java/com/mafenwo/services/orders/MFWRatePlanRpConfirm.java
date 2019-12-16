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

	private long totalPrice;
	private Integer roomStatus;
	private String breakfastType;
	private String bedType;
	private List<MFWOccupancyDetail> roomOccupancy;
	private List<MFWPriceItem> priceList;
	private String standardOccupancy;
	private String inventoryCount;
	private Integer maxOccupancy;
}
