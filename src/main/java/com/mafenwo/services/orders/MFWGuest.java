package com.mafenwo.services.orders;

import java.util.List;

import lombok.Data;

@Data
public class MFWGuest {

	private Integer roomNum;
	private List<MFWGuestInfo> guestInfo;
}
