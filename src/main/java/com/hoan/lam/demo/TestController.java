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
import com.hoan.lam.demo.FileXL;

@Controller
public class TestController {

	@PostMapping("/download")
	public ResponseEntity<InputStreamResource> downloadFile1() throws IOException, URISyntaxException {

		File file = new File(new FileXL().getUri());
		System.out.println(file.getPath());
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
