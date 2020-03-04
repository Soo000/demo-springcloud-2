package com.alisls.demo.springcloud.oauth.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

public class JavaYhWebException implements WebResponseExceptionTranslator {

	@Override
	public ResponseEntity translate(Exception e) throws Exception {
		return ResponseEntity.ok("发生错误");
	}


	
}
