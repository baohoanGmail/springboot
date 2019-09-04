package com.hoan.lam.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileXL {

	public URI getUri() throws URISyntaxException {

		log.info("get Class Loader: {}", this.getClass() != null);
		URL url = getClass().getClassLoader().getResource("SMC157-OUT_20190822145120_RES.txt");

		log.info("URL is empty {}", url);

		return url.toURI();
	}

//	 // get file from classpath, resources folder
//    private File getFileFromResources(String fileName) {
//
//        ClassLoader classLoader = getClass().getClassLoader();
//
//        URL resource = classLoader.getResource(fileName);
//        if (resource == null) {
//            throw new IllegalArgumentException("file is not found!");
//        } else {
//            return new File(resource.getFile());
//        }
//
//    }
}
