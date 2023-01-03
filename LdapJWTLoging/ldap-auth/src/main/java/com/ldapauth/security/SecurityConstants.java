package com.ldapauth.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_SECRET = "8OFghUKHRGEdhIDKFHSYnDlWf/434=";
	public static String jwtSecret = "ldapJwtSecret";
	
	/** The Constant ALGO. */
	private static final String ALGO = "AES";
	/** The Constant keyValue. */
	private static final byte[] KEY_VALUE = "secreatkey".getBytes();

	//	public static void main(String[] args) {
	//		System.out.println("decrypted token_secret = " +decrypt(TOKEN_SECRET) );
	//	}

	/**
	 * Decrypt.
	 *
	 * @param encryptedData
	 *            the encrypted data
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String decrypt(final String encryptedData) {
		Key key;
		try {
			key = generateKey();
			final Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			final byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
			final byte[] decValue = c.doFinal(decordedValue);			
			return new String(decValue);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generate key.
	 *
	 * @return the key
	 * @throws Exception
	 *             the exception
	 */
	private static Key generateKey() throws Exception {		
		return new SecretKeySpec(KEY_VALUE, ALGO);
	}

	public String encodeValue(String value) {
		Argon2 argon2 = Argon2Factory.create();
		String hash;
		char[] password = value.toCharArray();
		try {			
			hash = argon2.hash(22, 65536, 1, password);
		} finally {
			argon2.wipeArray(password);
		}
		return hash;
	}
	
	public static String hashPassword(final String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				hexString.append(hex);
			}

			return hexString.toString().toUpperCase();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
