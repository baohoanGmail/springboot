package com.hoan.lam.demo.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoan.lam.demo.JsonHelper;
import com.hoan.lam.demo.filter.MultiReadRequestWrapper;
import com.hoan.lam.demo.util.JsonUtil;
import com.mafenwo.services.orders.MFWBookingDetails;
import com.mafenwo.services.orders.MFWOrderInquiry;
import com.mafenwo.services.orders.MFWOrderStatusEnum;
import com.mafenwo.services.orders.MFWResponse;
import com.mafenwo.services.orders.MFWSysStatusEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestTestingController {

//	@PostMapping(value = "search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//	public ResponseEntity<Boolean> search(@RequestBody MultiValueMap<String, Object> parameters) {
//		log.info("{}", parameters);
//		return ResponseEntity.ok().build();
//	}
	private static String JSON = " {\r\n" + 
			"        \"totalPrice\": 2266,\r\n" + 
			"        \"orderDate\": \"2019-09-27 13:21\",\r\n" + 
			"        \"checkInDate\": \"2019-09-26 15:15\",\r\n" + 
			"        \"checkOutDate\": \"2019-09-27 11:15\",\r\n" + 
			"        \"totalOfRooms\": 1,\r\n" + 
			"        \"status\": 1000,\r\n" + 
			"        \"guestList\": [\r\n" + 
			"          {\r\n" + 
			"            \"roomIndex\": 1,\r\n" + 
			"            \"guestInfo\": [\r\n" + 
			"              {\r\n" + 
			"                \"name\": {\r\n" + 
			"                  \"first\": \"ZHAO\",\r\n" + 
			"                  \"last\": \"SHANNI\"\r\n" + 
			"                },\r\n" + 
			"                \"isAdult\": true,\r\n" + 
			"                \"age\": null\r\n" + 
			"              },\r\n" + 
			"              {\r\n" + 
			"                \"name\": {\r\n" + 
			"                  \"first\": \"ZHAO\",\r\n" + 
			"                  \"last\": \"Chid 4\"\r\n" + 
			"                },\r\n" + 
			"                \"isAdult\": false,\r\n" + 
			"                \"age\": 4\r\n" + 
			"              },\r\n" + 
			"              {\r\n" + 
			"                \"name\": {\r\n" + 
			"                  \"first\": \"ZHAO\",\r\n" + 
			"                  \"last\": \"Chid 10\"\r\n" + 
			"                },\r\n" + 
			"                \"isAdult\": false,\r\n" + 
			"                \"age\": 12\r\n" + 
			"              }\r\n" + 
			"            ]\r\n" + 
			"          }\r\n" + 
			"        ],\r\n" + 
			"        \"contact\": {\r\n" + 
			"          \"name\": {\r\n" + 
			"            \"first\": \"ZHAO\",\r\n" + 
			"            \"last\": \"SHANNI\"\r\n" + 
			"          },\r\n" + 
			"          \"email\": \"hoanlam.hana@gmail.com\",\r\n" + 
			"          \"phone\": \"19001080\"\r\n" + 
			"        },\r\n" + 
			"        \"hotel\": {\r\n" + 
			"          \"totalPriceWithoutSupplement\": 0,\r\n" + 
			"          \"totalSupplement\": 0,\r\n" + 
			"          \"totalPrice\": 5726,\r\n" + 
			"          \"hotelId\": \"J08937\",\r\n" + 
			"          \"hotelName\": \"Artiso Hotel\",\r\n" + 
			"          \"ratePlanList\": [\r\n" + 
			"            {\r\n" + 
			"              \"totalPrice\": 5726,\r\n" + 
			"              \"roomStatus\": 1,\r\n" + 
			"              \"breakfastType\": \"01\",\r\n" + 
			"              \"bedType\": \"DOUBLE\",\r\n" + 
			"              \"roomOccupancy\": [\r\n" + 
			"                {\r\n" + 
			"                  \"childCount\": 2,\r\n" + 
			"                  \"adultCount\": 1,\r\n" + 
			"                  \"roomIndex\": 1\r\n" + 
			"                }\r\n" + 
			"              ],\r\n" + 
			"              \"priceList\": [\r\n" + 
			"                {\r\n" + 
			"                  \"stayDate\": \"2019-09-26 02:00:00\",\r\n" + 
			"                  \"price\": 2266\r\n" + 
			"                }\r\n" + 
			"              ],\r\n" + 
			"              \"standardOccupancy\": null,\r\n" + 
			"              \"inventoryCount\": null,\r\n" + 
			"              \"maxOccupancy\": 0,\r\n" + 
			"              \"supplement\": 0,\r\n" + 
			"              \"ratePlanName\": \"Artiso Room-11111\",\r\n" + 
			"              \"ratePlanId\": \"P190916M29710\",\r\n" + 
			"              \"currency\": \"102\",\r\n" + 
			"              \"roomName\": \"Artiso Room\",\r\n" + 
			"              \"roomId\": \"R1909169220\"\r\n" + 
			"            }\r\n" + 
			"          ],\r\n" + 
			"          \"cancellationPolicyList\": []\r\n" + 
			"        },\r\n" + 
			"        \"clientReference\": \"20170816367483833\",\r\n" + 
			"        \"bookingId\": \"DHB170816104137206\"\r\n" + 
			"      }";
	
	
	@PostMapping(value = "search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<MFWResponse> search(@RequestBody MultiValueMap<String, String> multipleValueMap) throws IOException {
		String json = JsonUtil.getValueOfFirst(multipleValueMap);
		MFWOrderInquiry inquiry = JsonUtil.str2bean(json, MFWOrderInquiry.class); 
		log.info("JsonNormalization: {}", JsonUtil.toJson(inquiry));
		MFWResponse resp = new MFWResponse(MFWSysStatusEnum.MFW0);
		Optional<Object> details = JsonHelper.string2object(JSON, MFWBookingDetails.class, "");
		resp.setData(details.get());
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping(value = "create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<MFWResponse> create(@RequestBody byte[] request) throws IOException {
		
		String inquiry = JsonUtil.parse(request, StandardCharsets.UTF_8.name()); 
		log.info("create: {}", inquiry);
		MFWResponse resp = new MFWResponse(MFWSysStatusEnum.MFW0);
		Optional<Object> details = JsonHelper.string2object(JSON, MFWBookingDetails.class, "");
		resp.setData(details.get());
		return ResponseEntity.ok(resp);
	}
//	
//	@PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<Boolean> search(@RequestBody Object parameters) {
//		log.info("{}", JsonUtil.toJson(parameters));
//		return ResponseEntity.ok().build();
//	}
	
	@PostMapping(value = "search")
	public ResponseEntity<Boolean> search(HttpServletRequest requestEntity) {
		log.info("{}", JsonUtil.toJson(requestEntity));
		return ResponseEntity.ok().build();
	}

	
}
