package com.hoan.lam.demo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoan.lam.demo.model.Product;
import com.hoan.lam.demo.response.ErrorElement;
import com.hoan.lam.demo.response.RestResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
public class ProductController {

	public static void main(String[] args) {
		long limitDate = 1559494398;
		LocalDate date = Instant.ofEpochMilli(limitDate).atZone(ZoneOffset.UTC).toLocalDate();
		System.out.println(date);
	}

	private String getSearchDate(String... dates) {
		return Stream.of(dates).filter(x -> StringUtils.isNotBlank(x)).findFirst().orElse(StringUtils.EMPTY);
	}

	Predicate<Product> contentTextSearch(String text) {
		return x -> x.getProductName().contains(text) || x.getProductNo().contains(text)
				|| x.getOrderNo().contains(text) || x.getReturnNo().contains(text);
	}

	private boolean compare(String date1, String date2) {
		System.out.println("localDate1: " + date1);
		System.out.println("localDate2: " + date2);

		LocalDate localDate1 = Instant.ofEpochMilli(Long.valueOf(date1)).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate2 = Instant.ofEpochMilli(Long.valueOf(date2)).atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate1.equals(localDate2);

	}

	Predicate<Product> isInLimitDate(String date) {
		return x -> compare(date, x.getArriveDate()) || compare(date, x.getShipmentDate())
				|| compare(date, x.getUsageDate());
	}

	private List<Product> searchWithCriterials(List<Product> products, String text, String date) {
		List<Product> temps = products;
		if (StringUtils.isNotBlank(date)) {
			temps = products.stream().filter(isInLimitDate(date)).collect(Collectors.toList());
		}
		if (StringUtils.isNotBlank(text)) {
			temps = temps.stream().filter(contentTextSearch(text)).collect(Collectors.toList());
		}
		return temps;
	}

	@RequestMapping(value = "/products")
	public ResponseEntity<RestResponse> search(@RequestParam(required = false, name = "productNo") String productNo,
			@RequestParam(required = false, name = "productName") String productName,
			@RequestParam(required = false, name = "orderNo") String orderNo,
			@RequestParam(required = false, name = "returnNo") String returnNo,
			@RequestParam(required = false, name = "orderName") String orderName,
			@RequestParam(required = false, name = "shipmentDate") String shipmentDate,
			@RequestParam(required = false, name = "usageDate") String usageDate,
			@RequestParam(required = false, name = "returnDate") String returnDate,
			@RequestParam(required = false, name = "orderDate") String orderDate,
			@RequestParam(required = true, name = "limit") int limit,
			@RequestParam(required = true, name = "offset") int offset) {

		String dateSearch = getSearchDate(shipmentDate, usageDate, returnDate, orderDate);
		String textSearch = getSearchDate(productNo, productName, orderNo, returnNo);

		log.info("Searching with criterials: {} - {}", textSearch, dateSearch);

		String url = RestHelper.getEndpoint("products");
		ResponseEntity<?> mockyResponse = RestHelper.get(url);
		RestResponse response = new RestResponse();
		if (mockyResponse.getStatusCode().isError()) {
			List<ErrorElement> errors = new ArrayList<>();
			ErrorElement element = new ErrorElement();
			element.setTimestamp(LocalDate.now().toString());
			element.setMessage("Something went wrong!!!");
			errors.add(element);
			response.setStatus("failure");
			return new ResponseEntity<RestResponse>(response, mockyResponse.getStatusCode());
		}
		String data = mockyResponse.getBody().toString();
		List<Product> products = JsonHelper.string2objects(data, Product.class, "Product");
		if (CollectionUtils.isNotEmpty(products)) {
			List<Product> results = searchWithCriterials(products, textSearch, dateSearch);
			response.setTotal(results.size());
			if (limit > 0) {
				response.setOffset(offset);
				int hasMore = (offset > 0) ? (offset * limit) : limit;
				response.setMore(hasMore < response.getTotal());
				response.setData(results.stream().skip((offset * limit)).limit(limit));
			} else {
				response.setMore(false);
				response.setData(results);
			}
			response.setStatus("success");
			return new ResponseEntity<RestResponse>(response, mockyResponse.getStatusCode());
		}
		return new ResponseEntity<RestResponse>(response, HttpStatus.NOT_FOUND);
	}
}
