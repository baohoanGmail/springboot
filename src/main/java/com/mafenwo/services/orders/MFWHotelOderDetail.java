package com.mafenwo.services.orders;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MFWHotelOderDetail {

	private long totalPriceWithoutSupplement;
	private long totalSupplement;
	private long totalPrice;
	private String hotelId;
	private String hotelName;
	private List<MFWRatePlanRpConfirm> ratePlanList;
	private List<MFWCancellationPolicy> cancellationPolicyList;
	
}
