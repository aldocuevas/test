package com.boutique.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptedProperties extends Properties {
	 
 	private static final long serialVersionUID = -5969052363426756707L;
	private static sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	private static sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	private Cipher encrypter, decrypter;
	private static final String PASSWORD= "quebecoise";
	private static byte[] salt = { (byte) 0x04,(byte) 0x05,(byte) 0x09,(byte) 0x05,(byte) 0x02,(byte) 0x03,(byte) 0x08,(byte) 0x11};  // make up your own

	public EncryptedProperties()   {
		PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, 20);
		SecretKeyFactory kf;
		try {
			kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(PASSWORD.toCharArray()));
			encrypter = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
			decrypter = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
			encrypter.init(Cipher.ENCRYPT_MODE, k, ps);
			decrypter.init(Cipher.DECRYPT_MODE, k, ps);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getProperty(String key) {
		try {
			return decrypt(super.getProperty(key));
		} catch( Exception e ) {
			throw new RuntimeException("Couldn't decrypt property");
		}
	}

	public synchronized Object setProperty(String key, String value) {
		try {
			return super.setProperty(key, encrypt(value));
		} catch( Exception e ) {
			e.printStackTrace();

			throw new RuntimeException("Couldn't encrypt property");
		}
	}

	private synchronized String decrypt(String str) throws Exception {
		byte[] dec = decoder.decodeBuffer(str);
		byte[] utf8 = decrypter.doFinal(dec);
		return new String(utf8, "UTF-8");
	}

	private synchronized String encrypt(String str) throws Exception {
		byte[] utf8 = str.getBytes("UTF-8");
		byte[] enc = encrypter.doFinal(utf8);
		return encoder.encode(enc);
	}
}