package com.example.userservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Slf4j
@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void hash() {
		String password = "adminAbc123!@#";

		// Sử dụng Argon2 để mã hóa mật khẩu
		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 65536, 10);
		String encodedPassword = encoder.encode(password);

		log.info("Encoded Password (Argon2): {}", encodedPassword);

		// Kiểm tra mật khẩu có khớp không
		boolean isMatch = encoder.matches(password, encodedPassword);
		log.info("Password Match: {}", isMatch);
	}
}


