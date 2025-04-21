package com.verizon.crm.api.generic;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptAndDecryptUtilityTest {
	public String encrypt(String input, String secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),"AES");
		IvParameterSpec ivparamSpec = new IvParameterSpec("4234567890123456".getBytes());
		Cipher cipher= Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivparamSpec);
		byte[] encrypted = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
		
	}
	public String decrypt(String input, String secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),"AES");
		IvParameterSpec ivparamSpec = new IvParameterSpec("4234567890123456".getBytes());
		Cipher cipher= Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivparamSpec);
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(input));
		return new String(decrypted);
		
	}


}
