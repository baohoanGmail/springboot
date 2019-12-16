package com.mafenwo.services.orders;

import java.util.List;

import lombok.Data;

@Data
public class MFWGuest {

	private Integer roomIndex;
	private List<MFWGuestInfo> guestInfo;
}
