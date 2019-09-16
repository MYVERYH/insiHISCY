package com.web.util.ASECode;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.web.common.Base64;

public class AESUtils {

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";// 默认的加密算法

	/**
	 * AES 加密操作
	 * 
	 * @param content
	 *            待加密内容
	 * @param password
	 *            加密密码
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content, String password) {
		try {
			SecretKeySpec skeySpec = getKey(password);
			byte[] clearText = content.getBytes("UTF8");
			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			// below code must be added in java end
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			// Cipher is not thread safe
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

			String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
			return encrypedValue;
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(String content, String password) {
		try {
			SecretKey key = getKey(password);
			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			byte[] encrypedPwdBytes = Base64.decode(content, Base64.DEFAULT);
			// below code must be added in java end
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			// cipher is not thread safe
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

			String decrypedValue = new String(decrypedValueBytes, "UTF-8");
			return decrypedValue;
		} catch (Exception ex) {
			System.err.println("Decrypt exception: " + ex.getMessage());
		}
		return null;
	}

	private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
		// You can change it to 128 if you wish
		int keyLength = 256;
		byte[] keyBytes = new byte[keyLength / 8];
		// explicitly fill with zeros
		Arrays.fill(keyBytes, (byte) 0x0);

		// if password is shorter then key length, it will be zero-padded
		// to key length
		byte[] passwordBytes = password.getBytes("UTF-8");
		int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
		System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		return key;
	}

	public static void main(String[] args) {
		String key = ")O[NB]6,YF}+efcaj{+oESb9d8>Z'e9M";
		String str = "111";
		// String Value = encrypt(str,key);
		// System.out.println(Value);

//		try {
//			System.out.println("statement1");
//			int i = 0;
//			int y = 2 / i;
//			System.out.println("statement2");
//		} finally {
//			System.out.println("statement3");
//		}
//		System.out.println("statement4");
		
		

	}
}
