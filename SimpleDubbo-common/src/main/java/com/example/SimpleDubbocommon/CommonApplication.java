package com.example.SimpleDubbocommon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(CommonApplication.class);
		logger.info("hello info");
		logger.error("hello error");
		logger.warn("hello warn");
		logger.debug("hello debug");
		logger.trace("hello trace");
	}

}
