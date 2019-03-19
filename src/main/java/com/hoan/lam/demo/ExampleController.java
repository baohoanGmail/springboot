package com.hoan.lam.demo;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

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

  private int deleteMails(Set<String> emailDeleteds) {
    if (emailDeleteds.isEmpty()) {
      return -1;
    }
    SendGrid sg =
        new SendGrid("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");
    DeleteMailRequest deleteMailsRequest = new DeleteMailRequest();
    deleteMailsRequest.setEmails(emailDeleteds);
    String requestBody = object2Json(deleteMailsRequest);
    log.info("REQUEST BODY {}", requestBody);
    Response response = null;
    try {
      Request request = new Request();
      request.setMethod(Method.DELETE);
      request.setEndpoint("/suppression/bounces");
      request.setBody(requestBody);
      response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      log.error("Request delete mails failed {}", ex.getMessage());
    }
    return (response != null) ? response.getStatusCode() : -1;
  }

  @PostMapping("/load")
  public String load(@RequestBody List<Bounce> requestBody) {
    if (requestBody != null) {
      String json = object2Json(requestBody);
      deleteMails(requestBody.stream().map(x -> x.getEmail()).collect(Collectors.toSet()));
      log.info("post method is loading... {}", json);
      return json;
    }
    log.info("post method is loading...");
    return "Greetings from Spring Boot!";
  }

}
