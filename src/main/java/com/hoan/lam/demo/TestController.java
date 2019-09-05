package com.hoan.lam.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestController {

  @PostMapping(value = "test/download")
  public ResponseEntity<InputStreamResource> downloadFile(
      @RequestParam(name = "filename", required = true) Optional<String> filename,
      @RequestParam(name = "contentType", required = false) Optional<String> type)
      throws IOException {

    if (!filename.isPresent()) {
      return ResponseEntity.badRequest().build();
    }

    File file = new File("src/main/resources/" + filename.get());

    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    BodyBuilder bodyBuilder = ResponseEntity.ok()
        // Content-Disposition
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

    // Content-Type
    if (type.isPresent()) {
      bodyBuilder.contentType(MediaType.valueOf(type.get()));
    } else {
      bodyBuilder.contentType(MediaType.TEXT_PLAIN);
    }
    // Contet-Length
    return bodyBuilder.contentLength(file.length()).body(resource);
  }

}

