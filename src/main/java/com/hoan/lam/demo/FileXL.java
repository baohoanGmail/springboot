package com.hoan.lam.demo;

import java.net.URI;
import java.net.URISyntaxException;

public class FileXL {

	public URI getUri() throws URISyntaxException {

		System.out.println("get Class Loader: " + this.getClass() != null);

		return this.getClass().getClassLoader().getResource("SMC157-OUT_20190822145120_RES.txt").toURI();
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
