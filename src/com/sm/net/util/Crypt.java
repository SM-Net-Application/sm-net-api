package com.sm.net.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by Molaro, S. on 25.10.2017.
 * 
 * Dependency: commons-codec-1.11
 * 
 */
public class Crypt {

	/**
	 * Generate SecretKey
	 * 
	 * @param password
	 * @return
	 */
	public static SecretKey generateKey(String password) {

		try {

			DESKeySpec keySpec = new DESKeySpec(password.getBytes(StandardCharsets.UTF_8));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			return keyFactory.generateSecret(keySpec);

		} catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException e) {

			System.out.println(e.getMessage());

		}
		return null;
	}

	/**
	 * Encrypt text
	 * 
	 * @param text
	 * @param key
	 * @return
	 */
	public static String encrypt(String text, SecretKey key) {

		Base64 base64 = new Base64();

		try {

			byte[] clearText = text.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return new String(base64.encode(cipher.doFinal(clearText)), StandardCharsets.UTF_8);

		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException
				| IllegalBlockSizeException e) {

			System.out.println(e.getMessage());

		}
		return null;
	}

	/**
	 * Decrypt text
	 * 
	 * @param text
	 * @param secret
	 * @return
	 */
	public static String decrypt(String text, SecretKey secret) {

		try {

			Base64 base64 = new Base64();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, secret);
			return new String((cipher.doFinal(base64.decode(text.getBytes()))), StandardCharsets.UTF_8);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
				| InvalidKeyException e) {

			System.out.println(e.getMessage());

		}

		return null;

	}
}