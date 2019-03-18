package com.hoan.lam.demo;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ExampleController {

  private static final ObjectMapper jsonMapper = new ObjectMapper();
  static {
    jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    jsonMapper.setSerializationInclusion(Include.NON_NULL);
  }
  //
  // private static <T> List<T> json2Object(Object values) {
  // try {
  // String jsonString = object2Json(values);
  // return jsonMapper.readValue(jsonString, new TypeReference<List<T>>() {});
  // } catch (IOException e) {
  // log.error(e.getMessage());
  // return Collections.emptyList();
  // }
  // }

  private static final String object2Json(Object values) {
    try {
      return jsonMapper.writeValueAsString(values);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      return e.getMessage();
    }
  }

  @RequestMapping("/")
  public String index() {
    log.info("get method is calling... {}");
    return "Greetings from Spring Boot!";
  }

  @PostMapping("/load")
  public String load(@RequestBody List<Bounce> requestBody) {
    if (requestBody != null) {
      String json = object2Json(requestBody);
      log.info("post method is loading... {}", json);
      return json;
    }
    log.info("post method is loading...");
    return "Greetings from Spring Boot!";
  }

}
