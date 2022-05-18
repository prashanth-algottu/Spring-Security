package com.tec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Demo {
	public static void main(String[] args) {
		BCryptPasswordEncoder en = new BCryptPasswordEncoder();
		String string = en.encode("prashanth");
		System.out.println(string);
	}

}
// $2a$10$f7DPhcNMT7tcBoOdyYCYseQh7BtWmFevZij1rBWCNYqq2V5w85Qlq
// $2a$10$jrR7OQE9/BJOk.QBqOO3.OkUhn2RAhiISs.u6Yx0uWz0t5RNJ64AG
