package com.hoan.lam.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {

  public class FileXL {

    public URI getUri() throws URISyntaxException {
      return FileXL.class.getResource("/SMC157-OUT_20190822145120_RES.txt").toURI();
    }
  }

  @PostMapping("/download")
  public ResponseEntity<InputStreamResource> downloadFile1()
      throws IOException, URISyntaxException {

    File file = new File(new FileXL().getUri());

    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

    return ResponseEntity.ok()
        // Content-Disposition
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
        // Content-Type
        .contentType(MediaType.TEXT_PLAIN)
        // Contet-Length
        .contentLength(file.length()) //
        .body(resource);
  }

}
