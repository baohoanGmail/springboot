package com.hoan.lam.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoan.lam.demo.util.JsonUtil;
import com.mafenwo.services.orders.MFWBookingDetailList;
import com.mafenwo.services.orders.MFWBookingDetails;
import com.mafenwo.services.orders.MFWCancellationPolicy;
import com.mafenwo.services.orders.MFWContact;
import com.mafenwo.services.orders.MFWDestination;
import com.mafenwo.services.orders.MFWGuest;
import com.mafenwo.services.orders.MFWGuestInfo;
import com.mafenwo.services.orders.MFWHotelOderDetail;
import com.mafenwo.services.orders.MFWName;
import com.mafenwo.services.orders.MFWOccupancyDetail;
import com.mafenwo.services.orders.MFWOrderInquiry;
import com.mafenwo.services.orders.MFWPriceItem;
import com.mafenwo.services.orders.MFWRatePlanRpConfirm;
import com.mafenwo.services.orders.MFWResponse;
import com.mafenwo.services.orders.MFWResult;
import com.mafenwo.services.orders.MFWSysStatusEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestTestingController {
	
	@PostMapping(value = "/search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<MFWResult> search(@RequestBody MultiValueMap<String, String> multipleValueMap) throws IOException {
		String json = JsonUtil.getValueOfFirst(multipleValueMap);
		MFWOrderInquiry inquiry = JsonUtil.str2bean(json, MFWOrderInquiry.class); 
		log.info("JsonNormalization: {}", JsonUtil.toJson(inquiry));
		MFWResponse resp = new MFWResponse(MFWSysStatusEnum.MFW0);
		//resp.setErrorCode(1007);
		//resp.setErrorMessage("order not exists");
		resp.setData(JsonUtil.emptyObject());
		
		MFWBookingDetails bookingDetail = new MFWBookingDetails();
		bookingDetail.setTotalPrice(2706);
		bookingDetail.setOrderDate("2019-04-26 18:41:37");
		bookingDetail.setCheckOutDate("2019-04-26 08:00:00");
		bookingDetail.setCheckInDate("2019-04-27 08:00:00");
		bookingDetail.setNumOfRooms(1);
		bookingDetail.setStatus(1003);
		
		
		
		MFWGuestInfo guestInfo = new MFWGuestInfo();
		MFWName name = new MFWName();
		name.setFirst("SHANNI");
		name.setLast("ZHAO");
		guestInfo.setAge(0);
		guestInfo.setAgeSpecified(true);
		guestInfo.setIsAdult(true);
		guestInfo.setName(name);
		List<MFWGuestInfo> listGuestInfo = new ArrayList<MFWGuestInfo>();
		listGuestInfo.add(guestInfo);
		MFWGuest guest = new MFWGuest();
		guest.setRoomNum(1);
		guest.setGuestInfo(listGuestInfo);
		List<MFWGuest> guestList = new ArrayList<MFWGuest>();
		guestList.add(guest);
		bookingDetail.setGuestList(guestList);
		
		
		MFWContact contact = new MFWContact();
		contact.setName(name);
		contact.setEmail("123@qq.com");
		contact.setPhone("15111112222");
		bookingDetail.setContact(contact);
		
		// MFWOrderDetail - hotel
		MFWHotelOderDetail hotel = new MFWHotelOderDetail();
		hotel.setTotalPriceWithoutSupplementSpecified(false);
		hotel.setTotalSupplementSpecified(false);
		hotel.setTotalPriceSpecified(true);
		MFWDestination destination = new MFWDestination();
		destination.setCityCode("180027");
		hotel.setDestination(destination);
		hotel.setTotalPrice(2706);
		hotel.setHotelName("新加坡市中豪亚酒店(Oasia Hotel Downtown Singapore)");
		hotel.setHotelId("220767");
		
		MFWRatePlanRpConfirm ratePlan = new MFWRatePlanRpConfirm();
		ratePlan.setStandardOccupancySpecified(false);
		ratePlan.setPriceWithoutSupplementSpecified(false);
		ratePlan.setSupplementSpecified(false);
		ratePlan.setTotalPrice(2706);
		ratePlan.setInventoryCountSpecified(false);
		ratePlan.setMaxOccupancySpecified(true);
		ratePlan.setRoomStatus(1);
		ratePlan.setBreakfastType(1);
		ratePlan.setBedType(9);
		ratePlan.setMaxOccupancy(2);
		ratePlan.setCurrency("CNY");
		ratePlan.setRoomName("四人房");
		ratePlan.setRoomId("24");
		ratePlan.setRatePlanName("Superior Room 不含早(Superior Room Room Only)");
		ratePlan.setRatePlanId("19:1324:2377:5278");
		MFWPriceItem priceItem = new MFWPriceItem();
		priceItem.setInventoryCount(0);
		priceItem.setInventoryCountSpecified(false);
		priceItem.setPrice(new BigDecimal(918));
		priceItem.setStayDate("2019-04-26 08:00:00");
		List<MFWPriceItem> listPriceItem = new ArrayList<MFWPriceItem>();
		listPriceItem.add(priceItem);
		ratePlan.setPriceList(listPriceItem);
		MFWOccupancyDetail roomOccupancy = new MFWOccupancyDetail();
		roomOccupancy.setAdultCount(2);
		roomOccupancy.setChildCount(0);
		roomOccupancy.setChildCountSpecified(true);
		roomOccupancy.setRoomIndex(1);
		ratePlan.setRoomOccupancy(roomOccupancy);
		
		List<MFWRatePlanRpConfirm> listRatePlan = new ArrayList<MFWRatePlanRpConfirm>();
		listRatePlan.add(ratePlan);
		hotel.setRatePlanList(listRatePlan);
		
		
		MFWCancellationPolicy cancelPolicy = new MFWCancellationPolicy();
		BigDecimal amount = new BigDecimal("2706");
		cancelPolicy.setAmount(amount);
		cancelPolicy.setFromDate("2019-04-20 08:00:00");
		List<MFWCancellationPolicy> listCancelPolicy = new ArrayList<MFWCancellationPolicy>();
		listCancelPolicy.add(cancelPolicy);
		
		hotel.setCancellationPolicyList(listCancelPolicy);
		
		bookingDetail.setHotel(hotel);
		bookingDetail.setClientReference("20170816367483811");
		bookingDetail.setBookingId("DHB170816104137188");
		
		MFWBookingDetailList resultBooking = new MFWBookingDetailList();
		resultBooking.setBookingDetailsList(bookingDetail);
		MFWResult result = new MFWResult();
		result.setData(resultBooking);
		
		return ResponseEntity.ok(result);
	}
	
	/*
	 * @PostMapping(value = "/search") public ResponseEntity<Boolean>
	 * search(HttpServletRequest requestEntity) { log.info("{}",
	 * JsonUtil.toJson(requestEntity)); return ResponseEntity.ok().build(); }
	 */

	@GetMapping(value = "/home")
	public String index(){
		return "<html><h1>chao cac ban </h1></html>";
	}
}
