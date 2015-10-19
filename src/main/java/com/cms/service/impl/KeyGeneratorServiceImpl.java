package com.cms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class KeyGeneratorServiceImpl implements KeyGenerator {
	
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Object generate(Object target, Method method, Object... params) {
		String key = method.getName().toLowerCase() + "_" + StringUtils.join(params, "_");
		logger.debug("KEY："+key);
		return key;
	}

	public static void main(String[] args) {

	}
}