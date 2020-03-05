package com.alisls.demo.springcloud.oauth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordUtil {

	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	private static String encode(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
	
	public static void main(String[] args) {
		// admin 加密后的值：$2a$10$sMIXqFI6LZGfJ/hDcKYwH.iX6lsYVBVbz1j.ImDISt2nEFcWguaVm
		String srcPassword = "admin";
		String enPassword = encode(srcPassword);
		System.out.println(enPassword);
	}
	
}
