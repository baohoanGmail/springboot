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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hoan.lam.demo.model.FilterResult;
import com.hoan.lam.demo.model.Product;
import com.hoan.lam.demo.response.ErrorElement;
import com.hoan.lam.demo.response.RestResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductController {

  public static void main(String[] args) {
    long limitDate = 1559494398;
    LocalDate date = Instant.ofEpochMilli(limitDate).atZone(ZoneOffset.UTC).toLocalDate();
    System.out.println(date);
  }

  private String getSearchDate(String... dates) {
    return Stream.of(dates).filter(x -> StringUtils.isNotBlank(x)).findFirst()
        .orElse(StringUtils.EMPTY);
  }

  Predicate<Product> contentTextSearch(String text) {
    return x -> x.getName().contains(text) || x.getProductNo().contains(text)
        || x.getOrderNo().contains(text) || x.getReturnNo().contains(text);
  }

  private boolean compare(String date1, String date2) {
    System.out.println("localDate1: " + date1);
    System.out.println("localDate2: " + date2);

    LocalDate localDate1 =
        Instant.ofEpochMilli(Long.valueOf(date1)).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDate2 =
        Instant.ofEpochMilli(Long.valueOf(date2)).atZone(ZoneId.systemDefault()).toLocalDate();
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

  private float getFilterLimit(String filter) {
    if ("rentaling".equalsIgnoreCase(filter)) {
      return 0.3f;
    }
    if ("not_return_yet".equalsIgnoreCase(filter)) {
      return 0.5f;
    }
    if ("prepare_day".equalsIgnoreCase(filter)) {
      return 0.2f;
    }
    return 1.0f;
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RestResponse> search(
      @RequestParam(required = false, name = "product_no") String productNo,
      @RequestParam(required = false, name = "name") String productName,
      @RequestParam(required = false, name = "order_no") String orderNo,
      @RequestParam(required = false, name = "return_no") String returnNo,
      @RequestParam(required = false, name = "order_name") String orderName,
      @RequestParam(required = false, name = "shipment_date") String shipmentDate,
      @RequestParam(required = false, name = "usage_date") String usageDate,
      @RequestParam(required = false, name = "return_date") String returnDate,
      @RequestParam(required = false, name = "order_date") String orderDate,
      @RequestParam(required = false, name = "filter") String filter,
      @RequestParam(required = true, name = "limit") int limit,
      @RequestParam(required = true, name = "current") int offset) {

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
      // computed for filter result
      int size = results.size();
      FilterResult filterResult = new FilterResult();
      int notReturnYet = (int) Math.round((getFilterLimit("not_return_yet") * size));
      int prepareDay = (int) Math.round((getFilterLimit("prepare_day") * size));
      int rentaling = (int) Math.ceil((getFilterLimit("rentaling") * size));

      filterResult.setNotReturnYet(notReturnYet);
      filterResult.setPrepareDay(prepareDay);
      filterResult.setRentaling(rentaling);
      response.setFilterResult(filterResult);

      if (StringUtils.isNoneBlank(filter)) {
        float filterRatio = getFilterLimit(filter);
        if (filterRatio < 1.f) {
          int filterLimit = (int) Math.ceil(filterRatio * results.size());
          results = results.stream().limit(filterLimit).collect(Collectors.toList());
        }
      }
      response.setTotal(results.size());


      // --------------------------------------------------------------------------------
      if (limit > 0 && offset > 0) {
        response.setCurrent(offset);
        response.setMore((offset * limit) < response.getTotal());
        response.setData(results.stream().skip(((offset - 1) * limit)).limit(limit));
      } else {
        response.setMore(false);
        response.setCurrent(1);
        response.setData(results);
      }
      response.setStatus("success");
      return new ResponseEntity<RestResponse>(response, mockyResponse.getStatusCode());
    }
    return new ResponseEntity<RestResponse>(response, HttpStatus.NOT_FOUND);
  }
}
