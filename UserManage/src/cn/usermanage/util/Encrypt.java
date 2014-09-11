package cn.usermanage.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Encrypt {
	static final int WELNAVI_KEY_LEN = 16;

	public static byte[] package_encrypt(String content, int len, byte[] key) throws UnsupportedEncodingException {

		byte[] sec = new byte[WELNAVI_KEY_LEN];
		byte temp = ' ';
		byte[] keyChar = key;
		;
		for (int i = 0; i < sec.length; i++) {
			try {
				sec[i] = keyChar[i];
			} catch (RuntimeException e) {
				e.printStackTrace();
				System.out.println("检查Key长度");
			}
		}
		byte[] contentChar = content.getBytes("utf-8");
		for (int i = 0; i < len; i++) {
			temp = contentChar[i];
			contentChar[i] = (byte) ((sec[0] + sec[1] + sec[7] + sec[WELNAVI_KEY_LEN - 1]) ^ temp);
			for (int j = 0; j < WELNAVI_KEY_LEN - 1; j++) {
				sec[j] = sec[j + 1];
			}
//			System.out.println(i + ":" + contentChar[i]);
			sec[WELNAVI_KEY_LEN - 1] ^= contentChar[i];
		}
		// return byte2hex(contentChar);
		return contentChar;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String tmp = "";
		for (int i = 0; i < b.length; i++) {
			tmp = java.lang.Integer.toHexString(b[i] & 0xFF);
			if (tmp.length() == 1) {
				hs = hs + "0" + tmp;
			} else {
				hs = hs + tmp;
			}
		}
		tmp = null;
		return hs.toUpperCase();
	}
	
	public static String decrypt(byte[] content,int len,byte[] key) throws Exception{
		 byte[] sec = new byte[WELNAVI_KEY_LEN];
		    byte tmp;
		    for (int i = 0; i < sec.length; i++) {
				try {
					sec[i] = key[i];
				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println("检查Key长度");
				}
			}
		    for (int cnt=0;cnt<len;cnt++)
		    {
		        tmp = content[cnt];
		        content[cnt] = (byte)((sec[0]+sec[1]+sec[7]+sec[WELNAVI_KEY_LEN-1])^tmp);
		        for(int j = 0;j<WELNAVI_KEY_LEN-1;j++)
		        {
		            sec[j]=sec[j+1];
		        }
		        sec[WELNAVI_KEY_LEN-1] ^= tmp;
		    }
//		    System.out.println("---"+content.toString());
		return new String(content,"utf-8");
	}
	
	public static void main(String[] args) throws Exception {
		String hid = "hgfdsa0987654321";
		String key = "caishujiecaishuj";
		String sid = "74cf6c39220bd2f2";

		String TmpKey1 = hid;
		byte[] TmpKey2 = package_encrypt(TmpKey1, 16, key.getBytes());
		System.out.println("TmpKey2len:" + TmpKey2.length + "TmpKey2:"
				+ TmpKey2);
		String a = "";
//		for (int i = 0; i < TmpKey2.length; i++) {
//			a = a + (char) TmpKey2[i];
//		}
		System.out.println(a);
		String SessionKey = sid;
		byte[] SessionKey2 = package_encrypt(SessionKey, 16, TmpKey2);
		System.out.println("SessionKeylen:" + SessionKey2.length
				+ "SessionKey:" + SessionKey2);

		String temp = "";
		File file = new File("C:\\notvalid.xml");
		if (file.exists()) {
			FileInputStream fi;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte buffer[] = new byte[1024];
			int len = 0;
			try {
				fi = new FileInputStream(file);
				while ((len = fi.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				temp = new String(bos.toByteArray(), "utf-8");
				System.out.println(temp);
				fi.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] mi = package_encrypt(temp, temp.getBytes("utf-8").length, SessionKey2);
		System.out
				.println("加密前：" + temp.getBytes("utf-8").length + "加密后：" + mi.length);
		String s = decrypt(mi, mi.length, SessionKey2);
		System.out.println("解密结果："+s);
//		try {
//			FileOutputStream fos = new FileOutputStream("c:/miwen.xml");
//			fos.write(mi);
//			fos.flush();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		File file2 = new File("C:\\notvalid_encrypt.xml");
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		if (file2.exists()) {
//			FileInputStream fi;
//			byte buffer[] = new byte[1024];
//			int len = 0;
//			try {
//				fi = new FileInputStream(file2);
//				while ((len = fi.read(buffer)) != -1) {
//					bos.write(buffer, 0, len);
//				}
//				temp = new String(bos.toByteArray(), "utf-8");
//				fi.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		String s2 = decrypt(bos.toByteArray(), bos.toByteArray().length, SessionKey2);
//		System.out.println("s2===="+s2);
	}
	

}
