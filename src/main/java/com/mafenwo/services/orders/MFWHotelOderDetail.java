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

	private boolean totalPriceWithoutSupplementSpecified;
	private boolean totalSupplementSpecified;
	private boolean totalPriceSpecified;
	private MFWDestination destination;
	private long totalPrice;
	private String hotelId;
	private String hotelName;
	private List<MFWRatePlanRpConfirm> ratePlanList;
	private List<MFWCancellationPolicy> cancellationPolicyList;
	
}
