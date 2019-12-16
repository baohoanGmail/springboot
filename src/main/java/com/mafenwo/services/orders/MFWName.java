package com.mafenwo.services.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MFWName {

	private String first;
	private String last;
	
	public String toString() {
		return last + " " + first;
	}
}
