package cn.usermanage.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class AES {  
	
	public static String entrypt(String content,String password){
		
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes("utf-8"),"AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE,key);
			byte[] result = cipher.doFinal(byteContent);
			return byte2hex(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	  
	public static String decrypt(String content,String password){
		
		try {
//			 KeyGenerator kgen = KeyGenerator.getInstance("AES");    
//			 //防止linux下 随机生成key   
//			 SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );      
//			 secureRandom.setSeed(password.getBytes());      
//			 kgen.init(128, secureRandom);   
//			 //kgen.init(128, new SecureRandom(password.getBytes()));      
//			 SecretKey secretKey = kgen.generateKey();      
//			 byte[] enCodeFormat = secretKey.getEncoded();      
			SecretKeySpec key = new SecretKeySpec(password.getBytes("utf-8"),"AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE,key);
			byte[] result = cipher.doFinal(content.getBytes("utf-8"));
			
			return new String(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String byte2hex(byte[] b){
			String hs = "";
			String tmp = "";
			for (int i = 0; i < b.length; i++) {
				tmp = java.lang.Integer.toHexString(b[i] & 0xFF);
				if(tmp.length() == 1){
					hs = hs + "0" +tmp;
				}else{
					hs = hs + tmp;
				}
			}
			tmp = null;
			return hs.toUpperCase();
	}
	public static void main(String[] args) {
		String content = "just fuck it";
		String password = "1234567890abcdef";
		String res = AES.entrypt(content, password);
		System.out.println("加密结果："+res);
//		String dekey = "1234567890abcdef";
		String dec = AES.decrypt("C009E01CE31669FC2F750ACDE9300361", password);
		System.out.println(dec);
	}
}  
