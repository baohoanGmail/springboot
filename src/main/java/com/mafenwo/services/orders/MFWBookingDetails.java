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
public class MFWBookingDetails {

	private long totalPrice;
	private String orderDate;
	private String checkInDate;
	private String checkOutDate;
	private Integer numOfRooms; // edit same response example - cntri
	private Integer status;
	private List<MFWGuest> guestList;
	private MFWContact contact;
	private MFWHotelOderDetail hotel;
	private String clientReference;
	private String bookingId;
}
