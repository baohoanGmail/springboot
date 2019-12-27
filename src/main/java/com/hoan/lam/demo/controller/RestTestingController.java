package com.hoan.lam.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoan.lam.demo.util.JsonUtil;
import com.mafenwo.services.orders.MFWOrderInquiry;
import com.mafenwo.services.orders.MFWResponse;
import com.mafenwo.services.orders.MFWSysStatusEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestTestingController {
	
	@PostMapping(value = "search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<MFWResponse> search(@RequestBody MultiValueMap<String, String> multipleValueMap) throws IOException {
		String json = JsonUtil.getValueOfFirst(multipleValueMap);
		MFWOrderInquiry inquiry = JsonUtil.str2bean(json, MFWOrderInquiry.class); 
		log.info("JsonNormalization: {}", JsonUtil.toJson(inquiry));
		MFWResponse resp = new MFWResponse(MFWSysStatusEnum.MFW0);
		resp.setErrorCode(1007);
		resp.setErrorMessage("order not exists");
		resp.setData(JsonUtil.emptyObject());
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping(value = "search")
	public ResponseEntity<Boolean> search(HttpServletRequest requestEntity) {
		log.info("{}", JsonUtil.toJson(requestEntity));
		return ResponseEntity.ok().build();
	}

	
}
