package com.clone.melonmarket;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MelonmarketApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void jasypt() {
		String url = "imageputbucket";


		System.out.println("안뇽");
		System.out.println(jasyptEncoding(url));
	}

	public String jasyptEncoding(String value) {

		String key = "my_jasypt_key";
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		pbeEnc.setPassword(key);
		return pbeEnc.encrypt(value);
	}


}
