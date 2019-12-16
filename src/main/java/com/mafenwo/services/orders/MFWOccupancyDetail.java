package com.mafenwo.services.orders;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MFWOccupancyDetail {

    @NotNull(message = "childCount is null")
	private Integer childCount;
    
    @NotNull(message = "adultCount is null")
	private Integer adultCount;
    
    @NotNull(message = "roomIndex is null")
	private Integer roomIndex;
}
